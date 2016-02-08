package search;

import java.util.*;
import state.Staterep;
import search.Node;



public class BreadthFSearch {
    Staterep kezdoallapot= new Staterep();
    
	protected int kiterjesztes;

	protected Node keres(Staterep kezdoallapot) {
		LinkedList<Node>	nyilt = new LinkedList<Node>();
        HashSet<Node>	csucsok = new HashSet<Node>();
        Node csucs=new Node(kezdoallapot);
        
        nyilt.add(csucs);
        csucsok.add(csucs);
        
		

		while ( ((csucs = nyilt.poll())!=null)  && ! csucs.aktualisAllapot.isGoal()){
		
			Node	gyerek;
			while ((gyerek = csucs.getNextChild()) != null) {
				if (! csucsok.contains(gyerek)) {
					csucsok.add(gyerek);	// az új csúcsok beszúrása a lista végére
					nyilt.add(gyerek);
				}
			}
			novelKiterjesztes();
		}
		return csucs;
	}

	public void novelKiterjesztes(){
		this.kiterjesztes++;
	}
	
	public int getKiterjesztes() {
		return kiterjesztes;
	}
	
	
	
	
	public static void main(String[] args) {

		Staterep allapot = new Staterep();
		long startTime = System.currentTimeMillis();
		BreadthFSearch bfs = new BreadthFSearch();
		Node megoldas = bfs.keres(allapot);
		startTime = System.currentTimeMillis() - startTime; 

		if(megoldas!= null){
			megoldas.utKiir(System.out);

			System.out.println("A megoldás : " + startTime +" millisecundum időt vesz igénybe.");
			System.out.println(bfs.getKiterjesztes()+" kiterjesztést végeztem");				
		}
		else{
			System.out.println("Nincs megoldás, és" + startTime +" ms a futási idő");

		}
	}
}




