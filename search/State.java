package search;

import java.util.Collection;

/**
 * Az állapotokat reprezentáló osztályok által implementálandó interfész.
 */
public interface State extends Cloneable {

	/**
	 * Célfeltétel vizsgálata az állapotra.
	 *
	 * @return <code>true</code>, ha az állapot célállapot, <code>false</code> egyébként.
	 */
	public boolean isGoal();

	/**
	 * Az adott operátor alkalmazási előfeltételének vizsgálata az állapotra.
	 *
	 * @param operator Az operátort reprezentáló objektum.
	 * @return <code>true</code>, ha az állapotra teljesül az operátor alkalmazási előfeltétele, <code>false</code> egyébként.
         */
	public boolean isApplicable(Operator operator);

	/**
	 * Az adott operátor alkalmazása az állapotra.
	 *
	 * @param operator Az operátort reprezentáló objektum.
	 */
	public void apply(Operator operator);

	/**
	 * Visszaadja az állapotban alkalmazható operátorokat.
	 *
	 * @return Az állapotban alkalmazható operátorokat reprezentáló objektumok kollekciója. A kollekció elemei {@link ai.framework.search.Operator} objektumok.
	 */
	public Collection<? extends Operator> operators();

	/**
	 * Másolat készítése az állapotról.
	 *
	 * @return Referencia a másolat objektumra.
	 */
	public State clone();

}
