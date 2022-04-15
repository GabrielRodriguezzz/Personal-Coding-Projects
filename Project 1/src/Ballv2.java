import java.awt.Color;

import processing.core.PApplet;

class  Ball{
		
		//Declaring attributes
		private float x;
		private float y;
		private float vx;
		private float vy;
		private float size;
		private Color c;
		private PApplet p;
		
		
		//Constructor    creates instance for an object
		public Ball(float nx, float ny, float nsize,Color nc,PApplet np) {
			x = nx;
			y = ny;
			size = nsize;
			c = nc;
			p = np;
			vx = 1;
			vy = 2;
		}
		
		//declare methods     the functions created
		
		
		public float getX() {
			return x;
		}
        public void setX(float newValueOfX) {
			x = newValueOfX;
		}
        public float getY() {
			return y;
		}	
        public void setY(float newValueOfY) {
			y = newValueOfY;
		}
		public float getSize() {
			return size;
		}
		public void setSize(float newValueOfSize) {
			size = newValueOfSize;
		}
		public float getVx() {
			return vx;
		}
		public void setVx(float newValueOfVx) {
			vx = newValueOfVx;
		}
		public float getVy() {
			return vy;
		}
		public void setVy(float newValueOfVy) {
			vy = newValueOfVy;
		}
		
		
		
		
		public void paint() {
			p.noStroke();
			p.fill(c.getRed(),c.getGreen(),c.getBlue());
			p.ellipse(x,y,size,size);
		}
		public void update() {
			x = x + vx;
			y = y + vy;
			
			if(x>p.width-size/2 || x < 0+size/2) {
				//vx = -vx;
			}
			if(y>p.height-size/2 || y < 0+size/2) {
				vy = -vy;
			}
			if(y>p.height+size/2) {
				p.background(255,0,0);
			}
		}
		
	}
	