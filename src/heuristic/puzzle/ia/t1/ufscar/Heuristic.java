package heuristic.puzzle.ia.t1.ufscar;

import java.util.HashMap;

import game.puzzle.ia.t1.ufscar.GameState;

public abstract class Heuristic<K, V> extends HashMap<K, V> {
	
	private static final long serialVersionUID = 1L;

	// retorna o valor da heuristica para um dado estado
	public abstract int getHeuristicValueTo(GameState state);
}
