public class Main {
    public static void main(String[] args) {
        Queue testQueue = new Queue();
        Stack testStack = new Stack();
        //Load Queue
        testQueue.enqueue("First Added");
        testQueue.enqueue("Second Added");
        testQueue.enqueue("Third Added");
        testQueue.enqueue("Fourth Added");
        //Print Queue
        testQueue.print();
        System.out.println("Dequeue one: " + testQueue.dequeue());
        //Load Stack
        testStack.push("First Added");
        testStack.push("Second Added");
        testStack.push("Third Added");
        testStack.push("Fourth Added");
        //Print Stack
        testStack.print();
        System.out.println("Pop one: " + testStack.pop());
    }
}