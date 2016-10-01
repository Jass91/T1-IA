package util.puzzle.ia.t1.ufscar;

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
		
		GameState state = null;

		if(elements.size() > 0){
			state = elements.get(elements.size() - 1);
			elements.remove(elements.size() - 1);
		}

		return state;
	}

}
