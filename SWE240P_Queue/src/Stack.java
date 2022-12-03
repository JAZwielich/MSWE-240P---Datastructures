public class Stack<E> {
    private Queue<E> queueOne = new Queue<>();
    private Queue<E> queueTwo = new Queue<>();
    private int size;

    /**
     * Default constructor for a stack of size 0;
     */
    public Stack(){
        size = 0;
    }

    /**
     * Pushes an element onto the stack
     * @param element - Whatever element is being added to the stack
     */
    protected void push (E element){
        size++;
        if (queueOne.size() == 0){
            queueOne.enqueue(element);
        } else {
            int size = queueOne.size() - 1;
            for (int i = 0;i <= size; i++){
                queueTwo.enqueue(queueOne.dequeue());
            }
            size = queueTwo.size() - 1;
            queueOne.enqueue(element);
            for (int i = 0; i <= size; i++){
                queueOne.enqueue(queueTwo.dequeue());
            }
        }
    }

    /**
     * Retruns and removes the top element of the stack
     * @return - Returns the element at the top of the stack
     */
    protected E pop(){
        if (size == 0){
            throw new RuntimeException("Cannot pop an empty stack");
        }
        size--;
        return queueOne.dequeue();
    }

    /**
     * returns the top of the stack but does not remove it
     * @return - The top of the stack
     */
    protected E peep(){
        if (size ==0){
            throw new RuntimeException("Cannot peep an empty stack");
        }
        size--;
        return queueOne.poll();
    }

    /**
     * returns an int of the size of the Stack
     * @return - Int indicating the number of elements in the stack
     */
    protected int size(){
        return this.size;
    }

    /**
     * Prints all items in the stack
     */
    protected void print(){
        queueOne.print();
    }
}
