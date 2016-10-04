package heuristic.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.SearchNode;

/*
 * H0 (CONFIRMAR):
 * relaxando a regra, vamos supor que os blocos podem pular
 * de qualquer posicao para a posicao vazia.
 *
 * Vamos supor tambem que para qualquer estado
 * saibamos a distancia de cada peca Xi em relacao ao espaço vazio Ev,
 * calculada como abs(posicao(Xi) - posicao(Ev)).
 * 
 * Consideremos nossa funcao heuristica h(n) descrita como:
 * A soma das distancias Di de cada peça Xi em relacao a Ev
 * entao:
 * 
 * 	h(n) = D1 + D2 + ... + DN, onde N = 2n + 1;
 * 
 * Dessa forma, se um nó possui h(n) < h(n'), então n é eleito a expansão.
 * 
 * h(n) nesse contexto significa escolher os nos com menor custo de movimentacao.
 * 
 */

/*
 * H1:
 *
 * Vamos supor que para qualquer estado E,
 * saibamos a o numero exato de movimentos legais (Xe) para E
 * calculado como: 
 * 
 * Xe = (Pv - Li) + (Ls - Pv), onde:
 * 
 * Pv eh a posicao do vazio;
 * 
 * Li eh o limite inferior do vetor, tal que:
 * 
 * 		Li = 0 se (Pv - N) < 0;
 * 		LI = (Pv - N) caso contrario;
 * 
 * Ls eh o limite superior do vetor, tal que:
 * 
 * 		Ls = (2N + 1) se (Pv + N) > (2N + 1);
 * 		Ls = (Pv + N) caso contrario;
 * 
 * Consideremos nossa funcao heuristica h(n) descrita como:
 * 
 * 	h(n) = Xe;
 * 
 * Dessa forma, se h(n) > h(n'), então n é eleito a expansão.
 * 
 * h(n) nesse contexto significa escolher o no com maior possibilidade de movimentacao.
 * 
 */
public class HeuristicOne extends Heuristic<String, Integer> {

	private int n;

	public HeuristicOne(int problemSize) {
		this.n = problemSize;
		//this.he
	}


	private int calculateHeuristicTo(SearchNode state){

		int maxPos = (n << 1);

		int pv = state.getEmptyPosition();
		int li = (pv - n < 0) ? 0 : pv - n;
		int ls = (pv + n > maxPos) ? maxPos : pv + n;

		int res = (pv - li) + (ls - pv);

		return res;
	}


	@Override
	public Integer getValueTo(SearchNode node) {

		// se já temos computado esse valor, apenas retornamos
		Integer val = values.get(node.getId());
		if(val != null)
			return val;

		// senao, calculamos
		val = calculateHeuristicTo(node);

		// armazenamos
		values.put(node.getId(), val);

		// e retornamos o valor para esse estado
		return val;

	}

}
