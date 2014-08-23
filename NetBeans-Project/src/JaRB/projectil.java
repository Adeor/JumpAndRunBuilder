package JaRB;

import org.newdawn.slick.opengl.Texture;

//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
public class projectil extends movableEntity implements commons{

	projektileType pT;
	Texture texture;
	
	public projectil(float x,float y, float width, float height, boolean visible, float dx, float dy, projektileType pT) {
		super(x, y, width, height, pT.location, visible, dx, dy);
		this.pT = pT;
	}
	
	@Override
	public void destroy() {
		start.projektile.remove(this);
	}
	
	void kollision(int i, int j) {
		if (pT == projektileType.FIREBALL) {
			try {
				if (start.grid.getBlocksAt(i, j).getBlockType() == blockType.GRAS) {
					start.grid.getBlocksAt(i, j).setBlockType(blockType.DIRT);
				}
				if (start.grid.getBlocksAt(i+1, j).getBlockType() != blockType.AIR  && start.grid.getBlocksAt(i+1, j).getBlockType() != blockType.FIRE && start.grid.getBlocksAt(i+1, j-1).getBlockType() == blockType.AIR) {
					if (start.grid.getBlocksAt(i+1, j).getBlockType() == blockType.GRAS) {
						start.grid.getBlocksAt(i+1, j).setBlockType(blockType.DIRT);
					}
					start.grid.getBlocksAt(i+1, j-1).setBlockType(blockType.FIRE);
				}
				if (start.grid.getBlocksAt(i-1, j).getBlockType() != blockType.AIR  && start.grid.getBlocksAt(i-1, j).getBlockType() != blockType.FIRE && start.grid.getBlocksAt(i-1, j-1).getBlockType() == blockType.AIR) {
					if (start.grid.getBlocksAt(i-1, j).getBlockType() == blockType.GRAS) {
						start.grid.getBlocksAt(i-1, j).setBlockType(blockType.DIRT);
					}
					start.grid.getBlocksAt(i-1, j-1).setBlockType(blockType.FIRE);
				}
				start.grid.getBlocksAt(i, j-1).setBlockType(blockType.FIRE);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Block auserhalb des Feldes");
			}
			destroy();
		}
		else if (pT == projektileType.ENERGIEBALL) {
			try {
				start.grid.getBlocksAt(i, j).setBlockType(blockType.AIR);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Block auserhalb des Feldes");
			}
			try {
				start.grid.getBlocksAt(i+1, j).setBlockType(blockType.AIR);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Block auserhalb des Feldes");
			}
			try {
				start.grid.getBlocksAt(i-1, j).setBlockType(blockType.AIR);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Block auserhalb des Feldes");
			}
			try {
				start.grid.getBlocksAt(i, j+1).setBlockType(blockType.AIR);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Block auserhalb des Feldes");
			}
			try {
				start.grid.getBlocksAt(i, j-1).setBlockType(blockType.AIR);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Block auserhalb des Feldes");
			}
			destroy();
		}
		else if (pT == projektileType.SAMEN) {
			//setzen des Baumes
			for (int s = 1; s < 4; s++) {
				try {
					start.grid.getBlocksAt(i, j-s).setBlockType(blockType.BAUMSTAMM);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Block auserhalb des Feldes");
				}
			}
			for (int t1 = -1; t1 < 2; t1++) {
				for (int t2 = -1; t2 < 2; t2++) {
					try {
						start.grid.getBlocksAt(i+t1, j-5+t2).setBlockType(blockType.BAUMKRONE);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Block auserhalb des Feldes");
					}
				}
			}
			destroy();
		}

	}
	
}
