public class Main {
    public static void main(String[] args) {
        //Create our new linked list
        LinkedList bankOfOrangeCounty = new LinkedList();
        //Add three users
        bankOfOrangeCounty.addUser("tom", "123 fake street", "123-43-2564", 100);
        bankOfOrangeCounty.addUser("Bill", "71000 Verano Place", "654-43-4621", 200);
        bankOfOrangeCounty.addUser("Farquad", "12258 Criss Cross Lane", "324-64-52460", 800);
        bankOfOrangeCounty.addUser("jim", "7604 Palmilla Drive", "755-98-1579", 450);
        //Check out what our linked list looks like
        System.out.println("\nWhat is our Linked List?\n");
        for (int i = 0; i <= bankOfOrangeCounty.size - 1; i++){
            bankOfOrangeCounty.iterate(i).print();
        }
        //Add one user and delete another
        bankOfOrangeCounty.deleteUser(2);
        bankOfOrangeCounty.addUser("Horris","283 Taco Tuesday Street", "875-98-2365", 900);
        System.out.println("\nDelete ID 2 'Bill' and add a user 'horris'\n");
        for (int i = 0; i <= bankOfOrangeCounty.size - 1; i++){
            bankOfOrangeCounty.iterate(i).print();
        }
        //Tries to transfer money between accounts
        System.out.println("\nID 2 pays ID 4 $133 Dollars\n");
        bankOfOrangeCounty.payUserToUser(2,9, 9999);
        for (int i = 0; i <= bankOfOrangeCounty.size - 1; i++){
            bankOfOrangeCounty.iterate(i).print();
        }
        //Add new duplicate user
        System.out.println("\nAdd a second Farquad Account\n");
        bankOfOrangeCounty.addUser("Farquad", "12258 Criss Cross Lane", "324-64-52460", 123);
        for (int i = 0; i <= bankOfOrangeCounty.size - 1; i++){
            bankOfOrangeCounty.iterate(i).print();
        }
        //Which is the median account?
        System.out.println("\nWhich account is the median account?\n");
        _Node median = bankOfOrangeCounty.getMedianID();
        median.print();
        //Merge accounts example
        System.out.println("\nMerges both farquad accounts\n");
        bankOfOrangeCounty.MergeAccounts(1, 3);
        for (int i = 0; i <= bankOfOrangeCounty.size - 1; i++){
            bankOfOrangeCounty.iterate(i).print();
        }
        //Create a new linked list Bank of Los Angeles
        System.out.println("\nCreating a new bank of Los Angeles and adding some nodes to it...\n");
        LinkedList bankOfLosAngeles = new LinkedList();
        bankOfLosAngeles.addUser("Mr.Spoonz", "Somewhere in florida", "977-26-2346", 9001);
        bankOfLosAngeles.addUser("Mr.Clamz", "Somewhere in California", "555-55-5555", 3589);
        for (int i = 0; i <= bankOfLosAngeles.size - 1; i++){
            bankOfLosAngeles.iterate(i).print();
        }
        //Merge the two companies together
        System.out.println("\nNow we merge Bank of Los Angeles and Bank of Orange County Together\n");
        LinkedList bankOfSouthernCalifornia = new LinkedList();
        bankOfSouthernCalifornia = bankOfSouthernCalifornia.corporateMerger(bankOfOrangeCounty, bankOfLosAngeles);
        for (int i = 0; i <= bankOfSouthernCalifornia.size - 1; i++){
            bankOfSouthernCalifornia.iterate(i).print();
        }
        bankOfSouthernCalifornia.getMedianID().print();
    }
}