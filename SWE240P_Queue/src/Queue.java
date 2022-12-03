import java.util.ArrayList;
import java.util.List;

public class Queue<E> {
    private List<E> localQueue = new ArrayList<>();
    private int size;

    /**
     * Generic constructor for a Queue that doesn't have any elements and a default size of 0
     */
    public Queue() {
        size = 0;
    }

    /**
     * Pushes a single element onto the beginning of the Queue
     *
     * @param element - The generic element that is being pushed onto the queue
     */
    public void enqueue(E element) {
        localQueue.add(0, element);
        size++;
    }

    /**
     * Pops a single element off of the top of the queue
     *
     * @return E - the item that was popped off of the queue
     */
    public E dequeue() {
        if (this.size == 0) {
            throw new RuntimeException("Cannot dequeue Empty Queue");
        }
        E popped = localQueue.get(this.size - 1);
        localQueue.remove(this.size - 1);
        size--;
        return popped;
    }

    /**
     * Looks at the earliest element without removing it
     *
     * @return E - The item that is on the top of the queue
     */
    public E poll() {
        if (this.size == 0) {
            throw new RuntimeException("Cannot poll Empty Queue");
        }
        return localQueue.get(this.size - 1);
    }

    /**
     * Returns the size of the queue
     *
     * @return int - the size of the stack as an int value
     */
    public int size() {
        return this.size;
    }

    /**
     * prints all items in the Queue
     */
    public void print(){
        for (int i = 0; i < localQueue.size(); i++){
            System.out.print(localQueue.get(i) + "->");
        }
        System.out.print("None\n");
    }
}

