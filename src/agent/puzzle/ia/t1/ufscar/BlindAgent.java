package agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.GameState;

public abstract class BlindAgent extends Agent {

	public BlindAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void expandNode(GameState node) {
		expandNodeAsGraph(node);
	}
	
	public void expandNodeAsGraph(GameState state) {

		// se o nó ja foi explorado então é ignorado
		if(nodeStatus.get(state.getId()) == "Explored"){
			return;
		}

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
				
				// se ainda nao foi gerado esse estado
				String status = nodeStatus.get(newState.getId());
				if(status == null){

					// insere o novo estado na borda
					border.add(newState);
					
					// marca como já gerado
					nodeStatus.put(newState.getId(), "Generated");
				}
			}
		}
		
	}

}
