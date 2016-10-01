package agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Queue;

public class BFSAgent extends Agent {

	public BFSAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);

		// inicializa a borda como uma pilha
		this.border = new Queue();
	}

	@Override
	public void addToBorder(GameState newState) {
		this.border.add(newState);
	}

	@Override
	public GameState getNextStateFromBorder() {
		return ((Queue)this.border).get();
	}

	@Override
	public void runSearch(GameState state) {

		// se o nó ja foi explorado então é ignorado
		if(nodeStatus.get(state.getId()) == "Explored"){
			return;
		}

		// gera os filhos do estado usando o criterio BFS
		int emptyPos = state.getEmptyPosition();
		int n = (2 * problemSize) + 1;

		// gera todos os filhos do estado atual segundo uma BFS
		for(int i = 0; i < n; i++){

			if(i == emptyPos)
				continue;

			// custo do movimento
			int distance = Math.abs(i - emptyPos);

			// se o movimento é legal
			if(distance <= problemSize){

				// retorna um novo estado movendo - se de i para emptPos a partir do estado atual
				GameState newState = move(state, i, emptyPos);

				// se ainda nao foi gerado esse estado
				String status = nodeStatus.get(newState.getId());
				if(status == null){

					// insere o novo estado na borda
					border.add(newState);
				}
			}
		}

	}	

}
