package algorithmus;

public class AStarKnoten {

	private AStarKnoten parent;
	private int x;
	private int y;
	private float h; //Abstand zum Ziel
	
	public AStarKnoten(AStarKnoten parent, int x, int y, float h){
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.h = h;
	}

	public AStarKnoten getParent() {
		return parent;
	}

	public void setParent(AStarKnoten parent) {
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}
	
	
	
}
