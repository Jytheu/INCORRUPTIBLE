import java.util.*;

public class INCORRUPTIBLE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== WELCOME TO INCORRUPTIBLE ===");

        Character char1 = chooseCharacter(sc, 1);
        Character char2 = chooseCharacter(sc, 2);

        System.out.println("\n" + char1.getName() + " VS " + char2.getName() + "!\nLet the battle begin!");

        Character[] characters = {char1, char2};
        int turn = 0;

        while (char1.isAlive() && char2.isAlive()) {
            Character current = characters[turn % 2];
            Character opponent = characters[(turn + 1) % 2];

            current.regenerateStamina();
            displayStats(char1, char2);
            takeTurn(current, opponent, sc);

            turn++;
        }

        System.out.println("\n--- GAME OVER ---");
        System.out.println((char1.isAlive() ? char1.getName() : char2.getName()) + " wins!");
        sc.close();
    }

    private static Character chooseCharacter(Scanner sc, int player) {
        System.out.println("\nPlayer " + player + ", choose your character:");
        System.out.println("1. Ren   2. Nuel   3. (others coming soon)");
        int choice = sc.nextInt();
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

    private static void takeTurn(Character current, Character opponent, Scanner sc) {
        System.out.println("\n--- " + current.getName() + "'s Turn ---");
        System.out.println("1: Basic  2: Skill  3: Ultimate");
        int choice = sc.nextInt();

        switch(choice) {
            case 1 -> current.basicAttack(opponent);
            case 2 -> current.skillAttack(opponent);
            case 3 -> current.ultimateAttack(opponent);
            default -> System.out.println("Invalid! Skipping turn.");
        }
    }
}