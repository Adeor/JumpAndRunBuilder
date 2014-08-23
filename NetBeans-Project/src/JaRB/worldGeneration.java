package JaRB;

/*import java.util.Random;*/

//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
public class worldGeneration implements commons{

	static block[][] generateWorld() {
		block[][] blocks = new block[(int) (BLOCK_ROWS_X)][(int) (BLOCK_ROWS_Y)];
		//Random rand;
		for (double x = 0; x <  BLOCK_ROWS_X; x++) {
			for (double y = 0; y < BLOCK_ROWS_Y; y++) {
				//rand = new Random();
				//double zufall = (rand.nextDouble()-0.5)/ENTROPIE;
				if (y/BLOCK_ROWS_Y /*+ zufall*/ <= 0.1) {
					blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.AIR);
				}
				else if (y/BLOCK_ROWS_Y /*+ zufall*/ <= 0.2) {
					blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.AIR);
				}
				else if (y/BLOCK_ROWS_Y /*+ zufall*/ <= 0.3) {
					blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.AIR);
				}
				else if (y/BLOCK_ROWS_Y /*+ zufall*/ <= 0.4) {
					blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.AIR);
				}
				else if (y/BLOCK_ROWS_Y /*+ zufall*/ <= 0.5) {
					blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.AIR);
				}
				else if (y/BLOCK_ROWS_Y /*+ zufall*/ <= 0.6) {
					if (blocks[(int)x][(int)y-1].getBlockType() == blockType.AIR) {
						blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.GRAS);
					}
					else {
						blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.DIRT);
					}
				}
				else if (y/BLOCK_ROWS_Y /*+ zufall*/ <= 0.7) {
					if (blocks[(int)x][(int)y-1].getBlockType() == blockType.AIR) {
						blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.GRAS);
					}
					else {
						blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.DIRT);
					}
				}
				else if (y/BLOCK_ROWS_Y /*+ zufall*/ <= 0.8) {
					if (blocks[(int)x][(int)y-1].getBlockType() == blockType.AIR) {
						blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.GRAS);
					}
					else {
						blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.DIRT);
					}
				}
				else if (y/BLOCK_ROWS_Y /*+ zufall*/ <= 0.9) {
					blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.STONE);
				}
				else {
					blocks[(int)x][(int)y] = new block((int)(x * BLOCK_SIZE*start.zoom), (int)(y * BLOCK_SIZE*start.zoom), true, blockType.STONE);
				}
				
				/*Wortspeicher ;)
				blocks[x][y] = new block(x * BLOCK_SIZE*start.zoom, y * BLOCK_SIZE*start.zoom, blockType.STONE);
				blocks[x][y] = new block(x * BLOCK_SIZE*start.zoom, y * BLOCK_SIZE*start.zoom, blockType.AIR);
				blocks[x][y] = new block(x * BLOCK_SIZE*start.zoom, y * BLOCK_SIZE*start.zoom, blockType.DIRT);
				*/
			}
		}
		return blocks;
	}
	
}
