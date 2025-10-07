import java.util.*;

public class INCORRUPTIBLE {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to INCORRUPTIBLE!");
        System.out.println("Choose your fighters!");
        System.out.println("Skills: 1 = Basic, 2 = Skill, 3 = Ultimate\n");

        System.out.println("Available Characters:");
        System.out.println("1. Ren");
        System.out.println("2. Nuel");
        System.out.println("3. (Other characters to be added by team)");

        System.out.print("\nPlayer 1, choose your character (1-3): ");
        int p1Choice = scanner.nextInt();
        Character char1 = chooseCharacter(p1Choice);

        System.out.print("Player 2, choose your character (1-3): ");
        int p2Choice = scanner.nextInt();
        Character char2 = chooseCharacter(p2Choice);

        System.out.println("\n" + char1.getName() + " VS " + char2.getName() + "!");
        System.out.println("The battle begins!\n");

        Character[] characters = {char1, char2};
        int currentTurn = 0;

        while (true) {
            Character current = characters[currentTurn % 2];
            Character opponent = characters[(currentTurn + 1) % 2];

            if (!opponent.isAlive()) {
                System.out.println("\n--- Game Over! ---");
                System.out.println(current.getName() + " wins!");
                break;
            }

            current.regenerateStamina();
            System.out.println("\n--- " + current.getName() + "'s Turn ---");
            System.out.println(char1.getName() + ": " + (char1.isAlive() ? char1.getHealth() + "/" + char1.getMaxHealth() : "DEAD") + " HP");
            System.out.println(char2.getName() + ": " + (char2.isAlive() ? char2.getHealth() + "/" + char2.getMaxHealth() : "DEAD") + " HP");
            System.out.println(current.getName() + " Stamina: " + current.getStamina() + "/" + current.getMaxStamina());

            System.out.print("Choose skill (1=Basic, 2=Skill, 3=Ultimate): ");
            int skillChoice = scanner.nextInt();

            switch(skillChoice) {
                case 1: current.basicAttack(opponent); break;
                case 2: current.skillAttack(opponent); break;
                case 3: current.ultimateAttack(opponent); break;
                default: System.out.println("Invalid choice! Skipping turn."); break;
            }

            currentTurn++;
        }

        scanner.close();
    }

    public static Character chooseCharacter(int choice) {
        return switch (choice) {
            case 1 -> new Ren();
            case 2 -> new Nuel();
            default -> {
                System.out.println("Invalid choice! Defaulting to Ren.");
                yield new Ren();
            }
        };
    }
}