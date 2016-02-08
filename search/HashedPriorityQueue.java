package search;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.SortedSet;

/**
 * A <tt>java.util.PriorityQueue</tt> osztályt kiterjesztő osztály, amely a sorba helyezett elemeket egy <tt>java.util.HashMap</tt> objektumban
 * is tárolja, ilyen módon a <tt>contains</tt> metódussal gyorsan vizsgálható, hogy a sorban szerepel-e az adott objektum.
 */
public class HashedPriorityQueue<E> extends PriorityQueue<E> {

	private static final long	serialVersionUID = 149389080130059035L;

	private HashMap<E, E>	map = new HashMap<E, E>();

	public HashedPriorityQueue() {
		super();
	}

	public HashedPriorityQueue(int initialCapacity) {
		super(initialCapacity);
	}

	public HashedPriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
		super(initialCapacity, comparator);
	}

	public HashedPriorityQueue(Collection<? extends E> c) {
		super(c);
	}

	public HashedPriorityQueue(HashedPriorityQueue<? extends E> c) {
		super(c);
	}

	public HashedPriorityQueue(SortedSet<? extends E> c) {
		super(c);
	}

	public void clear() {
		super.clear();
		map.clear();
	}

	public boolean remove(Object o) {
		if (! map.containsKey(o)) return false;
		super.remove(o);
		map.remove(o);
		return true;
	}

	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	public boolean offer(E e) {
		if (map.containsKey(e)) throw new IllegalArgumentException();
		super.offer(e);
		map.put(e, e);
		return true;
	}

	public E poll() {
		E	e = super.poll();
		if (e != null) map.remove(e);
		return e;
	}

	public E remove() {
		E	e = super.remove();
		if (e != null) map.remove(e);
		return e;
	}

	public boolean removeAll(Collection<?> c) {
		boolean	modified = map.keySet().removeAll(c);
		if (modified) super.removeAll(c);
		return modified;
	}

	public boolean retainAll(Collection<?> c) {
		boolean	modified = map.keySet().retainAll(c);
		if (modified) super.retainAll(c);
		return modified;
	}

	public E get(E e) {
		return map.get(e);
	}

}
