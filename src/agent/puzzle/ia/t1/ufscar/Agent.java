package agent.puzzle.ia.t1.ufscar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import game.puzzle.ia.t1.ufscar.Action;
import game.puzzle.ia.t1.ufscar.Block;
import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Border;

public abstract class Agent {

	// armazena como chave o id do estado e o valor como "Visited" ou "Explored"
	protected HashMap<String, String> nodeStatus;

	protected Border border;
	protected GameState initialState;
	protected GameState goalState;
	protected int problemSize;

	public Agent(GameState initialState, int problemSize){
		this.initialState = initialState;
		this.problemSize = problemSize;
		nodeStatus = new HashMap<String, String>();
	}

	// executa a transição de estado
	public GameState move(GameState state, int src, int dst) {

		// custo do movimento
		int coast = Math.abs(src - dst);

		Block[] newGameConfig = new Block[2 * problemSize + 1];
		Block[] currentGameConfig = state.getGameConfig();

		// copia os valores
		for(int j = 0; j < currentGameConfig.length; j++)
			newGameConfig[j] = currentGameConfig[j];

		// cria a ação necessária para esse novo estado
		Action action = new Action(coast, currentGameConfig[src], currentGameConfig[dst]);

		// executa a ação trocando os blocos
		newGameConfig[dst] = currentGameConfig[src];
		newGameConfig[src] = currentGameConfig[dst];

		// cria o novo estado
		GameState newState = new GameState(state, newGameConfig, action, src);

		return newState;

	}

	public List<GameState> run(){

		addToBorder(initialState);

		// enquanto a borda não está vazia
		while(border.getSize() > 0){

			// retira um nó da borda
			GameState state = getNextStateFromBorder();

			// se o nó é objetivo
			if(isGoal(state)){

				// retorna a solução
				List<GameState> solutionPath = getSolutionPath(state);
				return solutionPath;
			}

			// marca o estado como visitado;
			nodeStatus.put(state.getId(),"Visited");

			// inicia a busca a partir do estado
			runSearch(state);

			// marca o estado como explorado;
			nodeStatus.put(state.getId(),"Explored");
		}

		return getSolutionPath(goalState);
	}

	public List<GameState> getSolutionPath(GameState goalState){

		List<GameState> solutionPath = new ArrayList<GameState>();
		GameState state = goalState;

		while(state != null){
			solutionPath.add(state);
			state = state.getParent();
		}

		return solutionPath;
	}

	public boolean isGoal(GameState state){

		Block[] gameConfig = state.getGameConfig();
		int n = (2 * problemSize) + 1;

		// teste
		System.out.println("Testando: ");
		for(Block block : gameConfig){

			Block.Type type = block.getType();

			if(type == Block.Type.White){
			   System.out.print("B");
			}else if(type == Block.Type.Blue){
				System.out.print("A");
			}else if(type == Block.Type.Empty){
				System.out.print("-");
			}
		}

		for(int i = 1; i < n; i++){

			// se não é objetivo
			if( (gameConfig[i - 1].getType() == Block.Type.Blue) &&
				(gameConfig[i].getType() == Block.Type.White)){
				
					System.out.println(": Não é objetivo!");
					return false;
					
			// outro caso que não é objetivo
			}else if((i >= 2)){
					 
				if( (gameConfig[i - 1].getType() == Block.Type.Empty) &&
					(gameConfig[i - 2].getType() == Block.Type.Blue) &&
					(gameConfig[i].getType() == Block.Type.White)){
					
						System.out.println(": Não é objetivo!");
						return false;
				}
				
			}
		}

		System.out.println(": É objetivo: ");
		goalState = state;

		return true;
	}

	public GameState getGoalState() {
		return goalState;
	}

	public void setGoalState(GameState goalState) {
		this.goalState = goalState;
	}

	public abstract void runSearch(GameState state);

	public abstract void addToBorder(GameState newState);

	public abstract GameState getNextStateFromBorder();

}
