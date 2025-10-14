import java.util.*;

public class INCORRUPTIBLE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== WELCOME TO INCORRUPTIBLE ===");
        System.out.println("=== PROGRESS BUILD / ARCADE EDITION ===");

        boolean playAgain;
        do {
            System.out.println("\nChoose a game mode:");
            System.out.println("1: Player vs Player");
            System.out.println("2: Player vs AI");
            System.out.println("3: Arcade Mode (1 hero vs all others)");
            System.out.println("x: Exit");

            String input = sc.next();
            if (input.equalsIgnoreCase("x")) {
                System.out.println("Exiting game...");
                break;
            }

            int mode;
            try { mode = Integer.parseInt(input); }
            catch (Exception e) { mode = 1; }

            Character player1 = chooseCharacter(sc, 1);
            Character player2;

            switch (mode) {
                case 1 -> {
                    player2 = chooseCharacter(sc, 2);
                    playBattle(player1, player2, sc);
                }
                case 2 -> {
                    System.out.println("\n--- Player vs AI ---");
                    player2 = getRandomAI(player1);
                    System.out.println("You will face AI: " + player2.getName());
                    playBattle(player1, player2, sc);
                }
                case 3 -> playArcade(player1, sc);
                default -> System.out.println("Invalid mode.");
            }

            System.out.print("\nPlay again? (y/n): ");
            playAgain = sc.next().equalsIgnoreCase("y");

        } while (playAgain);

        System.out.println("\n=== THANK YOU FOR PLAYING INCORRUPTIBLE ===");
        sc.close();
    }

    // === CHARACTER SELECTION ===
    private static Character chooseCharacter(Scanner sc, int player) {
        System.out.println("\nPlayer " + player + ", choose your character:");
        System.out.println("[1] Ren the Boxer");
        System.out.println("[2] Nuel the Combo Hero");
        System.out.println("[3] Nagumo the Trickster");
        System.out.println("[4] Taro Sakamoto the Silent Marksman");
        System.out.print("Enter choice: ");

        int choice;
        try { choice = Integer.parseInt(sc.next()); } 
        catch (Exception e) { choice = 1; }

        return switch (choice) {
            case 1 -> new Ren();
            case 2 -> new Nuel();
            case 3 -> new Nagumo();
            case 4 -> new TaroSakamoto();
            default -> new Ren();
        };
    }

    // === PLAYER VS PLAYER / AI ===
    private static void playBattle(Character p1, Character p2, Scanner sc) {
        System.out.println("\n--- " + p1.getName() + " VS " + p2.getName() + " ---");
        Character[] fighters = {p1, p2};
        int turn = 0;

        while (p1.isAlive() && p2.isAlive()) {
            Character current = fighters[turn % 2];
            Character enemy = fighters[(turn + 1) % 2];

            current.regenerateStamina();
            showStats(p1, p2);

            if (!takeTurn(current, enemy, sc)) break;
            turn++;
        }

        System.out.println("\n=== " + (p1.isAlive() ? p1.getName() : p2.getName()) + " wins! ===");
    }

    // === ARCADE MODE ===
    private static void playArcade(Character player, Scanner sc) {
        System.out.println("\n=== ARCADE MODE START ===");
        System.out.println("Defeat all opponents to face the Final Boss!");

        List<Character> stages = new ArrayList<>(Arrays.asList(
            new Ren(), new Nuel(), new Nagumo(), new TaroSakamoto()
        ));
        stages.removeIf(c -> c.getName().equals(player.getName()));

        int stageNum = 1;
        for (Character enemy : stages) {
            System.out.println("\n--- STAGE " + stageNum + ": " + player.getName() + " VS " + enemy.getName() + " ---");
            playBattle(player, enemy, sc);

            if (!player.isAlive()) {
                System.out.println("\n💀 You were defeated at Stage " + stageNum + "!");
                return;
            }

            System.out.println("\n✅ Stage " + stageNum + " cleared!");
            player.recoverAfterStage();
            stageNum++;
        }
        
        // FINAL BOSS
        System.out.println("\n🔥 FINAL STAGE: THE DARKNESS RISES...");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        System.out.println("\n💀 FINAL BOSS APPEARS: King Void, the Corrupted Hero! 💀");

        Character boss = new KingVoid();
        playBattle(player, boss, sc);

        if (player.isAlive()) {
            System.out.println("\n🌟 YOU DEFEATED KING VOID! 🌟");
            System.out.println("=== ARCADE MODE COMPLETE ===");
        } else {
            System.out.println("\n💀 You were consumed by darkness...");
        }
    }

    // === HELPERS ===
    private static Character getRandomAI(Character exclude) {
        List<Character> chars = Arrays.asList(new Ren(), new Nuel(), new Nagumo(), new TaroSakamoto());
        chars.removeIf(c -> c.getName().equals(exclude.getName()));
        return chars.get(new Random().nextInt(chars.size()));
    }

    private static void showStats(Character c1, Character c2) {
        System.out.println("\n--- Current Stats ---");
        System.out.printf("%-25s HP: %3d | Stamina: %3d%n", c1.getName(), c1.getHealth(), c1.getStamina());
        System.out.printf("%-25s HP: %3d | Stamina: %3d%n", c2.getName(), c2.getHealth(), c2.getStamina());
    }

    private static boolean takeTurn(Character attacker, Character defender, Scanner sc) {
        System.out.println("\n--- " + attacker.getName() + "'s Turn ---");
        System.out.println("[1] Basic  [2] Skill  [3] Ultimate  [x] Exit");
        String input = sc.next();

        if (input.equalsIgnoreCase("x")) {
            System.out.println(attacker.getName() + " has forfeited!");
            return false;
        }

        switch (input) {
            case "1" -> attacker.basicAttack(defender);
            case "2" -> attacker.skillAttack(defender);
            case "3" -> attacker.ultimateAttack(defender);
            default -> System.out.println("Invalid move!");
        }
        return true;
    }
}