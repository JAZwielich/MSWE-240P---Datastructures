import org.w3c.dom.Node;

public class _Node {
    private int id = -1;
    private String name;
    private String address;
    private String socialSecurity;
    private int moneyTotal = 0;
    _Node next;
    _Node prev;

    /**
     * Basic Constructor for a node. It does not by default specify a next node or a previous node
     * @param name - Name of the account holder
     * @param address - Address of account holder
     * @param socialSecurity - Social of the account holder
     * @param initDeposit - Initial deposit added to the money total
     */
    public _Node ( String name, String address, String socialSecurity, int initDeposit) {
        this.name = name;
        this.address = address;
        this.socialSecurity = socialSecurity;
        this.moneyTotal = initDeposit;
    }

    /**
     * Setter for the next node
     * @param next - Link to the next node in the linked list
     */
    protected void setNext (_Node next){
        this.next = next;
    }

    /**
     * setter for the prev node
     * @param prev - Link to the previous node in the linked list
     */
    protected void setPrev (_Node prev){
        this.prev = prev;
    }

    /**
     * Setter for the unique ID number of the account
     * @param id - Unique ID number for the account
     */
    protected void setID (int id){
        this.id = id;
    }

    /**
     * Setter for moneyTotal
     * @param moneyTotal - Money total to be set
     */
    protected void setMoneyTotal (int moneyTotal) {this.moneyTotal = moneyTotal;}

    /**
     * Sets address for the user account
     * @param address - Users address
     */
    protected void setAddress (String address) {this.address = address;}

    /**
     * Setter for social security number
     * @param socialSecurity - Sets a social security number for the user
     */
    protected void setSocialSecurity (String socialSecurity) {this.socialSecurity = address;}

    /**
     * setter for ID
     * @param ID - Sets the users ID number for their account
     */
    protected void setId (int ID) {this.id = ID;}

    /**
     * Sets a value for the name of the user account
     * @param name - Name of the account holder
     */
    protected void setName (String name) {this.name = name;}

    /**
     * Getter for Money total
     * @return - Money Total Value
     */
    protected int getMoneyTotal () {return this.moneyTotal;}

    /**
     * Getter for ID
     * @return - Returns an int of the ID number
     */
    protected int getID (){return this.id;}

    /**
     * Getter for address
     * @return - Returns address of the account holder
     */
    protected String getAddress () {return this.address;}

    /**
     * getter for name
     * @return - Returns the name of the account holder
     */
    protected String getName(){return this.name;}

    /**
     * Getter for social security number
     * @return - returns social security number of the user
     */
    protected String getSocialSecurity(){return this.socialSecurity;}

    /**
     * GetNext returns the first name of the user
     * @return - Name of the account holder
     */
    protected String getNext (){return this.next == null ?  "Null" : this.next.getName();}

    /**
     * getPrev returns the name of the account holder of the previous node
     * @return - returns the name of the previous nodes account holder
     */
    protected String getPrev (){return this.prev == null ? "Null" : this.prev.getName();}
    /**
     * Prints all elements of the account
     */
    public void print (){
        System.out.println("*********\nID: "+this.id + "\nName: " + this.name + "\nAddress: " + this.address + "\nSocial Security: "+ this.socialSecurity + "\nBalance: $"+ this.moneyTotal + "\nPrevious Node: " + this.getPrev() + "\nNext Node: " + this.getNext()  + "\n*********" );
    }
}
