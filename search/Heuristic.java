package search;

/**
 * Heurisztikákat reprezentáló osztályok által implementálandó interfész.
 */
public interface Heuristic<S extends State> {

	/**
	 * A heurisztika értékének számolása a paraméterként adott állapotra. Visszaad egy becslést arra vonatkozólag, hogy az adott állapotból milyen költséggel érhető el a legközelebbi célállapot.
	 *
	 * @param state
	 * @return Becslés az állapotból a legközelebbi célállapotba vezető út költségére.
	 */
	public int getValue(S state);

}
