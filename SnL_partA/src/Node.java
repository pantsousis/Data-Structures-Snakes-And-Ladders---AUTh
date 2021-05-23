
//Σαμαράς Δημήτριος 	9605 	dzsamaras@ece.auth.gr
//Τσούσης Παναγιώτης 	9590	pitsousis@ece.auth.gr

import java.util.ArrayList;

public class Node {
	
	private Node parent;
	private ArrayList<Node> children;
	private int nodeDepth;
	private int[] nodeMove;
	private Board nodeBoard;
	private double nodeEvaluation;
	
	public Node(Board board,int nodeDepth)
    { 
		setNodeBoard(board);
		this.setNodeDepth(nodeDepth);
		this.children = new ArrayList<>();
    }
	
    public void addChild(Node child)
    {
        children.add(child);
    }
    
    public ArrayList<Node> getChildren(){
    	return children;
    }

	public int getNodeDepth() {
		return nodeDepth;
	}

	public void setNodeDepth(int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}

	public Board getNodeBoard() {
		return nodeBoard;
	}

	public void setNodeBoard(Board nodeBoard) {
		this.nodeBoard = nodeBoard;
	}

	public double getNodeEvaluation() {
		return nodeEvaluation;
	}

	public void setNodeEvaluation(double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int[] getNodeMove() {
		return nodeMove;
	}

	public void setNodeMove(int[] nodeMove) {
		this.nodeMove = nodeMove;
	}
    
}


