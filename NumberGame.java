import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int QUIT_VALUE = -1;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 100;

    public static void main(String[] args) {
        // Initialize random number generator
        Random rand = new Random();

        // Generate a random number between MIN_VALUE and MAX_VALUE
        int numberToGuess = rand.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;

        // Initialize scanner for user input
        try (Scanner scanner = new Scanner(System.in)) {
            // Initialize number of guesses
            int numGuesses = 0;

            // Game loop
            while (true) {
                try {
                    // Ask user for their guess
                    System.out.print("Guess a number between " + MIN_VALUE + " and " + MAX_VALUE + " (or " + QUIT_VALUE + " to quit): ");
                    int userGuess = scanner.nextInt();

                    // Check if user wants to quit
                    if (userGuess == QUIT_VALUE) {
                        System.out.println("Thanks for playing!");
                        break;
                    }

                    // Check if user's guess is valid
                    if (userGuess < MIN_VALUE || userGuess > MAX_VALUE) {
                        System.out.println("Invalid input! Please enter a number between " + MIN_VALUE + " and " + MAX_VALUE + ".");
                        continue;
                    }

                    // Increment number of guesses
                    numGuesses++;

                    // Check if user's guess is correct
                    if (userGuess == numberToGuess) {
                        System.out.println("Congratulations! You guessed the number in " + numGuesses + " tries.");
                        break;
                    } else if (userGuess < numberToGuess) {
                        System.out.println("Too low! Try again.");
                    } else {
                        System.out.println("Too high! Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next(); // Clear invalid input
                }
            }
        }
    }
}

