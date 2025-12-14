package com.enigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static ArrayList<Character> alphabet;
    public static ArrayList<Character> randAlpha;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enigma");
        Main.alphabet = alphabetArrayList();
        System.out.println("Custom initial substitution?");
        System.out.print("(t/f)> ");
        String initialSub = scanner.nextLine().toUpperCase();
        ArrayList<Character> rA;
        if(initialSub.equals("T")) {rA = randomAlphabet(alphabet);}
        else{rA = randomAlphabet();}
        Main.randAlpha = rA;
        System.out.println();
        System.out.println("Custom pairs?");
        System.out.print("(t/f)> ");
        String custPairs = scanner.nextLine().toUpperCase();
        boolean cpBoolean = custPairs.equals("T");
        HashMap<Character, Character> pairs = randomPairs(cpBoolean);
        System.out.println();
        printTable(randAlpha, pairs);
        while (true) {
            System.out.println();
            System.out.println(run(randAlpha, pairs));
        }
    }

    public static String run(ArrayList<Character> rand, HashMap<Character, Character> pairs) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your message:");
        String strMessage = scanner.nextLine().toUpperCase();
        ArrayList<Character> message = new ArrayList<>();
        ArrayList<Character> crypted = new ArrayList<>();
        for(int i =0; i<strMessage.length(); i++) {
            message.add(strMessage.charAt(i));
        }
        int r1Count = 0;
        int r2Count = 0;
        int r3Count = 0;
        for(Character m : message) {
            Character x = m;
            if(alphabet.contains(m)) {
                Character r1Output = rotorOutput(m, r1Count);
                Character r2Output = rotorOutput(r1Output, r2Count);
                Character r3Output = rotorOutput(r2Output, r3Count);
                Character pair = pairs.get(r3Output);
                Character r3Input = rotorInput(pair, r3Count);
                Character r2Input = rotorInput(r3Input, r2Count);
                x = rotorInput(r2Input, r1Count);
            }
            crypted.add(x);

            r1Count++;
            if(r1Count>25){
                r1Count-=26;
                r2Count++;
            }
            if(r2Count>25){
                r2Count-=26;
                r3Count++;
            }
            if(r3Count>25){
                r3Count-=26;
            }

        }
        String fullCrypted = "";
        for (Character c : crypted) {
            fullCrypted += c;
        }
        return fullCrypted;
    }

    public static Character rotorOutput(Character c, int keypress) {
        Character output;
        int index = alphabet.indexOf(c) + keypress;
        if (index >= 25) {index %= 26;}
        if (index < 0) {index += 26;}
        output = randAlpha.get(index);
        return output;
    }

    public static Character rotorInput(Character c, int keypress) {
        Character input;
        int index = randAlpha.indexOf(c) - keypress;
        if (index >= 25) {index %= 26;}
        if (index < 0) {index += 26;}
        input = alphabet.get(index);
        return input;
    }

    public static ArrayList<Character> alphabetArrayList(){
        ArrayList<Character> alphabet = new ArrayList<>();
        alphabet.add('A');
        alphabet.add('B');
        alphabet.add('C');
        alphabet.add('D');
        alphabet.add('E');
        alphabet.add('F');
        alphabet.add('G');
        alphabet.add('H');
        alphabet.add('I');
        alphabet.add('J');
        alphabet.add('K');
        alphabet.add('L');
        alphabet.add('M');
        alphabet.add('N');
        alphabet.add('O');
        alphabet.add('P');
        alphabet.add('Q');
        alphabet.add('R');
        alphabet.add('S');
        alphabet.add('T');
        alphabet.add('U');
        alphabet.add('V');
        alphabet.add('W');
        alphabet.add('X');
        alphabet.add('Y');
        alphabet.add('Z');
        return alphabet;
    }

    public static ArrayList<Character> randomAlphabet(){
        ArrayList<Character> rand = new ArrayList<>();
        rand.add('Q');
        rand.add('W');
        rand.add('E');
        rand.add('R');
        rand.add('T');
        rand.add('Y');
        rand.add('U');
        rand.add('I');
        rand.add('O');
        rand.add('P');
        rand.add('A');
        rand.add('S');
        rand.add('D');
        rand.add('F');
        rand.add('G');
        rand.add('H');
        rand.add('J');
        rand.add('K');
        rand.add('L');
        rand.add('Z');
        rand.add('X');
        rand.add('C');
        rand.add('V');
        rand.add('B');
        rand.add('N');
        rand.add('M');
        return rand;
    }

    public static ArrayList<Character> randomAlphabet(ArrayList<Character> alphabet) {
        ArrayList<Character> alpha = alphabetArrayList();
        ArrayList<Character> randomAlphabet = new ArrayList<>();
        for(int i = 0; i<alphabet.size(); i++) {

            System.out.print("Remaining: ");
            for(Character a : alpha) {
                System.out.print(a);
            }
            System.out.println();

            Character c = letterForLetter(alphabet.get(i));
            while(c.equals(alphabet.get(i)) || randomAlphabet.contains(c)) {
                System.out.println("Letter Is Already Taken/Same Letter");
                c = letterForLetter(alphabet.get(i));
            }
            randomAlphabet.add(i, c);
            alpha.remove(c);
        }
        return randomAlphabet;
    }

    public static HashMap<Character, Character> randomPairs(boolean custom) {
        ArrayList<Character> alpha = alphabetArrayList();
        HashMap<Character, Character> pairs = new HashMap<>();
        if(custom) {
            while (alpha.size() > 0) {

                System.out.print("Unpaired: ");
                for (Character a : alpha) {
                    System.out.print(a);
                }
                System.out.println();
                Character c = letterForLetter(alpha.get(0));
                while (pairs.containsKey(c) || c.equals(alpha.get(0))) {
                    System.out.println("Letter is already paired");
                    c = letterForLetter(alpha.get(0));
                }
                pairs.put(alpha.get(0), c);
                pairs.put(c, alpha.get(0));
                alpha.remove(c);
                alpha.remove(0);
            }
        }
        else {
            pairs.put('A','Z');
            pairs.put('B','Y');
            pairs.put('C','X');
            pairs.put('D','W');
            pairs.put('E','V');
            pairs.put('F','U');
            pairs.put('G','T');
            pairs.put('H','S');
            pairs.put('I','R');
            pairs.put('J','Q');
            pairs.put('K','P');
            pairs.put('L','O');
            pairs.put('M','N');
            pairs.put('N','M');
            pairs.put('O','L');
            pairs.put('P','K');
            pairs.put('Q','J');
            pairs.put('R','I');
            pairs.put('S','H');
            pairs.put('T','G');
            pairs.put('U','F');
            pairs.put('V','E');
            pairs.put('W','D');
            pairs.put('X','C');
            pairs.put('Y','B');
            pairs.put('Z','A');
        }
        return pairs;
    }

    public static Character letterForLetter(Character inputLetter){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a letter for " + inputLetter);
        System.out.print("> ");
        Character c = scanner.next().toUpperCase().charAt(0);
        return c;
    }

    public static void printTable(ArrayList<Character> random, HashMap<Character, Character> pairs) {
        System.out.println("alpha | rand || alpha | pair");
        for (int i = 0; i < alphabet.size(); i++) {
            System.out.println(alphabet.get(i) + " -> " + random.get(i) + " || " + alphabet.get(i) + "=" + pairs.get(alphabet.get(i)));
        }
    }
}
