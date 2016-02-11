package state;
import search.OperatorImp;
import search.State;

public class PaintSquare extends OperatorImp{
	
	private int r,c,s; //row, column, s  as described in the task  
	
	//converted into coordinates to handle array operations
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public PaintSquare(int r, int c, int s) {
		super();
		this.r = r;
		this.c = c;
		this.s = s;
		
		//convert it to coordinates:
		this.setX1(r-s);
		this.setY1(c-s);
		this.setX2(r+s);
		this.setY2(c+s);
	}

	public PaintSquare(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		//convert it to row, column, s
		this.setC(((y2-y1) / 2) + 1) ;
		this.setR(y1 + this.getS());
		this.setC(x1 + this.getS());
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	/**
	 * Helps to print out the used command at the end of prog
	 * writes: PAINT_SQUARE R C S
	 */
	public String toString(){
		return new StringBuilder().append("PAINT_SQUARE").append(" ").append(r).append(" ").append(c).append(s).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + c;
		result = prime * result + r;
		result = prime * result + s;
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
		PaintSquare other = (PaintSquare) obj;
		if (c != other.c)
			return false;
		if (r != other.r)
			return false;
		if (s != other.s)
			return false;
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