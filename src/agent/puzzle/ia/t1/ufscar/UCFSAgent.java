package agent.puzzle.ia.t1.ufscar;

import java.util.Comparator;

import border.agent.puzzle.ia.t1.ufscar.PriorityQueueBorder;
import game.puzzle.ia.t1.ufscar.SearchNode;
import general.agent.puzzle.ia.t1.ufscar.GraphAgent;

public class UCFSAgent extends GraphAgent implements Comparator<SearchNode> {

	public UCFSAgent(SearchNode initialState, int problemSize) {
		super(initialState, problemSize);
		
		// instancia a borda como uma fila de prioridade
		this.border = new PriorityQueueBorder(this);
	}

	// compara os estados com base no custo
	@Override
	public int compare(SearchNode node1, SearchNode node2) {
		if(node1.getCoast() > node2.getCoast()) return 1;
		if(node1.getCoast() < node2.getCoast()) return -1;
		return 0;
	}

}
