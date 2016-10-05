package general.agent.puzzle.ia.t1.ufscar;

import java.util.Comparator;
import border.agent.puzzle.ia.t1.ufscar.PriorityQueueBorder;
import game.puzzle.ia.t1.ufscar.SearchNode;
import heuristic.puzzle.ia.t1.ufscar.Heuristic;

public abstract class GuidedTreeAgent extends TreeAgent implements Comparator<SearchNode> {

	// representa a funcao de avaliacao h(n)
	protected Heuristic h;

	public GuidedTreeAgent(SearchNode initialState, int problemSize, Heuristic heuristic) {
		super(initialState, problemSize);

		this.h = heuristic;

		// instancia a borda como uma fila de prioridade,
		// cuja a ordem eh dada pela funcao de comparacao
		this.border = new PriorityQueueBorder(this);
	}
	
}
