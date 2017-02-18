

public class IntoPost {
	private LinkedStack<Character> theStack; 				// stack to store operator
	private LinkedQueue<String> theQueue= new LinkedQueue<>();          		// Queue to store the output
	private LinkedQueue<String> Queue = new LinkedQueue<>();	
	private String input; 									// string that store the input
	public IntoPost(String in) {								
	      input = in;											// store string form fill to input
	      theStack = new LinkedStack();		// assume the stack array's size is the length of input string
	}
	public LinkedQueue<String> intoPostfix() {
		String e="";
		   for (int j = 0; j < input.length(); j++) {			// convert string in to char one by one  
			   char character = input.charAt(j); 	
			   	if(character==' '){
			   		
			   	}else
		         if (character=='+'||character=='-'||character=='–'||character=='*'||character=='/'){ 
		        	 checkPrec(character);						// if character is operator it will go to checkPrec to check precedence
		         }else
		         if(character=='('){							// if character is ( will store in to operation stack
		        	 theStack.push(character);
		         }else
		         if(character==')'){							// if character is ) will pop all the operation in the stack
		        	 gotParen(character); 
		         } 
		         
		         else{ 
		        	e=checkNext(input,j);		// check if the next character can combine with the current character
		        	theQueue.enqueue(e);		// if is not operator and operation will add to Queue
		        	j+=e.length()-1;			// increment j by the length of the string(which mean how much it go )
		        
		         }
		     	
		    
		    }
		    while (!theStack.isEmpty()) {						// if there is any operator still the the stack add the to Queue
		         theQueue.enqueue(Character.toString(theStack.pop()));
		    }
		      
		      return theQueue; 									//return output
	}
	
	public String checkNext(String s ,int i){					//check if the there is any char that can be a double or other operand  
		StringBuilder str = new StringBuilder();		
		str.append(s.charAt(i));
		while( i+1 <=s.length()-1){								//make sure there is a next character to check
			if(checkChar(s.charAt(i+1))||checkDouble(s.charAt(i+1))){		//if the next character is character or double
				str.append(s.charAt(i+1));									//then combine those character until it screen a operator
			}else{
				break;														//and break out the loop
			}
			i++;												// increment the i as we go though the string
		}
		return str.toString();								// as we finish finding the character we return the string the we combine
	}
	public boolean checkChar(char chr){						// check if the character is valid  
		   return Character.isLetter(chr);
	}
	public boolean checkDouble(char chr){					//check if the char is digit or dot
		 return chr=='.'||Character.isDigit(chr);
	}
	public void checkPrec(char operator) {
		      while (!theStack.isEmpty()) {						// check the operation stack
		         char preOp = theStack.pop();					// pop out the previous operation
		         if (preOp == '(') {							// if operator is parenthesis  
		            theStack.push(preOp);						// we store the don't pop operator so we push back the operator
		            break;
		         }
		         else {
		            if (precLevel(preOp) < precLevel(operator)) { 		// compare previous operator level for current operator and previous operator
		               theStack.push(preOp);							// if current operator larger push back the operator because we don't need to pop
		               break;
		            }
				    else
				    	theQueue.enqueue(Character.toString(preOp));							// else add the operator to output string
		         }
		      }
		      theStack.push(operator);									// push back the operator if current operator level smaller than previous operator or is not "("
	}
	public int precLevel(char op){				// get the precedence's level
		   if (op == '+' || op == '-'|| op =='–'){	// + and - get low precedence level
			   return 1;
		   }else									// else get high precedence level
			   return 2;
	}
	public  void gotParen(char ch){ 
		   while (!theStack.isEmpty()) {			// every operator that is in the parenthesis')' pop out 
		      char paren = theStack.pop();
		      if (paren == '(') 						// until '('
		      break; 
		      else
		      theQueue.enqueue(Character.toString(paren)); 				// and add the output string
		   }
	}   
	public LinkedQueue<String> printPost() {			//print out the queue and return the queue
		String tem;
		while(!theQueue.isEmpty()){						//while queue is empty
			tem=theQueue.dequeue();						//dequeue the queue and store the value into tem
			System.out.print(tem+" ");					//print out the tem
			Queue.enqueue(tem);							//store them in to other queue
			
		}
		System.out.println();
		return Queue;									//return the queue
	}
	
 
     
 }