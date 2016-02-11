package state;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import search.Operator;
import search.State;

public class Paint implements State {
	
	
	//Goal to paint this :
	static char [][] paint = toCharArray("D:\\UNI\\Practice\\java\\Forgatos\\src\\state\\logo.in");
	//private static char[][] paint;
	//Start state:
	private char[][] canvas;
	//size of the painting: 
	private int n;
	private int m;
	
	static char [][] toCharArray(String path){
		BufferedReader myStrings;
		String aLine;
		int n=0,m=0;
		char[][] paint = new char[0][0];
		try
	    {
	        myStrings = new BufferedReader(new FileReader("D:\\UNI\\Practice\\java\\Forgatos\\src\\state\\logo.in"));                
	                    try
	                    {
	                        Scanner s = new Scanner(myStrings);
	                        if (s.hasNextInt()){ n = s.nextInt();}
	                        if (s.hasNextInt()){ m = s.nextInt();}
	                        //char array to store the "pixels"
	                    	paint = new char[n][m];
	                        //System.out.println(n);
	                        //System.out.println(m);
	                        // skip /n
	                        s.nextLine();
	                        //read lines
	                        int i = 0;
	                        while (s.hasNextLine()){
	                        	aLine = s.nextLine();
	                        	paint[i] = aLine.toCharArray();
	                        	//System.out.println(paint[i]);
	                        	i++;
	                        }
	                        s.close();
	                        myStrings.close();
	                    }
	                    catch (IOException ie){
	                    	ie.printStackTrace();
	                    }
	                   
	    }
	    catch (IOException e)
	    {
	        System.out.println(e.getMessage());
	    }
	    return paint;
	}

	//array to store all the possible operators
	private static final Operator[]	OPERATORS;

	static {
		ArrayList<Operator>	temp = new ArrayList<Operator>();
		//System.out.println("start");
		// four for loop ?????? any easier way?
		int n=paint.length;
		int	m=paint[0].length;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				for(int k = 0; k < n ; k++){
					for(int l = 0; l < m; l++){
						if ((i<=k) && (j<=l)){
						// add operators to paint lines (try to min)
						if ((i == k ) || (j == l)){
					 		PaintLine line = new PaintLine(i,k,j,l);
					 		temp.add(line);
					 		//System.out.println("line");
					 		//System.out.println("erase");
						}
						if ((i - k == j - l) /*&& (j-l % 2 == 1)*/){
						// add operators to paint squares (try to min)
						PaintSquare square = new PaintSquare(i,j,k,l);
					//	System.out.println("sq");
						temp.add(square);
						}
					}
					}
				}
			
			//add operators to erase all the cells
			EraseCell cell = new EraseCell(i,j);
			temp.add(cell);
			}
		//
		}
		//create the OPERATORS array
		OPERATORS = temp.toArray(new Operator[0]);
	}

	public char[][] getPaint() {
		return paint;
	}
	
	public char[][] getCanvas() {
		return canvas;
	}

	public void setCanvas(char[][] canvas) {
		this.canvas = canvas;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}
	
	/**constructor creating a new paint object using a char array
	 * (initial instantiation)
	*/
	public Paint(){
		super();
		//paint = CharArray;
		this.n = paint.length;
		this.m = paint[0].length;
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
	public Paint(char[][] Paint, char[][] canvas){
		super();
		paint = Paint;
		this.n = paint.length;
		this.m = paint[0].length;
		this.canvas = canvas;
	}	
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
	//	for(int gy: ring){
	//	 sb.append("hhh");
	//	}
		return sb.toString();
	}
	
	@Override
	/** return is the canvas = to  the paint*/
	public boolean isGoal() {
	    return Arrays.deepEquals(this.canvas, paint);
	}

	//method to return the number of cells to be painted in 
	//a given area
	public int howManyToPaint(int x1, int y1, int x2, int y2){
		int cell = 0;
		for(int i = x1; i < x2; i++ ){
			for(int j = y1; j < y2; j++ ){
				//System.out.println(i);
				//System.out.println(j);
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
			//easier way to do it (abs not needed, just in case)
			return Math.abs((x2-x1) * (y2-y1));
			/*int cell = 0;
			for(int i = x1; i <= x2; i++ ){
				for(int j = y1; j <= y2; j++ ){
						cell++;
				}
			}
		 return cell;*/
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
				&& canvas[((EraseCell) operator).getX()][((EraseCell) operator).getY()] == '#';
		}
		if (operator instanceof PaintLine){
			int x1 = ((PaintLine) operator).getX1();
			int x2 = ((PaintLine) operator).getX2();
			int y1 = ((PaintLine) operator).getY1();
			int y2 = ((PaintLine) operator).getY2();
			int all = this.howManyCells(x1, y1, x2, y2);
			int toPaint = this.howManyToPaint(x1, y1, x2, y2);			
			//is the line straight
			//might not need it as we can create them straight...
			return ((x1 == x2) || (y1 == y2))
					// cells to paint > all cells / 2 
					//to check if it worth to use the operator 
					&& ( toPaint > (all / 2));
		}
		
		//handle single cell paint as well with PaintSquare command
		if (operator instanceof PaintSquare){	
			int x1 = ((PaintSquare) operator).getX1();
			int x2 = ((PaintSquare) operator).getX2();
			int y1 = ((PaintSquare) operator).getY1();
			int y2 = ((PaintSquare) operator).getY2();
			int all = this.howManyCells(x1, y1, x2, y2);
			int toPaint = this.howManyToPaint(x1, y1, x2, y2);
			
			// cells to paint > all cells / 2 
			//to check if it worth to use the operator 
			return  ( toPaint > (all / 2));				
		}
		return false;
	}
	
	
	/**
	 * Estimates how close the actual canvas compared to the paint
	 * (How close we are to the goal) 
	 * @return the number of right pixels at right place
	 */
	public int estimate(){
		int rightCell = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if (canvas[i][j] == paint[i][j]){
					rightCell++;
				}
			}
		}
		return rightCell;
	}
	
	@Override
	/**implement the operators effect on the canvas 
	 * 
	 */	
	public void apply(Operator operator) {
		//EraseCell sets the cell back to '.'
		if (operator instanceof EraseCell){
			EraseCell cell = ((EraseCell) operator);
			this.canvas[cell.getX()][cell.getY()] = '.';
		}
		//PaintLine 
		if (operator instanceof PaintLine){
			PaintLine line = ((PaintLine) operator);
			for(int i = line.getX1(); i <= line.getX2(); i++){
				for(int j = line.getY1(); j <= line.getY2(); j++){
					this.canvas[i][j] = '#';
				}
			}
		}
		if (operator instanceof PaintSquare){
			PaintSquare square = ((PaintSquare) operator);
			for(int i = square.getX1(); i <= square.getX2(); i++){
				for(int j = square.getY1(); j <= square.getY2(); j++){
					this.canvas[i][j] = '#';
				}
			}
		}
	}
	
	/**
	 * Collection to store operators
	 */
	@Override
	public Collection<Operator> operators() {
		// TODO fill the collection with operators
		ArrayList<Operator>	operators = new ArrayList<Operator>();
		//go through the OPERATORS array
		//(all possible operators
		for (Operator op : OPERATORS) {
			//add it to the list if applicable
			if (isApplicable(op)) operators.add(op);
		}
		return operators;
	}

	/**
	 * clone method to create new states in the search graph
	 */
	@Override
	public State clone() {
		Paint copy = null;
		try {
			copy = (Paint) super.clone();

		} catch(CloneNotSupportedException e) {}
		copy.canvas = (char[][]) this.getCanvas().clone();
		return copy;
	}

}
