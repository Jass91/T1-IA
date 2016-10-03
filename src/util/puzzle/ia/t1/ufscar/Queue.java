package util.puzzle.ia.t1.ufscar;

import java.util.NoSuchElementException;

import game.puzzle.ia.t1.ufscar.GameState;

public class Queue extends Border {

	public Queue() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void add(GameState state) {
		elements.add(state);
	}

	@Override
	public GameState get(){
		
		if(elements.size() == 0){
	       throw new NoSuchElementException();
		}
		
		GameState state = elements.get(0);
		elements.remove(0);

		return state;
	}
}
