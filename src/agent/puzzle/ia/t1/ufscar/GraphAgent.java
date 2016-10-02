package agent.puzzle.ia.t1.ufscar;

import java.util.HashMap;

import game.puzzle.ia.t1.ufscar.GameState;

public abstract class GraphAgent extends Agent {

	// armazena como chave o id do estado e o valor como o id do estado"
	protected HashMap<String, String> nodeStatus;
		
	public GraphAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);
		this.nodeStatus = new HashMap<String, String>();
	}

	@Override
	public void expandNode(GameState node){
		expandNodeAsGraph(node);
	}
	
	private boolean canAddStateToBorder(GameState state){
		String stateId = nodeStatus.get(state.getId());
		if(stateId == null)
			return true;
		else
			return false;
	}
	// executa a ação de expandir o nó, porém desconsidera nós já gerados
	private void expandNodeAsGraph(GameState state) {

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
				if(canAddStateToBorder(newState)){

					// insere o novo estado na borda
					border.add(newState);

					// marca como já gerado
					nodeStatus.put(newState.getId(), newState.getId());
				}
			}
		}
	}
}
