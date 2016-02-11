package state;

import java.util.EnumSet;
import java.util.Set;

import search.Operator;
import search.State;



public class Forgatos implements State {



	byte[] ring; //= new byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	
	
    private static final int INITIAL = 8;


	private static final byte[] tomb = {17,18,19,20,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
	public Forgatos(){
		this(INITIAL);
	}
	
	public Forgatos(int meret) {
		ring = new byte[meret];
		for(int j=0; j<meret;++j){
			ring[j] = ((byte)(j+1) );
		}
	}


	public Forgatos(byte[] a) {
		if (! isPermutation(a)) throw new IllegalArgumentException();
		int i = 0;
		ring = new byte[a.length];
		for(byte elem: a){
			ring[i++] = elem;  
		}
	}


	
	private boolean isPermutation(byte[] a) {
		if (a == null) return false;
		boolean[]	b = new boolean[a.length];
		try {
			for (int i = 0; i < a.length; ++i) {
				if (b[a[i] - 1]) return false;
				b[a[i] - 1] = true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}



	public void apply(Forgat forgat) {
		
		switch( forgat ){
		case TURN_LEFT: { 
			byte temp = ring[0];
			byte[] arcop = new byte[ring.length];
			
			System.arraycopy(ring, 1, arcop, 0, ring.length-1);
			arcop[ring.length-1] = temp;
			ring = arcop;
			break;      
		}
		case 	TURN_RIGHT:{
			
			byte temp = ring[ring.length-1];
			byte[] arcop = new byte[ring.length];
			
			System.arraycopy(ring, 0, arcop, 1, ring.length-1);
			arcop[0] = temp;
			ring = arcop;
		
			break;      
		}
		case ROTATE: { 
			
			byte[] temp = new byte[4];
			
			for(int i=0 ; i<4 ; i++){
				temp[3-i] = ring[i];
			} 

			for(int i=0 ; i<4 ; i++){
				ring[i]=temp[i];
			}   		 
		
		
		}
		}
	}



	public boolean isApplicable(Operator operator) {
		return (operator instanceof Forgat);
	}



	@Override
	public Forgatos clone()  {
		Forgatos	copy = null;
		try {
			copy = (Forgatos) super.clone();

		} catch(CloneNotSupportedException e) {}
		copy.ring = (byte[])ring.clone();
		
		return copy;
	}

	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null || ! getClass().equals(o.getClass())) return false;
		Forgatos	that = (Forgatos) o;
		return this.ring.equals(that.ring);
	}


	@Override
	public int hashCode() {
		return ring.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int gy: ring){
			sb.append(gy).append(", ");
		}
		return sb.toString();
	}


	public boolean isGoal() {
							
			for(int i=0; i<ring.length-1; i++){
				if((ring[i]-ring[i+1]!=1) &&  (ring[i+1]-ring[i]!= (ring.length-1))){ 
					return false;    	    	  
				}
			}
		
		
		return true;
	}

	public Set<Forgat> operators() {
		EnumSet<Forgat>	operators = EnumSet.noneOf(Forgat.class);
		for (Forgat op: Forgat.values()) {
			operators.add(op);
		}
		return operators;
	}

	public byte[] getGyuru(){
		return ring;
	}

	public void apply(Operator operator) {
		apply((Forgat) operator);
	}
	
	public int estimate(){
		
		int value = 4;
		
		for(int i=0; i<ring.length-1; i++){
			if((ring[i]-ring[i+1]!=1) &&  (ring[i+1]-ring[i]!= (ring.length-1))){ 
				int dif = Math.abs(ring[i]-ring[i+1]);
				value += dif*20; 
			}
		}
		
		return value;
	}

	
}