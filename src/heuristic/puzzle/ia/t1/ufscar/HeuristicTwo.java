package heuristic.puzzle.ia.t1.ufscar;

import game.puzzle.ia.t1.ufscar.Block;
import game.puzzle.ia.t1.ufscar.BlockType;
import game.puzzle.ia.t1.ufscar.SearchNode;

/*
 * H2:
 *
 * Vamos supor que para qualquer estado E,
 * saibamos a quantidade Dx de pecas azuis a esquerda de alguma peca branca,
 * ou seja, Dx eh o numero de pecas em posicoes erradas.
 * 
 * Consideremos nossa funcao heuristica h(n) descrita como:
 *
 * 	h(n) = Dx;
 * 
 * Dessa forma, se um nó possui h(n) < h(n'), então n é eleito a expansao.
 * 
 * h(n) nesse contexto significa escolher os nos com menor numero de pecas em posicoes erradas.
 * 
 */
public class HeuristicTwo extends Heuristic {

	private int n;

	public HeuristicTwo(int problemSize) {
		super();
		this.n = problemSize;
	}

	public HeuristicTwo(int problemSize, boolean isMaxBetter) {
		super(isMaxBetter);
		this.n = problemSize;
	}


	// retorna o numero de blocos em posicoes erradas para o no.
	// executa em tempo linear
	@Override
	protected int calculateValueTo(SearchNode node) {

		int i = 0;
		int j = 0;
		int size = (n << 1) + 1;
		int numberOfWrongBlocks = 0;
		int emptPos = node.getEmptyPosition();
		Block []gameState = node.getGameState();

		while(j < size){
			
			// encontra o limite inferior do subvetor
			while(gameState[i].getType() != BlockType.White)
				i++;

			// se existe blocos azuis antes de encontrar o primeiro bloco branco
			// contabiliza esses azuis
			if(j < i){
				// se o vazio esta contido no intervalo, entao desconta 1
				if(i >= emptPos)
					numberOfWrongBlocks = (i - 1);
				else
					numberOfWrongBlocks = i;
			}

			// encontra o limite superior do subvetor
			j = i + 1;
			while(j < size && gameState[j].getType() != BlockType.White)
				j++;

			// se j atingiu o fim do vetor
			if(j >= size)
				continue;

			// se o vazio esta contido no intervalo, entao desconta 1
			if(i <= emptPos && emptPos <= j )
				numberOfWrongBlocks += (j - i) - 2;
			else
				numberOfWrongBlocks += (j - i) - 1;

			// agora repete a partir de j
			i = j;
		}

		return numberOfWrongBlocks;
	}

}
