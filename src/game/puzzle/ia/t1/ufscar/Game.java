package game.puzzle.ia.t1.ufscar;

import java.io.IOException;
import java.util.List;

import general.search.agent.ia.Agent;
import general.search.agent.ia.DomainRules;
import general.search.agent.ia.SearchNode;
import seach.agent.ia.AStarAgent;
import seach.agent.ia.BFSAgent;
import seach.agent.ia.DFSAgent;
import seach.agent.ia.GBFSAgent;
import seach.agent.ia.IDFSAgent;
import seach.agent.ia.LDFSAgent;
import seach.agent.ia.UCFSAgent;

public class Game {


	public Game(){

	}

	public static void main(String[] args) {

		// resolve e mostra a solucao
		try {

			System.out.println("**** Puzzle Resolver ****");

			Game game = new Game();

			// le os dados de entrada
			GameInput gameInput = game.readInput();

			// define regras para o domimio especifico (o jogo da regua)
			DomainRules puzzleRule = new PuzzleRules(gameInput.getInitialConfig(), gameInput.getProblemSize());

			// obtem o agente desejado (BL, BP, ...)
			Agent agent = game.getAgent(gameInput, puzzleRule);

			// executa o agente
			List<SearchNode> solutionPath = agent.resolve();
			
			// exibe os dados
			game.mostraDados(agent, gameInput);
						
			// mostra a sequencia de acoes
			game.tellSolution(solutionPath);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Agent getAgent(GameInput gameInput, DomainRules puzzleRule) throws Exception {

		if(gameInput == null)
			throw new Exception("Voce precisa ler os dados de entrada antes de resolver o problema");

		// nosso agente
		Agent agent = null;

		// criar o agent escolhido
		if(gameInput.getAgentType().equals("BL")){
			agent = new BFSAgent(puzzleRule);
		}else if(gameInput.getAgentType().equals("BP")){
			agent = new DFSAgent(puzzleRule);
		}else if(gameInput.getAgentType().equals("BCU")){
			agent = new UCFSAgent(puzzleRule);
		}else if(gameInput.getAgentType().equals("BPL")){
			agent = new LDFSAgent(puzzleRule, gameInput.getMaxLimit());
		}else if(gameInput.getAgentType().equals("BPI")){
			agent = new IDFSAgent(puzzleRule, gameInput.getMaxLimit());
		}else if(gameInput.getAgentType().equals("A*(h1)")){
			agent = new AStarAgent(puzzleRule, new HeuristicOne(gameInput.getProblemSize()));
		}else if(gameInput.getAgentType().equals("A*(h2)")){;
		agent = new AStarAgent(puzzleRule, new HeuristicTwo(gameInput.getProblemSize()));
		}else if(gameInput.getAgentType().equals("GBFS(h1)")){

			// passando true para a heuristica,
			// faz com que a fila de prioridade seja um heap de maximo,
			// onde valores maiores de h(n) tem maior prioridade
			agent = new GBFSAgent(puzzleRule, new HeuristicOne(gameInput.getProblemSize()));
		}
		// executa o agente de busca guiada com a heuristica 2
		else if(gameInput.getAgentType().equals("GBFS(h2)")){

			// a omissao do parametro booleano ou indicar um valor falso para a heuristica,
			// faz com que a fila de prioridade seja um heap de minimo,
			// onde valores menores de h(n) tem maior prioridade
			agent = new GBFSAgent(puzzleRule, new HeuristicTwo(gameInput.getProblemSize()));
		}

		if(agent == null)
			throw new Exception("Tipo de agente inválido");

		return agent;
	}

	public GameInput readInput(){

		try {
			GameInput gameInput = new GameInput();
			gameInput.read();

			return gameInput;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	// informa as acoes para alcancar o no meta
	public void tellSolution(List<SearchNode> solutionPath) {

		for(SearchNode node : solutionPath){

			if(node.getAction() == null){
				System.out.print("Estado Inicial: ");
				System.out.println(node.getState().toString());
			}else{
				node.getAction().showMovement();
				System.out.println(": " + node.toString());
			}
		}

	}
	
	public void mostraDados(Agent agent, GameInput gameInput){

		// mostra os dados
		System.out.println();
		System.out.println("Tamanho do problema: " + gameInput.getProblemSize());
		System.out.println("Estados Gerados: " + agent.getNumberOfGeneratedNodes());
		System.out.println("Estados Explorados: " + agent.getNumberOfExploredNodes());
		System.out.println("Fator de ramificacao medio: " + agent.getAverageBranchingFactor());
		System.out.println("Custo da Solucao: " + agent.getSolutionCoast());
		System.out.println("Profundidade da Solucao: " + agent.getDepthOfSolution());

		if(agent.getGoalNode() == null){
			System.out.println();
			System.out.println("Nao encontrou solucao");
		}else{

			System.out.print("Solucao encontrada: ");

			// converte um no para um estado do jogo
			System.out.print(agent.getGoalNode().getState().toString());

			System.out.println();
			System.out.println();
		}
	}
}
