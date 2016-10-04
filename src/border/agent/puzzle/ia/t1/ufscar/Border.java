package border.agent.puzzle.ia.t1.ufscar;

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
	
	public void add(GameState newState) {
		elements.add(newState);
	}
	
    //Just print out the underlying array list.
    @Override
    public String toString() {
        return elements.toString();
    }
    
	public abstract GameState get();
}
