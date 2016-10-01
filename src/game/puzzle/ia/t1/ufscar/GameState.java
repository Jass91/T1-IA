package game.puzzle.ia.t1.ufscar;

public class GameState {

	private GameState parent;
	private Block[] gameConfig;
	private Action action;
	private String id;
	private int emptyPosition;
	
	public GameState(GameState parent, Block[] gameConfig, Action action, int emptyPosition){
		this.parent = parent;
		this.gameConfig = gameConfig;
		this.action = action;
		this.emptyPosition = emptyPosition;
		
		setId();
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
