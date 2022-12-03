public class Main {
    public static void main(String[] args){
        //Check normal reading and inserting
        Hash prideAndPred = new Hash();
        String text = "pride-and-prejudice.txt";
        prideAndPred.anagramIt(text);

        System.out.println(prideAndPred.size());
        //Check anagram method
        Hash anagram = new Hash();
        text = "words-shuffled.txt";
        anagram.anagramIt(text);
        System.out.println(anagram.size());
        Hash minigram = new Hash();
        minigram.anagramIt("MiniGram.txt");
        System.out.println(minigram.size());
    }
}