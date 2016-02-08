package state;
import java.io.*;
import java.io.IOException;
import java.util.*;

public class Orchestra {

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
                        /*System.out.println(n);
                        System.out.println(m);*/
                        // skip /n
                        s.nextLine();
                        //read lines
                        int i = 0;
                        while (s.hasNextLine()){
                        aLine = s.nextLine();
                        paint[i] = aLine.toCharArray();
                        i++;
                        //System.out.println(aLine);
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
	public static void main(String[] args) {
		//get the file as first argument 
		//char [][] paint = toCharArray(args[0]);
		char [][] paint = toCharArray("D:\\UNI\\Practice\\java\\Forgatos\\src\\state\\logo.in");
		int n=paint.length;
		int	m=paint[0].length;
		 for (int j=0;j<n;j++){
         	for(int k =0;k<m;k++){
    		System.out.print(paint[j][k]);
    	}
    	System.out.println();
    }
	}
}