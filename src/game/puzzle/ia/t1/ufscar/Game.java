package game.puzzle.ia.t1.ufscar;

import java.util.List;

import agent.puzzle.ia.t1.ufscar.Agent;
import agent.puzzle.ia.t1.ufscar.BFSAgent;

public class Game {

	public static void main(String[] args) {

		Game game = new Game();

		int n = 7;
		String gameInput = "AAABBABA-ABABBB";

		// tipos de agente: BL, BP, BPL, BPI, BCU ou A*
		String agentType = "BL";

		// estado inicial;
		GameState initialState = game.getInitialState(gameInput, n);

		// nosso agente
		Agent agent = null;
		
		// criar o agent escolhido
		if(agentType == "BL"){
			agent = new BFSAgent(initialState, n);
		}else if(agentType == "BP"){
			
		}else if(agentType == "BPL"){

		}else if(agentType == "BPI"){

		}else if(agentType == "BCU"){

		}else if(agentType == "A*"){

		}

		// executa o agent
		List<GameState> solutionPath = agent.resolve();
		
		if(solutionPath == null){
			System.out.println();
			System.out.println("Não tem solução");
		}else{
			System.out.println();
			game.showGoalState(agent.getGoalState());
			System.out.println("Nós Gerados: " + agent.getNumberOfGeneratedNodes());
			System.out.println("Nós Explorados: " + agent.getNumberOfExploredNodes());
			game.showSolution(solutionPath);
		}
	}

	public void showGoalState(GameState state){
	
		System.out.print("Solução encontrada: ");
		for(Block block : state.getGameConfig()){

			BlockType type = block.getType();

			if(type == BlockType.White){
			   System.out.print("B");
			}else if(type == BlockType.Blue){
				System.out.print("A");
			}else if(type == BlockType.Empty){
				System.out.print("-");
			}
		}
		
		System.out.println();
	}
	
	public void showSolution(List<GameState> solutionPath) {
		
		System.out.println("Sequência de ações:");
		for(GameState state : solutionPath){
			if(state.getAction() != null)
				state.getAction().showMovement();
			else{
				System.out.println("Estado Inicial");
			}
		}
	}

	public GameState getInitialState(String gameInput, int n){
		
		// configuração inicial
		int i = 0;
		int emptyPos = 0;
		
		Block[] gameConfig = new Block[2 * n + 1];
		
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
		GameState estadoInicial = new GameState(null, gameConfig, null, emptyPos);
		
		return estadoInicial;
	}
}
