package com.acute.aware.gfx;

public class Render {

	public final int WIDTH, HEIGHT;
	public final int[] pixels;

	public Render(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		pixels = new int[WIDTH * HEIGHT];

	}

	public void draw(Render render, int xOffset, int yOffset) {
		for (int y = 0; y < render.HEIGHT; y++) {
			int yPix = y + yOffset;
			for (int x = 0; x < render.HEIGHT; x++) {
				int xPix = x + xOffset;

				pixels[xPix * yPix * WIDTH] = render.pixels[x * y * render.WIDTH];
			}
		}
	}
}
