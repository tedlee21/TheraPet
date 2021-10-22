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
        int bet = getBet();
        if (bet == -1) {
            return;
        }
        int multiplier = getMultiplier(bet);
        if (multiplier == -1) {
            return;
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

    // EFFECTS : prompts user for their bet and returns it; if bet is 0, returns -1
    private Integer getBet() {
        int bet = -1;
        while (!((user.getBalance() - bet) >= 0) || !(bet > 0)) {
            printBalance();
            System.out.println("\nHow much would you like to bet?");
            System.out.println("(Remember, your bet should not be more coins than you have!)");
            bet = input.nextInt();
            if (bet == 0) {
                System.out.println("Cannot bet 0 coins!");
                return -1;
            }
        }
        return bet;
    }

    // EFFECTS : prompts user for their desired multiplier and returns it; if multiplier is 0, returns -1
    private Integer getMultiplier(int bet) {
        int multiplier = -1;
        while (((user.getBalance() - (bet * multiplier)) < 0) || (multiplier < 0)) {
            System.out.println("\nWhat would you like the multiplier to be?");
            System.out.println("(Remember, your multiplier should not make your bet more coins than you have!)");
            multiplier = input.nextInt();
            if (multiplier == 0) {
                System.out.println("Cannot have multiplier of 0!");
                return -1;
            }
        }
        return multiplier;
    }

    // EFFECTS: prints out user balance
    private void printBalance() {
        System.out.println("You have: " + user.getBalance() + " coins.");
    }

}
