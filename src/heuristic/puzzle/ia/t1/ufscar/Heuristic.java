package heuristic.puzzle.ia.t1.ufscar;

import java.util.HashMap;

import game.puzzle.ia.t1.ufscar.GameState;

public abstract class Heuristic<K, V>{
	
	protected HashMap<K, V> values;
	
	public Heuristic(){
		values = new HashMap<K, V>();
	}
		
	public abstract Integer getValueTo(GameState state);
}
