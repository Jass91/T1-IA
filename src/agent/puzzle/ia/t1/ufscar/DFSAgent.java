package agent.puzzle.ia.t1.ufscar;

import border.agent.puzzle.ia.t1.ufscar.StackBorder;
import game.puzzle.ia.t1.ufscar.SearchNode;
import general.agent.puzzle.ia.t1.ufscar.GraphAgent;

// agente que executa a busca em profundidade
public class DFSAgent extends GraphAgent {

	public DFSAgent(SearchNode initialState, int problemSize) {
		super(initialState, problemSize);
		
		// inicializa a borda como uma pilha
		this.border = new StackBorder();
	}
	
}
