package search;

import java.io.PrintStream;
import java.util.ArrayList;


import search.Operator;
import state.Paint;

public class Node {

	protected Paint actualState; // actual state 

	protected Node parent; // parent node

	protected Operator operator; //the operator created this state
 
	
	protected ArrayList<Operator> OpsToUse; // operators to use
	
	protected int cost; // cost of using the operator



	//Create the start node
	public Node(Paint state){
		this.actualState = (Paint)state;
		this.OpsToUse = (ArrayList)((Paint)state).operators();
		
		
	}

	//inner nodes
	public Node(Paint  state, Node parent, Operator creatorOp) {
		this.actualState = state;
		this.parent = parent;
		this.OpsToUse = (ArrayList) state.operators();
		this.operator = creatorOp;
		this.cost = parent.getCost();
	} 


	//Is there any more children
	public boolean hasMoreChildren() {
		return ! OpsToUse.isEmpty();
	}

	//returns next child
	public Node getNextChild() {
		if (! OpsToUse.isEmpty()) {
			//Iterator<Forgat>	iterator = hasznoperatorok.iterator();
			//Forgat	kovOperator = iterator.next();
			//iterator.remove();
			Operator nextOperator = ((Operator) OpsToUse.remove(0));
			Paint newPaint = (Paint)actualState.clone();
			newPaint.apply(nextOperator);
			return new Node(newPaint, this, nextOperator);
		}
		return null;
	}

	//prints out the operators leading to the solution
	public void printPath(PrintStream stream){
		if (parent!=null) parent.printPath(stream);
		stream.println(this);
	}
	/*
	public void utKiir(PrintStream stream){
		if (szulo!=null) szulo.utKiir(stream);
		stream.println(this);
	}
*/
	public boolean equals(Object o) {
		return o == this || (o != null && this.getClass().equals(o.getClass()) && actualState.equals(((Node) o).actualState));
	}


	public int hashCode() {
		return actualState.hashCode();
	}

	public Object clone() {
		Node	copy = null;
		try {
			copy = (Node) super.clone();
		} catch(CloneNotSupportedException e) {}
		copy.actualState.setCanvas(this.actualState.getCanvas());		
		copy.OpsToUse = (ArrayList<Operator>)OpsToUse.clone();
		return copy; 
	}

	public String toString() {
		StringBuilder  sb = new StringBuilder(actualState.toString());
		if (operator != null) sb.append(operator);
		return sb.toString();
	}



	public Paint getState(){
		return actualState;
	}
	public int getCost(){
		return cost;
	}

}
