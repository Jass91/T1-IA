package game.puzzle.ia.t1.ufscar;

import java.util.ArrayList;
import java.util.List;

import general.search.agent.ia.Action;
import general.search.agent.ia.DomainRules;
import general.search.agent.ia.SearchNode;
import general.search.agent.ia.State;
import general.search.agent.ia.StateAction;

public class PuzzleRules implements DomainRules {

	private int problemSize;
	private String initialConfig;

	public PuzzleRules(String initialConfig, int problemSize) {
		super();

		this.initialConfig = initialConfig;
		this.problemSize = problemSize;
	}


	@Override
	public boolean isGoal(SearchNode node){

		Block[] gameState = ((PuzzleState)node.getState()).getDescription();
		int n = (problemSize << 1) + 1;

		for(int i = 1; i < n; i++){

			// se nao eh objetivo
			if( (gameState[i - 1].getType() == BlockType.Blue) &&
					(gameState[i].getType() == BlockType.White)){

				return false;

				// outro caso que nao eh objetivo
			}else if((i >= 2)){

				if( (gameState[i - 1].getType() == BlockType.Empty) &&
						(gameState[i - 2].getType() == BlockType.Blue) &&
						(gameState[i].getType() == BlockType.White)){

					return false;
				}

			}
		}

		return true;
	}

	@Override
	public List<StateAction>getChildrenOf(SearchNode node) {

		List<StateAction> children = new ArrayList<StateAction>();

		// calcula os limites do vetor
		int n = (problemSize << 1);
		int emptyPos = ((PuzzleState)node.getState()).getEmptyPosition();
		int li = (emptyPos - problemSize < 0) ? 0 : emptyPos - problemSize;
		int ls = (emptyPos + problemSize > n) ? n : emptyPos + problemSize;

		// gera todos os filhos do no atual
		// baseado nos movimentos possiveis
		for(int i = li; i <= ls; i++){

			if(i == emptyPos)
				continue;

			// retorna um novo no movendo-se de i para emptyPos a partir do no atual
			StateAction newStateAction = move(node, i, emptyPos);

			children.add(newStateAction);
		}

		return children;
	}


	// executa a acao (troca src com dst), resultando em um novo no
	private StateAction move(SearchNode node, int src, int dst) {

		Block[] newGameState = new Block[(problemSize << 1) + 1];
		Block[] currentGameState = ((PuzzleState)node.getState()).getDescription();

		// copia os valores
		for(int j = 0; j < currentGameState.length; j++)
			newGameState[j] = currentGameState[j];

		// custo do movimento
		int coast = Math.abs(src - dst);

		// executa a ação trocando os blocos
		newGameState[dst] = currentGameState[src];
		newGameState[src] = currentGameState[dst];

		// cria o novo estado
		PuzzleState state = new PuzzleState(generateIdFor(newGameState), newGameState, src);
		

		// cria a ação necessaria para esse novo estado
		Action action = new PuzzleAction(coast, src, dst);

		// diz que para chegar nesse estado foi necessario essa acao
		StateAction stateAction = new StateAction(state, action);

		return stateAction;

	}

	// define o estado inicial
	@Override
	public State getInitialState() {

		// configuracao inicial
		int i = 0;
		int emptyPos = 0;

		Block[] initialGameState = new Block[(problemSize << 1) + 1];
		for(char c : initialConfig.toCharArray()){
			if(c == 'B'){
				Block block = new Block(BlockType.White, i);
				initialGameState[i] = block;
			}else if(c == 'A'){
				Block block = new Block(BlockType.Blue, i);
				initialGameState[i] = block;
			}else if(c == '-'){
				Block block = new Block(BlockType.Empty, i);
				initialGameState[i] = block;
				emptyPos = i;
			}

			i++;
		}


		return new PuzzleState(generateIdFor(initialGameState), initialGameState, emptyPos);

	}


	private String generateIdFor(Block[] state) {
		String id = "";
		for(Block block : state){
			char type = block.getType().getValueAsChar(); 
			id += type;
		}

		return id;
	}

}
