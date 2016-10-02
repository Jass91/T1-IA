import agent.puzzle.ia.t1.ufscar.Agent;
import game.puzzle.ia.t1.ufscar.GameState;
import util.puzzle.ia.t1.ufscar.Stack;

// agente que executa uma busca em profundidade limitada
public class LDFSAgent extends Agent {

	private int depth;
	private int limit;
	
	public LDFSAgent(GameState initialState, int problemSize, int depth, int limit) {
		super(initialState, problemSize);
		
		this.border = new Stack();
		this.depth = depth;
		this.limit = limit;
	}

	@Override
	public void expandNode(GameState node) {
		// TODO Auto-generated method stub
		
	}

	// sobrescreve o comportamento de adicionar a borda,
	// pois só adiciona nós que estão dentro do limite
	@Override
	public void addStateToBorder(GameState newState){
		
	}
}
