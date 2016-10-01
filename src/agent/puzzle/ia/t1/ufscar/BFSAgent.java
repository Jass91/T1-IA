package agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Queue;

public class BFSAgent extends BlindAgent {

	public BFSAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);

		// inicializa a borda como uma pilha
		this.border = new Queue();
	}


	@Override
	public GameState getNextStateFromBorder() {
		return ((Queue)this.border).get();
	}	

}
