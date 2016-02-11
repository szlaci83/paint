package search;

import java.io.PrintStream;

import search.Operator;
import state.Paint;
public class ANode extends Node {

	protected int estimatedCost;
	
	public ANode(Paint state){
		super(state);
		estimatedCost = state.estimate();
	}
	
	public ANode(Paint state, Node parent, Operator made){
		super(state, parent, made);
		estimatedCost = state.estimate();
	}
	
	public ANode getNextChild(){
		if( hasMoreChildren() ){
			Operator op = (Operator)OpsToUse.remove(0);
			Paint next = ((Paint)this.getState().clone());
			next.apply(op);
			return new ANode(next,this,op);
		}
	return null;
	}
	
	public void printPath(PrintStream stream){
		if (parent!=null){ 
			parent.printPath(stream);
		}
		if (this.operator != null){
		stream.println(this.operator);
		}
	}
	
	public int getEstimatedCost(){
		return actualState.estimate();
	}

	public int getEvaluation(){
		return this.getCost() + this.getEstimatedCost();
	}

	
}
