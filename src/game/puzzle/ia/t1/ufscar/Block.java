package game.puzzle.ia.t1.ufscar;


public class Block {

	private BlockType type;
	private int id;
	
	public Block(BlockType color, int id){
		this.type = color;
		this.id = id;
	}
	
	public void setType(BlockType type){
		this.type = type;
	}
	
	public BlockType getType(){
		return this.type;
	}
	
	public char getTypeAsChar(){
		return this.type.getValueAsChar();
	}
	
	public int getId(){
		return this.id;
	}
	
}
