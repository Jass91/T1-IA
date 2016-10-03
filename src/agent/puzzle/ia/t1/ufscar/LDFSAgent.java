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

	public void setLimit(int limit){
		this.limit = limit;
	}
	
	
	public int getLimit(){
		return limit;
	}
	
	// Sobrescreve a funcao que expande o estado.
	// De acordo com o comportamento geral desse tipo de agente,
	// O estado sera expandido apenas se n.depth < limit
	@Override
	public void expandNode(GameState node){
		if(node.getDepth() < limit){
			super.expandNode(node);
		}
	}
}
