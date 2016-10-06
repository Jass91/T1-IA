package game.puzzle.ia.t1.ufscar;

import general.search.agent.ia.State;

public class PuzzleState extends State{

	private Block[] state;
	private int emptyPosition;

	public PuzzleState(String id, Block[] state, int emptyPosition){
		super(id);
		this.state = state;
		this.emptyPosition = emptyPosition;
	}
	
	public int getEmptyPosition() {
		return emptyPosition;
	}

	public void setEmptyPosition(int emptyPosition) {
		this.emptyPosition = emptyPosition;
	}


	public Block[] getDescription() {
		return state;
	}


	public void setState(Block[] state) {
		this.state = state;
	}

	@Override
	public String toString() {
		String s = new String();
		for(Block b : state){
			s += b.toString();
		}
		
		return s;
	}

}
