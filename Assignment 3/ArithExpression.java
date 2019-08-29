//Che-Chi (Jack) Liu
//V00850558

/*
 *The ArithExpression is an arithmetic expression that has a single real number solution. 
 *Allowable arithmetic operators are {^,*,/,+,-}. 
 *All real numbers are allowed, except in the case of the exponent in the power '^' operation, which must only be a non-negative integer. 
 *Allowable parentheses are ( and ) only.
 */
 
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class ArithExpression {
	private TokenList postfixTokens;
	private TokenList infixTokens;
	
	public ArithExpression(String word) {
		if(Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		}else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}
	
	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixToken list, where each token is 
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 || 	
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}
	
	//Determines whether a single character string is an operator.
	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}
	
	//Determines the order of an operator.
	public static int order(String op) {
		switch(op) {
			case "+":
			case "-":
				return 1;
			case "/":
			case "*":
				return 2;
			case "^":
				return 3;
			default:
				return -1;
		}
	}
	
	//Helper method that does the math.
	public static double doMath(String op, double x, double y) {
		switch(op) {
			case "+":
				return x+y;
			case "-":
				return x-y;
			case "/":
				return y/x;
			case "*":
				return x*y;
			case "^":
				return Math.pow(y, x);
			default:
				throw new InvalidExpressionException("Invalid Expression"); // invalid expression
		}
	}
	
	//Changes infix expression to postfix.
	private void infixToPostfix() {
		postfixTokens = new TokenList(infixTokens.size());
		StringStack stack = new StringStack();
		
		for(int i = 0; i < infixTokens.size(); i++) {
			String s = infixTokens.get(i);
			
			if(!isOperator(s) && !s.equals("(") && !s.equals(")")) {
				postfixTokens.append(s);
			}
			else if(s.equals("(")) {
				stack.push(s);
			}
			else if(s.equals(")")) {
				while(!stack.isEmpty() && !stack.peek().equals("(")) {
					postfixTokens.append(stack.pop());
                }
				
                if(!stack.isEmpty() && !stack.peek().equals("(")) {
					throw new InvalidExpressionException("Invalid Expression"); // invalid expression 
				}
                else {
                    stack.pop();
				}
			}
			else{
				while(!stack.isEmpty() && order(s) <= order(stack.peek())) { 
					if(stack.peek().equals("(")) {
						throw new InvalidExpressionException("Invalid Expression"); // invalid expression
					}
					postfixTokens.append(stack.pop());
				}
				stack.push(s);
			}
		}
		
		while(!stack.isEmpty()) {
            if(stack.peek().equals("(")) {
				throw new InvalidExpressionException("Invalid Expression"); // invalid expression
			}
            postfixTokens.append(stack.pop());
		}
	}
	
	//Returns a string representing the infix expression where every token is separated by a space.
	public String getInfixExpression() {
		String express = "";
		
		for(int i = 0; i < infixTokens.size(); i++) {
			if(i != infixTokens.size()-1) {
				express = express+ infixTokens.get(i)+" ";
			}else {
				express = express+ infixTokens.get(i);
			}
		}
		
		return express;
	}

	//Returns a string representing the postfix expression where every token is separated by a space.
	public String getPostfixExpression() {
		String express = "";
		
		for(int i = 0; i < postfixTokens.size(); i++) {
			if(i != postfixTokens.size()-1) {
				express = express+ postfixTokens.get(i)+" ";
			}else {
				express = express+ postfixTokens.get(i);
			}
		}
		
		return express;
	}
	
	//Evaluates the postfix expression.
	public double evaluate() {
		StringStack stack = new StringStack();
		
		for(int i = 0; i < postfixTokens.size(); i++) {
			String s = postfixTokens.get(i);
			
			if(!isOperator(s) && !s.equals("(") && !s.equals(")")) {
				stack.push(s);
			}else {
				double x = Double.valueOf(stack.pop());
				double y = Double.valueOf(stack.pop());
				
				String z = String.valueOf(doMath(s, x, y));
				stack.push(z);
			}
		}
		
		double answer = Double.valueOf(stack.pop());
		return answer;
	}

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter: ");
		
		String input = userInput.nextLine();
		
		ArithExpression test = new ArithExpression(input);
		
		System.out.println("\n");
		System.out.println("Testing getInfixExpression: ");
		System.out.println(test.getInfixExpression());
		
		System.out.println("\n");
		System.out.println("Testing getPostfixExpression: ");
		System.out.println(test.getPostfixExpression());
		
		System.out.println("\n");
		System.out.println("Testing evaluate: ");
		System.out.println("Answer is: "+ test.evaluate());
	}
}
