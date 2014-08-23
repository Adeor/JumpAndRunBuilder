package JaRB;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Unit extends movableEntity {

	int LP = 100;
	unitType uT;
	Texture texture;
	int jumpCounter;
	
	public Unit(float x,float y, float width, float height, boolean visible, float dx, float dy, unitType uT) {
		super(x, y, width, height, uT.locationMid, visible, dx, dy);
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(uT.locationMid)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.uT = uT;
	}
	
	void nachRechts() {
		if (dx < 0) {
			dx = 0;
			try {
				texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(uT.locationMid)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			dx = 3;
			try {
				texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(uT.locationRight)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void nachLinks() {
		if (dx > 0) {
			dx = 0;
			try {
				texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(uT.locationMid)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			dx = -3;
			try {
				texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(uT.locationLeft)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void jump() {
		jumpCounter = 20;
	}
	
	@Override
	public void draw() {
		texture.bind();
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
        glPushMatrix();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        	glTexCoord2f(0, 0);
        	glVertex2f(0, 0);
        	glTexCoord2f(1, 0);
        	glVertex2f(width, 0);
        	glTexCoord2f(1, 1);
        	glVertex2f(width, height);
        	glTexCoord2f(0, 1);
        	glVertex2f(0, height);
        glEnd();
        glPopMatrix();
	}
	
	@Override
	boolean kollisionsabfrage(float x, float y, float height, float width) {
		//Auf X-Achse innerhalb der MovableEntity
		if (!(this.x > x+width ||x>this.x+this.width) && dx > 0) 
			dx = 0;
		{
			//Auf Y-Achse innerhalb der MovableEntity
			if (!(y+height<this.y ||
					y>this.y+this.height)) {
				return true;
			}
		}
		if (!(this.x > x+width ||x>this.x+this.width) && dx < 0) 
			dx = 0;
		{
			//Auf Y-Achse innerhalb der MovableEntity
			if (!(y+height<this.y ||
					y>this.y+this.height)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void update() {
		super.update();
		if (jumpCounter > 0) {
			dy = -3;
			--jumpCounter;
		}
	}
	
}
