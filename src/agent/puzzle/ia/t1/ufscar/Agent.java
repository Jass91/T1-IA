package agent.puzzle.ia.t1.ufscar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import game.puzzle.ia.t1.ufscar.Action;
import game.puzzle.ia.t1.ufscar.Block;
import game.puzzle.ia.t1.ufscar.BlockType;
import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Border;

public abstract class Agent {

	// armazena como chave o id do estado e o valor como "Visited" ou "Explored"
	protected HashMap<String, String> nodeStatus;

	protected Border border;
	protected GameState initialState;
	protected GameState goalState;
	protected int problemSize;
	protected int numberOfGeneratedNodes;
	protected int numberOfExploredNodes;
	protected int depth;
	
	public Agent(GameState initialState, int problemSize){
		this.initialState = initialState;
		this.problemSize = problemSize;
		goalState = null;
		numberOfExploredNodes = 0;
		numberOfGeneratedNodes = 0;
		depth = 1;
		nodeStatus = new HashMap<String, String>();
	}

	// executa a ação, resultando em um novo estado
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

	public List<GameState> resolve(){

		addToBorder(initialState);

		// enquanto a borda não está vazia
		while(border.getSize() > 0){

			// retira um nó da borda
			GameState state = getNextStateFromBorder();

			// se o nó é objetivo
			if(isGoal(state)){

				// guarda o estado meta
				goalState = state;
				
				// retorna a solução
				List<GameState> solutionPath = getSolutionPath(state);
				
				// incrementa o numero de nós explorados
				numberOfExploredNodes++;
				
				return solutionPath;
			}

			// inicia a busca a partir do estado
			expandNode(state);

			// marca o estado como explorado;
			nodeStatus.put(state.getId(),"Explored");
			
			// incrementa o numero de nós explorados
			numberOfExploredNodes++;
			
		}

		return getSolutionPath(goalState);
	}

	private List<GameState> getSolutionPath(GameState goalState){

		if(goalState == null)
			return null;
		
		List<GameState> solutionPath = new ArrayList<GameState>();
		GameState state = goalState;

		while(state != null){
			solutionPath.add(state);
			state = state.getParent();
		}

		// inverte a lista
		Collections.reverse(solutionPath);
		
		return solutionPath;
	}

	public boolean isGoal(GameState state){

		Block[] gameConfig = state.getGameConfig();
		int n = (2 * problemSize) + 1;

		/*
		// TODO: teste remover depois
		System.out.print("Testando state #" + state.getId() + " = (");
		for(Block block : gameConfig){

			BlockType type = block.getType();

			if(type == BlockType.White){
			   System.out.print("B");
			}else if(type == BlockType.Blue){
				System.out.print("A");
			}else if(type == BlockType.Empty){
				System.out.print("-");
			}
		}

		System.out.print("): ");
		*/
		for(int i = 1; i < n; i++){

			// se não é objetivo
			if( (gameConfig[i - 1].getType() == BlockType.Blue) &&
				(gameConfig[i].getType() == BlockType.White)){
				
					//System.out.println("Não é objetivo!");
					return false;
					
			// outro caso que não é objetivo
			}else if((i >= 2)){
					 
				if( (gameConfig[i - 1].getType() == BlockType.Empty) &&
					(gameConfig[i - 2].getType() == BlockType.Blue) &&
					(gameConfig[i].getType() == BlockType.White)){
					
						//System.out.println("Não é objetivo!");
						return false;
				}
				
			}
		}

		//System.out.println("É objetivo: ");

		return true;
	}

	public int getNumberOfExploredNodes(){
		return numberOfExploredNodes;
	}
	
	public int getNumberOfGeneratedNodes(){
		return numberOfGeneratedNodes;
	}
	
	public int getDepth(){
		return depth;
	}
	
	public GameState getGoalState() {
		return goalState;
	}

	public void addToBorder(GameState newState) {
		this.border.add(newState);
	}

	public abstract GameState getNextStateFromBorder();

	public abstract void expandNode(GameState node);
}
