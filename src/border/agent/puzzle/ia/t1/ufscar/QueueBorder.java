package border.agent.puzzle.ia.t1.ufscar;

import java.util.NoSuchElementException;

import game.puzzle.ia.t1.ufscar.SearchNode;

/*
 * Representa a borda em forma de uma fila,
 * resultando em uma busca em largura.
 * 
 */
public class QueueBorder extends Border {

	public QueueBorder() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void add(SearchNode state) {
		elements.add(state);
	}

	@Override
	public SearchNode get(){
		
		if(elements.size() == 0){
	       throw new NoSuchElementException();
		}
		
		SearchNode state = elements.get(0);
		elements.remove(0);

		return state;
	}
}
