package JaRB;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class start implements commons{
	static double feuerballAbklingzeit = 0;
	static double energieballAbklingzeit = 0;
	static double plantTreeAbklingzeit = 0;
	static blockGrid grid;
	static Unit unit;
	static ArrayList<projectil> projektile = new ArrayList<projectil>();
	static ArrayList<block> sandDerZeit = new ArrayList<block>();	//aehhhm..... Sande? Sand? Prinz of Persia? Beach?
	static float zoom = 1;
	
    public static void main(String[] args) {
    	initDisplay();
        initWorld();
        initGL();
        unit = new Unit(50, 50, 36, 72, true, 0, 0, unitType.TEST);
        // While we aren't pressing the red button on the display
        while (!Display.isCloseRequested()) {
            clear();
            input();
            kollisionsabfrage();
            drawAll();

            
            //Abkilngen ;)
            if (feuerballAbklingzeit > 0) {
                --feuerballAbklingzeit;
			}
            if (energieballAbklingzeit > 0) {
                --energieballAbklingzeit;
			}
            if (plantTreeAbklingzeit > 0) {
                -- plantTreeAbklingzeit;
			}
            
            
            //sand faellt :*
            for (int i = 0; i < sandDerZeit.size(); i++) {
				//if (grid.getBlocksAt((int)sandDerZeit.get(i).x/BLOCK_SIZE*start.zoom, (int)sandDerZeit.get(i).y/BLOCK_SIZE*start.zoom+BLOCK_SIZE*start.zoom).getBlockType() == blockType.AIR ||grid.getBlocksAt((int)sandDerZeit.get(i).x, (int)sandDerZeit.get(i).y+BLOCK_SIZE*start.zoom).getBlockType() == blockType.FIRE) {
				//	grid.setAt((int)sandDerZeit.get(i).x/BLOCK_SIZE*start.zoom, (int)sandDerZeit.get(i).y/BLOCK_SIZE*start.zoom+1, blockType.SAND);
				//}
			}
            //(noch) NICHT !!!
            
            
            update();
            syncronisieren();
        }
        programmBeenden();
    }

    
	private static void kollisionsabfrage() {
		kollisionUnit();
		for (int i = 0; i < projektile.size(); i++) {
			if (!(projektile.get(i).isInMovment())) {
				projektile.get(i).destroy();
				break;
			}
			kollisionProjektile(i);
		}
	}


	private static void kollisionProjektile(int i) {
		if (projektile.get(i).pT == projektileType.FIREBALL) {
			kollisionFireball(i);
		}
		else if (projektile.get(i).pT == projektileType.ENERGIEBALL) {
			kollisionEnergieball(i);
		}
		else if (projektile.get(i).pT == projektileType.SAMEN) {
			kollisionSamen(i);
		}
	}

	//test
	private static boolean kollisionUnit() {
		for (int y = 0; y < BLOCK_ROWS_Y; y++) {
			for (int x = 0; x <  BLOCK_ROWS_X; x++) {
				try {
					if (grid.getBlocksAt(x, y+1).getBlockType() != blockType.AIR && grid.getBlocksAt(x, y+1).getBlockType() != blockType.FIRE){
						if (unit.kollisionsabfrage(grid.getBlocksAt(x, y+1).x, grid.getBlocksAt(x, y+1).y, grid.getBlocksAt(x, y+1).height, grid.getBlocksAt(x, y+1).width)) {
							if (unit.jumpCounter < 1) {
								unit.dy = 0;
							}
							return true;
						}
						else {
							unit.dy += 0.001f;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	return false;
	}
	
	private static boolean kollisionSamen(int i) {
		for (int x = 0; x <  BLOCK_ROWS_X; x++) {
			for (int y = 0; y < BLOCK_ROWS_Y; y++) {
				if (grid.getBlocksAt(x, y).getBlockType() == blockType.GRAS){
					if (!(projektile.get(i).x > grid.getBlocksAt(x, y).x+grid.getBlocksAt(x, y).width  ||
							grid.getBlocksAt(x, y).x>projektile.get(i).x+25)) {
						if (!(grid.getBlocksAt(x, y).y+grid.getBlocksAt(x, y).height<projektile.get(i).y ||
								grid.getBlocksAt(x, y).y>projektile.get(i).y+25)) {
							//Ueberpruefung ob moeglcih
							for (int s = 1; s < 4; s++) {
								try {
									if (grid.getBlocksAt(x, y-s).getBlockType() != blockType.AIR) {
										return false;
									}
								} catch (ArrayIndexOutOfBoundsException e) {
									System.out.println("Block auserhalb des Feldes");
								}
							}
							for (int t1 = -1; t1 < 2; t1++) {
								for (int t2 = -1; t2 < 2; t2++) {
									try {
										if (grid.getBlocksAt(x+t1, y-5+t2).getBlockType() != blockType.AIR) {
											return false;
										}
									} catch (ArrayIndexOutOfBoundsException e) {
										System.out.println("Block auserhalb des Feldes");
									}
								}
							}
							projektile.get(i).kollision(x, y);
							return true;
						}
					}
				}
			}
		}
	return false;
	}


	private static boolean kollisionEnergieball(int i) {
		for (int x = 0; x <  BLOCK_ROWS_X; x++) {
			for (int y = 0; y < BLOCK_ROWS_Y; y++) {
				if (grid.getBlocksAt(x, y).getBlockType() != blockType.AIR){
					if (projektile.get(i).kollisionsabfrage(grid.getBlocksAt(x, y).x, grid.getBlocksAt(x, y).y, grid.getBlocksAt(x, y).height, grid.getBlocksAt(x, y).width)) {
						projektile.get(i).kollision(x, y);
						return true;
					}
				}
			}
		}
	return false;
	}


	private static boolean kollisionFireball(int i) {
		for (int x = 0; x <  BLOCK_ROWS_X; x++) {
			for (int y = 0; y < BLOCK_ROWS_Y; y++) {
				if (grid.getBlocksAt(x, y).getBlockType() != blockType.AIR && grid.getBlocksAt(x, y).getBlockType() != blockType.FIRE){
					if (projektile.get(i).kollisionsabfrage(grid.getBlocksAt(x, y).x, grid.getBlocksAt(x, y).y, grid.getBlocksAt(x, y).height, grid.getBlocksAt(x, y).width)) {
						projektile.get(i).kollision(x, y);
						return true;
					}
				}
			}
		}
	return false;
	}
	

	private static void input() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}
		while (Mouse.next()) {
			if (Mouse.isButtonDown(0)) {
				shootFireball();
			}
			if (Mouse.isButtonDown(1)) {
				shootEnergieball();
			}
			if (Mouse.isButtonDown(2)) {
				plantTree();
			}
		}
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_D && Keyboard.getEventKeyState()) {
				unit.nachRechts();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_A && Keyboard.getEventKeyState()) {
				unit.nachLinks();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_ADD && Keyboard.getEventKeyState()) {
				if (zoom+0.125 <= 5) {
					zoom += 0.125;
					grid.update();
					try {
				        for (int j = 0; j < BLOCK_ROWS_X*BLOCK_SIZE/zoom; j++) {
				        	for (int i = 0; i < BLOCK_ROWS_Y*BLOCK_SIZE/zoom; i++) {
				                grid.getBlocksAt(j, i).update();
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_SUBTRACT && Keyboard.getEventKeyState()) {
				if (zoom-0.125 >= 1) {
					zoom -= 0.125;
					grid.update();
					try {
				        for (int j = 0; j < BLOCK_ROWS_X*BLOCK_SIZE/zoom; j++) {
				        	for (int i = 0; i < BLOCK_ROWS_Y*BLOCK_SIZE/zoom; i++) {
				                grid.getBlocksAt(j, i).update();
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_SPACE && Keyboard.getEventKeyState()) {
				unit.jump();
				/*
				grid.setAt((Mouse.getX())/BLOCK_SIZE*start.zoom, (WINDOW_HEIGHT - Mouse.getY())/BLOCK_SIZE*start.zoom, blockType.SAND);
				sandDerZeit.add(grid.getBlocksAt((Mouse.getX())/BLOCK_SIZE*start.zoom, (WINDOW_HEIGHT - Mouse.getY())/BLOCK_SIZE*start.zoom));*/
			}
		}
		
	}


	private static void plantTree() {
		projectil samen;
		if (plantTreeAbklingzeit <= 0) {
			plantTreeAbklingzeit = PLANT_TREE_ABKLINGZEIT;
			try {
				float dx = (Mouse.getX())/30;
				float dy = (WINDOW_HEIGHT - Mouse.getY())/30;
				samen = new projectil(0, 0, 25, 25, true, dx, dy, projektileType.SAMEN);
			       projektile.add(samen);
			} catch (ArithmeticException ae) {
				System.out.println("Division durch Null!");
			}
		}
	}


	private static void shootFireball() {
		projectil feuerball;
		if (feuerballAbklingzeit <= 0) {
			feuerballAbklingzeit = FEUERBALL_ABKLINGZEIT;
			try {
				float dx = (Mouse.getX())/30;
				float dy = (WINDOW_HEIGHT - Mouse.getY())/30;
					feuerball = new projectil(0, 0, 25, 25, true, dx, dy, projektileType.FIREBALL);
		        projektile.add(feuerball);
			} catch (ArithmeticException ae) {
				System.out.println("Division durch Null!");
			}
		}
	}
	
	private static void shootEnergieball() {
		projectil energieball;
		if (energieballAbklingzeit <= 0) {
			energieballAbklingzeit = ENERGIEBALL_ABKLINGZEIT;
			try {
				float dx = (Mouse.getX())/30;
				float dy = (WINDOW_HEIGHT - Mouse.getY())/30;
				energieball = new projectil(0, 0, 25, 25, true, dx, dy, projektileType.ENERGIEBALL);
		        projektile.add(energieball);
			} catch (ArithmeticException ae) {
				System.out.println("Division durch Null!");
			}
		}
	}


	private static void drawAll() {
		//bloecke
        grid.draw();
        //projectile
        drawProjektile();
        //einheit(en)
        unit.draw();
	}


	private static void drawProjektile() {
		for (int i = 0; i < projektile.size(); i++) {
			projektile.get(i).draw();
		}
	}


	private static void syncronisieren() {
		// Wait until we reach 60 frames-per-second.
        Display.sync(FRAMES_PER_SECOUND);
	}


	private static void clear() {
        // Clear the 2D contents of the window.
		glClear(GL_COLOR_BUFFER_BIT);
	}


	private static void update() {
		//hier stattdessen update der abillity
		updateProjektile();
        // Update the contents of the display and check for input.
        Display.update();
        //Uptate unit(a)
        unit.update();
        //update bloecke

	}


	private static void updateProjektile() {
		for (int i = 0; i < projektile.size(); i++) {
			if (projektile.get(i).x<WINDOW_WIDTH && projektile.get(i).x>-10	&&
				projektile.get(i).y<WINDOW_HEIGHT && projektile.get(i).y>-10){
				projektile.get(i).update();
			}
			else {
				projektile.get(i).destroy();
			}
		}
		
	}


	private static void programmBeenden() {
		Display.destroy();
        System.exit(0);
	}


	private static void initGL() {
		glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
	}


	private static void initWorld() {
		//grid
        grid = new blockGrid();
	}


	private static void initDisplay() {
		try {
            // Sets the width of the display to 640 and the height to 480
            Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
            // Sets the title of the display to "Episode 2 - Display"
            Display.setTitle("Jump and Run Builder");
            // Creates and shows the display
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
	}
	
	
}