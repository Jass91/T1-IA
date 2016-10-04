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
		this.depth = setDepth();
		this.id = generateId();
		this.coast = getCoastToGetHere();
	}

	private int getCoastToGetHere() {
		if(parent == null)
			return 0;
		else{
			return (parent.getCoast() + action.getCoast());
		}

	}

	public int getCoast() {
		return coast;
	}

	private int setDepth(){
		if(parent == null)
			return 0;
		else
			return parent.getDepth() + 1;
	}

	public int getDepth() {
		return depth;
	}

	private String generateId(){
		
		String id = "";
		for(Block block : gameConfig){
			char type = block.getType().getValueAsChar(); 
			id += type;
		}
		
		return id;
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
		return new String(id);
	}

	@Override
	public String toString(){
		return id;
	}
}
