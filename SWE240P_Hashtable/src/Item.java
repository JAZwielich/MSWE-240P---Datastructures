public class Item<K,V> { //RENAME TO ITEM
    private K key;
    private V value;

    private Item nextNode;

    /**
     * Basic constructor for the Map base
     * @param key - key used to refrence item
     * @param value - value of the item
     */
    public Item(K key, V value){
        this.key = key;
        this.value = value;
    }

    /**
     * overides existing equals method
     * @param other - the other Item Node
     * @return
     */
    public boolean equals(Item other) {
        return this.key == other.getKey();
    }

    /**
     * getter for the Key value
     * @return - return key of node
     */
    public K getKey() {
        return this.key;
    }

    /**
     * getter for value
     * @return - return value of a given node
     */
    public V getValue(){
        return this.value;
    }

    /**
     * getter for nextNode
     * @return - Returns the nextNode in the chaining collission
     */
    public Item getNextNode() {
        return nextNode;
    }

    /**
     * sets the key of Hash node
     * @param key - key of the node
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * setter for the value generic
     * @param value - sets the value of this node
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Setter for the next node to handle collisions
     * @param nextNode - The next node in the chain
     */
    public void setNextNode (Item nextNode){
        this.nextNode = nextNode;
    }

    /**
     * checks if the value of two Item items are the same
     * @param other - value of a given node to be compared
     * @return
     */
    public boolean contains(Item other){
        return this.value == other.getValue();
    }
}
