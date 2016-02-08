package search;

/**
 * Az operátorokat reprezentáló osztályok által implementálandó interfész.
 */
public interface Operator {

	/**
	 * Visszaadja az operátor alkalmazási költségét az adott állapotra, amennyiben az operátor alkalmazható rá.
	 *
	 * @param state Az állapot, amelyre az operátort alkalmazni kell.
	 * @return Az operátor alkalmazási költsége.
	 */
	public int getCost(State state);

	/**
	 * Az operátor alkalmazása az adott állapotra.
	 *
	 * @param state Az állapot, amelyre az operátort alkalmazni kell.
	 * @return Az operátornak az adott állapotra történő alkalmazása során előálló új állapotot.
	 */
	public State applyTo(State state);

	/**
	 * Visszaadja, hogy lehet-e alkalmazni az operátort az adott állapotra.
	 *
	 * @param state Az állapot, amelyre az operátort alkalmazni kell.
	 * @return <code>true</code>, ha az adott állapotra teljesül az operátor alkalmazási előfeltétele, <code>false</code> egyébként.
	 */
	public boolean isApplicableTo(State state);

}
