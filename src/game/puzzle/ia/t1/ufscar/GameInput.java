package game.puzzle.ia.t1.ufscar;

import java.io.IOException;
import java.util.Scanner;

public class GameInput {

	private int problemSize;
	private String initialConfig;
	private String agentType;
	private int maxLimit;
	
	public GameInput() {
		// TODO Auto-generated constructor stub
	}

	public int getMaxLimit() {
		return maxLimit;
	}


	public void setMaxLimit(int maxLimit) {
		this.maxLimit = maxLimit;
	}
	
	public int getProblemSize() {
		return problemSize;
	}


	public void setProblemSize(int problemSize) {
		this.problemSize = problemSize;
	}


	public String getInitialConfig() {
		return initialConfig;
	}


	public void setInitialConfig(String initialConfig) {
		this.initialConfig = initialConfig;
	}


	public String getAgentType() {
		return agentType;
	}


	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}


	public void Read() throws IOException{
		
		Scanner scanner = new Scanner(System.in);
		
		//System.out.print("Tamanho do problema: ");
		problemSize = scanner.nextInt();
		
		//System.out.println("Estado inicial: ");
		initialConfig = scanner.next();
		
		//System.out.print("Tipo de busca (BL, BP, BPL, BPI, BCU, A*): ");
		agentType = scanner.next();
		
		if(agentType == "BPL" || agentType == "BPI"){
			System.out.print("Limite maximo: ");
			maxLimit = scanner.nextInt();
		}
		scanner.close();
	}
}