package general.agent.puzzle.ia.t1.ufscar;

import java.util.HashMap;

import game.puzzle.ia.t1.ufscar.SearchNode;

public abstract class GraphAgent extends Agent {

	// armazena como chave o id do no e o valor como o id do no"
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

		// se o no ja foi explorado entao eh ignorado
		if(nodeStatus.get(node.getId()) == "Explored"){
			return;
		}

		int emptyPos = node.getEmptyPosition();
		int n = (2 * problemSize) + 1;

		// gera todos os filhos do no atual
		for(int i = 0; i < n; i++){

			if(i == emptyPos)
				continue;

			// custo do movimento
			int distance = Math.abs(i - emptyPos);

			// se o movimento eh legal
			if(distance <= problemSize){

				// retorna um novo no movendo-se de i para emptyPos a partir do no atual
				SearchNode newNode = move(node, i, emptyPos);

				// incrementa o numero de nos gerados
				numberOfGeneratedNodes++;

				// se ainda nao foi gerado esse no
				if(canAddNodeToBorder(newNode)){

					// insere o novo no na borda
					border.add(newNode);

					// marca como já gerado
					nodeStatus.put(newNode.getId(), newNode.getId());
				}
			}
		}
	}
}
