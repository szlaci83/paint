package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import state.Paint;

public class ASearch {

	protected int expansion;
	
	protected ANode startNode(Paint startstate){
		return new ANode(startstate);
	}
	
	protected ANode chooseNode(List<ANode> open){
		return open.isEmpty() ? null : open.remove(0);
	}
	
	protected void insertNode(List<ANode> nodes, ANode anode) {
		int i;
		for (i = 0; i < nodes.size(); ++i) {
			ANode currentNode = (ANode) nodes.get(i);
			if (anode.getEstimatedCost() <= currentNode.getEstimatedCost()){break;}
		}
			nodes.add(i, anode);
	} 		
	
	protected void expand(List<ANode> open, List<ANode> closed, ANode node) {
		while (node.hasMoreChildren()) {
			ANode child = node.getNextChild();
			int	index;
			if ( (index = open.indexOf(child)) != -1) {
				ANode oldNode = open.get(index);
				if (child.getCost() < oldNode.getCost()) {
					open.remove(index);
					insertNode(open, (ANode) child);
				}
			} else if ((index = closed.indexOf(child)) != -1) {
				ANode oldNode = closed.get(index);
				if (child.getCost() < oldNode.getCost()) {
					closed.remove(index);
					insertNode(open, (ANode) child);
				}
			} else {
				insertNode(open, (ANode) child);
			}	
		}
	}
	
	protected ANode search(Paint openState) {
		List<ANode>	open = new ArrayList<ANode>();	
		List<ANode>	closed = new ArrayList<ANode>();
		open.add(startNode(openState));
		ANode node;
		
		while ((node = chooseNode(open)) != null && ! node.getState().isGoal()) {
			expand(open, closed, node);
			closed.add(node);
			increaseExpansion();
		}
          
        return node;
	}
	
	public void increaseExpansion(){
		this.expansion++;
	}
	
	public int getExpansion() {
		return expansion;
	}
	
		
	public static void main(String[] args) {	
		//char [][] paint = toCharArray("D:\\UNI\\Practice\\java\\Forgatos\\src\\state\\logo.in");
		/*int n=paint.length;
		int	m=paint[0].length;
		 for (int j=0;j<n;j++){
         	for(int k =0;k<m;k++){
    		System.out.print(paint[j][k]);
         	}
         	System.out.println();
		}*/
	   Paint startState = new Paint();
		ASearch search = new ASearch();
		Node solution = search.search(startState);
		
		if(solution!=null){
		       solution.printPath(System.out);
		     }
		     else{ 
		    	System.out.print("No solution found");
		     }
	}
}