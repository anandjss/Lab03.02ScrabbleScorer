import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Scrabble Scorer app calculates points for valid scrabble plays
 * @version January 26, 2023
 * @author 24jayashankar
 */
public class ScrabbleScorer {
    private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int[] values = {1, 3, 3, 2, 1, 4, 2, 4,1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    private ArrayList<ArrayList<String>> dictionary;
    /**
     * simple constructor that initializes
     */
    public ScrabbleScorer() {
        dictionary = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            dictionary.add(new ArrayList<String>());
        }
        buildDictionary();


    }

    /**
     * Parses through the Scrabble Words text file and creates buckets and then sorts them alphabetically
     */
    public void buildDictionary() {
        try{
            Scanner in = new Scanner(new File("SCRABBLE_WORDS (2).txt"));
            while(in.hasNext()) {
                String word = in.nextLine();
                int index = alpha.indexOf(word.substring(0, 1));
                dictionary.get(index).add(word);

            }
            in.close();
            // now I need to sort all the buckets
            for(int i = 0; i < dictionary.size(); i++)  {
                Collections.sort(dictionary.get(i));
            }

        }
        catch(Exception e)  {
            System.out.println("Error here: " + e);
        }


    }
    /**
     * Determines the validity of the user input based on whether the word can be found in dictionary and eliminates words with numbers and null inputs
     * @param word user input of a potential Scrabble Word
     * @return true if the word is a valid Scrabble play; false if the word is not
     */

    public boolean isValidWord(String word) {
        // find the correct bucket for word by looking at the first letter
        // find indexOf the first letter in alpha
        // get the corresponding bucket
       if(word.length() > 0)    {
           if(alpha.indexOf(word.substring(0,1)) >= 0  && (Collections.binarySearch(dictionary.get(alpha.indexOf(word.substring(0,1))), word) >= 0 ))
               return true;
           else
               return false;
       }
       else
           return false;
        }


    /**
     * Iterates through the user input and assigns values to each letter based Arraylist "values" and then sums up the points to get a total
     * @param word user input of a potential Scrabble Word
     * @return # of points that the Scrabble word generates
     */
    public int getWordScore(String word)    {
        int score = 0;
        for(int i = 0; i<word.length(); i++) {
         // length w/ parentheses because it's a string; with parentheses is for array
            int index = alpha.indexOf(word.substring(i, i+1));
            score += values[index];

        }
        return score;
    }

    /**
     * Main method for class Scrabble Scorer
     * @param args command line arguments, if needed
     */

    public static void main(String[] args) {
        ScrabbleScorer app = new ScrabbleScorer();
        System.out.println("* Welcome to the Scrabble Word Scorer app *");
        Scanner in  = new Scanner(System.in);
        while(true) {
            System.out.print("Enter a word to score or 0 to quit: ");
            String word = in.nextLine().toUpperCase();
            if(word.equals("0"))
                break;
            if(app.isValidWord(word))
                System.out.println(word + " = " + app.getWordScore(word) + " points ");
            else
                System.out.println(word + " is not a valid word in the dictionary");

        }
        System.out.println("Exiting the program thanks for playing");
    }

}
