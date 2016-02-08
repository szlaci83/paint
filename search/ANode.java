package search;

import state.Operator;
import state.Staterep;

public class ANode extends Node {

	protected int estimatedCost;
	
	public ANode(Staterep state){
		super(state);
		estimatedCost = state.estimate();
	}
	
	public ANode(Staterep state, Node parent, Operator made){
		super(state, parent, made);
		estimatedCost = state.estimate();
	}
	
	public ANode getNextChild(){
		if( hasMoreChildren() ){
			Operator op = (Operator)hasznoperatorok.remove(0);
			Staterep next = ((Staterep)this.getState().clone());
			next.apply(op);
			return new ANode(next,this,op);
		}
	return null;
	}
	
	public int getEstimatedCost(){
		return aktualisAllapot.estimate();
	}

	public int getEvaluation(){
		return this.getCost() + this.getEstimatedCost();
	}

	
}
