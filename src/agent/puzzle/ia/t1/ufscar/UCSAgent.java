package agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.PriorityQueue;

public class UCSAgent extends GraphAgent {

	public UCSAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);
		
		// instancia a borda como uma fila de prioridade
		this.border = new PriorityQueue();
	}

}
