package eg.edu.alexu.csd.datastructure.hangman.cs18011373;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Hangman implements IHangman{
    public String[] dictionary;
    public int maxWrongGuesses;
    public String filedir = "src/words"; //path to file containing the dictionary
    public String secretWord=null;
    public String playerGuess=""; //the displayed word with player guessed letters

    public void readFile(String filePath) throws IOException {
        String[] words;
        BufferedReader buffread1 = new BufferedReader(new FileReader(filePath));
        BufferedReader buffread2 = new BufferedReader(new FileReader(filePath));
        int count = 0;
        while ((buffread1.readLine()) != null  ){
            ++count;
        }
        words = new String[count];
        for(int i=0;i<count;) words[i++] = buffread2.readLine();
        setDictionary(words);

    }
    @Override
    public void setDictionary(String[] words) {
        this.dictionary = words;
    }

    @Override
    public String selectRandomSecretWord() {
        Random generate = new Random();
        int randomNumber = generate.nextInt(dictionary.length);
        secretWord = dictionary[randomNumber].toLowerCase();
        for(char c : secretWord.toCharArray()){
            if(Character.isLetter(c)) playerGuess = playerGuess +"_";
            else if(c==' ')playerGuess = playerGuess+" ";
        }

        return dictionary[randomNumber];
    }

    @Override
    public String guess(Character c) throws Exception {
        if(secretWord == null || secretWord.trim().isEmpty() || playerGuess.length()!=secretWord.length()) throw new Exception("buggy secret word");
        if(!Character.isLetter(c) || c == ' ') return null;
        c = Character.toLowerCase(c);
        boolean correct=false;
        char[] tempPlayerGuess= playerGuess.toCharArray();
        int i=0;
        for(char d : secretWord.toCharArray()){
           if(c==d) {
                correct=true;
                tempPlayerGuess[i] = d;
            }
           i++;
        }
        playerGuess = String.copyValueOf(tempPlayerGuess);
        if (!correct)maxWrongGuesses--;
        if (maxWrongGuesses==0) return null;
        if (playerGuess == secretWord) return null;
        return playerGuess;
    }

    @Override
    public void setMaxWrongGuesses(Integer max) {
        if(max == null)this.maxWrongGuesses = 1;
        else this.maxWrongGuesses = max;
    }
    @Override
    public String[] getDictionary() {
        return dictionary;
    }

    public static void main(String[] args) throws Exception {
        Hangman obj = new Hangman();
        obj.readFile("src/words");
        obj.selectRandomSecretWord();
        obj.setMaxWrongGuesses(3);
        Scanner input = new Scanner(System.in);
        char guessChar ;
        System.out.print(obj.playerGuess);
        while(obj.maxWrongGuesses != 0){
            guessChar = input.next().charAt(0);
            System.out.println(obj.guess(guessChar));


        }
    }
}
