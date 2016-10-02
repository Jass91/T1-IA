package game.puzzle.ia.t1.ufscar;

import java.util.List;

import agent.puzzle.ia.t1.ufscar.Agent;
import agent.puzzle.ia.t1.ufscar.BFSAgent;
import agent.puzzle.ia.t1.ufscar.DFSAgent;

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
			System.out.println("*** Busca em largura ***");
			agent = new BFSAgent(initialState, n);
		}else if(agentType == "BP"){
			System.out.println("*** Busca em profundidade ***");
			agent = new DFSAgent(initialState, n);
		}else if(agentType == "BPL"){
			System.out.println("*** Busca em profundidade limitada ***");
		}else if(agentType == "BPI"){
			System.out.println("*** Busca em profundidade iterativa ***");
		}else if(agentType == "BCU"){
			System.out.println("*** Busca de custo uniforme ***");
		}else if(agentType == "A*"){
			System.out.println("*** Busca A* ***");
		}

		// executa o agent
		List<GameState> solutionPath = agent.resolve();
		
		if(solutionPath == null){
			System.out.println();
			System.out.println("Não tem solução");
		}else{
			System.out.println();
			System.out.print("Solução encontrada: ");
			agent.showGoalState();
			
			System.out.println();
			System.out.println("Nós Gerados: " + agent.getNumberOfGeneratedNodes());
			System.out.println("Nós Explorados: " + agent.getNumberOfExploredNodes());
			System.out.println("Profundidade da Solução: " + agent.getDepthOfSolution());
			
			System.out.println();
			agent.tellSolution();
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
		GameState estadoInicial = new GameState(gameConfig, emptyPos, null, null);
		
		return estadoInicial;
	}
}
