package tile;

import java.awt.image.BufferedImage;

public class Tile {
	public BufferedImage image;
	public boolean collision  = false;

	public Tile() {}
	public Tile(BufferedImage image, boolean collision) {
		this.collision = collision;
		this.image = image;
	}
}
