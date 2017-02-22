import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Project3 {

    public static void main(String[] args) throws IOException {
        File temp = new File("project3.txt"); //opens the text file
        Scanner file = new Scanner(temp);
        Queue<String> print; //store the queue to print out the original expression
        Queue<String> print2; //store the queue to print out the postfix expression
        int counter = 1;


        while (file.hasNext()) { //while there is a next line)
            String s = file.nextLine().trim(); //this will read in the first line store it to String s, and ignore all the leading and ending white spaces

            if(s.length() == 0){ //if encounter a blank line, skip it
                continue;
            }
            System.out.println("#" + counter + ":"); //will number each expression that is to be solved

            String t = "";

            System.out.println("Infix expression: " + s); //prints the original expression

            print = InfixToPostfix.infixToPostfix(s); //we will call the method to change infix to postfix for string s
            while (!(print.isEmpty())){ //while the queue is not empty, we will dequeue until empty and append all expressions into the String t
                t += print.dequeue() + " ";
            }
            System.out.println("Postfix expression: " + t); //print the postfix expression

            print2 = InfixToPostfix.infixToPostfix(s); //once again call the method to change infix to postfix
            ArithmeticExpTree.createTree(print2); //create the tree from the postfix queue and compute the final value

            System.out.println();  //
            System.out.println();
            counter++;
        }
    }
}
