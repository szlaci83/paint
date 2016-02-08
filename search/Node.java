package search;

import java.io.PrintStream;
import java.util.ArrayList;


import state.Operator;
import state.Staterep;

public class Node {

	protected Staterep aktualisAllapot;//Az aktuális állapot

	protected Node szulo; //Az aktuális áll szülője

	protected Operator operator; //az előállító operátor

	//protected Set<Forgat> hasznoperatorok; //Használható operátorok
	
	protected ArrayList<Operator> hasznoperatorok;
	
	protected int	ktsg; // költség



	//Start csúcsot hozza létre
	public Node(Staterep allapot){
		this.aktualisAllapot = (Staterep)allapot;
		this.hasznoperatorok = ((Staterep)allapot).operators();
		//this.hasznoperatorok = allapot.operators();
		
	}

	//közbülső csúcsok
	public Node(Staterep allapot, Node szulo, Operator eloallitoOp) {
		this.aktualisAllapot = allapot;
		this.szulo= szulo;
		this.hasznoperatorok = allapot.operators();
		
		this.operator=eloallitoOp;
		int plusz=0;
		if (eloallitoOp==Operator.ROTATE){ plusz=3;} 
		this.ktsg=szulo.getCost() + 1+plusz;
	} 


	//van-e még gyereke

	public boolean hasMoreChildren() {
		return ! hasznoperatorok.isEmpty();
	}

	//köv gyermeket adja
	public Node getNextChild() {
		if (! hasznoperatorok.isEmpty()) {
			//Iterator<Forgat>	iterator = hasznoperatorok.iterator();
			//Forgat	kovOperator = iterator.next();
			//iterator.remove();
			Operator kovOperator = hasznoperatorok.remove(0);
			Staterep newStateRep = (Staterep)aktualisAllapot.clone();
			newStateRep.apply(kovOperator);
			return new Node(newStateRep, this, kovOperator);
		}
		return null;
	}

	//A veremben visszalépked a szülő csúcsig majd kiírja a megoldásig vezető utat
	public void utKiir(PrintStream stream){
		if (szulo!=null) szulo.utKiir(stream);
		stream.println(this);
	}


	public boolean equals(Object o) {
		return o == this || (o != null && this.getClass().equals(o.getClass()) && aktualisAllapot.equals(((Node) o).aktualisAllapot));
	}


	public int hashCode() {
		return aktualisAllapot.hashCode();
	}

	public Object clone() {
		Node	copy = null;
		try {
			copy = (Node) super.clone();
		} catch(CloneNotSupportedException e) {}
		copy.aktualisAllapot = aktualisAllapot.clone();
		//copy.hasznoperatorok = ((Set<Forgat>)((HashSet) hasznoperatorok).clone());
		copy.hasznoperatorok = (ArrayList<Operator>)hasznoperatorok.clone();
		return copy; 
	}

	public String toString() {
		StringBuilder	sb = new StringBuilder(aktualisAllapot.toString());
		if (operator != null) sb.append(' ').append(operator);
		sb.append(" Költség: ").append(ktsg);
		return sb.toString();
	}

	public Staterep getState(){
		return aktualisAllapot;
	}
	public int getCost(){
		return ktsg;
	}

}
