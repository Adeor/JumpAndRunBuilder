package JaRB;


//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
public class movableEntity extends entity {
	
	
	protected float dx = 0;
	protected float dy = 0;

	public movableEntity(float x,float y, float width, float height, String texturePath, boolean visible, float dx, float dy) {
		super(x, y, width, height, texturePath, visible);
		this.dx = dx;
		this.dy = dy;
	}
	
@Override
void update() {
	x += dx;
	y += dy;
	super.update();
}

	boolean isInMovment() {
		if (dx != 0 || dy != 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean kollisionsabfrage(float x, float y, float height, float width) {
		//Auf X-Achse innerhalb der MovableEntity
		if (!(this.x > x+width  ||
				x>this.x+this.width)
			) {
			//Auf Y-Achse innerhalb der MovableEntity
			if (!(y+height<this.y ||
					y>this.y+this.height)) {
				return true;
			}
		}
	return false;
	}
	
	void kollision() {
			//kollisions stuff
	}
	
}
