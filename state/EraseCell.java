package state;

public class EraseCell {
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
	
	/**
	 * Helps to print out the used command at the end of prog
	 * writes : ERASE_CELL R C 
	 */
	public String toString(){
		return new StringBuilder().append("ERASE_CELL").append(" ").append(x).append(" ").append(y).toString();
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
