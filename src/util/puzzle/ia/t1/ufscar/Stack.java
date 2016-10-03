package util.puzzle.ia.t1.ufscar;

import java.util.NoSuchElementException;

import game.puzzle.ia.t1.ufscar.GameState;

public class Stack extends Border {

	public Stack() {
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
