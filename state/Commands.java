/**enumerator to represent the painting commands (Operators)
 * 
 * @author Laszlo Szoboszlai
 *
 */
package state;
import search.Operator;
import search.OperatorImp;
import search.State;

public enum Commands implements Operator{
	PAINT_SQUARE,
	ERASE_CELL,
	PAINT_LINE;

	@Override
	public int getCost(State state) {
		//all command costs 1
		return 1;
	}

	@Override
	public State applyTo(State state) {
		return OperatorImp.applyTo(this, state);
	}

	@Override
	public boolean isApplicableTo(State state) {
		return OperatorImp.isApplicableTo(this, state);
	}
	

}
