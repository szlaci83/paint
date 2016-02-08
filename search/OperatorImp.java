package search;

/**
 * Az operátorokat reprezentáló osztályok készítéséhez felhasználható, az {@link ai.framework.search.Operator} interfészt implementáló ősosztály.
 */
public abstract class OperatorImp implements Operator {

	public int getCost(State state) {
		return 1;
	}

	public State applyTo(State state) {
		return applyTo(this, state);
	}

	public boolean isApplicableTo(State state) {
		return isApplicableTo(this, state);
	}

	/**
	 * Az adott operátor alkalmazása az adott állapotra.
	 *
	 * @param operator Az operátor, amelyet alkalmazni kell az adott állapotra.
	 * @param state Az állapot, amelyre alkalmazni kell az adott operátort.
	 * @return Az adott operátornak az adott állapotra történő alkalmazása során előálló új állapotot.
	 */
	public static State applyTo(Operator operator, State state) {
		State	newState = state.clone();
		newState.apply(operator);
		return newState;
	}

	/**
	 * Visszaadja, hogy lehet-e alkalmazni az adott operátort az adott állapotra.
	 *
	 * @param operator Az operátor, amelyet alkalmazni kell az adott állapotra.
	 * @param state Az állapot, amelyre az adott operátort alkalmazni kell.
	 * @return <code>true</code>, ha az adott állapotra teljesül az adott operátor alkalmazási előfeltétele, <code>false</code> egyébként.
	 */
	public static boolean isApplicableTo(Operator operator, State state) {
		return state.isApplicable(operator);
	}

}
