package general.agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.SearchNode;

public class TreeAgent extends Agent {

	public TreeAgent(SearchNode initialNode, int problemSize) {
		super(initialNode, problemSize);
	}

	@Override
	public void expandNode(SearchNode node) {
		expandNodeAsTree(node);

	}
	
	// executa a acao de expandir o no, porem NAO desconsidera nos ja gerados
	private void expandNodeAsTree(SearchNode state) {

		int emptyPos = state.getEmptyPosition();
		int n = (2 * problemSize) + 1;

		// gera todos os filhos do estado atual
		for(int i = 0; i < n; i++){

			if(i == emptyPos)
				continue;

			// custo do movimento
			int distance = Math.abs(i - emptyPos);

			// se o movimento eh legal
			if(distance <= problemSize){

				// retorna um novo no movendo-se de i para emptyPos a partir do no atual
				SearchNode newNode = move(state, i, emptyPos);

				// incrementa o numero de nos gerados
				numberOfGeneratedNodes++;

				// insere o novo no na borda
				border.add(newNode);
			}
		}

	}

}
