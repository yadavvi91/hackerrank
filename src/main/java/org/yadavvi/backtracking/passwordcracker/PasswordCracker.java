package org.yadavvi.backtracking.passwordcracker;

import java.util.*;

/**
 * Created by vishal on 13/7/17.
 */
public class PasswordCracker {

    private String[] passwords;
    private String input;

    private String[] matches;
    private int position;

    private String[] sequenceOfPasswords;
    private boolean found;

    PasswordCracker(String[] passwords, String input) {
        this.passwords = sanitizePasswords(passwords, input);
        this.input = input;

        int length = findLengthForMatches(passwords, input.length());
        this.matches = new String[length];
        this.position = 0;

        enumerate(this.input);
    }

    private String[] sanitizePasswords(String[] passwords, String input) {
        Set<String> exclude = new HashSet<>();
        HashMap<Character, Integer> characterMap = new HashMap<>();
        for (char character : input.toCharArray()) {
            Integer count = characterMap.get(character);
            if (count == null) {
                characterMap.put(character, 1);
            } else {
                characterMap.put(character, count + 1);
            }
        }

        for (String password : passwords) {
            for (char character : password.toCharArray()) {
                if (characterMap.get(character) == null) {
                    exclude.add(password);
                    break;
                }
            }
        }
        String[] validPasswords = new String[passwords.length - exclude.size()];
        int i = 0;
        for (String password : passwords) {
            if (exclude.contains(password)) continue;
            validPasswords[i++] = password;
        }

        if (!canTheWordBeFormed(characterMap, validPasswords)) {
            return new String[0];
        }
        return validPasswords;
    }

    private boolean canTheWordBeFormed(HashMap<Character, Integer> characterMap, String[] validPasswords) {
        for (String validPassword : validPasswords) {
            for (char character : validPassword.toCharArray()) {
                characterMap.remove(character);
            }
        }
        return characterMap.size() == 0;
    }

    private int findLengthForMatches(String[] passwords, int length) {
        if (passwords.length == 0) return 0;
        int min = passwords[0].length();
        for (String password : passwords) {
            if (password.length() < min) min = password.length();
        }
        return length / min + 1;
    }

    private void enumerate(String input) {
        if (found) return;

        if (input.length() == 0) {
            found = true;
            process();
            return;
        }

        for (String password : passwords) {
            if (password.length() > input.length()) continue;

            matches[position++] = password;
            if (!canBackTrack(input, password)) enumerate(input.substring(password.length()));
            matches[--position] = "";
        }
    }

    private boolean canBackTrack(String input, String password) {
        String initialPart = input.substring(0, password.length());
        return !initialPart.equals(password);
    }

    private void process() {
        sequenceOfPasswords = new String[position];
        for (int i = 0; i < position; i++) {
            sequenceOfPasswords[i] = matches[i];
        }
    }

    public String[] getSequence() {
        return sequenceOfPasswords == null ? new String[]{"WRONG PASSWORD"} : sequenceOfPasswords;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();

            String[] passwords = new String[N];
            for (int j = 0; j < N; j++) {
                passwords[j] = scanner.next();
            }

            String input = scanner.next();
            PasswordCracker cracker = new PasswordCracker(passwords, input);
            String[] sequence = cracker.getSequence();
            for (String password: sequence) {
                System.out.printf("%s ", password);
            }
            System.out.println();
        }

        scanner.close();
    }

}
