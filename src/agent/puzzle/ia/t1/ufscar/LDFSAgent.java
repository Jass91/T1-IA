package agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Stack;

// agente que executa uma busca em profundidade limitada
public class LDFSAgent extends TreeAgent {

	private int limit;
	
	public LDFSAgent(GameState initialState, int problemSize, int limit) {
		super(initialState, problemSize);
		
		this.limit = limit;
		this.border = new Stack();
		
	}

	// sobrescreve a função que expande o nó.
	//De acordo com o comportamento geral desse tipo de agente,
	// o nó é expandido apenas se n.depth < limit
	@Override
	public void expandNode(GameState node){
		if(node.getDepth() < limit){
			super.expandNode(node);
		}
	}
}
