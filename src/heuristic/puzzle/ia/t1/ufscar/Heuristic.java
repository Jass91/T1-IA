package heuristic.puzzle.ia.t1.ufscar;

import java.util.HashMap;

import game.puzzle.ia.t1.ufscar.SearchNode;

public abstract class Heuristic{

	// hash que armazena o valor de h(n) para um dado no
	protected HashMap<String, Integer> values;

	// indica se o criterio de melhor eh o no com maior valor de h(n)
	protected boolean isMaxBetter;

	public Heuristic(){

		this.values = new HashMap<String, Integer>();
		this.isMaxBetter = false;
	}

	public Heuristic(boolean isMaxBetter){

		this.values = new HashMap<String, Integer>();
		this.isMaxBetter = isMaxBetter;
	}

	public boolean isMaxBetter(){
		return isMaxBetter;
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
