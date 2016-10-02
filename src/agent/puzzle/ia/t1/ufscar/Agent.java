package agent.puzzle.ia.t1.ufscar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import game.puzzle.ia.t1.ufscar.Action;
import game.puzzle.ia.t1.ufscar.Block;
import game.puzzle.ia.t1.ufscar.BlockType;
import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Border;

// define o comportamento comum a todos os agentes
public abstract class Agent {

	protected int problemSize;
	protected int numberOfGeneratedNodes;
	protected int numberOfExploredNodes;
	protected int depth;
	protected Border border;
	protected GameState initialState;
	protected GameState goalState;
	
	public Agent(GameState initialState, int problemSize){
		this.initialState = initialState;
		this.problemSize = problemSize;
		goalState = null;
		numberOfExploredNodes = 0;
		numberOfGeneratedNodes = 0;
		depth = 0;
	}

	// executa a ação (troca src com dst), resultando em um novo estado
	public GameState move(GameState state, int src, int dst) {

		Block[] newGameConfig = new Block[2 * problemSize + 1];
		Block[] currentGameConfig = state.getGameConfig();

		// copia os valores
		for(int j = 0; j < currentGameConfig.length; j++)
			newGameConfig[j] = currentGameConfig[j];

		// custo do movimento
		int coast = Math.abs(src - dst);
				
		// cria a ação necessária para esse novo estado
		Action action = new Action(coast, src, dst);

		// executa a ação trocando os blocos
		newGameConfig[dst] = currentGameConfig[src];
		newGameConfig[src] = currentGameConfig[dst];

		// cria o novo estado
		GameState newState = new GameState(newGameConfig, src, state, action);

		return newState;

	}

	// retorna o caminho encontrado (lista de estados)
	public List<GameState> resolve(){

		addStateToBorder(initialState);

		// enquanto a borda não está vazia
		while(border.getSize() > 0){

			// retira um nó da borda
			GameState state = getStateFromBorder();

			// se o nó é objetivo
			if(isGoal(state)){

				// guarda o estado meta
				goalState = state;

				// incrementa o numero de nós explorados
				numberOfExploredNodes++;
				
				// retorna a solução
				return getSolutionPath();

			}

			// inicia a busca a partir do estado
			expandNode(state);

			// incrementa o numero de nós explorados
			numberOfExploredNodes++;

		}

		return getSolutionPath();
	}

	// retorna o caminho do estado meta até o estado inicial
	private List<GameState> getSolutionPath(){

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

	// verifica se o estado é meta
	public boolean isGoal(GameState state){

		Block[] gameConfig = state.getGameConfig();
		int n = (2 * problemSize) + 1;

		for(int i = 1; i < n; i++){

			// se não é objetivo
			if( (gameConfig[i - 1].getType() == BlockType.Blue) &&
					(gameConfig[i].getType() == BlockType.White)){

				return false;

			// outro caso que não é objetivo
			}else if((i >= 2)){

				if( (gameConfig[i - 1].getType() == BlockType.Empty) &&
						(gameConfig[i - 2].getType() == BlockType.Blue) &&
						(gameConfig[i].getType() == BlockType.White)){

					return false;
				}

			}
		}

		return true;
	}

	public int getNumberOfExploredNodes(){
		return numberOfExploredNodes;
	}

	public int getNumberOfGeneratedNodes(){
		return numberOfGeneratedNodes;
	}

	// retorna a profundidade do nó meta
	public int getDepthOfSolution(){
		return goalState.getDepth();
	}

	public GameState getGoalState() {
		return goalState;
	}

	public void addStateToBorder(GameState newState) {
		this.border.add(newState);
	}

	public GameState getStateFromBorder() {
		return this.border.get();
	}
	
	public int getSolutionCoast(){
		return goalState.getCoastToGetHere();
	}
	
	// exibe o estado meta
	public void showGoalState(){
		
		for(Block block : goalState.getGameConfig()){
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
	
	// informa as ações para alcançar o estado meta
	public void tellSolution() {
		
		for(GameState state : getSolutionPath()){
			if(state.getAction() == null)
				System.out.println("Estado Inicial");
			else{
				state.getAction().showMovement();
			}
		}
		
	}
	
	public abstract void expandNode(GameState node);

}
