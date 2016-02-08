package search;

import state.*;


public class Heuristica /*implements Heuristic*/ {

  public int getValue(Staterep s){
	  int value =0;
	 byte[] r=s.getRing();	
		for(int i=0; i< r.length-1; i++){
	
		if(((r[i]- s.getNext(i))==1) || ((r[i]-s.getNext(i))==-(r.length-1)) )
          	{value-=101;}
         	else {value+=Math.abs(r[i]-s.getNext(i))*5;}
   }
	 return value;		
	}
}

