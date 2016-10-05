package heuristic.puzzle.ia.t1.ufscar;

import java.util.HashMap;

import game.puzzle.ia.t1.ufscar.SearchNode;

public abstract class Heuristic{
	
	protected HashMap<String, Integer> values;
	
	public Heuristic(){
		values = new HashMap<String, Integer>();
	}
	
		
	public int getValueTo(SearchNode n){
		
		// procura o valor de h(n) no hash
		Integer value = values.get(n.getId());
		
		// se conseguiu, retorna o valor
		if(value != null)
			return value;
		
		// senao, calcula h(n)
		value = calculateValueTo(n);
		
		// armazena o que foi computado e
		values.put(n.getId(), value);
		
		// retorna o valor
		return value;
	};
	
	protected abstract int calculateValueTo(SearchNode state);
}
