
public class InfixToPostfix {

    public static Queue<String> infixToPostfix(String expression){
        DynamicArrayStack<String> s = new DynamicArrayStack<String>(); //creates a new stack object
        LinkedQueue<String> result = new LinkedQueue<String>();
        for (int i = 0; i < expression.length(); i++){ //iterate through the expression that was passed in
            if(isOperand(expression.charAt(i))) { //if character i in the expression is an operand
                String operand = expression.substring(i,i+1);  //append that operand into our string "operand"
                while ((i != expression.length()-1) && isOperand(expression.charAt(i+1))) {  //while the next character is also an operand, append that character into our string "operand"
                    operand += expression.substring(i+1,i+2);
                    i++;
                }
                result.enqueue(operand);  //enqueue the final operand into our queue
            }

            else if (isOperator(expression.charAt(i))){ //if the character at the position "i" is an operator
                while (!s.isEmpty() && hasHigherPrecedent(s.top(), expression.substring(i,i+1)) && !(s.top().contains("("))){
                    //while the stack is not empty and if the operator at the top of stack is higher than the operator at
                    //position "i" and if the top of the stack is not an open parenthesis
                    result.enqueue(s.top());//append the operator at position "i" to the result queue
                    s.pop(); //remove the operator at the top of the stack
                }
                s.push(expression.substring(i,i+1)); //if the character at position "i" does not satisfy the conditions in while loop
                //push that operator on the stack
            }

            else if (expression.charAt(i) == '('){ //if character at position "i" is an open parenthesis
                s.push(expression.substring(i,i+1)); //push the open parenthesis onto the stack
            }

            else if (expression.charAt(i) == ')'){ //if character at position "i" is a closed parenthesis
                while (!s.isEmpty() && !(s.top().contains("("))) { //while stack is not empty and top of the stack is not an open parenthesis
                    result.enqueue(s.top()); //append the operator at top of the stack to the result queue
                    s.pop(); //pop the operator at top of stack
                }
                s.pop(); //pop top of stack if above is not met
            }
        }
        while(!s.isEmpty()){ //while the stack is not empty
            result.enqueue(s.top()); //append the rest of the operators left in the stack to the result queue
            s.pop(); //pop all the operations left in the stack
        }
        return result;
    }

    private static boolean hasHigherPrecedent(String c, String d) { //checks for higher precedent
        if ((c.contains("*") || c.contains("/")) && (d.contains("+") || d.contains("-"))) {
            return true;
        }
        else if ((c.contains("+") || c.contains("-")) && (d.contains("*") || d.contains("/"))) {
            return false;
        }
        else if ((c.contains("+") || c.contains("-")) && (d.contains("+") || d.contains("-"))) {
            return true;
        }
        else return false;
    }

    private static boolean isOperand(char c){ //checks if the character is an operand
        if (c == '*' || c == '/' || c == '+' || c == '-' || c == '(' || c == ')' || c == ' '){
            return false;
        }
        else return true;
    }

    public static boolean isOperator(char c){ //checks if character is an operator
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }
}