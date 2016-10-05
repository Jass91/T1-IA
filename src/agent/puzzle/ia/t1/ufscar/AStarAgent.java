package agent.puzzle.ia.t1.ufscar;

import java.util.Comparator;

import border.agent.puzzle.ia.t1.ufscar.PriorityQueueBorder;
import game.puzzle.ia.t1.ufscar.SearchNode;
import general.agent.puzzle.ia.t1.ufscar.GraphAgent;

public class AStarAgent extends GraphAgent implements Comparator<SearchNode>{

	public AStarAgent(SearchNode initialNode, int problemSize) {
		super(initialNode, problemSize);

		// instancia a borda como uma fila de prioridade
		this.border = new PriorityQueueBorder(this);
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
		// TODO Auto-generated method stub
		return 0;
	}



}
