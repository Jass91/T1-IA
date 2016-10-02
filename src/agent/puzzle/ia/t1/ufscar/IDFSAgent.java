package agent.puzzle.ia.t1.ufscar;

import java.util.List;

import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Stack;

public class IDFSAgent extends GraphAgent{

	private int limit;
	private int depth;

	public IDFSAgent(GameState initialState, int problemSize, int limit) {
		super(initialState, problemSize);

		this.depth = 1;
		this.limit = limit;
		this.border = new Stack();

	}

	// sobrescreve o método de resolução
	@Override
	public List<GameState> resolve(){

		while(depth <= limit){
			List<GameState> resolution = super.resolve();
			if(resolution != null){
				return resolution;
			}else{
				depth++;
			}
		}
		return null;
	}

	// Sobrescreve a função que expande o nó.
	// De acordo com o comportamento geral desse tipo de agente,
	// o nó é expandido apenas se n.depth < limit
	@Override
	public void expandNode(GameState node){
		if(node.getDepth() < depth){
			super.expandNode(node);
		}
	}
}
