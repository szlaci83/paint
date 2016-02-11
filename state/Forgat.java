package state;

import search.Operator;
import search.OperatorImp;
import search.State;

public enum Forgat implements Operator {

	TURN_LEFT,
	ROTATE,
	TURN_RIGHT;

	public State applyTo(State state) {
		return OperatorImp.applyTo(this, state);

	}

	public int getCost(State state) {
		if ( Forgat.this == ROTATE ) 
			return 4;
		else return 1;

	}



	public boolean isApplicableTo(State state) {
		return OperatorImp.isApplicableTo(this, state);
	}

}
