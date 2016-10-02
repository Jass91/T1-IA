package game.puzzle.ia.t1.ufscar;

public class Action {

	private int coast;
	private int srcBlock;
	private int destBlock;
	
	public Action(int coast, int srcBlock, int destBlock){
		this.srcBlock = srcBlock;
		this.destBlock = destBlock;
		this.coast = coast;
	}

	public int getCoast() {
		return coast;
	}

	public void setCoast(int coast) {
		this.coast = coast;
	}

	public int getSrcBlock() {
		return srcBlock;
	}

	public void setSrcBlock(int srcBlock) {
		this.srcBlock = srcBlock;
	}

	public int getDestBlock() {
		return destBlock;
	}

	public void setDestBlock(int destBlock) {
		this.destBlock = destBlock;
	}
	
	public void showMovement(){
		System.out.println("Mova o bloco em R[" + srcBlock + "] para R[" + destBlock + "] com custo = " + coast);
	}
}
