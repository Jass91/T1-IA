package agent.puzzle.ia.t1.ufscar;

import border.agent.puzzle.ia.t1.ufscar.StackBorder;
import game.puzzle.ia.t1.ufscar.SearchNode;
import general.agent.puzzle.ia.t1.ufscar.TreeAgent;

// agente que executa uma busca em profundidade limitada
public class LDFSAgent extends TreeAgent {

	private int limit;
	
	public LDFSAgent(SearchNode initialState, int problemSize, int limit) {
		super(initialState, problemSize);
		
		this.limit = limit;
		this.border = new StackBorder();
		
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
	public void expandNode(SearchNode node){
		if(node.getDepth() < limit){
			super.expandNode(node);
		}
	}
}
