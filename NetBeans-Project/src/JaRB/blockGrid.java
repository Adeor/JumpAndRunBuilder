package JaRB;

//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
class blockGrid implements commons{

    private block[][] blocks;
    
    public blockGrid() {
		blocks = worldGeneration.generateWorld();
	}

	public block getBlocksAt(int x_index, int y_index) {
		return getBlocks()[x_index][y_index];
	}
	
    public void setAt(int x_index, int y_index, blockType bT) {
        getBlocks()[x_index][y_index] = new block((int)(x_index * BLOCK_SIZE*start.zoom), (int)(y_index * BLOCK_SIZE*start.zoom), true, bT);
    }
 
    void update() {
		blocks = worldGeneration.generateWorld();	//TODO
	}
    
    public void draw() {
        for (int x = 0; x < BLOCK_ROWS_X; x++) {
            for (int y = 0; y < BLOCK_ROWS_Y; y++) {
                getBlocks()[x][y].draw();
            }
        }
    }

	public block[][] getBlocks() {
		return blocks;
	}
    
}
    