package state;

import search.OperatorImp;
import search.State;

public class PaintLine extends OperatorImp{
	//representing the coordinates from(x1,y1) to (x2,y2) of the line
	protected int	x1;
	protected int	x2;
	protected int	y1;
	protected int	y2;
	/**
	 * Constructor creating a PaintLine object
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 */
	
	public PaintLine(int x1, int x2, int y1, int y2) {
		super();
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	/**
	 * getters setters...
	 * @return
	 */
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	/**
	 * Helps to print out the used command at the end of prog
	 *  writes : PAINT_LINE 	R1 C1 R2 C2 
	 *    	
	 **/
	public String toString(){
		return new StringBuilder().append("PAINT_LINE").append(" ").append(x1).append(" ").append(y1).append(" ").append(" ").append(x2).append(" ").append(y2).toString();
	}

	//TODO check
	public State applyTo(State state) {
		return OperatorImp.applyTo(this, state);

	}
	//All commands cost 1 
	public int getCost(State state) {
		return 1;
	}


	//TODO check
	public boolean isApplicableTo(State state) {
		return OperatorImp.isApplicableTo(this, state);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x1;
		result = prime * result + x2;
		result = prime * result + y1;
		result = prime * result + y2;
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
		PaintLine other = (PaintLine) obj;
		if (x1 != other.x1)
			return false;
		if (x2 != other.x2)
			return false;
		if (y1 != other.y1)
			return false;
		if (y2 != other.y2)
			return false;
		return true;
	}	
}