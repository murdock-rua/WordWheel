package ie.corktrainingcentre.wordwheel;

import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by AppDeveloper on 10/06/2015.
 */
public class WordWheel {

    private final String TAG = "Word Wheel";
    private final boolean   LOGS_ON = true;

    private String word;
    private char centreChar;
    private int wordlenght = 9;


    public WordWheel(int wordLength){

        this.wordlenght = wordLength;
        setWord(getWordFromDic());
        setCentreLetter();

    }
// TODO Make this method more usable to by using a parameter of the file to be read
    private String getWordFromDic() {

        String file = "res/raw/list_of_words.txt"; // res/raw/test.txt also work.
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        String word = "";
        ArrayList<String> words = new ArrayList<>();

//      read the file and add all the words that are equal the the length we have selected
        try {
            while ((word = bufferedReader.readLine()) != null)
                if(word.length() == getWordlenght())
                    words.add(word);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        pick out a random word from the words arraylist that we have populated
        Random rand = new Random();
        if(LOGS_ON) Log.d(TAG, "words size is " + words.size());
        int index = rand.nextInt(words.size());

        return words.get(index);

    }



    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public char getCentreChar() {
        return centreChar;
    }

    public void setCentreChar(char centreChar) {
        this.centreChar = centreChar;
    }

    public int getWordlenght() {
        return wordlenght;
    }

    public void setWordlenght(int wordlenght) {
        this.wordlenght = wordlenght;
    }

    private void setCentreLetter(){

        Random rand = new Random();
        int centreIndex = rand.nextInt(getWordlenght());
        if(LOGS_ON) Log.d(TAG, "Setting centre character " + getWord().charAt(centreIndex));
        setCentreChar(getWord().charAt(centreIndex));


    }
    // Jumbles all the letters of a word and returns the result
    public String scrambledWord(String word)
    {
        char[] scrambled = word.toCharArray();

        int lenght = word.length();
        Random random = new Random();
        int oldCharIndex=0;
        int newCharIndex=0;
        char tempChar= ' ';
        char c = ' ';

        for(int i=0; i< 50;i++)
        {
            // Assign a ramdom index to oldCharIndex within the bounds of the word
            oldCharIndex = random.nextInt(lenght);

            // Assign a ramdom index to newCharIndex within the bounds of the word
            newCharIndex = random.nextInt(lenght);

            // save the old characters that is going to be moved
            tempChar = scrambled[oldCharIndex];
            scrambled[oldCharIndex] = scrambled[newCharIndex];
            scrambled[newCharIndex] = tempChar;

        }
        Log.d(TAG,scrambled.toString());
        String string1 = new String(scrambled);
        return string1;


    }


}
