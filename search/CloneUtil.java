package search;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Segédosztály objektumok klónozásához.
 */
public class CloneUtil {

	/**
	 * A paraméterként adott objektum klónozása.
	 *
	 * @param o A klónozandó objektum.
	 * @return Referencia a klónra.
	 * @throws CloneNotSupportedException Ha az objektum nem klónozható,
	 */
	public static Object clone(Object o) throws CloneNotSupportedException {
		if (o instanceof Cloneable) {
			try {
				return o.getClass().getMethod("clone", (Class[]) null).invoke(o);
			} catch(Exception e) {
				throw null;
			}
		} else
			throw new CloneNotSupportedException();
	}

	/**
	 * A paraméterként adott kollekció klónozása.
	 *
	 * @param c A klónozandó kollekció.
	 * @return Referencia a klónra.
	 */
	public static Object clone(Collection<?> c) {
		if (c == null) return null;
		try {
			return clone((Object) c);
		} catch(CloneNotSupportedException e) {
			ArrayList	copy = new ArrayList(c.size());
			copy.addAll(c);
			return copy;
		}
	}

}
