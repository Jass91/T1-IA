package util.puzzle.ia.t1.ufscar;

import java.util.ArrayList;
import java.util.List;

import game.puzzle.ia.t1.ufscar.GameState;

public abstract class Border {

	protected List<GameState> elements;
	
	public Border() {
		elements = new ArrayList<GameState>();
	}

	public int getSize(){
		return elements.size();
	}
	public abstract void add(GameState state);
	
	public abstract GameState get();
}
