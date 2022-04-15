import java.awt.Color;

import processing.core.PApplet;

public class Rectangles {
	
	//Attributes
	private float x;
	private float y;
	private float width;
	private float height;
	private Color innerColour;
	private PApplet p;
	
	//Constructor
	public Rectangles(float initialX, float initialY, float initialW, float initialH, Color initialC, PApplet initialP) {
		x = initialX;
		y = initialY;
		width = initialW;
		height = initialH;
		innerColour = initialC;
		p=initialP;
		
	}
	
	//Methods
	
	//Getter and setter for x
	public float getX(){
		return x;
	}
	
	public void setX(float newValueOfX) {
		x = newValueOfX;
	}
	
	//Getter and setter for y
	
	public float getY() {
		return y;
	}
	
	public void setY(float newValueOfY) {
		y = newValueOfY;
	}
	
	//Getter and setter for width
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float newValueOfWidth) {
		width = newValueOfWidth;
	}
	
	//Getter and setter for height
	
	public float getHeight() {
		return height;
	}
	public void setHeight(float newValueOfHeight) {
		height = newValueOfHeight;
	}
	
	//Getter and setter for innerColour
	public Color getInnerColour() {
		return innerColour;
	}
	
	public void setInnerColour(Color newValueOfInnerColour) {
		innerColour = newValueOfInnerColour;
	}
	
	
	
	public void paint() {
		p.strokeWeight(3);
		p.stroke(0);
		p.fill(innerColour.getRed(),innerColour.getGreen(),innerColour.getBlue());
		p.rect(x, y, width, height);
	
	}
	
	
	

}