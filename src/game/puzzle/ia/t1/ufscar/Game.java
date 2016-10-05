package game.puzzle.ia.t1.ufscar;

import java.io.IOException;
import java.util.List;

import agent.puzzle.ia.t1.ufscar.AStarAgent;
import agent.puzzle.ia.t1.ufscar.BFSAgent;
import agent.puzzle.ia.t1.ufscar.DFSAgent;
import agent.puzzle.ia.t1.ufscar.GBFSAgent;
import agent.puzzle.ia.t1.ufscar.IDFSAgent;
import agent.puzzle.ia.t1.ufscar.LDFSAgent;
import agent.puzzle.ia.t1.ufscar.UCFSAgent;
import general.agent.puzzle.ia.t1.ufscar.Agent;
import heuristic.puzzle.ia.t1.ufscar.HeuristicOne;
import heuristic.puzzle.ia.t1.ufscar.HeuristicTwo;

public class Game {


	public Game(){

	}

	public static void main(String[] args) {

		// resolve e mostra a solucao
		try {

			Game game = new Game();

			// le os dados de entrada
			GameInput gameInput = game.readInput();

			game.resolve(gameInput);

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public GameInput readInput(){

		GameInput gameInput = new GameInput();
		try {
			gameInput.Read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gameInput;
	}

	public void resolve(GameInput gameInput) throws Exception{

		if(gameInput == null)
			throw new Exception("Voce precisa ler os dados de entrada antes de resolver o problema");

		// estado inicial;
		SearchNode initialNode = getInitialState(gameInput.getInitialConfig(), gameInput.getProblemSize());

		// nosso agente
		Agent agent = null;

		// criar o agent escolhido
		if(gameInput.getAgentType().equals("BL")){

			System.out.println("*** Busca em largura ***");
			agent = new BFSAgent(initialNode, gameInput.getProblemSize());

		}else if(gameInput.getAgentType().equals("BP")){

			System.out.println("*** Busca em profundidade ***");
			agent = new DFSAgent(initialNode, gameInput.getProblemSize());

		}else if(gameInput.getAgentType().equals("BCU")){

			System.out.println("*** Busca de custo uniforme ***");
			agent = new UCFSAgent(initialNode, gameInput.getProblemSize());

		}else if(gameInput.getAgentType().equals("BPL")){

			System.out.println("*** Busca em profundidade limitada ***");
			agent = new LDFSAgent(initialNode, gameInput.getProblemSize(), gameInput.getMaxLimit());

		}else if(gameInput.getAgentType().equals("BPI")){

			System.out.println("*** Busca em profundidade iterativa ***");
			agent = new IDFSAgent(initialNode, gameInput.getProblemSize(), gameInput.getMaxLimit());

		}else if(gameInput.getAgentType().equals("H1A*")){

			System.out.println("*** Busca A* usando H1 ***");
			agent = new AStarAgent(initialNode, gameInput.getProblemSize(),
					new HeuristicOne(gameInput.getProblemSize()));

		}else if(gameInput.getAgentType().equals("H2A*")){

			System.out.println("*** Busca A* usando H2 ***");
			agent = new AStarAgent(initialNode, gameInput.getProblemSize(),
					new HeuristicTwo(gameInput.getProblemSize()));
			
		// executa o agente de busca guiada com a heuristica 1
		}else if(gameInput.getAgentType().equals("GBFS1")){

			System.out.println("*** Busca de melhor escolha usando H1 ***");

			// passando true para a heuristica,
			// faz com que a fila de prioridade seja um heap de maximo,
			// onde valores maiores de h(n) tem maior prioridade
			agent = new GBFSAgent(initialNode, gameInput.getProblemSize(),
					new HeuristicOne(gameInput.getProblemSize()));
		}
		// executa o agente de busca guiada com a heuristica 2
		else if(gameInput.getAgentType().equals("GBFS2")){

			System.out.println("*** Busca de melhor escolha usando H2 ***");

			// a omissao do parametro booleano ou indicar um valor falso para a heuristica,
			// faz com que a fila de prioridade seja um heap de minimo,
			// onde valores menores de h(n) tem maior prioridade
			agent = new GBFSAgent(initialNode, gameInput.getProblemSize(),
					new HeuristicTwo(gameInput.getProblemSize()));
		}else{
			System.out.println("Invalid agent type");
			System.exit(1);
		}


		// executa o agente escolhido
		List<SearchNode> solutionPath = agent.resolve();
		showData(gameInput, agent, solutionPath);

	}

	private void showData(GameInput gameInput, Agent agent, List<SearchNode> solutionPath){

		System.out.println();
		System.out.println("Tamanho do problema: " + gameInput.getProblemSize());
		System.out.println("Estados Gerados: " + agent.getNumberOfGeneratedNodes());
		System.out.println("Estados Explorados: " + agent.getNumberOfExploredNodes());
		System.out.println("Fator de ramificação medio: " + agent.getAverageBranchingFactor());
		System.out.println("Custo da Solucao: " + agent.getSolutionCoast());
		System.out.println("Profundidade da Solucao: " + agent.getDepthOfSolution());
		if(solutionPath == null){
			System.out.println();
			System.out.println("Nao encontrou solucao");
		}else{
			System.out.print("Solucao encontrada: ");
			showGoalState(agent.getGoalNode());

			System.out.println();
			System.out.println();
			agent.tellSolution();
		}
	}

	// exibe o estado meta
	private void showGoalState(SearchNode goalState){

		for(Block block : goalState.getGameState()){
			BlockType type = block.getType();
			if(type == BlockType.White){
				System.out.print("B");
			}else if(type == BlockType.Blue){
				System.out.print("A");
			}else if(type == BlockType.Empty){
				System.out.print("-");
			}
		}
	}

	private SearchNode getInitialState(String gameInput, int n){

		// configuracao inicial
		int i = 0;
		int emptyPos = 0;

		Block[] initialGameState = new Block[(n << 1) + 1];
		for(char c : gameInput.toCharArray()){
			if(c == 'B'){
				Block block = new Block(BlockType.White, i);
				initialGameState[i] = block;
			}else if(c == 'A'){
				Block block = new Block(BlockType.Blue, i);
				initialGameState[i] = block;
			}else if(c == '-'){
				Block block = new Block(BlockType.Empty, i);
				initialGameState[i] = block;
				emptyPos = i;
			}

			i++;
		}

		// no inicial;
		return new SearchNode(initialGameState, emptyPos, null, null);

	}

}
