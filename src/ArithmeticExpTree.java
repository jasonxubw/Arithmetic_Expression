
import java.util.Scanner;
import java.util.Iterator;

public class ArithmeticExpTree {

    public static void createTree(Queue<String> q) { //creating the arithmetic expression tree
        LinkedStack<BinaryTree> result = new LinkedStack<BinaryTree>(); //the resulting stack containing our arithmetic expression tree
        while (!q.isEmpty()) { //while the postfix queue is not empty
            String c = q.first();
            if (c.matches(".*[a-z].*")) {    //if the string is a variable, create a new binary tree with the variable as a root and push it on top of stack
                BinaryTree created = new BinaryTree(c);
                result.push(created);
                q.dequeue();
            }
            else if (c.matches(".*\\d+.*")) {    //if the string is a number, create a new binary tree with the number as a root and push it on top of stack
                BinaryTree created = new BinaryTree(c);
                result.push(created);
                q.dequeue();
            }
            else if (isOperator(c)){     //if the string is an operator, create a new binary tree with operator as root, attach its children by popping the stack once for its right child, and again for its left child
                q.dequeue();
                BinaryTree created = new BinaryTree(c);
                BinaryTree right = result.pop();
                BinaryTree left = result.pop();
                created.attach(left, right);
                result.push(created);
            }
            else{
                q.dequeue();
            }
        }
        evaluateTree(result.pop());   //pop the tree off the stack and evaluate the tree
    }

    public static void evaluateTree(BinaryTree t) {
        LinkedStack<Double> stack = new LinkedStack<Double>();  //stack to store numbers to be evaluated
        Iterator<String> itr = t.iterator();     //create an iterator of the tree

        while (itr.hasNext()) {     //while iterator has next
            String s = itr.next().toString();
            if (s.matches("[0-9]+")) {   //if the string is a number, parse it to be double and push it on top of the stack
                stack.push(Double.parseDouble(s));
            }
            else if (isVariable(s)) {   //if string is a variable, ask user to input a number. Loop if a number is not inputted. Push that on top of the stack.
                Scanner user_input = new Scanner(System.in);
                System.out.println("Please enter a number for the variable " + '"' + s + '"' + ':');
                s = user_input.next();
                while (!isInputANumber(s)) {
                    System.out.println("You did not enter a number for the variable. Please try again: ");
                    s = user_input.next();
                }
                stack.push(Double.parseDouble(s));
            }

            else{  //if string is an operator, do the following: +, -, *, / if applicable
                double a = stack.pop();  //pop a variable off the stack and assign to a
                double b = stack.pop();  //pop a variable off the stack and assign to b
                double c = 0.0;      //our final answer
                if (isAddition(s)){  // if that string is a plus sign
                    c = b + a;
                }
                else if (isSubtraction(s)){   //if that string is a minus sign
                    c = b - a;
                }
                else if (isMultiplication(s)){   //if that string is a multiplication sign
                    c = b * a;
                }
                else if (isDivision(s)){   //if that string is a division sign
                    c = b / a;
                }
                stack.push(c);
            }
        }
        System.out.println("Your answer: " + stack.pop());   //push the final number evaluated off our stack and print it out
    }

    public static boolean isVariable(String s){  //detects if string is a variable
        char c= s.charAt(0);
        return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }


    public static boolean isOperator(String c) {   //detects if string is an operator
        return ((c.contains("+") || c.contains("-") || c.contains("*") || c.contains("/")));
    }

    public static boolean isInputANumber(String s){   //detects if the input by user is a number
        try{
            double d = Double.parseDouble(s);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    public static boolean isAddition(String s){   //is the operator a plus sign
        return (s.contains("+"));
    }  //is the operator a plus sign

    public static boolean isSubtraction(String s){    //is the operator a minus sign
        return (s.contains("-"));
    }

    public static boolean isMultiplication(String s){   //is the operator a multiplication sign
        return (s.contains("*"));
    }

    public static boolean isDivision(String s){    //is the operator a division sign
        return (s.contains("/"));
    }  //is the operator a division sign
}
