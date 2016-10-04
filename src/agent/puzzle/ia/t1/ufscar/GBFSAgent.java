package agent.puzzle.ia.t1.ufscar;

import java.util.Comparator;

import border.agent.puzzle.ia.t1.ufscar.PriorityQueueBorder;
import game.puzzle.ia.t1.ufscar.GameState;
import general.agent.puzzle.ia.t1.ufscar.GraphAgent;
import heuristic.puzzle.ia.t1.ufscar.Heuristic;

/*
 * Agente que executa uma busca informada usando melhor escolha. 
 * 
 * Esse agente utiliza uma função heurística descrita como:
 * 
 * H1: Dado um problema de tamanho N, bastam no máximo X movimentos para alcançar a meta.
 * 
 */
public class GBFSAgent extends GraphAgent implements Comparator<GameState> {

	private Heuristic<?, ?> heuristic;

	public GBFSAgent(GameState initialState, int problemSize, Heuristic<?, ?> heuristic) {
		super(initialState, problemSize);

		this.heuristic = heuristic;
		
		// instancia a borda como uma fila de prioridade segundo a heuristica
		this.border = new PriorityQueueBorder(this);
	}

	// utiliza o valor da heuristica para comparar os estados
	@Override
	public int compare(GameState gs1, GameState gs2) {
		
		heuristic.get(gs1.getId());
		
		if(gs1.getCoast() < gs2.getCoast()) return 1;
		if(gs1.getCoast() > gs2.getCoast()) return -1;
		return 0;
	}
}
