import java.awt.Color;

import processing.core.PApplet;

//import ddf.minim.AudioPlayer;
//import ddf.minim.AudioSample;
//import ddf.minim.Minim;


public class Game1 extends PApplet {
	
	public Ball mainball;
	public Rectangles player1;
	public Rectangles player2;
	public Rectangles startGame;
	public Rectangles options;
	public Rectangles optionChangeSpeedPlus;
	public Rectangles optionChangeSpeedMinus;
	public Rectangles optionChangeBallSizePlus;
	public Rectangles optionChangeBallSizeMinus;
	public Rectangles optionChangePaddleSizePlus;
	public Rectangles optionChangePaddleSizeMinus;

	public Rectangles backToDefault;
	public Rectangles backToMenu;
	public Rectangles audio;
	public Rectangles audioMute;
	public Rectangles audioUnmute;
	
	boolean up;
	boolean down;
	boolean w;
	boolean s;
	boolean hitP1 = true;
	boolean hitP2 = true;
	boolean gameOn = false;
	boolean optionsOn =false;
	boolean menuOn =false;
	boolean audioOn=false;
	boolean speedPlusHover;
	boolean speedMinusHover;
	boolean paddleSizePlusHover;
	boolean paddleSizeMinusHover;
	boolean ballSizePlusHover;
	boolean ballSizeMinusHover;
	boolean backToMenuHover;
	boolean startGameHover;
	boolean audioHover;
	boolean audioMuteHover;
	boolean audioUnmuteHover;
	boolean backToDefaultHover;
	boolean victoryOn;
	//Minim minim;
	//AudioPlayer backgroundMusic;
	//AudioSample ballHitsSound;
	int player1Score = 10;
	int player2Score = 10;
	int textSize = 20;
	int speed =4;
	

	public static void main(String[] args) {
		PApplet.main("Game1");

	}
	
	public void settings(){
		size(800,400);
	}
	
	public void setup() {
		//minim = new Minim(this);
	//	backgroundMusic = minim.loadFile("Oscar Peterson - Blues for Basie.mp3");
	//	ballHitsSound = minim.loadSample("Ball_Bounce-Popup_Pixels-172648817.mp3");
		mainball = new Ball(width / 2, height / 2, 20, Color.GREEN, this);
		player1 = new Rectangles(width-10,200,10,80,Color.GREEN,this);
		player2 = new Rectangles(0,200,10,80,Color.GREEN,this);
		
		startGame = new Rectangles(width/2-150,10,300,40,Color.PINK,this);
		
		options = new Rectangles(width/2-150,55,300,40,Color.PINK,this);
		
		backToDefault = new Rectangles(width/2-150,145,300,40,Color.PINK,this);
		
		optionChangeSpeedPlus = new Rectangles(80,height/2,50,50,Color.PINK,this);
		optionChangeSpeedMinus = new Rectangles(160,height/2,50,50,Color.PINK,this);
		optionChangeBallSizePlus = new Rectangles(width/2-80,height/2,50,50,Color.PINK,this);
		optionChangeBallSizeMinus = new Rectangles(width/2,height/2,50,50,Color.PINK,this);
		optionChangePaddleSizePlus = new Rectangles(width-160,height/2,50,50,Color.PINK,this);
		optionChangePaddleSizeMinus = new Rectangles(width-80,height/2,50,50,Color.PINK,this);
		backToMenu = new Rectangles(width-80,height-50,80,50,Color.BLUE,this);
		audio = new Rectangles(width/2-150,100,300,40,Color.PINK,this);
		audioMute = new Rectangles(width/3,200,60,60,Color.PINK,this);
		audioUnmute = new Rectangles(width-width/3,200,60,60,Color.PINK,this);
		
	//	backgroundMusic.loop();
		frameRate(150);
		menuOn=true;
			}
	public void keyPressed() {
		if(keyCode==DOWN) {
			down=true;
		}
		if(keyCode==UP) {
			up=true;
		}
		if(key=='w') {
			w=true;
		}
		if(key=='s') {
			s=true;
		}
		if(key=='r') {
			int random = (int)random(2);
			if(random==0) {		
			mainball.setVx(1);
			mainball.setVy(2);
		}
			else {
				mainball.setVx(-1);
				mainball.setVy(2);
			}
		}
	}
	public void keyReleased() {
		if(keyCode==DOWN) {
			down=false;
		}
		if(keyCode==UP) {
			up=false;
		}
		if(key=='w') {
			w=false;
		}
		if(key=='s') {
			s=false;
		}
	}
	public void draw() {
		//println("player1 width: ",player1.getWidth()," ","player2 width: ",player2.getWidth());
		
		//println("back to menu hover is: ", backToMenuHover);
		
		if(gameOn==true) {
			
			if(mouseX > backToMenu.getX() 
					&& mouseX < backToMenu.getX()+backToMenu.getWidth()
					&&mouseY > backToMenu.getY() 
					&& mouseY < backToMenu.getY()+backToMenu.getHeight()) {
				backToMenu.setInnerColour(Color.GRAY);
				backToMenuHover=true;
			}
			else {
				backToMenu.setInnerColour(Color.BLUE);
				backToMenuHover=false;
			}
			
		noStroke();
		fill(0, 0, 0, 10);
		rect(0, 0, 600, 600);
		background(0);

		mainball.update();
		mainball.paint();
		
		player1.paint();
		player2.paint();
		
		gameUpdate();
		score();
		if(mainball.getVx()==0) {
			
		backToMenu.paint();
		}
		if(player1Score==11||player2Score==11) {
			changeScene("victoryScreen");
		}
		}
		else if(optionsOn==true) {
			options();
		}
		else if(menuOn==true){
			menu();
		}
		else if(audioOn==true) {
			audio();
		}
		else if(victoryOn==true) {
			victoryScreen();
		}
		
	}
	public void gameUpdate() {
		//fix bugs P1 XD
		if(hitP1 == true &&mainball.getX()+mainball.getSize()/2>player1.getX()
				&& mainball.getY()+mainball.getSize()/2==player1.getY()) {
			mainball.setVy(-mainball.getVy());
			hitP1=false;
			hitP2=true;
			
		}
		if(hitP1 == true &&mainball.getX()+mainball.getSize()/2>player1.getX()
				&& mainball.getY()+mainball.getSize()/2==player1.getY()+player1.getHeight()) {
			mainball.setVy(-mainball.getVy());
			hitP1=false;
			hitP2=true;
			
		}
		//fix bugs P2
		if(hitP2 == true &&mainball.getX()+mainball.getSize()/2<player2.getX()+player2.getWidth()
				&& mainball.getY()+mainball.getSize()/2==player2.getY()) {
			mainball.setVy(-mainball.getVy());
			hitP2=false;
			hitP1=true;
			
		}
		if(hitP2 == true &&mainball.getX()+mainball.getSize()/2<player2.getX()+player2.getWidth()
				&& mainball.getY()+mainball.getSize()/2==player2.getY()+player2.getHeight()) {
			mainball.setVy(-mainball.getVy());
			hitP2=false;
			hitP1=true;
			
		}
		
		//bounce P1
		if (hitP1 == true && mainball.getX()+mainball.getSize()/2>= player1.getX()
				&& mainball.getY() + mainball.getSize()/2 > player1.getY()
				&& mainball.getY() - mainball.getSize()/2 < player1.getY()+player1.getHeight()) {
			
			mainball.setVx(-mainball.getVx());
			println("ball X1: ", mainball.getX()+mainball.getSize());
			
			if(mainball.getVx()>0) {
				mainball.setVx(mainball.getVx()+1);
			}
			if(mainball.getVx()<0) {
				mainball.setVx(mainball.getVx()-1);
			}
		//	ballHitsSound.trigger();
			hitP1=false;
			hitP2=true;
		}
		//bounce P2
		if (hitP2 == true && mainball.getX()-mainball.getSize()/2 <= player2.getX()+player2.getWidth() 
				&& mainball.getY() < player2.getHeight() + player2.getY()
				&& mainball.getY() + mainball.getSize() / 2 > player2.getY()) {
			
			mainball.setVx(-mainball.getVx());
			if(mainball.getVx()>0) {
				mainball.setVx(mainball.getVx()+1);
			}
			if(mainball.getVx()<0) {
				mainball.setVx(mainball.getVx()-1);
			}
			
		//	ballHitsSound.trigger();
			println("ball X2: ", mainball.getX()+mainball.getSize());
			hitP2=false;
			hitP1=true;
			
		}
		
		//Moving player 1
		
		if(down==true){
		player1.setY(player1.getY()+speed);
			
			
		}
		if(up==true){
			player1.setY(player1.getY()-speed);
		}
		
		
		//Moving player 2
			
		if(s==true) {
			player2.setY(player2.getY()+speed);
		}
		if(w==true) {
			player2.setY(player2.getY()-speed);
		}
			
			
			
			
		//resetp1
		if (player1.getY() > height) {
					player1.setY(0-player1.getHeight());
			}
		if (player1.getY() < 0-player1.getHeight()) {
					player1.setY(height);
			}
		//resetp2
		if (player2.getY() > height) {
					player2.setY(0-player2.getHeight());
			}
		if (player2.getY() < 0-player2.getHeight()) {
					player2.setY(height);
			}
		
		
		//When ball exists p1(left)
		if(mainball.getX()-mainball.getSize() >width+10) {
			mainball.setX(width/2);
			mainball.setY(height/2);
			mainball.setVx(0);
			mainball.setVy(0);
			player1Score += 1;
			hitP1=true;
			hitP2=true;
		}
		//When ball exist p2(right)
		if(mainball.getX()+mainball.getSize() <0-10) {
			mainball.setX(width/2);
			mainball.setY(height/2);
			mainball.setVx(0);
			mainball.setVy(0);
			player2Score += 1;
			hitP1=true;
			hitP2=true;
		}
	
	}
	public void mouseClicked() {
		
		if(backToDefaultHover==true) {
			player1.setHeight(80);
			player2.setHeight(80);
			speed=4;
			mainball.setSize(20);
		}
		if(startGameHover==true) {
			println("Changing scene to game");
			
			changeScene("game");
		}
		if(mouseX > options.getX() 
				&& mouseX < options.getX()+options.getWidth()
				&&mouseY > options.getY() 
				&& mouseY < options.getY()+options.getHeight()) {
			println("option button works ");
			changeScene("options");
		}
		if(audioHover) {
			
			println("audio button works ");
			changeScene("audio");
			
		}
		//////////////////////////////////
		//muting button
		if(audioMuteHover==true) {
	//		backgroundMusic.mute();
		}
		//unmuting button
		if(audioUnmuteHover==true) {
	//		backgroundMusic.unmute();
		}
		
		//////////////////////////////////////////////////////////
		//OPTIONS IN DEPTH
		//SPEED OF PADDLE
		if(speedPlusHover==true) {
			speed=speed+2;
		}
		if(speedMinusHover==true) {
			speed=speed-2;
			if(speed==0) {
				speed=2;
			}
		}
		//SIZE OF PADDLES
		if(paddleSizePlusHover==true) {
			optionChangePaddleSizePlus.setInnerColour(Color.GRAY);
		player1.setHeight(player1.getHeight()+4);
		player2.setHeight(player2.getHeight()+4);
		}
		if(paddleSizeMinusHover==true) {
			optionChangePaddleSizeMinus.setInnerColour(Color.GRAY);
			player1.setHeight(player1.getHeight()-4);
			player2.setHeight(player2.getHeight()-4);
		}
		//BALLSIZE
		if(ballSizePlusHover==true) {
			optionChangeBallSizePlus.setInnerColour(Color.GRAY);
		mainball.setSize(mainball.getSize()+2);
		}
		if(ballSizeMinusHover==true) {
			optionChangeBallSizeMinus.setInnerColour(Color.GRAY);
			mainball.setSize(mainball.getSize()-2);
		}
		//Back to Menu in options
		if(backToMenuHover==true) {
			changeScene("menu");
		}
		println("options is:  ",optionsOn);
		println("menu is: ", menuOn);
		println("audio is: ", audioOn);
		println("game is: ", gameOn);
		}
	public void score() {
	textAlign(CENTER);
	text("Score",width/2,80);
	textSize(textSize);
	text((player1Score),(width/2)-textSize,80+textSize);
	text((player2Score),(width/2)+textSize,80+textSize);
	}
	public void menu() {
		player1Score=0;
		player2Score=0;
		background(255);
		
		textAlign(CENTER);
		textSize(20);
		//first Box (STARTGAME)
		startGame.paint();
		fill(0);
		text("Start Game",width/2,startGame.getY()+25);
		if(mouseX > startGame.getX() 
				&& mouseX < startGame.getX()+startGame.getWidth()
				&&mouseY > startGame.getY() 
				&& mouseY < startGame.getY()+startGame.getHeight()) {
			startGame.setInnerColour(Color.GRAY);
			startGameHover=true;
		}
		else {
			
			startGame.setInnerColour(Color.PINK);
			startGameHover=false;
			
		}
		//Second Box (OPTIONS)
		options.paint();
		fill(0);
		text("Options",width/2,options.getY()+25);
		if(mouseX > options.getX() 
				&& mouseX < options.getX()+options.getWidth()
				&&mouseY > options.getY() 
				&& mouseY < options.getY()+options.getHeight()) {
			options.setInnerColour(Color.GRAY);
		}
		else {
			options.setInnerColour(Color.PINK);
			
		}
		//Third Box (AUDIO)
		audio.paint();
		fill(0);
		text("Audio",width/2,audio.getY()+25);
		
		if(mouseX > audio.getX() 
				&& mouseX < audio.getX()+audio.getWidth()
				&&mouseY > audio.getY() 
				&& mouseY < audio.getY()+audio.getHeight()) {
			audio.setInnerColour(Color.GRAY);
			audioHover=true;
		}
		else {
			audio.setInnerColour(Color.PINK);
			audioHover=false;
		}
		//FORTH BOX
		backToDefault.paint();
		fill(0);
		text("Default",width/2,backToDefault.getY()+25);
		if(mouseX > backToDefault.getX() 
				&& mouseX < backToDefault.getX()+backToDefault.getWidth()
				&&mouseY > backToDefault.getY() 
				&& mouseY < backToDefault.getY()+backToDefault.getHeight()) {
			backToDefault.setInnerColour(Color.GRAY);
			backToDefaultHover=true;
		}
		else {
			backToDefault.setInnerColour(Color.PINK);
			backToDefaultHover=false;
		}
		
		
	}
	public void changeScene(String scene) {
		if(scene=="game") {
			println("game scene is working");
			noHover();
			menuOn=false;
			gameOn=true;
			optionsOn=false;
			audioOn=false;
			victoryOn=false;
			println("gameOn is: ",gameOn);
		}
		if(scene=="menu") {
			noHover();
			backToMenuHover=false;
			menuOn=true;
			gameOn=false;
			optionsOn=false;
			audioOn=false;
			victoryOn=false;
			println("menu scene is working");
		}
		if(scene=="options") {
			noHover();
			optionsOn=true;
			menuOn=false;
			gameOn=false;
			audioOn=false;
			victoryOn=false;
			println("options scene is working");
		}
		if(scene=="audio") {
			noHover();
			audioOn=true;
			menuOn=false;
			optionsOn=false;
			gameOn=false;
			victoryOn=false;
			println("audio scene is working");
		}
		if(scene=="victoryScreen") {
			noHover();
			victoryOn=true;
			audioOn=false;
			menuOn=false;
			optionsOn=false;
			gameOn=false;
		}
		
	}
	public void options() {
		background(255);
		textAlign(CENTER);
		textSize(20);
		
		//speed+ color
		if(mouseX > optionChangeSpeedPlus.getX() 
				&& mouseX < optionChangeSpeedPlus.getX()+optionChangeSpeedPlus.getWidth()
				&&mouseY > optionChangeSpeedPlus.getY() 
				&& mouseY < optionChangeSpeedPlus.getY()+optionChangeSpeedPlus.getHeight()) {
			optionChangeSpeedPlus.setInnerColour(Color.GRAY);
			speedPlusHover=true;
		}
		else {
			optionChangeSpeedPlus.setInnerColour(Color.PINK);
			speedPlusHover=false;
		}
		//speed- color
		if(mouseX > optionChangeSpeedMinus.getX() 
				&& mouseX < optionChangeSpeedMinus.getX()+optionChangeSpeedMinus.getWidth()
				&&mouseY > optionChangeSpeedMinus.getY() 
				&& mouseY < optionChangeSpeedMinus.getY()+optionChangeSpeedMinus.getHeight()) {
			optionChangeSpeedMinus.setInnerColour(Color.GRAY);
			speedMinusHover=true;
		}
		else {
			optionChangeSpeedMinus.setInnerColour(Color.PINK);
			speedMinusHover=false;
		}
		//paddle size+ color
		if(mouseX > optionChangePaddleSizePlus.getX() 
				&& mouseX < optionChangePaddleSizePlus.getX()+optionChangePaddleSizePlus.getWidth()
				&&mouseY > optionChangePaddleSizePlus.getY() 
				&& mouseY < optionChangePaddleSizePlus.getY()+optionChangePaddleSizePlus.getHeight()) {
			optionChangePaddleSizePlus.setInnerColour(Color.GRAY);
			paddleSizePlusHover=true;
		}
		else {
			optionChangePaddleSizePlus.setInnerColour(Color.PINK);
			paddleSizePlusHover=false;
		}
		//paddle size- color
		if(mouseX > optionChangePaddleSizeMinus.getX() 
				&& mouseX < optionChangePaddleSizeMinus.getX()+optionChangePaddleSizeMinus.getWidth()
				&&mouseY > optionChangePaddleSizeMinus.getY() 
				&& mouseY < optionChangePaddleSizeMinus.getY()+optionChangePaddleSizeMinus.getHeight()) {
			optionChangePaddleSizeMinus.setInnerColour(Color.GRAY);
			paddleSizeMinusHover=true;
		}
		else {
			optionChangePaddleSizeMinus.setInnerColour(Color.PINK);
			paddleSizeMinusHover=false;
		}
		//ball size+ color
		if(mouseX > optionChangeBallSizePlus.getX() 
				&& mouseX < optionChangeBallSizePlus.getX()+optionChangeBallSizePlus.getWidth()
				&&mouseY > optionChangeBallSizePlus.getY() 
				&& mouseY < optionChangeBallSizePlus.getY()+optionChangeBallSizePlus.getHeight()) {
			optionChangeBallSizePlus.setInnerColour(Color.GRAY);
			ballSizePlusHover=true;
		}
		else {
			optionChangeBallSizePlus.setInnerColour(Color.PINK);
			ballSizePlusHover=false;
		}
		//ball size- color
		if(mouseX > optionChangeBallSizeMinus.getX() 
				&& mouseX < optionChangeBallSizeMinus.getX()+optionChangeBallSizeMinus.getWidth()
				&&mouseY > optionChangeBallSizeMinus.getY() 
				&& mouseY < optionChangeBallSizeMinus.getY()+optionChangeBallSizeMinus.getHeight()) {
			optionChangeBallSizeMinus.setInnerColour(Color.GRAY);
			ballSizeMinusHover=true;
		}
		else {
			optionChangeBallSizeMinus.setInnerColour(Color.PINK);
			ballSizeMinusHover=false;
		}
		//back to menu color
		if(mouseX > backToMenu.getX() 
				&& mouseX < backToMenu.getX()+backToMenu.getWidth()
				&&mouseY > backToMenu.getY() 
				&& mouseY < backToMenu.getY()+backToMenu.getHeight()) {
			backToMenu.setInnerColour(Color.GRAY);
			backToMenuHover=true;
		}
		else {
			backToMenu.setInnerColour(Color.BLUE);
			backToMenuHover=false;
		}
		optionChangeSpeedPlus.paint();
		
		optionChangeSpeedMinus.paint();
		
		//Ball size
		optionChangeBallSizePlus.paint();
		
		optionChangeBallSizeMinus.paint();
		
		//Paddle size
		optionChangePaddleSizePlus.paint();
		
		optionChangePaddleSizeMinus.paint();
		
		backToMenu.paint();
		fill(0);
		//text for speed up
		text("Paddle Speed",(optionChangeSpeedPlus.getX()+optionChangeSpeedMinus.getX())/2,optionChangeSpeedPlus.getY()-50);
		text(speed,25+(optionChangeSpeedPlus.getX()+optionChangeSpeedMinus.getX())/2,optionChangeSpeedPlus.getY()+optionChangeSpeedPlus.getHeight()+25);
		text("+",optionChangeSpeedPlus.getX()+optionChangeSpeedPlus.getWidth()/2,optionChangeSpeedPlus.getY()+optionChangeSpeedPlus.getWidth()/2);
		text("-",optionChangeSpeedMinus.getX()+optionChangeSpeedMinus.getWidth()/2,optionChangeSpeedMinus.getY()+optionChangeSpeedMinus.getWidth()/2);
		
		//text for Ball size up
		text("Ball size",(optionChangeBallSizePlus.getX()+optionChangeBallSizeMinus.getX())/2,optionChangeBallSizePlus.getY()-50);
		text(mainball.getSize(),25+(optionChangeBallSizePlus.getX()+optionChangeBallSizeMinus.getX())/2,optionChangeBallSizePlus.getY()+optionChangeBallSizePlus.getHeight()+25);
		text("+",optionChangeBallSizePlus.getX()+optionChangeBallSizePlus.getWidth()/2,optionChangeBallSizePlus.getY()+optionChangeBallSizePlus.getWidth()/2);
		text("-",optionChangeBallSizeMinus.getX()+optionChangeBallSizeMinus.getWidth()/2,optionChangeBallSizeMinus.getY()+optionChangeBallSizeMinus.getWidth()/2);
		
		//text for paddle size
		text("Paddle Size",(optionChangePaddleSizePlus.getX()+optionChangePaddleSizeMinus.getX())/2,optionChangePaddleSizePlus.getY()-50);
		text(player1.getHeight(),25+(optionChangePaddleSizePlus.getX()+optionChangePaddleSizeMinus.getX())/2,optionChangePaddleSizePlus.getY()+optionChangePaddleSizePlus.getHeight()+25);
		text("+",optionChangePaddleSizePlus.getX()+optionChangePaddleSizePlus.getWidth()/2,optionChangePaddleSizePlus.getY()+optionChangePaddleSizePlus.getWidth()/2);
		text("-",optionChangePaddleSizeMinus.getX()+optionChangePaddleSizeMinus.getWidth()/2,optionChangePaddleSizeMinus.getY()+optionChangePaddleSizeMinus.getWidth()/2);
		
		//creating the return to menu box
		text("BACK",backToMenu.getX()+backToMenu.getWidth()/2,height-60);
		
		
		
		
	}
	public void noHover() {
		speedPlusHover=false;
		speedMinusHover=false;
		paddleSizePlusHover=false;
		ballSizePlusHover=false;
		ballSizeMinusHover=false;
		backToMenuHover=false;
		startGameHover=false;
		audioHover=false;
		audioMuteHover=false;
		audioUnmuteHover=false;
		backToDefaultHover=false;
	}
	public void audio() {
		background(255);
		textAlign(CENTER);
		textSize(20);
		fill(0);
		text("MUTE",audioMute.getX()+audioMute.getWidth()/2,audioMute.getY()-25);
		text("BACK",width-40,height-60);
		text("UNMUTE",audioUnmute.getX()+audioUnmute.getWidth()/2,audioUnmute.getY()-25);
		audioUnmute.paint();
		audioMute.paint();
		backToMenu.paint();
		if(mouseX > audioMute.getX() 
				&& mouseX < audioMute.getX()+audioMute.getWidth()
				&&mouseY > audioMute.getY() 
				&& mouseY < audioMute.getY()+audioMute.getHeight()) {
			audioMute.setInnerColour(Color.GRAY);
			audioMuteHover=true;
		}
		else {
			audioMute.setInnerColour(Color.PINK);
			audioMuteHover=false;
	}
		if(mouseX > audioUnmute.getX() 
				&& mouseX < audioUnmute.getX()+audioUnmute.getWidth()
				&&mouseY > audioUnmute.getY() 
				&& mouseY < audioUnmute.getY()+audioUnmute.getHeight()) {
			audioUnmute.setInnerColour(Color.GRAY);
			audioUnmuteHover=true;
		}
		else {
			audioUnmute.setInnerColour(Color.PINK);
			audioUnmuteHover=false;
	}
		//back to menu color
				if(mouseX > backToMenu.getX() 
						&& mouseX < backToMenu.getX()+backToMenu.getWidth()
						&&mouseY > backToMenu.getY() 
						&& mouseY < backToMenu.getY()+backToMenu.getHeight()) {
					backToMenu.setInnerColour(Color.GRAY);
					backToMenuHover=true;
				}
				else {
					backToMenu.setInnerColour(Color.BLUE);
					backToMenuHover=false;
				}
				
				
	}
	public void victoryScreen() {
		textAlign(CENTER);
		textSize(40);
		
	
		//PLAYER 1 VICTORY SCREEN
		if(player1Score==11) {
			background(255,0,0);
			fill(0);
			text("PLAYER 2 WINS!",width/2,height/2);
			
			if(mouseX > backToMenu.getX() 
					&& mouseX < backToMenu.getX()+backToMenu.getWidth()
					&&mouseY > backToMenu.getY() 
					&& mouseY < backToMenu.getY()+backToMenu.getHeight()) {
				backToMenu.setInnerColour(Color.GRAY);
				backToMenuHover=true;
			}
			else {
				backToMenu.setInnerColour(Color.BLUE);
				backToMenuHover=false;
			}
			backToMenu.paint();
			textSize(20);
			text("BACK",backToMenu.getX()+backToMenu.getWidth()/2,height-60);
			
		}
		
		//PLAYER 2 VICTORY SCREEN
		if(player2Score==11) {
			background(0,0,255);
			fill(0);
			text("PLAYER 1 WINS!",width/2,height/2);
			if(mouseX > backToMenu.getX() 
					&& mouseX < backToMenu.getX()+backToMenu.getWidth()
					&&mouseY > backToMenu.getY() 
					&& mouseY < backToMenu.getY()+backToMenu.getHeight()) {
				backToMenu.setInnerColour(Color.GRAY);
				backToMenuHover=true;
			}
			else {
				backToMenu.setInnerColour(Color.RED);
				backToMenuHover=false;
			}
			backToMenu.paint();
			textSize(20);
			text("BACK",backToMenu.getX()+backToMenu.getWidth()/2,height-60);
			
		}
		
	}
}

