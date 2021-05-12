import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;


//Map Application
//Author: Maksim Zakharau, 256629 
//Date: October 2020;

public class Edge implements Serializable {
	  private static final long serialVersionUID = 1L;
	  
	  protected Node nodeStart;
	  protected Node nodeEnd;
	  
	  public Edge(Node nodeStart, Node nodeEnd) {
	    this.nodeStart = nodeStart;
	    this.nodeEnd = nodeEnd;
	  }
	  
	  public Node getNodeStart() {
	    return nodeStart;
	  }
	  
	  public Node getNodeEnd() {
	    return nodeEnd;
	  }
	  
	  void draw(Graphics g) {  
	    g.drawLine(nodeStart.getX(), nodeStart.getY(), nodeEnd.getX(), nodeEnd.getY());
	    g.setColor(Color.BLACK);
	  }
	  
	  public boolean isMouseOver(int mousex, int mousey) {
	    if ((mousex > nodeStart.getX() && mousex > nodeEnd.getX()) || (mousex < nodeStart.getX() && mousex < nodeEnd.getX()))
	     return false; 
	    if ((mousey > nodeStart.getY() && mousey > nodeEnd.getY()) || (mousey < nodeStart.getY() && mousey < nodeEnd.getY()))
	     return false; 
	    int x = nodeEnd.getY() - nodeStart.getY();
	    int y = nodeStart.getX() - nodeEnd.getX();
	    int z = nodeEnd.getX() * nodeStart.getY() - nodeStart.getX() * nodeEnd.getY();
	    double edgesp = Math.abs(x * mousex + y * mousey + z) / Math.sqrt((x * x + y * y));
	    return (edgesp <= 4);
	  }
	  
	  public String toString() {
	    return "(" + nodeStart + "->" + nodeEnd + ")";
	  }
	}