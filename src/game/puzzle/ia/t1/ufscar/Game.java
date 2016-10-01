package game.puzzle.ia.t1.ufscar;

import java.util.List;

import agent.puzzle.ia.t1.ufscar.Agent;
import agent.puzzle.ia.t1.ufscar.BFSAgent;

public class Game {

	public static void main(String[] args) {

		Game game = new Game();

		int n = 2;
		String gameInput = "BA-AB";

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
		List<GameState> solutionPath = agent.run();
		
		if(solutionPath == null){
			System.out.println();
			System.out.println("Não tem solução");
		}else{
			System.out.println();
			game.showGoalState(agent.getGoalState());
			game.showSolution(solutionPath);
		}
	}

	public void showGoalState(GameState state){
	
		System.out.print("Solução encontrada: ");
		for(Block block : state.getGameConfig()){

			Block.Type type = block.getType();

			if(type == Block.Type.White){
			   System.out.print("B");
			}else if(type == Block.Type.Blue){
				System.out.print("A");
			}else if(type == Block.Type.Empty){
				System.out.print("-");
			}
		}
		
		System.out.println();
		System.out.println();
	}
	
	public void showSolution(List<GameState> solutionPath) {
		
		System.out.println("Sequência de ações:");
		for(int i = solutionPath.size(); i > 0; i--){
			GameState state = solutionPath.get(i - 1);
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
				Block block = new Block(Block.Type.White, i);
				gameConfig[i] = block;
			}else if(c == 'A'){
				Block block = new Block(Block.Type.Blue, i);
				gameConfig[i] = block;
			}else if(c == '-'){
				Block block = new Block(Block.Type.Empty, i);
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
