package search;

import java.util.ArrayList;
import java.util.List;

import state.Staterep;

public class ASearch {

	protected int kiterjesztes;
	
	protected ANode startCsucs(Staterep startstate){
		return new ANode(startstate);
	}
	
	protected ANode kivalasztCsucs(List<ANode> nyilt){
		return nyilt.isEmpty() ? null : nyilt.remove(0);
	}
	
	protected void insertNode(List<ANode> nodes, ANode anode) {
		int i;
		for (i = 0; i < nodes.size(); ++i) {
			ANode currentNode = (ANode) nodes.get(i);
			if (anode.getEstimatedCost() <= currentNode.getEstimatedCost()){break;}
		}
			nodes.add(i, anode);
	} 		
	
	protected void kiterjeszt(List<ANode> open, List<ANode> closed, ANode node) {
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
	
	protected ANode search(Staterep kezdoallapot) {
		List<ANode>	nyilt = new ArrayList<ANode>();	
		List<ANode>	zart = new ArrayList<ANode>();
		nyilt.add(startCsucs(kezdoallapot));
		ANode node;
		
		while ((node = kivalasztCsucs(nyilt)) != null && ! node.getState().isGoal()) {
			kiterjeszt(nyilt, zart, node);
			zart.add(node);
			novelKiterjesztes();
		}
          
        return node;
	}
	
	public void novelKiterjesztes(){
		this.kiterjesztes++;
	}
	
	public int getKiterjesztes() {
		return kiterjesztes;
	}
	
	public static void main(String[] args) {
	
		 Staterep kezdoallapot = new Staterep();
		 System.out.print(kezdoallapot.getRing().length);
		 ASearch search = new ASearch();
		 
		 long startTime = System.currentTimeMillis();
		 
		 Node megoldas = search.search(kezdoallapot);
		 
		 startTime = System.currentTimeMillis() - startTime; 
	     
		 if(megoldas!=null){
	       megoldas.utKiir(System.out);
	       System.out.println("Kiterjesztések száma: \n" + search.getKiterjesztes());
	       System.out.println("Valamint : " + startTime +" millisecundum időt vesz igénybe.\n");
	     }
	     else{ 
	    	System.out.print("Nincs megoldás.");
	     }
	}

}
