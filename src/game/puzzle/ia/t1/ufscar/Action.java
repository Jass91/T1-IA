package game.puzzle.ia.t1.ufscar;

public class Action {

	private int coast;
	private int srcPos;
	private int dstPos;
	
	public Action(int coast, int srcBlock, int destBlock){
		this.srcPos = srcBlock;
		this.dstPos = destBlock;
		this.coast = coast;
	}

	public int getCoast() {
		return coast;
	}

	public void setCoast(int coast) {
		this.coast = coast;
	}

	public int getSoucePosition() {
		return srcPos;
	}

	public void setSoucePosition(int srcPos) {
		this.srcPos = srcPos;
	}

	public int getDestinationPosition() {
		return dstPos;
	}

	public void setDestinationPosition(int dstPos) {
		this.dstPos = dstPos;
	}
	
	public void showMovement(){
		System.out.print("Mova o bloco em R[" + srcPos + "] para R[" + dstPos + "] (custo = " + coast + ")");
	}
}
