package game.puzzle.ia.t1.ufscar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

			if(args.length < 2 || args.length > 3){
				String msg = "Modo de uso: <tipoDeAgente> [<limite>] <arquivoDeEntrada>\n";
				msg += "Caso o tipo de agente seja BPL, então você precisa passar o limite da busca como segundo parâmetro.";
				throw new Exception(msg);
			}
			
			System.out.println("**** Puzzle Resolver ****");

			Game game = new Game();

			String agentType = null;
			String initialConfig = null;
			int problemSize = 0;
			int maxLimit = 1;
			
			try{
				
				BufferedReader br = null;
				
				if(args.length == 3){
					
					maxLimit = Integer.valueOf(args[1]);
					br = new BufferedReader(new FileReader(new File(args[2])));
					
				}else{
					br = new BufferedReader(new FileReader(new File(args[1])));
				}
				
				agentType = args[0];
				problemSize = Integer.valueOf(br.readLine());
				initialConfig = br.readLine();
				
				br.close();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
					
			// define regras para o domimio especifico (o jogo da regua)
			DomainRules puzzleRule = new PuzzleRules(initialConfig, problemSize);

			// obtem o agente desejado (BL, BP, ...)
			Agent agent = game.buildAgent(agentType, maxLimit, problemSize, puzzleRule);

			// executa o agente
			List<SearchNode> solutionPath = agent.resolve();
			
			// exibe os dados
			game.mostraDados(agent, problemSize);
						
			// mostra a sequencia de acoes
			game.tellSolution(solutionPath);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Agent buildAgent(String agentType, int maxLimit, int problemSize, DomainRules puzzleRule) throws Exception {

		// nosso agente
		Agent agent = null;

		// criar o agent escolhido
		if(agentType.equals("BL")){
			agent = new BFSAgent(puzzleRule);
		}else if(agentType.equals("BP")){
			agent = new DFSAgent(puzzleRule);
		}else if(agentType.equals("BCU")){
			agent = new UCFSAgent(puzzleRule);
		}else if(agentType.contains("BPL")){
			agent = new LDFSAgent(puzzleRule, maxLimit);
		}else if(agentType.equals("BPI")){
			agent = new IDFSAgent(puzzleRule);
		}else if(agentType.equals("A*(h1)")){
			agent = new AStarAgent(puzzleRule, new HeuristicOne(problemSize));
		}else if(agentType.equals("A*(h2)")){;
		agent = new AStarAgent(puzzleRule, new HeuristicTwo(problemSize));
		}else if(agentType.equals("GBFS(h1)")){

			// passando true para a heuristica,
			// faz com que a fila de prioridade seja um heap de maximo,
			// onde valores maiores de h(n) tem maior prioridade
			agent = new GBFSAgent(puzzleRule, new HeuristicOne(problemSize));
		}
		// executa o agente de busca guiada com a heuristica 2
		else if(agentType.equals("GBFS(h2)")){

			// a omissao do parametro booleano ou indicar um valor falso para a heuristica,
			// faz com que a fila de prioridade seja um heap de minimo,
			// onde valores menores de h(n) tem maior prioridade
			agent = new GBFSAgent(puzzleRule, new HeuristicTwo(problemSize));
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

		if(solutionPath == null){
			return;
		}
		
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
	
	public void mostraDados(Agent agent, int problemSize){

		// mostra os dados
		System.out.println();
		System.out.println("Tamanho do problema: " + problemSize);
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
