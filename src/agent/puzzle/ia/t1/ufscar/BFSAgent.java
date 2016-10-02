package agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Queue;

// agente que executa a busca em largura
public class BFSAgent extends Agent {

	public BFSAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);

		// inicializa a borda como uma fila
		this.border = new Queue();
	}

	@Override
	public void expandNode(GameState node) {
		expandNodeAsGraph(node);
	}	

}
