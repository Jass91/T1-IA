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
	private void expandNodeAsTree(SearchNode node) {

		// calcula os limites do vetor
		int n = (problemSize << 1);
		int emptyPos = node.getEmptyPosition();
		int li = (emptyPos - problemSize < 0) ? 0 : emptyPos - problemSize;
		int ls = (emptyPos + problemSize > n) ? n : emptyPos + problemSize;

		// gera todos os filhos do no atual
		// baseado nos movimentos possiveis
		for(int i = li; i <= ls; i++){

			if(i == emptyPos)
				continue;

			// retorna um novo no movendo-se de i para emptyPos a partir do no atual
			SearchNode newNode = move(node, i, emptyPos);

			// incrementa o numero de nos gerados
			numberOfGeneratedNodes++;

			// insere o novo no na borda
			border.add(newNode);

		}

	}

}
