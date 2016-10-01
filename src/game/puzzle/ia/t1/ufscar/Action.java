package game.puzzle.ia.t1.ufscar;

public class Action {

	private int coast;
	private Block srcBlock;
	private Block destBlock;
	
	public Action(int coast, Block srcBlock, Block destBlock){
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

	public Block getSrcBlock() {
		return srcBlock;
	}

	public void setSrcBlock(Block srcBlock) {
		this.srcBlock = srcBlock;
	}

	public Block getDestBlock() {
		return destBlock;
	}

	public void setDestBlock(Block destBlock) {
		this.destBlock = destBlock;
	}
	
	public void showMovement(){
		System.out.println("Move block #" + srcBlock.getId() + " to block #" + destBlock.getId() + " position");
	}
}
