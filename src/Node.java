import java.awt.Color;
import java.awt.Graphics;

//Map Application
//Author: Maksim Zakharau, 256629 
//Date: October 2020;

public class Node {
	
	
	protected int x;
	protected int y;
	protected String name;
	
	protected int r;
	
	
	private Color color;
	
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.r = 10;
		this.color = Color.WHITE;
		this.name = "";
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean isMouseOver(int mx, int my){
		return (x-mx)*(x-mx)+(y-my)*(y-my)<=r*r;
	}

	void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x-r, y-r, 2*r, 2*r);
		g.setColor(Color.BLACK);
		g.drawOval(x-r, y-r, 2*r, 2*r);
		g.drawString(name, x-(name.length()+r/4), y);
	}
	
	@Override
	public String toString(){
		return ("(" + x +", " + y + ", " + r + ")");
	}
	
}
