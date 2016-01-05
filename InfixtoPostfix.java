import edu.princeton.cs.algs4.*;

public class InfixtoPostfix {


	public static boolean comparePrior(String o1, String o2) {
		if(o2.equals("(")) {
			return true;
		}
		if(o1.equals("*") || o1.equals("/")) {
			if(o2.equals("+") || o2.equals("-")) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		NodeStack<String> stack = new NodeStack<String>();

		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(s.equals("(")) {
				stack.push(s);
			} else if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
				if(stack.isEmpty()) {
					stack.push(s);
				} else if(comparePrior(s, stack.peek())) {
					stack.push(s);
				} else {
					while(!stack.isEmpty() && !comparePrior(s, stack.peek())) {
						StdOut.print(stack.pop() + " ");
					}
					stack.push(s);
				}
			} else if(s.equals(")")) {
				while(!stack.isEmpty()) {
/*					if(stack.pop().equals("(")) {
						break;
					} else {
						StdOut.print(stack.pop() + " ");
					}*/
					String p = stack.pop();
					if(p.equals("(")) {
						break;
					} else {
						StdOut.print(p + " ");
					}
				}
			} else {
				StdOut.print(s + " ");
			}
		}
		while(!stack.isEmpty()) {
			StdOut.print(stack.pop() + " ");
		}
	}


}