package agent.puzzle.ia.t1.ufscar;

import java.util.Comparator;

import border.agent.puzzle.ia.t1.ufscar.PriorityQueueBorder;
import game.puzzle.ia.t1.ufscar.GameState;
import general.agent.puzzle.ia.t1.ufscar.GraphAgent;
import heuristic.puzzle.ia.t1.ufscar.Heuristic;

/*
 * 
 * Agente que executa uma busca informada usando melhor escolha. 
 * Esse agente utiliza uma função heurística dada como entrada:
 * 
 */
public class GBFSAgent extends GraphAgent implements Comparator<GameState> {

	// representa a funcao de avaliacao h(n)
	private Heuristic<String, Integer> h;

	public GBFSAgent(GameState initialState, int problemSize, Heuristic<String, Integer> heuristic) {
		super(initialState, problemSize);

		this.h = heuristic;
		
		// instancia a borda como uma fila de prioridade,
		// cuja a ordem eh dada pela heuristica h(n)
		this.border = new PriorityQueueBorder(this);
	}

	// utiliza o valor de h(n) para comparar os estados w
	// entao montar um Heap de acordo com h(n)
	@Override
	public int compare(GameState gs1, GameState gs2) {
				
		int val1 = (int)h.getValueTo(gs1);
		int val2 = (int)h.getValueTo(gs2);
		
		// monta um heap de maximo
		if(val1 > val2) return 1;
		if(val1 < val2) return -1;
		return 0;
	}
}
