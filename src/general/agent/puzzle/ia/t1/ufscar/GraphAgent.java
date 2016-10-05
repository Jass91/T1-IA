package general.agent.puzzle.ia.t1.ufscar;

import java.util.HashMap;

import game.puzzle.ia.t1.ufscar.SearchNode;

public abstract class GraphAgent extends Agent {

	// armazena como chave o id do no e o valor como o id do no
	// representa o conjunto de nos ja gerados
	protected HashMap<String, String> nodeStatus;

	public GraphAgent(SearchNode initialNode, int problemSize) {
		super(initialNode, problemSize);
		this.nodeStatus = new HashMap<String, String>();
	}

	@Override
	public void expandNode(SearchNode node){
		expandNodeAsGraph(node);
	}

	private boolean canAddNodeToBorder(SearchNode node){
		String nodeId = nodeStatus.get(node.getId());
		if(nodeId == null)
			return true;
		else
			return false;
	}
	// executa a acao de expandir o no, porem desconsidera nos ja gerados
	private void expandNodeAsGraph(SearchNode node) {

		// calcula os limites do vetor
		int n = (problemSize << 1);
		int emptyPos = node.getEmptyPosition();
		int li = (emptyPos - problemSize < 0) ? 0 : emptyPos - problemSize;
		int ls = (emptyPos + problemSize > n) ? n : emptyPos + problemSize;

		// gera todos os filhos do no atual
		// baseado nos movimentos possiveis
		for(int i = li; i <= ls; i++){

			if(i == emptyPos)
				continue;
			
			// retorna um novo no movendo-se de i para emptyPos a partir do no atual
			SearchNode newNode = move(node, i, emptyPos);

			// incrementa o numero de nos gerados
			numberOfGeneratedNodes++;

			// se ainda nao foi gerado esse no
			if(canAddNodeToBorder(newNode)){

				// insere o novo no na borda
				border.add(newNode);

				// marca como ja gerado
				nodeStatus.put(newNode.getId(), newNode.getId());
			}

		}
	}
}
