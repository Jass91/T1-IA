package agent.puzzle.ia.t1.ufscar;

import java.util.List;
import border.agent.puzzle.ia.t1.ufscar.StackBorder;
import game.puzzle.ia.t1.ufscar.GameState;
import general.agent.puzzle.ia.t1.ufscar.TreeAgent;

//agente que executa uma busca em profundidade iterativa
public class IDFSAgent extends TreeAgent{

	private int maxLimit;
	private int depth;

	public IDFSAgent(GameState initialState, int problemSize, int maxLimit) {
		super(initialState, problemSize);

		this.depth = 1;
		this.maxLimit = maxLimit;
		this.border = new StackBorder();

	}
	
	/*
	 * Sobrescreve o metodo de resolucao,
	 * pois esse agente executa n = 1, 2, ..., maxLimit
	 * 
	 */
	@Override
	public List<GameState> resolve(){

		int totalOfExploredNodes = 0;
		int totalOfGeneratedNodes = 0;
		
		// aumenta o limite de depth ate maxLimit gradativamente
		while(depth <= maxLimit){
			
			List<GameState> resolution = super.resolve();
			
			// atualiza as variaveis (para cada busca conta os estados gerados e explorados)
			totalOfExploredNodes += numberOfExploredNodes;
			totalOfGeneratedNodes += numberOfGeneratedNodes;
			
			if(resolution != null){
				numberOfExploredNodes = totalOfExploredNodes;
				numberOfGeneratedNodes = totalOfGeneratedNodes;
				return resolution;
			}
			
			// muda a profundidade limite
			depth++;
		}
		
		numberOfExploredNodes = totalOfExploredNodes;
		numberOfGeneratedNodes = totalOfGeneratedNodes;
		
		return null;
	}

	
	// Sobrescreve a funcao que expande o estado.
	// De acordo com o comportamento geral desse tipo de agente,
	// o estado sera expandido apenas se n.depth < limit
	@Override
	public void expandNode(GameState node){
		if(node.getDepth() < depth){
			super.expandNode(node);
		}
	}
}
