package agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Stack;

// agente que executa a busca em profundidade
public class DFSAgent extends Agent {

	public DFSAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);
		
		// inicializa a borda como uma pilha
		this.border = new Stack();
	}

	@Override
	public void expandNode(GameState node) {
		expandNodeAsGraph(node);
	}

}
