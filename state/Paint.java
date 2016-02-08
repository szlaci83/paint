package state;

import java.util.Arrays;
import java.util.Collection;

import search.Operator;
import search.State;

public class Paint implements State {
	
	//Goal to paint this :
	private char[][] paint;
	//Start state:
	private char[][] canvas;
	//size of the painting: 
	private int n;
	private int m;
	
	/**constructor creating a new paint object using a char array
	 * (initial instantiation)
	*/
	public Paint(char[][] CharArray){
		this.paint = CharArray;
		this.n = CharArray.length;
		this.m = CharArray[0].length;
		this.canvas = new char[n][m];
		//fill the canvas with dots
			for (int i = 0; i < this.canvas.length ; i++){
				for(int j = 0; j < this.canvas[0].length; j++){
					this.canvas[i][j] = '.';
				}
			}
	}
	
	/**constructor creting Paint objects with set start and goal
	 * (canvas and paint)
	 * @param paint
	 * @param canvas
	 */
	public Paint(char[][] paint, char[][] canvas){
		this.paint = paint;
		this.n = paint.length;
		this.m = paint[0].length;
		this.canvas = canvas;
	}	
	

	@Override
	/** return is the canvas = to  the paint*/
	public boolean isGoal() {
	    return Arrays.deepEquals(this.canvas, this.paint);
	}

	//method to return the number of cells to be painted in 
	//a given area
	public int howManyToPaint(int x1, int y1, int x2, int y2){
		int cell = 0;
		for(int i = x1; i <= x2; i++ ){
			for(int j = y1; j <= y2; j++ ){
				if (paint[i][j] == '#'){
					cell++;
				}
			}
		}
	 return cell;
	}
	
	//method to return the number of cells in 
	//a given area
		public int howManyCells(int x1, int y1, int x2, int y2){
			int cell = 0;
			for(int i = x1; i <= x2; i++ ){
				for(int j = y1; j <= y2; j++ ){
						cell++;
				}
			}
		 return cell;
		}
		
	
	/**
	 * Checks if the operator can be used on the current state of the 
	 * canvas.
	 */
	@Override
	public boolean isApplicable(Operator operator) {
		//we can apply the erasecell operator on a coordinate if 
		//the paint has an empty cell and the canvas was painted on that cell
		if (operator instanceof EraseCell){
			return paint[((EraseCell) operator).getX()][((EraseCell) operator).getY()] == '.' 
				&& canvas[((EraseCell) operator).getX()][((EraseCell) operator).getY()] == '#' ;
		}
		if (operator instanceof PaintLine){
			return (((PaintLine) operator).getX1() == ((PaintLine) operator).getX2())
				|| (((PaintLine) operator).getY1() == ((PaintLine) operator).getY2());
			//TODO include (all cells -  cells to paint) < all cells / 2 ???? 
				//&& (); 				
		}
		
		//handle single cell paint as well with PaintSquare command
		if (operator instanceof PaintSquare){	
	
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/**
	 * Estimates how close the actual canvas compared to the paint
	 * (How close we are to the goal) 
	 * @return the number of right pixels at right place
	 */
	public int estimate(){
		int rightCell = 0;
		for(int i = 0; i <= n; i++){
			for(int j = 0; j <= m; j++){
				if (canvas[i][j] == paint[i][j]){
					rightCell++;
				}
			}
		}
		return rightCell;
	}
	

	@Override
	
	
	public void apply(Operator operator) {
		// TODO Auto-generated method stub
		
	}
	
	/**something similar to tictactoe  operator arraylist we need to create to 
	 * cover the # -s 
	 */
	@Override
	public Collection<? extends Operator> operators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
