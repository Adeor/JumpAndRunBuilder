package JaRB;

//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
public interface commons {
	static final int BLOCK_ROWS_X = 108;
	static final int BLOCK_ROWS_Y = 60;
	static final int FRAMES_PER_SECOUND = 60;
	static final double FEUERBALL_ABKLINGZEIT = FRAMES_PER_SECOUND*0.00001;			//3 statt 0.1
	static final double ENERGIEBALL_ABKLINGZEIT = FRAMES_PER_SECOUND*0.000000003;		//5 statt 0.3
	static final double PLANT_TREE_ABKLINGZEIT = FRAMES_PER_SECOUND*0.001;			//Nur Alpha Test!!!
	static final int WINDOW_HEIGHT = 720;
	static final int WINDOW_WIDTH = 1280;
	static final int BLOCK_SIZE = 12;
	static final int ENTROPIE = 9;
}