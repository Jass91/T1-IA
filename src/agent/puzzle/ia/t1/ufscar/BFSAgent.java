package agent.puzzle.ia.t1.ufscar;

import border.agent.puzzle.ia.t1.ufscar.QueueBorder;
import game.puzzle.ia.t1.ufscar.GameState;
import general.agent.puzzle.ia.t1.ufscar.GraphAgent;

// agente que executa a busca em largura
public class BFSAgent extends GraphAgent {

	public BFSAgent(GameState initialState, int problemSize) {
		super(initialState, problemSize);

		// inicializa a borda como uma fila
		this.border = new QueueBorder();
	}

}
