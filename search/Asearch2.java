package search;


import java.util.List;

import state.Staterep;


import java.util.HashMap;


public class Asearch2 {

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
	


	
	
	protected void kiterjeszt(HashedPriorityQueue<ANode> open, HashMap<ANode,ANode> closed, ANode node) {
		while (node.hasMoreChildren()) {
			ANode child = node.getNextChild();
			if ( open.contains(child)) {

				ANode oldNode = open.get(child);
				
				if (child.getCost() < oldNode.getCost()) {
					open.remove(child);
					open.offer(child);
				}
				
				
			} else if (closed.containsKey(child)) {
				ANode oldNode = closed.get(child);
				if (child.getCost() < oldNode.getCost()) {
					closed.remove(child);
					open.offer(child);
				}
			} else { open.offer(child);}	
			
		}
	
	}
	
	
	
	
	public ANode search(Staterep state) {
		ANode	node = new ANode(state);

		
		HashedPriorityQueue<ANode>	nyilt = new HashedPriorityQueue<ANode>(11, new TotalCostComparator());
		nyilt.offer(node);
		HashMap<ANode, ANode>	closed = new HashMap<ANode, ANode>();

		while ( (node=nyilt.poll()) != null && ! node.getState().isGoal()) {
			
			kiterjeszt(nyilt, closed, node);
			closed.put(node,node);
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
		 ASearch search = new ASearch();
		 
		 long startTime = System.currentTimeMillis();
		 
		 ANode megoldas = search.search(kezdoallapot);
		 
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
