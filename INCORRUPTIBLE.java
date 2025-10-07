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
                    // TODO: Replace with AI character logic later
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
        System.out.println("\n--- PvP: " + c1.getName() + " VS " + c2.getName() + " ---");
        battleLoop(c1, c2, sc);
    }

    private static void playPvE(Character player, Character ai, Scanner sc) {
        System.out.println("\n--- PvE: " + player.getName() + " VS " + ai.getName() + " (AI) ---");
        // TODO: Implement actual AI attack logic
        battleLoop(player, ai, sc);
    }

    private static void playArcade(Character player, Scanner sc) {
        System.out.println("\n--- Arcade Mode ---");
        System.out.println("You will fight all other characters in sequence.");
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

    // === HELPER METHODS ===
    private static Character chooseCharacter(Scanner sc, int player) {
        System.out.println("\nPlayer " + player + ", choose your character (x to exit):");
        System.out.println("[1] Ren   [2] Nuel   [3] (others coming soon)");
        String input = sc.next();

        if(input.equalsIgnoreCase("x")) {
            System.out.println("Exiting game. Goodbye!");
            System.exit(0);
        }

        int choice;
        try { choice = Integer.parseInt(input); }
        catch(Exception e) { choice = 1; }

        // TODO: Add more characters here
        return switch(choice) {
            case 1 -> new Ren();
            case 2 -> new Nuel();
            default -> {
                System.out.println("Invalid choice! Defaulting to Ren.");
                yield new Ren();
            }
        };
    }

    private static void displayStats(Character c1, Character c2) {
        System.out.println("\n--- Current Stats ---");
        System.out.printf("%-15s HP: %-4d | Stamina: %-4d%n", c1.getName(), c1.getHealth(), c1.getStamina());
        System.out.printf("%-15s HP: %-4d | Stamina: %-4d%n", c2.getName(), c2.getHealth(), c2.getStamina());
    }

    private static boolean takeTurn(Character current, Character opponent, Scanner sc) {
        System.out.println("\n--- " + current.getName() + "'s Turn ---");
        System.out.println("[1] Basic  [2] Skill  [3] Ultimate  [x] Exit");
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