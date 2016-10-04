package border.agent.puzzle.ia.t1.ufscar;

import java.util.NoSuchElementException;

import game.puzzle.ia.t1.ufscar.SearchNode;

/*
 * Representa a borda em forma de uma pilha,
 * resultando em uma busca em profundidade.
 * 
 */
public class StackBorder extends Border {

	public StackBorder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(SearchNode state) {
		elements.add(state);
	}

	@Override
	public SearchNode get() {
		
		if(elements.size() == 0){
	       throw new NoSuchElementException();
		}
		
		SearchNode state = elements.get(elements.size() - 1);
		elements.remove(elements.size() - 1);
		
		return state;
	}

}
