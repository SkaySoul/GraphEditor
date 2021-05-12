import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

//Map Application
//Author: Maksim Zakharau, 256629 
//Date: October 2020;


public class GraphEditor extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private static final String APP_AUTHOR = "Autor: Maksim Zakharau\n\nDate: december 2020";
	private static final String APP_TITLE = "Graph Editor";
	
	private static final String APP_INSTRUCTION =
			"                  PROGRAM GUIDE         \n\n" + 
	        "Active keys:\n" +
			"   arrows  ==> moving all nodes\n" +
			"   SHIFT + arrows ==> fast moving all of nodes\n\n" +
			"when the cursor points to the node:\n" +
			"   DEL   ==> delete node\n" +
			"   +/-   ==> enlarge/reduce node\n" +
			"   r,g,b,c,y ==> change node color\n\n" +
			"Mouse actions:\n" +
			"   dragging ==> replacing all nodes\n" +
			"   RMB ==> creating new node on the cursor place\n" +
	        "when the cursor points to the node:\n" +
	        "   dragging ==> moving nodes\n" +
			"   RMB ==> changing node color, creating new edge etc.\n" ;
	                          
	
	
	public static void main(String[] args) {
		new GraphEditor();
	}

	
	// private GraphBase graph;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuGraph = new JMenu("Graph");
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem menuNew = new JMenuItem("New", KeyEvent.VK_N);
	private JMenuItem menuShowExample = new JMenuItem("Example", KeyEvent.VK_X);
	private JMenuItem menuExit = new JMenuItem("Exit", KeyEvent.VK_E);
	private JMenuItem menuListOfNodes = new JMenuItem("List of Nodes", KeyEvent.VK_N);
	private JMenuItem menuAuthor = new JMenuItem("Author", KeyEvent.VK_A);
	private JMenuItem menuInstruction = new JMenuItem("Instruction", KeyEvent.VK_I);
	private JMenuItem menuSave = new JMenuItem("Save to File", KeyEvent.VK_S);
	private JMenuItem menuOpenFile = new JMenuItem("Open File", KeyEvent.VK_O);
	
	  
	private GraphPanel panel = new GraphPanel();
	
	
	public GraphEditor() {
		super(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		setLocationRelativeTo(null);
		setContentPane(panel);
		createMenu();
		showBuildinExample();
		setVisible(true);
	}

	private void showListOfNodes(Graph graph) {
		Node array[] = graph.getNodes();
		int i = 0;
		StringBuilder message = new StringBuilder("Amount of all nodes: " + array.length + "\n");
		for (Node node : array) {
			message.append(node.name+"\n"+node + "\n   ");
			if (++i % 5 == 0)
				message.append("\n");
		}
		JOptionPane.showMessageDialog(this, message, APP_TITLE + " - List of All Nodes", JOptionPane.PLAIN_MESSAGE);
	}

	private void createMenu() {
		menuNew.addActionListener(this);
		menuShowExample.addActionListener(this);
		menuExit.addActionListener(this);
		menuListOfNodes.addActionListener(this);
		menuAuthor.addActionListener(this);
		menuInstruction.addActionListener(this);
		menuOpenFile.addActionListener(this);
		menuSave.addActionListener(this);
		
		menuGraph.setMnemonic(KeyEvent.VK_G);
		menuGraph.add(menuNew);
		menuGraph.add(menuShowExample);
		menuGraph.addSeparator();
		menuGraph.add(menuListOfNodes);
		menuGraph.addSeparator();
		menuGraph.add(menuSave);
		menuGraph.add(menuOpenFile);
		menuGraph.addSeparator();
		menuGraph.add(menuExit);
		
		
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.add(menuInstruction);
		menuHelp.add(menuAuthor);
		
		menuBar.add(menuGraph);
		menuBar.add(menuHelp);
		setJMenuBar(menuBar);
	}

	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == this.menuOpenFile) {
			String filename = JOptionPane.showInputDialog("Give the name of file:");
			if (filename == null || filename.equals(""))
				return;
			try {
				panel.setGraph(Graph.readFromFile(filename));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Reading process error", 0);
			}
		}
		if (source == this.menuSave) {
			String fileName = JOptionPane.showInputDialog("Give the name of file:");
			if (fileName == null || fileName.equals(""))
				return;
			try {
				Graph.writeToFile(panel.getGraph(), fileName);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Writing process error", 0);
			}
		}
		if (source == menuNew) {
			panel.setGraph(new Graph());
		}
		if (source == menuShowExample) {
			showBuildinExample();
		}
		if (source == menuListOfNodes) {
			showListOfNodes(panel.getGraph());
		}
		if (source == menuAuthor) {
			JOptionPane.showMessageDialog(this, APP_AUTHOR, APP_TITLE, JOptionPane.INFORMATION_MESSAGE);
		}
		if (source == menuInstruction) {
			JOptionPane.showMessageDialog(this, APP_INSTRUCTION, APP_TITLE, JOptionPane.PLAIN_MESSAGE);
		}
		if (source == menuExit) {
			System.exit(0);
		}
	}

	private void showBuildinExample() {
		Graph graph = new Graph();

		Node n1 = new Node(100, 100);
		n1.setColor(Color.BLUE);
		Node n2 = new Node(100, 200);
		n2.setColor(Color.CYAN);
		n2.setR(15);
		Node n3 = new Node(200, 100);
		n3.setR(20);
		Node n4 = new Node(200, 200);
		n4.setColor(Color.GREEN);
		n4.setR(30);

		graph.addNode(n1);
		graph.addNode(n2);
		graph.addNode(n3);
		graph.addNode(n4);
		panel.setGraph(graph);
	}
}
