package agent.puzzle.ia.t1.ufscar;

import java.util.Comparator;

import border.agent.puzzle.ia.t1.ufscar.PriorityQueueBorder;
import game.puzzle.ia.t1.ufscar.SearchNode;
import general.agent.puzzle.ia.t1.ufscar.GraphAgent;
import heuristic.puzzle.ia.t1.ufscar.Heuristic;

/*
 * 
 * Agente que executa uma busca informada usando melhor escolha. 
 * Esse agente utiliza uma funcao heuristica dada como entrada:
 * 
 */
public class GBFSAgent extends GraphAgent implements Comparator<SearchNode> {

	// representa a funcao de avaliacao h(n)
	private Heuristic h;

	public GBFSAgent(SearchNode initialState, int problemSize, Heuristic heuristic) {
		super(initialState, problemSize);

		this.h = heuristic;

		// instancia a borda como uma fila de prioridade,
		// cuja a ordem eh dada pela funcao de comparacao
		this.border = new PriorityQueueBorder(this);
	}

	// utiliza o valor de h(n) e a definicao do que eh melhor (max(h(n)) ou min(h(n)))
	// para determinar a ordem dos nos na borda
	@Override
	public int compare(SearchNode node1, SearchNode node2) {

		// obtem os valores heuristicos para os nos
		int val1 = h.getValueTo(node1);
		int val2 = h.getValueTo(node2);

		// monta um heap de maximo
		// se o melhor no eh o de maior valor h(n),
		// retorna o valor da comparacao
		// de forma que esse no va para o topo do heap
		if(h.isMaxBetter()){

			// node1 tem maior prioridade que node2
			if(val1 > val2) return -1;

			// node2 tem maior prioridade que node1
			if(val1 < val2) return 1;

			// se tem a mesma prioridade
			return 0;
		}

		// monta um heap de minimo
		// se o melhor no eh o de menor valor h(n),
		// retorna o valor da comparacao
		// de forma que esse no va para o topo do heap

		// node1 tem maior prioridade que node2
		if(val1 < val2) return -1;

		// node2 tem maior prioridade que node1
		if(val1 > val2) return 1;

		// se tem a mesma prioridade
		return 0;

	}
}
