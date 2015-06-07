package org.aiofwa.Panel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.*; 
import com.badlogic.gdx.graphics.g2d.*; 
import java.io.*; 
import com.badlogic.gdx.math.Intersector;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.logging.FileHandler;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.ScreenUtils;


public class Panel extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont bm; 
	TextureRegion tr; 
	// Sprite btnTick;
	ArrayList<Sprite> arr = new ArrayList<Sprite>(); 
	ArrayList<String> str = new ArrayList<String>(); 
	Sprite block; 
	Sprite sp;
    Sprite tool;
	Sprite lock;
    Sprite dialog;
	Sprite cur;
    BitmapFont bm1;
    boolean sigcl = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("data/badlogic.jpg");
		tr = new TextureRegion(new Texture(Gdx.files.internal("data/Gray.png")));
        // tool = new Sprite(new Texture(Gdx.files.internal("Gray.png")));
        // tool.setSize(300, 150);
        // tool.setPosition(0, Gdx.graphics.getHeight() - 317);
		bm = new BitmapFont(Gdx.files.internal("data/font.fnt"));
        bm1 = new BitmapFont(Gdx.files.internal("data/font.fnt"));
        bm1.setColor(Color.BLACK);
        dialog = new Sprite(new Texture(Gdx.files.internal("data/Dialog.png")));
		bm.setColor(Color.BLACK); 
		block = new Sprite(new Texture(Gdx.files.internal("data/Orange.png")));
		sp = new Sprite(new Texture(Gdx.files.internal("data/Gray.png")));
		sp.setSize(300, 150);
		// Build(new Texture(Gdx.files.internal("Brown.png")), 21, 300, 320, 160, "back"); 
		lock = new Sprite(new Texture(Gdx.files.internal("data/Brown.png")));
		lock.setPosition(0, 0); 
		lock.setSize(300, 1600); 
		cur = new Sprite(new Texture(Gdx.files.internal("data/Cursor.png")));
		// hideCursor(); 
		cur.setSize(30, 30); 
		Build(new Texture(Gdx.files.internal("data/tick.png")), 32, 220, 40, 40, "ok");
		Build(new Texture(Gdx.files.internal("data/quit.png")), 32, 265, 40, 40, "quit");
		Build(new Texture(Gdx.files.internal("data/btn.png")), 20, 127, "gen");
		boolean aye = false; 
		for (int i = 0; i != 4; i ++) {
			for (int p = 0; p != 4; p ++) {
				if ((i * 3 + p) % 2 == 0) {
					aye = false; 
					Build(new Texture(Gdx.files.internal("data/Gray.png")), 370 + p * 50, 160 + i * 50, 50, 50, p + "," + i);
				} else {
					aye = true; 
					Build(new Texture(Gdx.files.internal("data/Deep.png")), 370 + p * 50, 160 + i * 50, 50, 50, p + "," + i);
				}
				// System.out.println("ASD"); 
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// batch.draw(img, 0, 0);
		lock.draw(batch);
        // tool.draw(batch);
        bm.draw(batch, "Exit ", 109, Gdx.graphics.getHeight() - 235);
        bm.draw(batch, "Export ", 109, Gdx.graphics.getHeight() - 189);
        bm.draw(batch, "This is the software\nwhich can generates\nicons like github. ", 24, Gdx.graphics.getHeight() - 339);
		bm.draw(batch, "Color Generator ", 20, Gdx.graphics.getHeight() - 20); 
		bm.setColor(Color.WHITE); 
		bm.draw(batch, "Toolchain: ", 23, Gdx.graphics.getHeight() - 143);
		// .draw(tr, 20, Gdx.graphics.getHeight() - 50 - tr.getRegionHeight()); 
		bm.draw(batch, "|\n|\n|\n|\n|", 89, Gdx.graphics.getHeight() - 177);
		// btnTick.setX(32); 
		// btnTick.setY(Gdx.graphics.getHeight() - 220); 
		// btnTick.draw(batch); 
		for (Sprite each : arr) {
			each.draw(batch); 
		}
		if (Gdx.input.isTouched()) {
			System.out.println("Found on touch. "); 
			int X = Gdx.input.getX(); 
			int Y = Gdx.input.getY(); 
			Rectangle rect = new Rectangle(X, Gdx.graphics.getHeight() - Y, 1, 1); 
			for (Sprite obj : arr) {
				if (Intersector.overlaps(rect, obj.getBoundingRectangle())) {
					System.out.println("CLICKED, ID IS " + str.get(arr.indexOf(obj))); 
					recv(str.get(arr.indexOf(obj))); 
					float X1 = obj.getX(); 
					float Y1 = obj.getY(); 
					block.setSize(obj.getWidth(), obj.getHeight()); 
					block.setPosition(X1, Y1); 
					block.draw(batch); 
				}
			}
			sp.setPosition(X, Gdx.graphics.getHeight() - Y); 
			// sp.draw(batch);
			// bm.draw(batch, "Export ", X, Gdx.graphics.getHeight() - Y);
			System.out.println("Debug: Position: " + X + ", " + Y); 
		}  else {
            sigcl = false;
        }
		int X = Gdx.input.getX(); 
		int Y = Gdx.input.getY(); 
		Rectangle rect = new Rectangle(X, Gdx.graphics.getHeight() - Y, 1, 1); 
			
		for (Sprite obj : arr) { // Hover
			if (Intersector.overlaps(rect, obj.getBoundingRectangle())) {
				// System.out.println("CLICKED, ID IS " + str.get(arr.indexOf(obj))); 
				// recv(str.get(arr.indexOf(obj))); 
				float X1 = obj.getX(); 
				float Y1 = obj.getY(); 
				block.setSize(obj.getWidth(), obj.getHeight()); 
				block.setPosition(X1, Y1); 
				block.draw(batch);
                dialog.setPosition(cur.getX() - 10, cur.getY() + 40);
                GlyphLayout layout = new GlyphLayout(); //dont do this every frame! Store it as member
                layout.setText(bm, str.get(arr.indexOf(obj)));
                float width = layout.width;// contains the width of the current set text
                float height = layout.height; // contains the height of the current set text
                dialog.setSize(width + 20, height + 20);
                dialog.draw(batch);
                bm1.draw(batch, str.get(arr.indexOf(obj)), cur.getX(), cur.getY() + 70);
			}
		}
		cur.setPosition(X, Gdx.graphics.getHeight() - Y - 30); 
		cur.draw(batch); 
		batch.end();
	}

	void Build(Texture tr, int X, int Y, String id) {
		arr.add(new Sprite(tr)); 
		str.add(id); 
		arr.get(arr.size() - 1).setPosition(X, Gdx.graphics.getHeight() - Y); 
	}

	void Build(Texture tr, int X, int Y, int W, int H, String id) {
		arr.add(new Sprite(tr)); 
		str.add(id); 
		arr.get(arr.size() - 1).setPosition(X, Gdx.graphics.getHeight() - Y); 
		arr.get(arr.size() - 1).setSize(W, H); 
	}
	int[] RGB = new int[3];
	void recv(String id) {
        if (!sigcl) {
            sigcl = true;
            if (id.equals("quit")) {
                ByteArrayOutputStream baos = null;
                baos.toString();
            } else if (id.equals("gen")) {
                Random x = new Random(System.currentTimeMillis());
                while (true) {
                    RGB = new int[]{x.nextInt(255), x.nextInt(255), x.nextInt(255)};
                    System.out.println("Generated RGB: " + RGB[0] + ", " + RGB[1] + ", " + RGB[2]);
                    for (int s : RGB) {
                        if (s > 250) {
                            continue;
                        }
                    }
                    break;
                }
                for (int ij = 0; ij != 4; ij++) {
                    for (int Y1 = 0; Y1 != 4; Y1++) {
                        for (int X1 = 0; X1 != 4; X1++) {
                            getSpriteById(X1 + "," + Y1).setColor(new Color(0, 0, 0, 0));
                        }
                    }

                    Random r = new Random(System.currentTimeMillis());
                    int shit = r.nextInt(5) + 1;
                    for (int sb = 0; sb != shit; sb++) {
                        int x1 = r.nextInt(3);
                        int y1 = r.nextInt(4);
                        Sprite s = getSpriteById(x1 + "," + y1);
                        Sprite s1 = getSpriteById((3 - x1) + "," + y1);
                        System.out.println(s);
                        s.setColor(Color.rgb888(RGB[0], RGB[1], RGB[2]));
                        s1.setColor(Color.rgb888(RGB[0], RGB[1], RGB[2]));
                    }
                }
            } else if (id.equals("ok")) {
                // System.out.println("OKED");
                Pixmap p = getScreenshot(370, 160, 50 * 4, 50 * 4);
                Random rand = new Random(System.currentTimeMillis());
                FileHandle fh = new FileHandle(Gdx.files.getLocalStoragePath() + "/heads/" + (char) rand.nextInt() + (char) rand.nextInt() + (char) rand.nextInt() + ".png");
                PixmapIO.writePNG(fh, p);
            }
        }
	}

	Sprite getSpriteById(String id) {
		for (int a = 0; a != str.size(); a ++) {
			if (id.equals(str.get(a))) {
				return arr.get(a); 
			}
		}
		return null; 
	}

    private static Pixmap getScreenshot(int x, int y, int w, int h){
        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);
        ByteBuffer pixels = pixmap.getPixels();
        int numBytes = w * h * 4;
        byte[] lines = new byte[numBytes];
        int numBytesPerLine = w * 4;
        for (int i = 0; i < h; i++) {
            pixels.position((h - i - 1) * numBytesPerLine);
            pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
        }
        pixels.clear();
        pixels.put(lines);
        return pixmap;
    }
}