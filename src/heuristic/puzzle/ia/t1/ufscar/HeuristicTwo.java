package heuristic.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.SearchNode;

/*
 * H2:
 *
 * Vamos supor que para qualquer estado E,
 * saibamos a quantidade Dx de pecas azuis a esquerda de alguma peca branca (numero de pecas em posicao errada)
 * 
 * Consideremos nossa funcao heuristica h(n) descrita como:
 *
 * 	h(n) = Dx;
 * 
 * Dessa forma, se um n� possui h(n) < h(n'), ent�o n � eleito a expans�o.
 * 
 * h(n) nesse contexto significa escolher os nos com menor numero de pecas em posicoes erradas.
 * 
 */
public class HeuristicTwo extends Heuristic<String, Integer> {

	public HeuristicTwo() {
	
	}
	
	@Override
	public Integer getValueTo(SearchNode state) {
		
		return null;
	}

}
