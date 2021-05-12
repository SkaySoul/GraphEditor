import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//Map Application
//Author: Maksim Zakharau, 256629 
//Date: October 2020;

public class Graph implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private List<Node> nodes;
	private List<Edge> edges;
	public Graph() {
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
	}
	
	public void addNode(Node node){
		nodes.add(node);
	}
	
	public void removeNode(Node node){
		nodes.remove(node);
	}
	
	public void addEdge(Edge edge){
		edges.add(edge);
	}
	
	public void removeEdge(Edge edge){
		edges.remove(edge);
	}
	
	
	public Node[] getNodes(){
		Node [] array = new Node[0];
		return nodes.toArray(array);
	}
	
	public Edge[] getEdges(){
		Edge[] array = new Edge[0];
		return edges.toArray(array);
	}
	
	public void draw(Graphics g){
		
		for(Edge edge : edges) {
			edge.draw(g);
		}
		for(Node node : nodes){
			node.draw(g);
		}
	}
	
//	public Node findByName(String nodename) throws Exception{
//		Node neednode = null;
//		for (Node node: nodes) {
//			if (node.getName() == nodename) {
//				neednode=node;
//			}
//		}
//		if (neednode == null) {
//			throw new NullPointerException("Node not found");
//		}
//		
//		return neednode;
//		
//	}
	
	public static void writeToFile(Graph graph, String filename) throws Exception {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
			oos.writeObject(graph);
			oos.close();
		} catch (IOException e) {
			throw new Exception("Error writing file");
		}
	}

	public static Graph readFromFile(String filename) throws Exception {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			Graph graph=(Graph)ois.readObject();
			return graph;
		} catch (FileNotFoundException e) {
			throw new Exception("File not found");
		} catch (IOException e) {
			throw new Exception("Error reading file");
		}
	}

}
