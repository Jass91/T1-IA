package agent.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.SearchNode;
import general.agent.puzzle.ia.t1.ufscar.GuidedGraphAgent;
import heuristic.puzzle.ia.t1.ufscar.Heuristic;

public class AStarAgent extends GuidedGraphAgent{

	public AStarAgent(SearchNode initialNode, int problemSize, Heuristic heuristic) {
		super(initialNode, problemSize, heuristic);
	}

	/*
	 * h(n): avalicao heuristica para o no n;
	 * g(n): custo real do caminho indo do estado inicial ate o no n;
	 * f(n) funcao de avaliacao que determina a prioridade dos nos na borda, onde:
	 * 
	 * f(n) = h(n) + g(n)
	 * 
	 */
	@Override
	public int compare(SearchNode node1, SearchNode node2) {

		// obtem os valores heuristicos para os nos
		// e soma com o custo real ate o no
		int val1 = h.getValueTo(node1) + node1.getCoast();
		int val2 = h.getValueTo(node2) + node2.getCoast();

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
