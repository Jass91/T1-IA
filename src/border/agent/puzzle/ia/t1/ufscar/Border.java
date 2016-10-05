package border.agent.puzzle.ia.t1.ufscar;

import java.util.ArrayList;
import java.util.List;

import game.puzzle.ia.t1.ufscar.SearchNode;

public abstract class Border {

	protected List<SearchNode> elements;
	
	public Border() {
		elements = new ArrayList<SearchNode>();
	}

	public int getSize(){
		return elements.size();
	}
	
	public void add(SearchNode newState) {
		elements.add(newState);
	}
	
    //Just print out the underlying array list.
    @Override
    public String toString() {
    	return elements.toString();
    }
    
	public abstract SearchNode get();
}
