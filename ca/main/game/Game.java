package ca.main.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ca.main.game.control.KeyInput;
import ca.main.game.gfx.BufferImageLoader;
import ca.main.game.gfx.Player;
import ca.main.game.network.GameClient;
import ca.main.game.network.GameServer;

public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 96*4; 
	public static final int HEIGHT = WIDTH / 12 *9; 
	public static final int SCALE = 2;
	public final String TITLE = "java 2D game";
	public static int buffering = 3;// it's not suggested to go over triple with buffering
	

	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
	private Player player;
	
	private GameClient socketClient;
	private GameServer socketServer;
	
	public void init(){
		requestFocus();
		BufferImageLoader loader = new BufferImageLoader();
		try{
			spriteSheet = loader.loadImage("/sheets/applejack2.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));//add keyLister to main game
		
		player = new Player(100,100,this);
		
		socketClient.sendData("ping".getBytes());
	}
	
	public synchronized void start(){
		if (running) return; //if game is already running don't do anything 
			
		running = true;
		thread = new Thread(this);
		thread.start();
		
		if (JOptionPane.showConfirmDialog(this, "start server? ") == 0){
			socketServer = new GameServer(this, 4200);
			socketServer.start();
		}
		
		socketClient = new GameClient(this, "localhost", 4200);
		socketClient.start();
	}
	
	public synchronized void stop(){
		if (!running) return; //if game is already dead ignore
		
		running = false;
		try{
			thread.join(); //groups up all running threads and kills them
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D; //how much ns does our game tick take
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		int ticks = 0; //counter for ticks, tick == update basically
		int frames = 0;	//counter for frames
		long timer = System.currentTimeMillis();
		
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			
			if (delta >= 1){ //check if game needs tick update
				tick();
				delta--; 
				ticks ++;
			}
			render();
			frames ++;
			
			if (System.currentTimeMillis() - lastTimer >= 1000 )
			{
				lastTimer += 1000;
				System.out.println("frames: "+frames+" ticks: "+ticks); // show frames and ticks per second
				ticks = 0;
				frames= 0;
			}
		}
		stop();
	}
	
	private void tick(){
		player.tick();//updates player position
		
		
	}
	
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy(); //set buffer strategy from our Canvas
		
		if (bs == null){
			createBufferStrategy(buffering); //if game's buffer strategy is null, create one
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//////////// Everything we want to draw on our screen /////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		player.render(g);
		/////////// end of drawing here! /////////////////////////////
		g.dispose();
		bs.show();
	}
	
	//add controls 
	public void keyPressed(KeyEvent e){
		int key = e.getExtendedKeyCode();
		
		if(key == KeyEvent.VK_RIGHT){
			player.setVelX(5);
		}else if(key == KeyEvent.VK_LEFT){
			player.setVelX(-5);
		}else if(key == KeyEvent.VK_DOWN){
			player.setVelY(5);
		}else if(key == KeyEvent.VK_UP){
			player.setVelY(-5);
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getExtendedKeyCode();
		
		if(key == KeyEvent.VK_RIGHT){
			player.setVelX(0);
		}else if(key == KeyEvent.VK_LEFT){
			player.setVelX(0);
		}else if(key == KeyEvent.VK_DOWN){
			player.setVelY(0);
		}else if(key == KeyEvent.VK_UP){
			player.setVelY(0);
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); 
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); 
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		//============= Main Frame ==========================
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack(); //basically pack everything together/extends window class
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();//call on game to start
	}

	public BufferedImage getSpriteSheet() { //fetches "main" spritesheet when other classes need models
		return spriteSheet;
	}
}
