package heuristic.puzzle.ia.t1.ufscar;

import java.util.HashMap;

import game.puzzle.ia.t1.ufscar.SearchNode;

public abstract class Heuristic{
	
	protected HashMap<String, Integer> values;
	
	public Heuristic(){
		values = new HashMap<String, Integer>();
	}
	
		
	public int getValueTo(SearchNode state){
		
		Integer value = values.get(state.getId());
		
		if(value != null)
			return value;
		
		value = calculateValueTo(state);
		
		// armazena o que foi computado
		values.put(state.getId(), value);
		
		return value;
	};
	
	protected abstract int calculateValueTo(SearchNode state);
}
