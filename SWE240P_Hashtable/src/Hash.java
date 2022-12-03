import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Hash {
    private List HashTable;
    private int hashSize;
    private int prime;

    /**
     * Basic Constructor
     */
    protected Hash() {
        this.prime = 19231;
        this.hashSize = 0;
        this.HashTable = new ArrayList<>();
        for (int i = 0; i <= prime; i++){
            HashTable.add(new Item<>(null, null));
        }
    }

    /**
     * Basic constructor allows for the division method to be modified
     * @param prime
     */
    protected Hash(int prime){
        this.prime = prime;
        this.hashSize = 0;
        this.HashTable = new ArrayList<>();
        for (int i = 0; i <= prime; i++){
            HashTable.add(new Item<>(null, null));
        }
    }

    /**
     * returns a number from the hash function using the MAD method
     * @param x - The string to be hashed
     * @return - The hash number
     */
    protected int hash(String x){ //Uniform hash function
        int valueOfString = 0;
        for (char value: x.toCharArray()){
            valueOfString += value;
        }
        return ((13 * valueOfString) + 17)%prime;
    }

    /**
     * inserts a string into the provided location after figuring out where it goes
     * from the Hash method
     * @param x - String that is to be stored
     */
    protected void insert(String x){
        //Inserts the string into the hash function
        int index = hash(x);
        //Creates a new node to be inserted into the HashTable
        Item newNode = new Item(index,x);
        Item temp = (Item) HashTable.get(index);
        //If the index we are trying to insert into the hashtable is empty we can just put it in
        if (temp.getKey() == null){
            HashTable.set((Integer)newNode.getKey(), newNode);
        } else { //If it's not already empty we have to go through every node in that index point and insert it at the end
            while (temp.getNextNode() != null){
                temp = temp.getNextNode();
            }
            temp.setNextNode(newNode);
        }
        hashSize++;
    }

    /**
     * Method that takes a text and scans the document it also inserts all words
     * @param textLocation - location to scan the text
     */
    public void storeText (String textLocation){
        //Referenced code from https://www.w3schools.com/java/java_files_read.asp
        try {
            File text = new File(textLocation);
            Scanner scanner = new Scanner(text);
            while (scanner.hasNext()){
                String word = scanner.next();
                if (forLetterInWord(word)){
                    insert(word);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Could not find " + textLocation);
            e.printStackTrace();
        }
    }

    /**
     * This returns true if the String given is a word or just a bunch of symbols
     * @param word - The String to be scanned to determine if it is a word
     * @return - True if this is a word
     */
    public boolean forLetterInWord(String word){
        if (word != null && word.matches("^[a-zA-Z0-9]*$")){
            return true;
        }
        return false;
    }

    /**
     * Goes to a specific index point and returns the Item from that area
     * @return - Item Node at the given index point at the hash table
     * NOTE: there could be more nodes there
     */
    public Item getItem(int key){
        return (Item) HashTable.get(key);
    }

    /**
     * Contains for looks for a specific word in the Hashtable
     * @param word - Word to be searched for
     * @return - true if the word exists
     */
    public boolean containsWord (String word){
        //Uses hash funtion to go to the correct index it would be sorted in
        int index = hash(word);
        Item temp = (Item) HashTable.get(index);
        String tempValue = (String)temp.getValue();
        //returns false if nothing is in that index point
        if (temp.getKey() == null){
            return false;
        } else if (tempValue.equals(word)){ // if our word matches the first Item at that point return true
            return true;
        } else{ //We also check any connected Item too
            while (temp.getNextNode() != null){
                temp = temp.getNextNode();
                if (((String) temp.getValue()).contains(word)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * a getter for size
     * @return - local value for size
     */
    public int size(){
        return this.hashSize;
    }

    /**
     *
     */
    public void anagramIt(String textLocation){
        //Referenced code from https://www.w3schools.com/java/java_files_read.asp
        try {
            File text = new File(textLocation);
            Scanner scanner = new Scanner(text);
            while (scanner.hasNext()){
                String word = scanner.next();
                if (forLetterInWord(word)){
                    char[] letters = word.toCharArray();
                    Arrays.sort(letters);
                    word = new String(letters);
                    if (!containsWord(word)){
                        insert(word);
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Could not find " + textLocation);
            e.printStackTrace();
        }
    }

}
