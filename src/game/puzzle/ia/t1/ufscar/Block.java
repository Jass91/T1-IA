package game.puzzle.ia.t1.ufscar;

public class Block {

	public enum Type {White, Blue, Empty};
	
	private Type type;
	private int id;
	
	public Block(Type color, int id){
		this.type = color;
		this.id = id;
	}
	
	public void setType(Type type){
		this.type = type;
	}
	
	public Type getType(){
		return this.type;
	}
	
	public int getId(){
		return this.id;
	}
	
}
