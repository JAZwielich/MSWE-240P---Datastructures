import java.util.ArrayList;
import java.util.List;

public class Stack<E> {
    private List<E> localStack = new ArrayList<>() {
    };
    private int size;
    private List<String> intList = new ArrayList<>();
    private List<String> operatorList = new ArrayList<>();

    /**
     * Generic constructor for a stack that doesn't have any elements and a default size of 0
     */
    public Stack() {
        size = 0;
        for (int i = 0; i <= 9; i++) {
            intList.add(Integer.toString(i));
        }
        operatorList.add("*");
        operatorList.add("/");
        operatorList.add("+");
        operatorList.add("-");
    }

    /**
     * Pushes a single element onto the beginning of the stack
     *
     * @param element - The generic element that is being pushed onto the stack
     */
    public void push(E element) {
        if (localStack.isEmpty()) {
            localStack.add(0, element);
        } else {
            localStack.add(0, element);
        }
        size++;
    }

    /**
     * Pops a single element off of the top of the stack
     *
     * @return E - the item that was popped off of the stack
     */
    public E pop() {
        if (this.size == 0) {
            throw new RuntimeException("Cannot Pop Empty Stack");
        }
        E popped = localStack.get(0);
        localStack.remove(0);
        size--;
        return popped;
    }

    /**
     * Looks at the top of the element without removing it
     *
     * @return E - The item that is on the top of the stack
     */
    public E peep() {
        if (this.size == 0) {
            throw new RuntimeException("Cannot Peep Empty Stack");
        }
        return localStack.get(0);
    }

    /**
     * Returns the size of the stack
     *
     * @return int - the size of the stack as an int value
     */
    public int size() {
        return this.size;
    }

    /**
     * Attempts to solve the current elements in a stack assuming they are an arithmetic equation
     *
     * @param equation - The equation being entered into the Stack
     * @return - The solution of the problem
     */
    public Double solveStack(String equation) {
        //Remove whitespace from the equation
        String newEquation = equation.replaceAll(" ", "");
        //Throw exception if the equation is empty
        if (equation == "") {
            throw new RuntimeException("Equation is empty");
        }
        //Throw an exception if there is an illegal character, throw an exception if there is.
        checkIllegalCharacter(newEquation);
        //If it's not an integer or an operator we throw an exception
        //Create two separate stacks. One for numbers and one for arithmetic expressions
        Stack symbolStack = new Stack();
        Stack numberStack = new Stack();
        //First pass to identify location of everything
        int indexWindowBeg = 0;
        int indexWindowEnd = 1;
        int size = newEquation.length();
        for (int i = 1; i <= size; i++){
            //Make sure our indexWindowEnd is not passed the size of the equation
            if (indexWindowEnd >= size) {
                numberStack.push(newEquation.substring(indexWindowBeg,indexWindowEnd));
                break;
            }
            Character tempBeg = newEquation.charAt(indexWindowBeg);
            Character tempEnd = newEquation.charAt(indexWindowEnd);
            //Only used to make sure we don't have a minus sign next to a different operand. We also don't want to throw an
            //We assign it 1 if it is true since we really don't care what this is if nothing is there
            Character tempBegPlusOne = newEquation.charAt(indexWindowBeg + 1) == 0 ?  '1' : newEquation.charAt(indexWindowBeg + 1);
            //We check out indexWindowBeg. We are cool with a negative sign anything else is not good
            //We also do not want to see another symbol after if we have a negative
            if (operatorList.contains(tempBeg.toString())&& tempBeg != '-') {
                throw new RuntimeException("Cannot have more than one operator next to each other or start an equation with one (unless its a '-' sign");
            } else if(tempBeg == '-' && operatorList.contains(tempBegPlusOne.toString())) {
                throw new RuntimeException("Cannot have an operator after a negative sign");
            } else if (indexWindowEnd == size-1 && operatorList.contains(tempEnd.toString()))  {
                throw new RuntimeException("Cannot end an equation with an operator");
            }
            //The actual part of the code that stores the numbers and operators in their respective stacks
            if(intList.contains(tempEnd.toString())){
                indexWindowEnd++;
            } else if (operatorList.contains(tempEnd.toString())) {
                symbolStack.push(tempEnd.toString());
                numberStack.push(newEquation.substring(indexWindowBeg,indexWindowEnd));
                indexWindowBeg = indexWindowEnd + 1;
                if (indexWindowBeg + 1 > size) {
                    numberStack.push(newEquation.substring(indexWindowBeg,indexWindowBeg));
                    break;
                }
                indexWindowEnd = indexWindowBeg + 1;
            }
        }
        return Double.parseDouble(solveEquation(numberStack, symbolStack));
    }

    /**
     *Checks if there is an illegal operator and throws an exception if there is
     * @param equation - An equation with only numbers and valid operands
     */
    public void checkIllegalCharacter (String equation) {
        for (int i = 0; i <= equation.length() - 1; i++){
            Character temp = equation.charAt(i);
            if (!intList.contains(temp.toString()) && !operatorList.contains(temp.toString())){
                throw new RuntimeException("Illegal character cannot parse equation");
            }
        }
    }

    public String solveEquation (Stack numberStack, Stack symbolStack){
        Stack tempNumberStack = new Stack();
        Stack tempSymbolStack = new Stack();
        //Put the stack back in order by loading into another stack and pop it back out
        int size = symbolStack.size;
        for (int n = 0; n <= size -1; n++){
            tempSymbolStack.push(symbolStack.pop());
        }
        size = numberStack.size;
        for (int n = 0; n <= size - 1; n++){
            tempNumberStack.push(numberStack.pop());
        }
        //Now that the stacks are in the right order put them back in the normal stacks
        numberStack = tempNumberStack;
        symbolStack = tempSymbolStack;
        tempSymbolStack = new Stack();
        tempNumberStack = new Stack();
        //Cycle through operators and solve one operator at a time.
        for (int i = 0; i <= operatorList.size() - 1; i++){
            int numberSize = numberStack.size;
            for (int n = 0; n <= numberSize - 1; n++){
                String temp = (String) numberStack.pop();
                //If the symbol matches the one according to PEMDAS we take the operand and use it with the two numbers near it;
                if  (0 == symbolStack.size){
                    tempNumberStack.push(temp);
                } else if (((String) symbolStack.peep()).contains(operatorList.get(i))){
                    String sum = eval(temp, (String) numberStack.pop(), (String)symbolStack.pop());
                    numberStack.push(sum);
                } else {
                    tempSymbolStack.push(symbolStack.pop());
                    tempNumberStack.push(temp);
                }
            }
            //Reload the temporary stacks into their original stacks
            size = tempSymbolStack.size;
            for (int n = 0; n <= size -1; n++){
                symbolStack.push(tempSymbolStack.pop());
            }
            size = tempNumberStack.size;
            for (int n = 0; n <= size - 1; n++){
                numberStack.push(tempNumberStack.pop());
            }
            //Empty the temporary stacks
            tempSymbolStack = new Stack();
            tempNumberStack = new Stack();
        }
        return (String) numberStack.pop();
    }

    /**
     * A simple evaluation expression that uses a switch to take two variables and add them depending on the operand
     * given
     * @param x - The first number
     * @param y - The second number
     * @param symbol - The operand for the equation
     * @return the sum of the two values
     */
    public String eval (String x, String y, String symbol){
        double sum = 0;
        //Sometimes a zero gets turned into
        if (y == null){
            y="0";
        }
        switch (symbol) {
            case "*":
                sum += Double.parseDouble(x) * Double.parseDouble(y);
                break;
            case "/":
                if (0==Double.parseDouble(y)){
                    throw new RuntimeException("Cannot Divide by zero");
                }
                sum +=  Double.parseDouble(x) / Double.parseDouble(y) ;
                break;
            case "+":
                sum += Double.parseDouble(x) + Double.parseDouble(y);
                break;
            case "-":
                sum +=Double.parseDouble(x) - Double.parseDouble(y);
                break;
        }
        return Double.toString(sum);
    }
}
