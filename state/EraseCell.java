package state;

import search.Operator;
import search.OperatorImp;
import search.State;

public class EraseCell extends OperatorImp{
	//the coordinates of the cell to be erased:
	private int x;
	private int y;
	
	public EraseCell(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	//TODO check
	public State applyTo(Paint state) {
		return OperatorImp.applyTo(this, state);

	}
	//commands cost 2 because you need to draw that cell first
		public int getCost(Paint state) {
			return Integer.MAX_VALUE - 2;
		}
	//TODO check
		public boolean isApplicableTo(Paint state) {
			return OperatorImp.isApplicableTo(this, state);
		}
	
	
	
	
	/**
	 * Helps to print out the used command at the end of prog
	 * writes : ERASE_CELL R C 
	 */
	public String toString(){
		return new StringBuilder().append("ERASE_CELL").append(" ").append(this.x).append(" ").append(this.y).toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EraseCell other = (EraseCell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
