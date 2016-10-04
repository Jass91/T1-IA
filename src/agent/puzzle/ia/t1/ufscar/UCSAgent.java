package agent.puzzle.ia.t1.ufscar;

import java.util.Comparator;

import border.agent.puzzle.ia.t1.ufscar.PriorityQueueBorder;
import game.puzzle.ia.t1.ufscar.GameState;
import general.agent.puzzle.ia.t1.ufscar.GraphAgent;

public class UCSAgent extends GraphAgent implements Comparator<GameState> {

	public UCSAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);
		
		// instancia a borda como uma fila de prioridade
		this.border = new PriorityQueueBorder(this);
	}

	// compara os estados com base no custo
	@Override
	public int compare(GameState gs1, GameState gs2) {
		if(gs1.getCoast() > gs2.getCoast()) return 1;
		if(gs1.getCoast() < gs2.getCoast()) return -1;
		return 0;
	}

}
