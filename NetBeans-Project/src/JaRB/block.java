package JaRB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.newdawn.slick.opengl.TextureLoader;

public class block extends entity implements commons{

	private blockType bT;
	/*boolean brennt;
	boolean brennbar;
	int hoerte;
	int stabilitaet;*/
	
	public block(int x, int y, boolean visible, blockType bT) {
		super(x, y, BLOCK_SIZE*start.zoom, BLOCK_SIZE*start.zoom, bT.location, visible);
		this.setBlockType(bT);
	}
	

	public void setBlockType(blockType bT){
		this.bT = bT;
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(bT.location)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public blockType getBlockType() {
		return bT;
	}
	
}
