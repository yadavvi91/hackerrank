package org.yadavvi.backtracking;

import java.util.Scanner;

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
        this.passwords = passwords;
        this.input = input;

        int length = findLengthForMatches(passwords, input.length());
        this.matches = new String[length];
        this.position = 0;

        enumerate(this.input);
    }

    private int findLengthForMatches(String[] passwords, int length) {
        if (passwords.length == 0) throw new IllegalArgumentException("The valid passwords passed is empty.");
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
