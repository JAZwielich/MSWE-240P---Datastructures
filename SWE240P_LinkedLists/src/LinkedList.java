public class LinkedList {
    _Node head;
    _Node tail;
    int size;

    /**
     * Basic constructor to create a linked list with no arguments and everything null
     */
    public LinkedList(){
        this.head = null;
        this.tail = null;
        size = 0;
    }

    /**
     * Add User method. Adds a user at either the beginning, middle or end of the linked list. It will also add them in ID
     * order
     * @param  - Name of account holder
     * @param address - Address of account holder
     * @param SSN - Social security number of account holder
     * @param depositAmount - Initial deposit amount
     */
    public void addUser(String name, String address, String SSN, int depositAmount){
        _Node newest = new _Node(name, address,SSN,depositAmount);
        newest = addUniqueID(newest);
        //If the Linkedlist is empty add a new head node
        if (this.head == null){
            addFirst(newest);
            size ++;
        }
        //if the tail is empty add the tail and connect the head to it
        else if (this.tail == null){
            addTail(newest);
            size++;
        }
        //Otherwise proceed as normal
        else {
            //Incase we are adding a node to the front of the Linkedlist
            if (newest.getID() - 1 == 0){
                this.head.setPrev(newest);
                newest.setNext(this.head);
                this.head = newest;
            }
            //incase we are adding a tail to the end of the Linkedlist
            else if (newest.getID() - 1 >= size) {
                this.tail.setNext(newest);
                newest.setPrev(tail);
                this.tail = newest;
            }
            //Incase we are adding a node to the middle of the linked list
            else {
                _Node nextNode = iterate(newest.getID() - 1);
                _Node prevNode = nextNode.prev;
                newest.setNext(nextNode);
                newest.setPrev(prevNode);
                prevNode.setNext(newest);
                nextNode.setPrev(newest);
            }
            size++;
        }
    }

    /**
     * Adds a Node to the beginning of the linked list
     * @param newest - Newest node being added to the list
     */
    protected void addFirst (_Node newest){
        //Adds first if empty list
        if (head == null){
            this.head = newest;
        }
    }

    /**
     * Adds a node to the tail of the linked list
     * @param newest - Newest node being added to the linked list
     */
    protected void addTail (_Node newest){
        newest.setPrev(head);
        head.setNext(newest);
        tail = newest;
    }

    /**
     * Gets Node from a specific ID Number (is basically iterate but it adjusts it so you can specify the ID number)
     * @param ID - The ID number to be retrieved
     * @return - Returns the node with the ID number
     */
    protected _Node getNodeWithID(int ID){
        _Node current = this.head;
        //Checks if the current is null
        if (ID <= 0|| current == null) {
            throw new RuntimeException(ID + " ID specified is too small. Please respecify ID number");
        }
        //Checks if the value is greater than the overall size of the list
        while (current != null) {
            if(current.getID() == ID) {
                return current;
            }
            current = current.next;
        }
        throw  new RuntimeException("ID " + ID + " does not exist in LinkedList");
    }
    /**
     * Iterates through the linked list to a specified point
     * @param num - The number of the position in the linked list
     * @return - Returns the node that was found at the specified indices
     */
    protected _Node iterate(int num){
        _Node current = this.head;
        //Checks if null or the value is the head
        if (num <= 0 || current == null) {
            if (num < 0 || current == null){
                throw new RuntimeException("Could not iterate to " + num + " index.");
            }
            return current;
        }
        //Checks if the value is greater than or equal to the size either way return the tail
        else if (num >= this.size){
            throw new RuntimeException("Could not iterate to " + num + " Index is out of bounds.");
        }
        //Iterates through the Linkedlist until we get to the specified indices
        else{
            for (int i = 1; i <= num; i++){
                current = current.next;
            }
        }
        return current;
    }

    /**
     * Adds a unique ID to a given node. This allows the program to keep all added users in ID order.
     * @param needsID - Gets a node that needs to be assigned an ID
     * @return - The node with the correct ID assigned to it
     */
    protected _Node addUniqueID (_Node needsID){
        int count = 1;
        //Iterates through the existing linked list and tries to find any missing IDs (from previous deletions)
        for (int i = 0; i <= this.size - 1; i++){
            _Node current = iterate(i);
            //If it's the first node assign ID "1"
            if (current == null){
                if (this.head == null) {
                    needsID.setID(1);
                } else {
                    needsID.setID(2);
                }
                return needsID;
            }
            //If there is a gap in the ID numbers assign it the first number in the gap
            if (count != current.getID()){
                needsID.setID(count);
                return needsID;
            }
            count++;
        }
        //Otherwise go in order and assign the last available ID
        needsID.setID(count);
        return needsID;
    }

    /**
     * Deletes the specified user at a given location
     * @param ID - The ID of the user
     */
    public void deleteUser(int ID){
        //Iterates to the specified user ID to be deleted (-1 because our list starts at index 0)
        _Node nodeDelete = getNodeWithID(ID);
        //If the Node is the only one there start a new LinkedList
        if (nodeDelete.prev == null  && nodeDelete.next == null){
            this.head = null;
            this.tail = null;
        }
        //If the user wants to delete the head node. Set the head to one node up and erase the pathway back
        else if (nodeDelete.prev == null) {
            this.head = head.next;
            head.prev = null;
        }
        //Else If the user wants to delete the tail node. Set the tail to one node back and erase the old tail
        else if (nodeDelete.next == null){
            this.tail = tail.prev;
            tail.next = null;
        }
        //Else the user wants to delete the middle node remove the pathways to this original node and set the other two nodes to only lead to each other
        else
        {
            _Node prevNode = nodeDelete.prev;
            _Node nextNode = nodeDelete.next;
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }
        this.size--;
    }

    /**
     * Tranfers money from one ID to another ID
     * @param payerID - Money to be transferred from this account
     * @param payeeID - Money to be transferred to this account
     * @param amount - The amount to be transferred
     */
    public void payUserToUser(int payerID, int payeeID, int amount){
        _Node payer = getNodeWithID(payerID);
        _Node payee = getNodeWithID(payeeID);
        //Checks if the payer actually has enough money
        if (payer.getMoneyTotal() < amount) {
            throw new RuntimeException(payer.getName() + " has Insufficient funds");
        }
        //Transfers money between users
        payer.setMoneyTotal(payer.getMoneyTotal() - amount);
        payee.setMoneyTotal(payee.getMoneyTotal() + amount);
    }

    /**
     * Merges two accounts as long as the name, social, and address are all the same
     * @param ID1 - ID number of the first account
     * @param ID2 - ID number of the second account
     */
    public void MergeAccounts(int ID1, int ID2){
        _Node firstAccount;
        _Node secondAccount;
        //Checks which ID number is bigger
        if (ID1 < ID2) {
            firstAccount = getNodeWithID(ID1);
            secondAccount = getNodeWithID(ID2);
        } else if (ID1 > ID2) {
            firstAccount = getNodeWithID(ID2);
            secondAccount = getNodeWithID(ID1);
        } else{
            throw new RuntimeException("Same account cannot merge");
        }
        //Checks if both accounts have the same owner CREATE NEW METHOD FOR CHECKING
        if (firstAccount.getName() == secondAccount.getName() && firstAccount.getAddress() == secondAccount.getAddress() && firstAccount.getSocialSecurity() == secondAccount.getSocialSecurity()) {
            payUserToUser(secondAccount.getID(),firstAccount.getID(), secondAccount.getMoneyTotal());
            deleteUser(secondAccount.getID());
        } else {
            System.out.println("Account is not owned by the same user. Cannot merge.");
        }
    }

    /**
     * Returns the middle node of the linked List
     * @return - Middle node of the linked List
     */
    public _Node getMedianID(){
        return iterate((size - (size%2)) / 2);
    }

    /**
     * Takes two Company LinkedLists and merges them into one entity
     * @param companyOne - First company to be merged. This one will maintain its ID structure
     * @param companyTwo - Second company to be merged. This one's ID will be added to the existing company indices
     * @return - Combined linked list with both companies
     */
    public LinkedList corporateMerger (LinkedList companyOne, LinkedList companyTwo) {
        int count =0;
        _Node temp = companyTwo.iterate(count);
        //Checks if there is a node to be merged
        if (temp == null) {
            throw  new RuntimeException("Company has no users to merge. Process completed");
        }
        //While there are nodes to be added. Add them to companyOne
        while (temp.getNext() != "Null") {
            temp = companyTwo.iterate(count);
            companyOne.addUser(temp.getName(), temp.getAddress(),
                    temp.getSocialSecurity(), temp.getMoneyTotal());
        count++;
        }
        //Return the final version of companyOne
        return companyOne;
    }

}
