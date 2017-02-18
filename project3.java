import java.io.BufferedReader;
import java.io.FileReader;

public class project3 {
	public static void main(String[] args){
		try
	     {
	     	FileReader fileReader = new FileReader("project3.txt");			//read text file
	        BufferedReader bufferedReader = new BufferedReader(fileReader);	
	        ArithmeticexpressionTree AETree = new ArithmeticexpressionTree();//create a tree
	        String input;
	        String output;
	      
	         while ((input= bufferedReader.readLine()) != null) {		//reading every line from text file
	        	  IntoPost theTrans = new IntoPost(input);				//store the string string in the file to IntoPost class.
	        	  output=input.replaceAll("\\s","");					//remove all the spaces
	    	      System.out.println("Infix notation "+output);		//print out the infix
	        	  theTrans.intoPostfix(); 							//make the infix to the post fix
	        	  System.out.print("Post notation ");					        	  							
	        	  AETree.Tree(theTrans.printPost());				//Print out the Post fix notation and Pass in Post fix to 
	        	  													//Arithmetic expression Tree and print out the result 
	    	      
	         }
	         bufferedReader.close();		//close bufferedreader
	         
	     }
	     catch(Exception e)				//catch exception
	     {
	         System.out.println(e);
	     }
	}
}
