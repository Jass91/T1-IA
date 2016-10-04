package border.agent.puzzle.ia.t1.ufscar;

import java.util.NoSuchElementException;

import game.puzzle.ia.t1.ufscar.GameState;

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
	public void add(GameState state) {
		elements.add(state);
	}

	@Override
	public GameState get() {
		
		if(elements.size() == 0){
	       throw new NoSuchElementException();
		}
		
		GameState state = elements.get(elements.size() - 1);
		elements.remove(elements.size() - 1);
		
		return state;
	}

}
