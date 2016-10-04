package agent.puzzle.ia.t1.ufscar;

import java.util.List;

import border.agent.puzzle.ia.t1.ufscar.StackBorder;
import game.puzzle.ia.t1.ufscar.GameState;
import general.agent.puzzle.ia.t1.ufscar.GraphAgent;

//agente que executa uma busca em profundidade iterativa
public class IDFSAgent extends GraphAgent{

	private int maxLimit;
	private int depth;

	public IDFSAgent(GameState initialState, int problemSize, int maxLimit) {
		super(initialState, problemSize);

		this.depth = 1;
		this.maxLimit = maxLimit;
		this.border = new StackBorder();

	}
	
	// sobrescreve o metodo de resolucao
	@Override
	public List<GameState> resolve(){

		int totalOfExploredNodes = 0;
		int totalOfGeneratedNodes = 0;
		
		// utiliza o agente de busca em profundidade limitada
		LDFSAgent agent = new LDFSAgent(initialState, problemSize, depth);
		
		// aumenta o limite de depth ate maxLimit gradativamente
		while(depth <= maxLimit){
			
			List<GameState> resolution = agent.resolve();
			
			// atualiza as variaveis (para cada busca conta os estados gerados e explorados)
			totalOfExploredNodes += agent.getNumberOfExploredNodes();
			totalOfGeneratedNodes += agent.getNumberOfGeneratedNodes();
			
			if(resolution != null){
				
				this.goalState = agent.getGoalState();
				this.numberOfExploredNodes = totalOfExploredNodes;
				this.numberOfGeneratedNodes = totalOfGeneratedNodes;
				
				return resolution;
			}
			
			// muda a profundidade limite
			agent.setLimit(++depth);
		}
		
		// monta as informacoes 
		this.goalState = agent.getGoalState();
		this.numberOfExploredNodes = totalOfExploredNodes;
		this.numberOfGeneratedNodes = totalOfGeneratedNodes;
		
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
