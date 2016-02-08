package search;

import java.util.Comparator;

/**
 * Csúcsok heurisztikus értékét összehasonlító osztály.
 *
 * Note: this comparator imposes orderings that are inconsistent with equals.
 */
public class TotalCostComparator implements Comparator<ANode> {

	public int compare(ANode node1, ANode node2) {
		return node1.getEstimatedCost() - node2.getEstimatedCost();
	
	}

}
