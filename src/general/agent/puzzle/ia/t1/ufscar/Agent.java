package general.agent.puzzle.ia.t1.ufscar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import border.agent.puzzle.ia.t1.ufscar.Border;
import game.puzzle.ia.t1.ufscar.Action;
import game.puzzle.ia.t1.ufscar.Block;
import game.puzzle.ia.t1.ufscar.BlockType;
import game.puzzle.ia.t1.ufscar.SearchNode;

// define o comportamento comum a todos os agentes
public abstract class Agent {

	protected int problemSize;
	protected int numberOfGeneratedNodes;
	protected int numberOfExploredNodes;
	protected int depth;
	protected Border border;
	protected SearchNode initialnode;
	protected SearchNode goalNode;

	public Agent(SearchNode initialnode, int problemSize){
		this.initialnode = initialnode;
		this.problemSize = problemSize;
		goalNode = null;
		numberOfExploredNodes = 0;
		numberOfGeneratedNodes = 0;
		depth = 0;
	}

	// executa a acao (troca src com dst), resultando em um novo no
	protected SearchNode move(SearchNode node, int src, int dst) {

		Block[] newGameState = new Block[(problemSize << 1) + 1];
		Block[] currentGameState = node.getGameState();

		// copia os valores
		for(int j = 0; j < currentGameState.length; j++)
			newGameState[j] = currentGameState[j];

		// custo do movimento
		int coast = Math.abs(src - dst);

		// cria a ação necessária para esse novo estado
		Action action = new Action(coast, src, dst);

		// executa a ação trocando os blocos
		newGameState[dst] = currentGameState[src];
		newGameState[src] = currentGameState[dst];

		// cria o novo estado
		SearchNode newNode = new SearchNode(newGameState, src, node, action);

		return newNode;

	}

	// verifica se o estado eh meta
	protected boolean isGoal(SearchNode node){

		Block[] gameState = node.getGameState();
		int n = (problemSize << 1) + 1;

		for(int i = 1; i < n; i++){

			// se nao eh objetivo
			if( (gameState[i - 1].getType() == BlockType.Blue) &&
					(gameState[i].getType() == BlockType.White)){

				return false;

			// outro caso que nao eh objetivo
			}else if((i >= 2)){

				if( (gameState[i - 1].getType() == BlockType.Empty) &&
						(gameState[i - 2].getType() == BlockType.Blue) &&
						(gameState[i].getType() == BlockType.White)){

					return false;
				}

			}
		}

		return true;
	}

	protected void addNodeToBorder(SearchNode newNode) {
		this.border.add(newNode);
	}

	protected SearchNode getNodeFromBorder() {
		return this.border.get();
	}

	// executa a estrategia de expansao do no (definida na subclasse)
	protected abstract void expandNode(SearchNode node);

	// calcula o caminho do no meta ate o no inicial
	private List<SearchNode> getSolutionPath(){

		if(goalNode == null)
			return null;

		List<SearchNode> solutionPath = new ArrayList<SearchNode>();
		SearchNode node = goalNode;

		while(node != null){
			solutionPath.add(node);
			node = node.getParent();
		}

		// inverte a lista
		Collections.reverse(solutionPath);

		return solutionPath;
	}


	// **************************************** //
	//											//
	// Acess�veis atrav�s da instancia de Agent //
	//											//
	// **************************************** //

	// retorna o caminho encontrado (lista de nos)
	public List<SearchNode> resolve(){

		addNodeToBorder(initialnode);

		// enquanto a borda nao estiver vazia
		while(border.getSize() > 0){

			// retira um no da borda
			SearchNode node = getNodeFromBorder();

			// se o eh objetivo
			if(isGoal(node)){

				// guarda o estado meta
				goalNode = node;

				// incrementa o numero de nos explorados
				numberOfExploredNodes++;

				// retorna a solução
				return getSolutionPath();

			}

			// inicia a busca a partir do no
			expandNode(node);

			// incrementa o numero de nos explorados
			numberOfExploredNodes++;

		}

		return getSolutionPath();
	}

	public int getAverageBranchingFactor(){

		if(numberOfExploredNodes == 0)
			return 0;

		return (numberOfGeneratedNodes / numberOfExploredNodes);
	}

	public int getNumberOfExploredNodes(){
		return numberOfExploredNodes;
	}

	public int getNumberOfGeneratedNodes(){
		return numberOfGeneratedNodes;
	}

	// retorna a profundidade do estado meta
	public int getDepthOfSolution(){
		return goalNode.getDepth();
	}

	public SearchNode getGoalNode() {
		return goalNode;
	}

	public int getSolutionCoast(){
		return goalNode.getCoast();
	}

	// informa as acoes para alcancar o no meta
	public void tellSolution() {

		for(SearchNode node : getSolutionPath()){
			if(node.getAction() == null){
				System.out.println("Estado Inicial:");
				System.out.println(node.toString());
			}else{
				node.getAction().showMovement();
				System.out.println(node.toString());
			}
		}

	}

}
