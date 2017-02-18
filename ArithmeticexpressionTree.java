import java.util.Iterator;
import java.util.Scanner;

public class ArithmeticexpressionTree {
	LinkedStack<BinaryTree> s= new LinkedStack<>();
	BinaryTree<String> Bt=new BinaryTree<>();
	
	public void Tree(LinkedQueue<String> Q){
		while(!Q.isEmpty()){								//check if the Queue is not empty 
			
			if(isOperator(Q.first())){						//if value in the queue is operator then
				BinaryTree<String> bt=new BinaryTree<>(Q.first()); //create the tree
				BinaryTree<String> right=s.pop();					//pop element on the stack and store it to the right side of the tree
				BinaryTree<String> left=s.pop();					//pop element on the stack and store it to the right side of the tree
				bt.attach(left,right);								// attach left and right
				s.push(bt);											//and push it on the stack
				Q.dequeue();									//then dequeue the queue
			}else{														//if the element in the linkedQueue is a Operand
				BinaryTree<String> bt1=new BinaryTree<>(Q.first());		//create the tree
				s.push(bt1);											//push the stack
				Q.dequeue();											//dequeue the queue
			}
			
		}
		System.out.println( getValue(s.pop()));   //call the function get value to  
	}
	public boolean isOperator(String op) {			// Check is operator or not
        if (op.contains("+") || op.equals("-")		
                || op.equals("*") || op.equals("/")
               ) {
            return true;
        }
        return false;
	}
	public boolean isNum(String num) {				//check if is double
		
		try{
			double n=Double.parseDouble(num);}
		catch(NumberFormatException e){
			return false;
		}
        return true;

	}
	public double getValue(BinaryTree B){
		LinkedStack<Double>ls= new LinkedStack<>();			//create a linked stack
		Iterator<String> Itr= B.iterator();					//create a iterator for the tree
		Scanner s = new Scanner(System.in);					//create a scanner
		double n ;											
		while(Itr.hasNext()){								// check is there is next
			String Str=Itr.next().toString();				//get the string from the tree
			if(!isNum(Str)&&!isOperator(Str))				//if string is not number or operator
			{	
				System.out.println("Please enter value for "+Str);		// then ask user to enter the value
				while(!s.hasNextDouble()){
					System.out.println("Please enter valid value for "+Str);	//if user didn't enter the valid entry
					s.next();													//keep asking user
				};
				n=s.nextDouble();												//store the number to n
				ls.push(n);														//and push the double in to stack
			}else
			if(isNum(Str)){														//if is number
				ls.push(Double.parseDouble(Str));								//push the number on to the stack
			}else
			if(isOperator(Str)){												//if is operator
			double Left= ls.pop();												//pop the the stack
			double right= ls.pop();												
			double result=0;
				if(Str.equals("+")){											//match the operator to do it's operation 
				result=right+Left;
				}
				if(Str.equals("*")){
				result=right*Left;
				}
				if(Str.equals("/")){
				result=right/Left;
				}
				if(Str.equals("-")){
				result=right-Left;
			}
			ls.push(result);													//and push the result onto the stack
			}
		}
		return ls.pop();											//then return only element on the stack which will be the stack
		
	}
	
}