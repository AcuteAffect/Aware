package com.acute.aware;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.acute.aware.gfx.Render;
import com.acute.aware.gfx.Screen;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int HEIGHT = 600, WIDTH = 800;
	public static final String TITLE = "Aware - 0.01";

	private Thread gameThread;
	private boolean running = false;

	private Render render;
	private Screen screen;
	private int[] pixels;
	private BufferedImage img;

	public void Display() {
		screen = new Screen(WIDTH, HEIGHT);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	}

	public void start() {
		if (running)
			return;
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void stop() {
		if (!running)
			return;
		running = false;

		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void run() {

		while (running) {
			tick();
			render();

		}

	}

	private void tick() {

	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		for (int i = 0; i < WIDTH * HEIGHT; i++) {
			pixels[i] = screen.pixels[i];

		}

		screen.render();

		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {

		Game game = new Game();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
		frame.setTitle(TITLE);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		game.start();

	}

}
