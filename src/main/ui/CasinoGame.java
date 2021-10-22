package ui;

import model.*;
import java.util.Scanner;
import java.util.Random;

// 50/50 Casino Game            //Console interface code based off TellerApp file from 210 class
public class CasinoGame {
    private Profile user;       //user imported from PetApp
    private Scanner input;
    private Random choice;

    // EFFECTS : runs the casino game
    public CasinoGame(Profile pf) {
        user = pf;
        runCasino();
    }

    // MODIFIES: this
    // EFFECTS : processes the users input
    private void runCasino() {
        String command;
        init();
        boolean keepGoing = true;

        System.out.println("Welcome to the casino, what would you like to do?");
        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("l")) {
                keepGoing = false;
                System.out.println("Comeback soon " + user.getName() + "!");
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS : initializes systems
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        choice = new Random();
    }

    // EFFECTS : displays main menu of options to user
    private void displayMainMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp -> Play");
        System.out.println("\tb -> Balance");
        System.out.println("\tl -> Leave Casino");
    }

    // EFFECTS: displays menu of gambling choices to the user
    private void displayPlayChoices() {
        System.out.println("\nSelect from:");
        System.out.println("\th -> Heads");
        System.out.println("\tt -> Tails");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("p")) {
            doPlay();
        } else if (command.equals("b")) {
            printBalance();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS : prompts user to play casino game
    private void doPlay() {
        String selection = "";  // force entry into loop
        int multiplier = 0;
        int bet = getBet();

        while (((user.getBalance() - (bet * multiplier)) < 0) || (multiplier < 1)) {
            System.out.println("\nWhat would you like the multiplier to be?");
            multiplier = input.nextInt();
        }
        while (!(selection.equals("t") || selection.equals("h"))) {
            displayPlayChoices();
            selection = input.next();
            selection = selection.toLowerCase();
        }
        boolean heads = choice.nextBoolean();
        if ((selection.equals("h") && heads) || (selection.equals("t") && !heads)) {
            System.out.println("You win!!");
            user.addBalance(bet * multiplier);
        } else {
            System.out.println("Better luck next time..");
            user.subBalance(bet * multiplier);
        }
        printBalance();
    }

    // EFFECTS : prompts user for their bet and returns it
    private Integer getBet() {
        int bet = 0;
        while (!((user.getBalance() - bet) > 0) || !(bet > 0)) {
            printBalance();
            System.out.println("\nHow much would you like to bet?");
            bet = input.nextInt();
        }
        return bet;
    }

    // EFFECTS: prints out user balance
    private void printBalance() {
        System.out.println("You have: " + user.getBalance() + " coins.");
    }

}
