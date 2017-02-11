import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RFN {

    private static Scanner sc;

    private static List <String> arrayF = new ArrayList<>();     // initially record all of the words in an array
    private static List <String> arrayN = new ArrayList<>();     // determine which words are repeated
    private static List <String> arrayOnceW = new ArrayList<>(); // create an array equal to the number of NOT repeating words
    private static int countWordsInFile; // write how many words
    private static int countEWITA;       // get the array is equal to an amount of not repeating the words
    private static int [] onceW;         // here we count how many repeated words
    private static int [] sequenceOW;    // sort words by their number (onceW)
    private static int i;
    private static boolean ok;

    public static void main (String [] args) {

        open(); //open our file
        RW();   //reading this file and count how much words in a file
        if (ok) { SOW(); }
        if (ok) { OutputW(); }
    }

    private static void open () {
        try {
            sc = new Scanner (new File("text.txt")); // specify the path to the file
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "file not found");
        }
    }


    private static void RW () {
        while (sc.hasNext()) {
            arrayF.add(sc.next());
        }
        countWordsInFile = arrayF.size();
        System.out.println("quantity all words: " + countWordsInFile);
        ok = true;
    }

    /**
     * Method SOW:
     *      We fill our emptiness array
     *      create an empty array is not equal to the number of repeating words
     *      create a new array in which no repeating of words
     *      we obtain the total number of words of our text
     *      we get the number of non-repeating words
     */

    private static void SOW () {
        ok = false;
        for (i = 0; i < countWordsInFile; i ++) {
            arrayN.add(null);
        }

        for (int fixM = 0; fixM < countWordsInFile; fixM ++ ) {
            int sizeDim = 0;
            for (int dimM = 0; dimM  <= fixM ; dimM ++) {
                if ((!Objects.equals(arrayF.get(fixM), arrayN.get(dimM )))) {
                    if ((sizeDim == fixM) && (fixM == dimM)) {
                        arrayN.add(fixM, arrayF.get(fixM));
                        sizeDim ++;
                    }
                    sizeDim ++;
                }
            }
        }

        for (int w = 0; w < countWordsInFile; w ++) {
            if (arrayN.get(w) != null) {
                arrayOnceW.add(arrayN.get(w));
            }
        }
        countEWITA = arrayOnceW.size();
        onceW = new int [countEWITA];        // create array for each ONE word
        for (int q = 0; q < onceW.length; q ++) {
            onceW[q] = 0;
        }
        System.out.println("quantity every word: " + countEWITA);

        ok = true;

    }

    /**
     *  Method outputW:
     *      calculate which word is most often found
     *      we derive all the words and show their numbers
     */

    private static void OutputW() {

        for (i = 0; i < countEWITA; i ++) {
            int que = 0;
            for (int j = 0; j < countWordsInFile; j ++) {

                if (Objects.equals(arrayOnceW.get(i), arrayF.get(j))) {
                    que ++;
                }
                onceW[i] = que;
            }
        }

        sequenceOW = new int[onceW.length];
        int con = 0;
        for (int j = 0; j < onceW.length; j ++) {

            if (sequenceOW[0] < onceW[j]) {
                sequenceOW[0] = onceW[j];
                con = j;
            }
        }
        System.out.println("-------------------------\n"
                + "the greatest words: "
                + arrayOnceW.get(con) + "(" + sequenceOW[0] + ")\n"
                + "-------------------------");

        for (int art = 0; art < countEWITA; art ++) {
            System.out.println(art + 1 + ". " + arrayOnceW.get(art) + "(" + onceW[art] + ")"); // output each words once
        }
    }
}
