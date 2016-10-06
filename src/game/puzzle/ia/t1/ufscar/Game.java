package game.puzzle.ia.t1.ufscar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import general.search.agent.ia.Agent;
import general.search.agent.ia.DomainRules;
import general.search.agent.ia.SearchNode;
import seach.agent.ia.AStarAgent;
import seach.agent.ia.BFSAgent;
import seach.agent.ia.DFSAgent;
import seach.agent.ia.GBFSAgent;
import seach.agent.ia.IDFSAgent;
import seach.agent.ia.LDFSAgent;
import seach.agent.ia.UCFSAgent;

public class Game {


	public Game(){

	}

	public static void main(String[] args) {

		// resolve e mostra a solucao
		try {

			System.out.println("**** Puzzle Resolver ****");

			Game game = new Game();

			// simula os dados automaticamente
			//game.GenerateInputFile(4);

			// le os dados de entrada
			GameInput gameInput = game.readInput();

			game.resolve(gameInput);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void GenerateInputFile(int problemSize){

		int n = 2 * problemSize + 1;
		char []initialState = new char[n];

		initialState[0] = '-';

		for(int i = 1; i <= problemSize; i++){
			initialState[i] = 'B';
		}

		for(int i = problemSize + 1; i < n; i++){
			initialState[i] = 'A';
		}

		// embaralha trocando a posicao i com j
		Random r = new Random();
		for(int i = 0; i < n; i++){

			int j = r.nextInt(n);

			char temp = initialState[i];
			initialState[i] = initialState[j];
			initialState[j] = temp;

		}

		String state = new String(initialState);
		int maxLimit = r.nextInt(10);

		List<String> agents = new LinkedList<String>();
		agents.add("BL");
		agents.add("BP");
		agents.add("BPL");
		agents.add("BPI");
		agents.add("BCU");
		agents.add("H1A*");
		agents.add("H2A*");
		agents.add("GBFS1");
		agents.add("GBFS2");

		try {

			String sys = System.getProperty("user.home");

			String fileurl = sys + "\\Desktop\\puzzle_in.txt";
			File f = new File(fileurl);
			PrintWriter pw;
			pw = new PrintWriter(f);

			for(String agent : agents){
				pw.println(problemSize);
				pw.println(state);
				pw.println(agent);
				if(agent.equals("BPL") || agent.equals("BPI"))
				{
					pw.println(maxLimit);
				}
			}

			pw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public GameInput readInput(){

		try {
			GameInput gameInput = new GameInput();
			gameInput.read();

			return gameInput;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void resolve(GameInput gameInput) throws Exception{

		if(gameInput == null)
			throw new Exception("Voce precisa ler os dados de entrada antes de resolver o problema");

		// nosso agente
		Agent agent = null;

		// define regras para esse domimio especifico
		DomainRules puzzleRule = new PuzzleRules(gameInput.getInitialConfig(), gameInput.getProblemSize());

		// criar o agent escolhido
		if(gameInput.getAgentType().equals("BL")){

			System.out.println("*** Busca em largura ***");
			agent = new BFSAgent(puzzleRule);

		}else if(gameInput.getAgentType().equals("BP")){

			System.out.println("*** Busca em profundidade ***");
			agent = new DFSAgent(puzzleRule);

		}else if(gameInput.getAgentType().equals("BCU")){

			System.out.println("*** Busca de custo uniforme ***");
			agent = new UCFSAgent(puzzleRule);

		}else if(gameInput.getAgentType().equals("BPL")){

			System.out.println("*** Busca em profundidade limitada ***");
			agent = new LDFSAgent(puzzleRule, gameInput.getMaxLimit());

		}else if(gameInput.getAgentType().equals("BPI")){

			System.out.println("*** Busca em profundidade iterativa ***");
			agent = new IDFSAgent(puzzleRule, gameInput.getMaxLimit());

		}else if(gameInput.getAgentType().equals("H1A*")){

			System.out.println("*** Busca A* usando H1 ***");
			agent = new AStarAgent(puzzleRule, new HeuristicOne(gameInput.getProblemSize()));

		}else if(gameInput.getAgentType().equals("H2A*")){

			System.out.println("*** Busca A* usando H2 ***");
			agent = new AStarAgent(puzzleRule, new HeuristicTwo(gameInput.getProblemSize()));

			// executa o agente de busca guiada com a heuristica 1
		}else if(gameInput.getAgentType().equals("GBFS1")){

			System.out.println("*** Busca de melhor escolha usando H1 ***");

			// passando true para a heuristica,
			// faz com que a fila de prioridade seja um heap de maximo,
			// onde valores maiores de h(n) tem maior prioridade
			agent = new GBFSAgent(puzzleRule, new HeuristicOne(gameInput.getProblemSize()));
		}
		// executa o agente de busca guiada com a heuristica 2
		else if(gameInput.getAgentType().equals("GBFS2")){

			System.out.println("*** Busca de melhor escolha usando H2 ***");

			// a omissao do parametro booleano ou indicar um valor falso para a heuristica,
			// faz com que a fila de prioridade seja um heap de minimo,
			// onde valores menores de h(n) tem maior prioridade
			agent = new GBFSAgent(puzzleRule, new HeuristicTwo(gameInput.getProblemSize()));
		}else{
			System.out.println("Invalid agent type");
			System.exit(1);
		}

		// executa o agente escolhido
		agent.resolve();

		// mostra os dados
		System.out.println();
		System.out.println("Tamanho do problema: " + gameInput.getProblemSize());
		System.out.println("Estados Gerados: " + agent.getNumberOfGeneratedNodes());
		System.out.println("Estados Explorados: " + agent.getNumberOfExploredNodes());
		System.out.println("Fator de ramificacao medio: " + agent.getAverageBranchingFactor());
		System.out.println("Custo da Solucao: " + agent.getSolutionCoast());
		System.out.println("Profundidade da Solucao: " + agent.getDepthOfSolution());

		if(agent.getGoalNode() == null){
			System.out.println();
			System.out.println("Nao encontrou solucao");
		}else{

			System.out.print("Solucao encontrada: ");

			// converte um no para um estado do jogo
			showGoalState(agent.getGoalNode());

			System.out.println();
			System.out.println();

			// mostra a sequencia de acoes
			agent.tellSolution();
		}

	}

	// exibe o estado meta
	private void showGoalState(SearchNode goalState){

		PuzzleState state = (PuzzleState) goalState.getState();
		for(Block block : state.getDescription()){
			BlockType type = block.getType();
			if(type == BlockType.White){
				System.out.print("B");
			}else if(type == BlockType.Blue){
				System.out.print("A");
			}else if(type == BlockType.Empty){
				System.out.print("-");
			}
		}
	}

}
