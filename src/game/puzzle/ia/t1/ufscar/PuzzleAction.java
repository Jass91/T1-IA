package game.puzzle.ia.t1.ufscar;

import general.search.agent.ia.Action;

public class PuzzleAction extends Action {

	private int src;
	private int dst;
	
	public PuzzleAction(int coast, int src, int dst) {
		super(coast);
		this.src = src;
		this.dst = dst;
	}

	
	@Override
	public void showMovement() {
		System.out.print("Mova o bloco em R[" + src + "] para R[" + dst + "] (custo = " + coast + ")");
	}


	public int getSrc() {
		return src;
	}


	public void setSrc(int src) {
		this.src = src;
	}


	public int getDst() {
		return dst;
	}


	public void setDst(int dst) {
		this.dst = dst;
	}

}
