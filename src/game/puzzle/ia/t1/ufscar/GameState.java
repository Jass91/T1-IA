package game.puzzle.ia.t1.ufscar;

public class GameState {

	private GameState parent;
	private Block[] gameConfig;
	private Action action;
	private String id;
	private int emptyPosition;
	private int depth;
	private int coast;
	
	public GameState(Block[] gameConfig, int emptyPosition, GameState parent, Action action){
		this.parent = parent;
		this.gameConfig = gameConfig;
		this.action = action;
		this.emptyPosition = emptyPosition;
		
		setDepth();
		setId();
		setCoastToGetHere();
	}

	private void setCoastToGetHere() {
		if(parent == null)
			this.coast = 0;
		else{
			this.coast = parent.getCoastToGetHere() + action.getCoast();
		}
		
	}

	public int getCoastToGetHere() {
		return coast;
	}

	private void setDepth(){
		if(parent == null)
			this.depth = 0;
		else
			this.depth = parent.getDepth() + 1;
	}
	
	public int getDepth() {
		return depth;
	}

	private void setId(){
		String id = "";
		for(Block block : gameConfig){
			int type = block.getType().getValue(); 
			id += type;
		}
		
		this.id = id;
	}
	
	public GameState getParent() {
		return parent;
	}

	public void setParent(GameState parent) {
		this.parent = parent;
	}

	public Block[] getGameConfig() {
		return gameConfig;
	}

	public void setGameConfig(Block[] gameConfig) {
		this.gameConfig = gameConfig;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public int getEmptyPosition() {
		return emptyPosition;
	}

	public void setEmptyPosition(int emptyPosition) {
		this.emptyPosition = emptyPosition;
	}
	
	public String getId(){
		return id;
	}

}
