import java.util.*;

public class INCORRUPTIBLE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== WELCOME TO INCORRUPTIBLE ===");
        System.out.println("=== PROGRESS BUILD / SKELETON VERSION ===");

        boolean playAgain;
        do {
            // Mode selection
            System.out.println("\nChoose a game mode:");
            System.out.println("1: Player vs Player");
            System.out.println("2: Player vs AI");
            System.out.println("3: Arcade Mode (1 hero vs all others)");
            System.out.println("x: Exit");
            
            String modeInput = sc.next();
            if(modeInput.equalsIgnoreCase("x")) {
                System.out.println("Exiting game...");
                break;
            }

            int mode;
            try { mode = Integer.parseInt(modeInput); }
            catch(Exception e) {
                System.out.println("Invalid input! Defaulting to PvP.");
                mode = 1;
            }

            // Character selection
            Character player1 = chooseCharacter(sc, 1);
            Character player2 = null;

            switch(mode) {
                case 1: // PvP
                    player2 = chooseCharacter(sc, 2);
                    playPvP(player1, player2, sc);
                    break;
                case 2: // PvAI
                    Character ai;
                    // Randomly choose an AI opponent from all available characters.
                    int aiChoice = new Random().nextInt(8) + 1;
                    switch (aiChoice) {
                        case 1:
                            ai = new Ren();
                            break;
                        case 2:
                            ai = new Nuel();
                            break;
                        case 3:
                            ai = new Jed();
                            break;
                        case 4:
                            ai = new Nino();
                            break;
                        case 5:
                            ai = new Christopher();
                            break;
                        case 6:
                            ai = new Dafaquer();
                            break;
                        case 7:
                            ai = new Jason();
                            break;
                        case 8:
                        default:
                            ai = new Masocol();
                            break;
                    }
                    System.out.println("\nYour opponent is " + ai.getName() + "!");
                    playPvAI(player1, ai, sc);
                    break;
                case 3: // Arcade placeholder
                    playArcade(player1, sc);
                    break;
                default:
                    System.out.println("Invalid mode! Defaulting to PvP.");
                    player2 = chooseCharacter(sc, 2);
                    playPvP(player1, player2, sc);
            }

            // Play again prompt
            System.out.print("\nPlay again? (y/n): ");
            String again = sc.next();
            playAgain = again.equalsIgnoreCase("y");

        } while(playAgain);

        sc.close();
        System.out.println("=== INCORRUPTIBLE ===");
        System.out.println("=== END OF PROGRESS BUILD ===");
    }

    // === MODE LOGIC ===
    private static void playPvP(Character c1, Character c2, Scanner sc) {
        // Add (Player 1) and (Player 2) to the names for clarity in PvP mode.
        c1.setName(c1.getName() + " (Player 1)");
        c2.setName(c2.getName() + " (Player 2)");

        System.out.println("\n--- PvP: " + c1.getName() + " VS " + c2.getName() + " ---");
        battleLoop(c1, c2, sc);
    }

    private static void playPvAI(Character player, Character ai, Scanner sc) {
        // Add (You) to the player's name for clarity in PvAI mode.
        player.setName(player.getName() + " (You)");

        System.out.println("\n--- PvAI: " + player.getName() + " VS " + ai.getName() + " (AI) ---");
        battleLoopPvAI(player, ai, sc);
    }

    private static void playArcade(Character player, Scanner sc) {
        System.out.println("\n--- ARCADE MODE ---");
        System.out.println("It's a battle between you against the CORRUPTED.");
        // TODO: Add array of enemies and sequential battles
    }

    // === BATTLE LOOP ===
    private static void battleLoop(Character c1, Character c2, Scanner sc) {
        Character[] characters = {c1, c2};
        int turn = 0;

        while (c1.isAlive() && c2.isAlive()) {
            Character current = characters[turn % 2];
            Character opponent = characters[(turn + 1) % 2];

            current.regenerateStamina();
            displayStats(c1, c2);
            if(!takeTurn(current, opponent, sc)) break; // false = exit

            turn++;
        }

        if(c1.isAlive() || c2.isAlive()) {
            System.out.println("\n--- GAME OVER ---");
            System.out.println((c1.isAlive() ? c1.getName() : c2.getName()) + " wins!");
        }
    }

    // Added on Oct 14
    private static void battleLoopPvAI(Character player, Character ai, Scanner sc) {
        int turn = 0;

        while (player.isAlive() && ai.isAlive()) {
            Character current = (turn % 2 == 0) ? player : ai;
            Character opponent = (turn % 2 == 0) ? ai : player;

            current.regenerateStamina();
            displayStats(player, ai);

            if (current == player) {
                if (!takeTurn(current, opponent, sc)) break; // Player's turn
            } else {
                takeAITurn(current, opponent); // AI's turn
            }
            turn++;
        }

        if (player.isAlive() || ai.isAlive()) {
            System.out.println("\n--- GAME OVER ---");
            System.out.println((player.isAlive() ? player.getName() : ai.getName()) + " wins!");
        }
    }

    // Added Oct 14
    private static void takeAITurn(Character current, Character opponent) {
        System.out.println("\n--- " + current.getName() + "'s Turn (AI) ---");
        Random random = new Random();
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        possibleMoves.add(1); // Basic attack is always possible

        // TODO: A better implementation would be to have skill costs as attributes
        // in the Character class instead of hardcoding them here.
        if (current.getStamina() >= 20) { // Skill cost
            possibleMoves.add(2);
        }
        if (current.getStamina() >= 40) { // Ultimate cost
            possibleMoves.add(3);
        }

        int choice = possibleMoves.get(random.nextInt(possibleMoves.size()));

        switch (choice) {
            case 1 -> current.basicAttack(opponent);
            case 2 -> current.skillAttack(opponent);
            case 3 -> current.ultimateAttack(opponent);
        }
    }

    // === HELPER METHODS ===
    private static Character chooseCharacter(Scanner sc, int player) {
        System.out.println("\nPlayer " + player + ", choose your character (x to exit):");
        System.out.println("[1] Ren         [2] Nuel        [3] Jed         [4] Nino");
        System.out.println("[5] Christopher [6] Dafaquer    [7] Jason       [8] Masocol");
        String input = sc.next();

        if(input.equalsIgnoreCase("x")) {
            System.out.println("Exiting game. Goodbye!");
            System.exit(0);
        }

        int choice;
        try { choice = Integer.parseInt(input); }
        catch(Exception e) { choice = 1; }

        // Add new characters to the switch statement.
        return switch(choice) {
            case 1 -> new Ren();
            case 2 -> new Nuel();
            case 3 -> new Jed();
            case 4 -> new Nino();
            case 5 -> new Christopher();
            case 6 -> new Dafaquer();
            case 7 -> new Jason();
            case 8 -> new Masocol();
            default -> {
                System.out.println("Invalid choice! Defaulting to Ren.");
                yield new Ren();
            }
        };
    }

    private static void displayStats(Character c1, Character c2) {
        System.out.println("\n--- Current Stats ---");
        // Header with character names, padded to 25 characters for alignment
        System.out.printf("%-25s\t%-25s%n", c1.getName(), c2.getName());
        System.out.println("-------------------------   -------------------------"); // Separator line

        // HP Stats, showing current/max
        String c1HP = String.format("HP: %d/%d", c1.getHealth(), c1.getMaxHealth());
        String c2HP = String.format("HP: %d/%d", c2.getHealth(), c2.getMaxHealth());
        System.out.printf("%-25s\t%-25s%n", c1HP, c2HP);

        // Stamina Stats, showing current/max
        String c1Stamina = String.format("Stamina: %d/%d", c1.getStamina(), c1.getMaxStamina());
        String c2Stamina = String.format("Stamina: %d/%d", c2.getStamina(), c2.getMaxStamina());
        System.out.printf("%-25s\t%-25s%n", c1Stamina, c2Stamina);
    }

    private static boolean takeTurn(Character current, Character opponent, Scanner sc) {
        System.out.println("\n--- " + current.getName() + "'s Turn ---");
        // The costs displayed here are based on the current characters (Ren and Nuel).
        // If new characters have different costs, this text may need to be updated.
        System.out.println("[1] Basic (Low Dmg, 0 Stamina)");
        System.out.println("[2] Skill (Med Dmg, 20 Stamina)");
        System.out.println("[3] Ultimate (High Dmg, 60 Stamina)");
        System.out.println("[x] Exit");
        String input = sc.next();

        if(input.equalsIgnoreCase("x")) {
            System.out.println(current.getName() + " has exited the game!");
            return false; // exit signal
        }

        int choice;
        try { choice = Integer.parseInt(input); }
        catch(Exception e) {
            System.out.println("Invalid! Skipping turn.");
            return true;
        }

        switch(choice) {
            case 1 -> current.basicAttack(opponent);
            case 2 -> current.skillAttack(opponent);
            case 3 -> current.ultimateAttack(opponent);
            default -> System.out.println("Invalid! Skipping turn.");
        }
        return true;
    }
}