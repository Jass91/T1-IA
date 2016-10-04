package game.puzzle.ia.t1.ufscar;

import java.io.IOException;
import java.util.List;

import agent.puzzle.ia.t1.ufscar.BFSAgent;
import agent.puzzle.ia.t1.ufscar.DFSAgent;
import agent.puzzle.ia.t1.ufscar.GBFSAgent;
import agent.puzzle.ia.t1.ufscar.IDFSAgent;
import agent.puzzle.ia.t1.ufscar.LDFSAgent;
import agent.puzzle.ia.t1.ufscar.UCSAgent;
import general.agent.puzzle.ia.t1.ufscar.Agent;
import heuristic.puzzle.ia.t1.ufscar.HeuristicOne;
import heuristic.puzzle.ia.t1.ufscar.HeuristicTwo;

public class Game {


	public Game(){

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
		GameState initialState = getInitialState(gameInput.getInitialConfig(), gameInput.getProblemSize());

		// nosso agente
		Agent agent = null;

		// criar o agent escolhido
		if(gameInput.getAgentType().equals("BL")){
			System.out.println("*** Busca em largura ***");
			agent = new BFSAgent(initialState, gameInput.getProblemSize());

		}else if(gameInput.getAgentType().equals("BP")){
			System.out.println("*** Busca em profundidade ***");
			agent = new DFSAgent(initialState, gameInput.getProblemSize());

		}else if(gameInput.getAgentType().equals("BCU")){
			System.out.println("*** Busca de custo uniforme ***");
			agent = new UCSAgent(initialState, gameInput.getProblemSize());

		}else if(gameInput.getAgentType().equals("BPL")){
			System.out.println("*** Busca em profundidade limitada ***");
			agent = new LDFSAgent(initialState, gameInput.getProblemSize(), gameInput.getMaxLimit());

		}else if(gameInput.getAgentType().equals("BPI")){
			System.out.println("*** Busca em profundidade iterativa ***");
			agent = new IDFSAgent(initialState, gameInput.getProblemSize(), gameInput.getMaxLimit());

		}else if(gameInput.getAgentType().equals("A*")){
			System.out.println("*** Busca A* ***");

		// executa o agente de busca guiada com a heuristica 1
		}else if(gameInput.getAgentType().equals("GBFS1")){
			System.out.println("*** Busca de melhor escolha com H1 ***");
			agent = new GBFSAgent(initialState, gameInput.getProblemSize(), new HeuristicOne());
		}
		// executa o agente de busca guiada com a heuristica 2
		else if(gameInput.getAgentType().equals("GBFS2")){
			System.out.println("*** Busca A* ***");
			agent = new GBFSAgent(initialState, gameInput.getProblemSize(), new HeuristicTwo());
		}


		// executa o agente escolhido
		List<GameState> solutionPath = agent.resolve();
		showData(gameInput, agent, solutionPath);

	}

	private void showData(GameInput gameInput, Agent agent, List<GameState> solutionPath){

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
			agent.showGoalState();

			System.out.println();
			System.out.println();
			agent.tellSolution();
		}
	}

	private GameState getInitialState(String gameInput, int n){

		// configuracao inicial
		int i = 0;
		int emptyPos = 0;

		Block[] gameConfig = new Block[(n << 1) + 1];
		for(char c : gameInput.toCharArray()){
			if(c == 'B'){
				Block block = new Block(BlockType.White, i);
				gameConfig[i] = block;
			}else if(c == 'A'){
				Block block = new Block(BlockType.Blue, i);
				gameConfig[i] = block;
			}else if(c == '-'){
				Block block = new Block(BlockType.Empty, i);
				gameConfig[i] = block;
				emptyPos = i;
			}

			i++;
		}

		// estado inicial;
		return new GameState(gameConfig, emptyPos, null, null);

	}

	public static void main(String[] args) {


		try {

			Game game = new Game();

			// le os dados de entrada
			GameInput gameInput = game.readInput();

			// resolve e mostra a solucao
			game.resolve(gameInput);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
