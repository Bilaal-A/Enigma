package com.enigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Machine {
    public ArrayList<Character> alphabet;
    public ArrayList<Character> randAlpha;
    public HashMap<Character, Character> randPairs;
    public Machine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enigma");
        this.alphabet = alphabetArrayList();

        System.out.println("Enter Date In DD/MM/WKD format or enter 00/00 for full customization");
        String dayMonth = scanner.nextLine();
        int day = Integer.parseInt(dayMonth.substring(0,2));
        int month = Integer.parseInt(dayMonth.substring(3,5));
        this.randAlpha = randomAlphabet(day);
        this.randPairs = randomPairs(month);
        int weekday1 = randAlpha.indexOf(dayMonth.toUpperCase().charAt(6));
        int weekday2 = randAlpha.indexOf(dayMonth.toUpperCase().charAt(7));
        int weekday3 = randAlpha.indexOf(dayMonth.toUpperCase().charAt(8));


        printTable(randAlpha, randPairs);
        System.out.println("Rotor Start Indices: " + weekday1 + "-" + weekday2 + "-" + weekday3);
        while (true) {
            System.out.println();
            System.out.println(run(weekday1, weekday2, weekday3));
        }
    }

    public String run(int r1Count, int r2Count, int r3Count) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your message:");
        String strMessage = scanner.nextLine().toUpperCase();
        if(strMessage.equals("/end")) {
            System.exit(0);
        }
        ArrayList<Character> message = new ArrayList<>();
        ArrayList<Character> crypted = new ArrayList<>();
        for(int i =0; i<strMessage.length(); i++) {
            message.add(strMessage.charAt(i));
        }


        for(Character m : message) {
            Character x = m;
            if(alphabet.contains(m)) {
                Character r1Output = rotorOutput(m, r1Count);
                Character r2Output = rotorOutput(r1Output, r2Count);
                Character r3Output = rotorOutput(r2Output, r3Count);
                Character pair = randPairs.get(r3Output);
                Character r3Input = rotorInput(pair, r3Count);
                Character r2Input = rotorInput(r3Input, r2Count);
                x = rotorInput(r2Input, r1Count);
            }
            crypted.add(x);

            r1Count++;
            if(r1Count>25){
                r1Count%=26;
                r2Count++;
            }
            if(r2Count>25){
                r2Count%=26;
                r3Count++;
            }
            if(r3Count>25){
                r3Count%=26;
            }

        }
        String fullCrypted = "";
        for (Character c : crypted) {
            fullCrypted += c;
        }
        return fullCrypted;
    }

    public Character rotorOutput(Character c, int keypress) {
        Character output;
        int index = alphabet.indexOf(c) + keypress;
        if (index >= 25) {index %= 26;}
        if (index < 0) {index += 26;}
        output = randAlpha.get(index);
        return output;
    }

    public Character rotorInput(Character c, int keypress) {
        Character input;
        int index = randAlpha.indexOf(c) - keypress;
        if (index >= 25) {index %= 26;}
        if (index < 0) {index += 26;}
        input = alphabet.get(index);
        return input;
    }

    public ArrayList<Character> alphabetArrayList(){
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

    public ArrayList<Character> randomAlphabet(int pres){
        ArrayList<Character> rand = new ArrayList<>();
        if(pres>12) {pres%=12;}
        if(pres == 0) {
            rand = cusRandomAlphabet();
        }
        else if (pres == 1) {
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
        }
        else if (pres == 2) {
            rand.add('K');
            rand.add('Z');
            rand.add('A');
            rand.add('M');
            rand.add('H');
            rand.add('T');
            rand.add('P');
            rand.add('C');
            rand.add('Y');
            rand.add('B');
            rand.add('R');
            rand.add('X');
            rand.add('O');
            rand.add('F');
            rand.add('J');
            rand.add('U');
            rand.add('S');
            rand.add('L');
            rand.add('N');
            rand.add('W');
            rand.add('V');
            rand.add('I');
            rand.add('Q');
            rand.add('G');
            rand.add('E');
            rand.add('D');

        }
        else if (pres == 3) {
            rand.add('R');
            rand.add('F');
            rand.add('U');
            rand.add('X');
            rand.add('C');
            rand.add('A');
            rand.add('P');
            rand.add('L');
            rand.add('T');
            rand.add('Z');
            rand.add('B');
            rand.add('N');
            rand.add('H');
            rand.add('O');
            rand.add('D');
            rand.add('W');
            rand.add('Q');
            rand.add('M');
            rand.add('K');
            rand.add('E');
            rand.add('I');
            rand.add('S');
            rand.add('V');
            rand.add('G');
            rand.add('J');
            rand.add('Y');

        }
        else if (pres == 4) {
            rand.add('S');
            rand.add('E');
            rand.add('Q');
            rand.add('V');
            rand.add('L');
            rand.add('O');
            rand.add('A');
            rand.add('H');
            rand.add('Z');
            rand.add('G');
            rand.add('T');
            rand.add('C');
            rand.add('X');
            rand.add('J');
            rand.add('N');
            rand.add('D');
            rand.add('I');
            rand.add('P');
            rand.add('W');
            rand.add('B');
            rand.add('F');
            rand.add('R');
            rand.add('U');
            rand.add('K');
            rand.add('M');
            rand.add('Y');

        }
        else if (pres == 5) {
            rand.add('B');
            rand.add('U');
            rand.add('H');
            rand.add('Q');
            rand.add('X');
            rand.add('S');
            rand.add('O');
            rand.add('T');
            rand.add('E');
            rand.add('W');
            rand.add('C');
            rand.add('Y');
            rand.add('A');
            rand.add('M');
            rand.add('G');
            rand.add('L');
            rand.add('Z');
            rand.add('J');
            rand.add('V');
            rand.add('K');
            rand.add('R');
            rand.add('I');
            rand.add('F');
            rand.add('P');
            rand.add('N');
            rand.add('D');

        }
        else if (pres == 6) {
            rand.add('Y');
            rand.add('C');
            rand.add('M');
            rand.add('F');
            rand.add('A');
            rand.add('Z');
            rand.add('W');
            rand.add('P');
            rand.add('H');
            rand.add('R');
            rand.add('U');
            rand.add('K');
            rand.add('T');
            rand.add('N');
            rand.add('X');
            rand.add('B');
            rand.add('I');
            rand.add('S');
            rand.add('L');
            rand.add('O');
            rand.add('J');
            rand.add('E');
            rand.add('Q');
            rand.add('V');
            rand.add('D');
            rand.add('G');

        }
        else if (pres == 7) {
            rand.add('N');
            rand.add('J');
            rand.add('W');
            rand.add('E');
            rand.add('S');
            rand.add('B');
            rand.add('Q');
            rand.add('I');
            rand.add('C');
            rand.add('V');
            rand.add('A');
            rand.add('X');
            rand.add('G');
            rand.add('T');
            rand.add('M');
            rand.add('Z');
            rand.add('F');
            rand.add('R');
            rand.add('U');
            rand.add('K');
            rand.add('P');
            rand.add('O');
            rand.add('H');
            rand.add('Y');
            rand.add('L');
            rand.add('D');

        }
        else if (pres == 8) {
            rand.add('D');
            rand.add('A');
            rand.add('V');
            rand.add('K');
            rand.add('O');
            rand.add('Y');
            rand.add('H');
            rand.add('M');
            rand.add('Q');
            rand.add('S');
            rand.add('X');
            rand.add('C');
            rand.add('R');
            rand.add('J');
            rand.add('W');
            rand.add('I');
            rand.add('T');
            rand.add('Z');
            rand.add('B');
            rand.add('P');
            rand.add('L');
            rand.add('E');
            rand.add('U');
            rand.add('F');
            rand.add('N');
            rand.add('G');

        }
        else if (pres == 9) {
            rand.add('U');
            rand.add('H');
            rand.add('Z');
            rand.add('B');
            rand.add('Q');
            rand.add('T');
            rand.add('E');
            rand.add('X');
            rand.add('M');
            rand.add('I');
            rand.add('S');
            rand.add('A');
            rand.add('C');
            rand.add('W');
            rand.add('P');
            rand.add('G');
            rand.add('R');
            rand.add('Y');
            rand.add('F');
            rand.add('N');
            rand.add('K');
            rand.add('D');
            rand.add('O');
            rand.add('V');
            rand.add('J');
            rand.add('L');

        }
        else if (pres == 10) {
            rand.add('G');
            rand.add('P');
            rand.add('E');
            rand.add('L');
            rand.add('R');
            rand.add('X');
            rand.add('A');
            rand.add('U');
            rand.add('D');
            rand.add('S');
            rand.add('M');
            rand.add('C');
            rand.add('V');
            rand.add('H');
            rand.add('T');
            rand.add('Y');
            rand.add('O');
            rand.add('F');
            rand.add('Q');
            rand.add('W');
            rand.add('Z');
            rand.add('I');
            rand.add('B');
            rand.add('K');
            rand.add('N');
            rand.add('J');

        }
        else if (pres == 11) {
            rand.add('C');
            rand.add('T');
            rand.add('G');
            rand.add('R');
            rand.add('I');
            rand.add('A');
            rand.add('Z');
            rand.add('L');
            rand.add('F');
            rand.add('P');
            rand.add('X');
            rand.add('U');
            rand.add('H');
            rand.add('D');
            rand.add('J');
            rand.add('S');
            rand.add('O');
            rand.add('B');
            rand.add('M');
            rand.add('K');
            rand.add('Q');
            rand.add('W');
            rand.add('V');
            rand.add('N');
            rand.add('E');
            rand.add('Y');

        }
        else if (pres == 12) {
            rand.add('W');
            rand.add('M');
            rand.add('Q');
            rand.add('E');
            rand.add('K');
            rand.add('N');
            rand.add('T');
            rand.add('Z');
            rand.add('B');
            rand.add('O');
            rand.add('C');
            rand.add('H');
            rand.add('A');
            rand.add('V');
            rand.add('I');
            rand.add('S');
            rand.add('G');
            rand.add('P');
            rand.add('D');
            rand.add('R');
            rand.add('X');
            rand.add('Y');
            rand.add('U');
            rand.add('J');
            rand.add('L');
            rand.add('F');

        }
        return rand;
    }

    private ArrayList<Character> cusRandomAlphabet() {
        ArrayList<Character> alpha = new ArrayList<>(alphabet);
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

    public HashMap<Character, Character> randomPairs(int pres) {
        HashMap<Character,Character> pairs = new HashMap<>();
        if(pres>12) {pres%=12;}
        if(pres == 0) {pairs = cusRandomPairs();}
        else if (pres == 1) {
            pairs.put('A','A');
            pairs.put('B','Z');
            pairs.put('C','Y');
            pairs.put('D','X');
            pairs.put('E','W');
            pairs.put('F','V');
            pairs.put('G','U');
            pairs.put('H','T');
            pairs.put('I','S');
            pairs.put('J','R');
            pairs.put('K','Q');
            pairs.put('L','P');
            pairs.put('M','O');
            pairs.put('N','N');
            pairs.put('O','M');
            pairs.put('P','L');
            pairs.put('Q','K');
            pairs.put('R','J');
            pairs.put('S','I');
            pairs.put('T','H');
            pairs.put('U','G');
            pairs.put('V','F');
            pairs.put('W','E');
            pairs.put('X','D');
            pairs.put('Y','C');
            pairs.put('Z','B');

        }
        else if (pres == 2) {
            pairs.put('A','M');
            pairs.put('B','B');
            pairs.put('C','X');
            pairs.put('D','W');
            pairs.put('E','E');
            pairs.put('F','V');
            pairs.put('G','U');
            pairs.put('H','T');
            pairs.put('I','I');
            pairs.put('J','R');
            pairs.put('K','Q');
            pairs.put('L','P');
            pairs.put('M','A');
            pairs.put('N','O');
            pairs.put('O','N');
            pairs.put('P','L');
            pairs.put('Q','K');
            pairs.put('R','J');
            pairs.put('S','S');
            pairs.put('T','H');
            pairs.put('U','G');
            pairs.put('V','F');
            pairs.put('W','D');
            pairs.put('X','C');
            pairs.put('Y','Z');
            pairs.put('Z','Y');

        }
        else if (pres == 3) {
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
        else if (pres == 4) {
            pairs.put('A','A');
            pairs.put('B','C');
            pairs.put('C','B');
            pairs.put('D','E');
            pairs.put('E','D');
            pairs.put('F','F');
            pairs.put('G','T');
            pairs.put('H','S');
            pairs.put('I','I');
            pairs.put('J','R');
            pairs.put('K','Q');
            pairs.put('L','P');
            pairs.put('M','N');
            pairs.put('N','M');
            pairs.put('O','O');
            pairs.put('P','L');
            pairs.put('Q','K');
            pairs.put('R','J');
            pairs.put('S','H');
            pairs.put('T','G');
            pairs.put('U','V');
            pairs.put('V','U');
            pairs.put('W','X');
            pairs.put('X','W');
            pairs.put('Y','Z');
            pairs.put('Z','Y');

        }
        else if (pres == 5) {
            pairs.put('A','H');
            pairs.put('B','G');
            pairs.put('C','F');
            pairs.put('D','E');
            pairs.put('E','D');
            pairs.put('F','C');
            pairs.put('G','B');
            pairs.put('H','A');
            pairs.put('I','I');
            pairs.put('J','Z');
            pairs.put('K','Y');
            pairs.put('L','X');
            pairs.put('M','W');
            pairs.put('N','V');
            pairs.put('O','U');
            pairs.put('P','T');
            pairs.put('Q','S');
            pairs.put('R','R');
            pairs.put('S','Q');
            pairs.put('T','P');
            pairs.put('U','O');
            pairs.put('V','N');
            pairs.put('W','M');
            pairs.put('X','L');
            pairs.put('Y','K');
            pairs.put('Z','J');

        }
        else if (pres == 6) {
            pairs.put('A','Z');
            pairs.put('B','Y');
            pairs.put('C','X');
            pairs.put('D','W');
            pairs.put('E','E');
            pairs.put('F','V');
            pairs.put('G','U');
            pairs.put('H','T');
            pairs.put('I','S');
            pairs.put('J','R');
            pairs.put('K','Q');
            pairs.put('L','P');
            pairs.put('M','M');
            pairs.put('N','N');
            pairs.put('O','O');
            pairs.put('P','L');
            pairs.put('Q','K');
            pairs.put('R','J');
            pairs.put('S','I');
            pairs.put('T','H');
            pairs.put('U','G');
            pairs.put('V','F');
            pairs.put('W','D');
            pairs.put('X','C');
            pairs.put('Y','B');
            pairs.put('Z','A');

        }
        else if (pres == 7) {
            pairs.put('A','B');
            pairs.put('B','A');
            pairs.put('C','D');
            pairs.put('D','C');
            pairs.put('E','F');
            pairs.put('F','E');
            pairs.put('G','G');
            pairs.put('H','I');
            pairs.put('I','H');
            pairs.put('J','K');
            pairs.put('K','J');
            pairs.put('L','M');
            pairs.put('M','L');
            pairs.put('N','O');
            pairs.put('O','N');
            pairs.put('P','Q');
            pairs.put('Q','P');
            pairs.put('R','S');
            pairs.put('S','R');
            pairs.put('T','U');
            pairs.put('U','T');
            pairs.put('V','W');
            pairs.put('W','V');
            pairs.put('X','X');
            pairs.put('Y','Z');
            pairs.put('Z','Y');

        }
        else if (pres == 8) {
            pairs.put('A','A');
            pairs.put('B','Z');
            pairs.put('C','Y');
            pairs.put('D','X');
            pairs.put('E','W');
            pairs.put('F','V');
            pairs.put('G','U');
            pairs.put('H','T');
            pairs.put('I','S');
            pairs.put('J','R');
            pairs.put('K','Q');
            pairs.put('L','P');
            pairs.put('M','M');
            pairs.put('N','N');
            pairs.put('O','O');
            pairs.put('P','L');
            pairs.put('Q','K');
            pairs.put('R','J');
            pairs.put('S','I');
            pairs.put('T','H');
            pairs.put('U','G');
            pairs.put('V','F');
            pairs.put('W','E');
            pairs.put('X','D');
            pairs.put('Y','C');
            pairs.put('Z','B');

        }
        else if (pres == 9) {
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
        else if (pres == 10) {
            pairs.put('A','A');
            pairs.put('B','C');
            pairs.put('C','B');
            pairs.put('D','E');
            pairs.put('E','D');
            pairs.put('F','F');
            pairs.put('G','T');
            pairs.put('H','S');
            pairs.put('I','I');
            pairs.put('J','R');
            pairs.put('K','Q');
            pairs.put('L','P');
            pairs.put('M','N');
            pairs.put('N','M');
            pairs.put('O','O');
            pairs.put('P','L');
            pairs.put('Q','K');
            pairs.put('R','J');
            pairs.put('S','H');
            pairs.put('T','G');
            pairs.put('U','V');
            pairs.put('V','U');
            pairs.put('W','X');
            pairs.put('X','W');
            pairs.put('Y','Z');
            pairs.put('Z','Y');

        }
        else if (pres == 11) {
            pairs.put('A','H');
            pairs.put('B','G');
            pairs.put('C','F');
            pairs.put('D','E');
            pairs.put('E','D');
            pairs.put('F','C');
            pairs.put('G','B');
            pairs.put('H','A');
            pairs.put('I','I');
            pairs.put('J','Z');
            pairs.put('K','Y');
            pairs.put('L','X');
            pairs.put('M','W');
            pairs.put('N','V');
            pairs.put('O','U');
            pairs.put('P','T');
            pairs.put('Q','S');
            pairs.put('R','R');
            pairs.put('S','Q');
            pairs.put('T','P');
            pairs.put('U','O');
            pairs.put('V','N');
            pairs.put('W','M');
            pairs.put('X','L');
            pairs.put('Y','K');
            pairs.put('Z','J');

        }
        else if (pres == 12) {
            pairs.put('A','Z');
            pairs.put('B','Y');
            pairs.put('C','X');
            pairs.put('D','W');
            pairs.put('E','E');
            pairs.put('F','V');
            pairs.put('G','U');
            pairs.put('H','T');
            pairs.put('I','S');
            pairs.put('J','R');
            pairs.put('K','Q');
            pairs.put('L','P');
            pairs.put('M','M');
            pairs.put('N','N');
            pairs.put('O','O');
            pairs.put('P','L');
            pairs.put('Q','K');
            pairs.put('R','J');
            pairs.put('S','I');
            pairs.put('T','H');
            pairs.put('U','G');
            pairs.put('V','F');
            pairs.put('W','D');
            pairs.put('X','C');
            pairs.put('Y','B');
            pairs.put('Z','A');

        }
        return pairs;
    }

    private HashMap<Character, Character> cusRandomPairs() {
        ArrayList<Character> alpha = new ArrayList<>(alphabet);
        HashMap<Character, Character> pairs = new HashMap<>();
        while (alpha.size() > 0) {

            System.out.print("Unpaired: ");
            for (Character a : alpha) {
                System.out.print(a);
            }
            System.out.println();
            Character c = letterForLetter(alpha.get(0));
            boolean paired2Itself = false;
            if (c.equals(alpha.get(0))) {
                paired2Itself = true;
            }
            while (pairs.containsKey(c)) {
                System.out.println("Letter is already paired");
                c = letterForLetter(alpha.get(0));
            }
            pairs.put(alpha.get(0), c);
            pairs.put(c, alpha.get(0));
            alpha.remove(c);
            if(!paired2Itself) {alpha.remove(0);}
        }
        return pairs;
    }


    public Character letterForLetter(Character inputLetter){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a letter for " + inputLetter);
        System.out.print("> ");
        Character c = scanner.next().toUpperCase().charAt(0);
        return c;
    }

    public void printTable(ArrayList<Character> random, HashMap<Character, Character> pairs) {
        System.out.println("alpha | rand || alpha | pair");
        for (int i = 0; i < alphabet.size(); i++) {
            System.out.println(alphabet.get(i) + " -> " + random.get(i) + " || " + alphabet.get(i) + "=" + pairs.get(alphabet.get(i)));
        }
    }
    
}
