package JaRB;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
public class entity {
	
	
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected float total_width;
	protected float total_height;
	protected Texture texture;
	protected boolean visible;

	public entity(float x, float y, float width, float height, String texturePath, boolean visible) {
		this.x = x;
		this.y = y;
		total_width = width;
		total_height = height;
		this.width = total_width*start.zoom;
		this.height = total_height*start.zoom;
		this.visible = visible;
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(texturePath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	void update() {
		width = total_width*start.zoom;
		height = total_height*start.zoom;
	}
	
	public void destroy() {
		//destroy stuff
	}
	
}
