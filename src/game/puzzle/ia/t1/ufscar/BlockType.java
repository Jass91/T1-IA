package game.puzzle.ia.t1.ufscar;

public enum BlockType {

	White(1), Blue(2), Empty(3);

	private final int valor;
	BlockType(int valorOpcao){
		valor = valorOpcao;
	}
	
	public int getValue(){
		return valor;
	}

}
