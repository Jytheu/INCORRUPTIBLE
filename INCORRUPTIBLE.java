import java.util.*;

public class INCORRUPTIBLE {
    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(CYAN + BOLD + "=== WELCOME TO INCORRUPTIBLE ===" + RESET);
        System.out.println(YELLOW + BOLD + "=== PROGRESS BUILD / SKELETON VERSION ===" + RESET);

        boolean playAgain;
        do {
            // Mode selection - repeat until valid input
            int mode = -1;
            while (mode == -1) {
                System.out.println(BLUE + BOLD + "\nChoose a game mode:" + RESET);
                System.out.println(GREEN + "1: Player vs Player" + RESET);
                System.out.println(GREEN + "2: Player vs AI" + RESET);
                System.out.println(GREEN + "3: Arcade Mode (1 hero vs all others)" + RESET);
                System.out.println(RED + "x: Exit" + RESET);
                
                String modeInput = sc.next();
                if(modeInput.equalsIgnoreCase("x")) {
                    System.out.println(RED + BOLD + "Exiting game..." + RESET);
                    sc.close();
                    return;
                }

                try { 
                    mode = Integer.parseInt(modeInput);
                    if (mode < 1 || mode > 3) {
                        System.out.println(YELLOW + "Invalid input! Please choose a gamemode (1, 2, 3, or x)" + RESET);
                        mode = -1;
                    }
                } catch(Exception e) {
                    System.out.println(YELLOW + "Invalid input! Please choose a gamemde (1, 2, 3, or x)" + RESET);
                    mode = -1;
                }
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
            }

            // Play again prompt
            System.out.print(BLUE + BOLD + "\nPlay again? (y/n): " + RESET);
            String again = sc.next();
            playAgain = again.equalsIgnoreCase("y");

        } while(playAgain);

        sc.close();
        System.out.println(CYAN + BOLD + "=== INCORRUPTIBLE ===" + RESET);
        System.out.println(YELLOW + BOLD + "=== END OF PROGRESS BUILD ===" + RESET);
    }

    // === MODE LOGIC ===
    private static void playPvP(Character c1, Character c2, Scanner sc) {
        // Add (Player 1) and (Player 2) to the names for clarity in PvP mode.
        c1.setName(c1.getName() + " (Player 1)");
        c2.setName(c2.getName() + " (Player 2)");

        System.out.println(BOLD + CYAN + "\n-~- PvP: " + c1.getName() + " VS " + c2.getName() + " -~-" + RESET);
        battleLoop(c1, c2, sc);
    }

    private static void playPvAI(Character player, Character ai, Scanner sc) {
        // Add (You) to the player's name for clarity in PvAI mode.
        player.setName(player.getName() + " (You)");

        System.out.println(BOLD + CYAN + "\n--- PvAI: " + player.getName() + " VS " + ai.getName() + " (AI) ---" + RESET);
        battleLoopPvAI(player, ai, sc);
    }

    private static void playArcade(Character player, Scanner sc) {
        System.out.println(BOLD + CYAN + "\n--~-- Arcade Mode --~--" + RESET);
        System.out.println(YELLOW + "You will fight all other characters in sequence." + RESET);
        // TODO: Add array of enemies and sequential battles
    }

    // === BATTLE LOOP ===
    private static void battleLoop(Character c1, Character c2, Scanner sc) {
        Character[] characters = {c1, c2};
        int turn = 0;
        int roundNumber = 1;

        while (c1.isAlive() && c2.isAlive()) {
            // Display turn number at the start of a new round (after both players have acted)
            if (turn % 2 == 0) {
                System.out.println(CYAN + BOLD + "\n========== TURN " + roundNumber + " ==========" + RESET);
                roundNumber++;
            }

            Character current = characters[turn % 2];
            Character opponent = characters[(turn + 1) % 2];

            // Stamina is restored by using Basic attacks now. No per-turn regen.
            displayStats(c1, c2);
            if(!takeTurn(current, opponent, sc)) break; // false = exit

            turn++;
        }

        if(c1.isAlive() || c2.isAlive()) {
            System.out.println(RED + BOLD + "\n-~-~-~-~- GAME OVER -~-~-~-~-" + RESET);
            System.out.println(GREEN + BOLD + (c1.isAlive() ? c1.getName() : c2.getName()) + " wins!" + RESET);
        }
    }

    // Added on Oct 14
    private static void battleLoopPvAI(Character player, Character ai, Scanner sc) {
        int turn = 0;
        int roundNumber = 1;

        while (player.isAlive() && ai.isAlive()) {
            // Display turn number at the start of a new round (after both players have acted)
            if (turn % 2 == 0) {
                System.out.println(CYAN + BOLD + "\n========== TURN " + roundNumber + " ==========" + RESET);
                roundNumber++;
            }

            Character current = (turn % 2 == 0) ? player : ai;
            Character opponent = (turn % 2 == 0) ? ai : player;

            // Added on Oct 17 - Stamina is restored by using Basic attacks now. No per-turn regen.
            displayStats(player, ai);

            if (current == player) {
                if (!takeTurn(current, opponent, sc)) break; // Player's turn
            } else {
                takeAITurn(current, opponent); // AI's turn
            }
            turn++;
        }

        if (player.isAlive() || ai.isAlive()) {
            System.out.println(RED + BOLD + "\n-~-~-~-~- GAME OVER -~-~-~-~-" + RESET);
            System.out.println(GREEN + BOLD + (player.isAlive() ? player.getName() : ai.getName()) + " wins!" + RESET);
        }
    }

    // Added Oct 14
    private static void takeAITurn(Character current, Character opponent) {
        System.out.println(BLUE + BOLD + "\n--- " + current.getName() + "'s Turn (AI) ---" + RESET);
        Random random = new Random();
        // Added Oct 17 Simple heuristic: if AI can't afford skill/ult, prefer basic to regen.
        if (current.getStamina() >= 60) {
            current.ultimateAttack(opponent);
            return;
        }
        if (current.getStamina() >= 20) {
            // 50/50 between skill and basic if it has enough for skill
            if (random.nextBoolean()) current.skillAttack(opponent);
            else current.basicAttack(opponent);
            return;
        }
        // Otherwise, basic to regain stamina
        current.basicAttack(opponent);
    }

    // === HELPER METHODS ===
    private static Character chooseCharacter(Scanner sc, int player) {
        System.out.println(BLUE + BOLD + "\nPlayer " + player + ", Choose your Incorruptible (x to Exit):" + RESET);
        System.out.println(GREEN + "[1] Ren the Boxer         [2] Nuel the Combo Hero      [3] J3DFighter         [4] Niño de Kidlat" + RESET);
        System.out.println(GREEN + "[5] Christopher the Tank  [6] Dafaquer the Sustainer   [7] Jason the Sniper   [8] Masocol the Trialburner" + RESET);
        String input = sc.next();

        if(input.equalsIgnoreCase("x")) {
            System.out.println(RED + BOLD + "Exiting game. Thank you for playing INCORRUPTIBLES!" + RESET);
            System.exit(0);
        }

        int choice;
        try { choice = Integer.parseInt(input); }
        catch(Exception e) { choice = 1; }

        // Add new characters to the switch statement.
        Character selected = switch(choice) {
            case 1 -> new Ren();
            case 2 -> new Nuel();
            case 3 -> new Jed();
            case 4 -> new Nino();
            case 5 -> new Christopher();
            case 6 -> new Dafaquer();
            case 7 -> new Jason();
            case 8 -> new Masocol();
            default -> null;
        };
        //Added Random Selection for invalid character selection
        if (selected == null) {
            // Pick a random character if choice was invalid
            int randomChoice = new Random().nextInt(8) + 1;
            System.out.println(YELLOW + BOLD + "Invalid choice! Picking a random Incorruptible..." + RESET);
            selected = switch(randomChoice) {
                case 1 -> new Ren();
                case 2 -> new Nuel();
                case 3 -> new Jed();
                case 4 -> new Nino();
                case 5 -> new Christopher();
                case 6 -> new Dafaquer();
                case 7 -> new Jason();
                case 8 -> new Masocol();
                default -> new Ren(); 
            };
            System.out.println(GREEN + BOLD + "Character chosen: " + selected.getName() + RESET);
        }
        return selected;
    }

    private static void displayStats(Character c1, Character c2) {
        System.out.println(CYAN + BOLD + "\n                 --- Current Stats ---" + RESET);
        // Header with character names, padded to 25 characters for alignment
        System.out.printf(BOLD + "%-25s\t%-25s%n" + RESET, c1.getName(), c2.getName());
        System.out.println(CYAN + "-----------------------------   -----------------------------" + RESET); // Separator line

        // HP Stats, showing current/max
        String c1HP = String.format("HP: %d/%d", c1.getHealth(), c1.getMaxHealth());
        String c2HP = String.format("HP: %d/%d", c2.getHealth(), c2.getMaxHealth());
        System.out.printf(RED + "%-25s\t%-25s%n" + RESET, c1HP, c2HP);

        // Stamina Stats, showing current/max
        String c1Stamina = String.format("Stamina: %d/%d", c1.getStamina(), c1.getMaxStamina());
        String c2Stamina = String.format("Stamina: %d/%d", c2.getStamina(), c2.getMaxStamina());
        System.out.printf(YELLOW + "%-25s\t%-25s%n" + RESET, c1Stamina, c2Stamina);
    }

    private static boolean takeTurn(Character current, Character opponent, Scanner sc) {
        System.out.println(BLUE + BOLD + "\n--- " + current.getName() + "'s Turn ---" + RESET);
        // The costs displayed here are based on the current characters (Ren and Nuel).
        // If new characters have different costs, this text may need to be updated.
        System.out.println(GREEN + "[1] Basic (Low Dmg, restores stamina)" + RESET);
        System.out.println(YELLOW + "[2] Skill (Med Dmg, "+  current.getSkillCost() +" Stamina)" + RESET);
        System.out.println(RED + "[3] Ultimate (High Dmg, "+  current.getUltimateCost() +" Stamina)" + RESET);
        System.out.println(CYAN + "[x] Exit" + RESET);
        String input = sc.next();

        if(input.equalsIgnoreCase("x")) {
            System.out.println(RED + current.getName() + " has exited the game!" + RESET);
            return false; // exit signal
        }

        int choice;
        try { choice = Integer.parseInt(input); }
        catch(Exception e) {
            System.out.println(YELLOW + "Invalid! Skipping turn." + RESET);
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