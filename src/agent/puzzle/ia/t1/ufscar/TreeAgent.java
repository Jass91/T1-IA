package agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.GameState;

public class TreeAgent extends Agent {

	public TreeAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);
	}

	@Override
	public void expandNode(GameState node) {
		expandNodeAsTree(node);

	}
	
	// executa a ação de expandir o nó, porém NÃO desconsidera nós já gerados
	private void expandNodeAsTree(GameState state) {

		int emptyPos = state.getEmptyPosition();
		int n = (2 * problemSize) + 1;

		// gera todos os filhos do estado atual
		for(int i = 0; i < n; i++){

			if(i == emptyPos)
				continue;

			// custo do movimento
			int distance = Math.abs(i - emptyPos);

			// se o movimento é legal
			if(distance <= problemSize){

				// retorna um novo estado movendo-se de i para emptyPos a partir do estado atual
				GameState newState = move(state, i, emptyPos);

				// incrementa o numero de nós gerados
				numberOfGeneratedNodes++;

				// insere o novo estado na borda
				border.add(newState);
			}
		}

	}

}
