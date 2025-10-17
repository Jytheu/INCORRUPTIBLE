import java.util.*;

public abstract class Character {
    protected String name;
    protected int hp, maxHp;
    protected int stamina, maxStamina;
    protected int regen;
    protected Random random = new Random();

    public Character(String name, int hp, int stamina, int regen) {
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.maxStamina = stamina;
        this.stamina = stamina;
        this.regen = regen;
    }

    public void regenerateStamina() {
        stamina = Math.min(maxStamina, stamina + regen);
    }

    public void recoverAfterStage() {
        int heal = (int)(maxHp * 0.3);
        hp = Math.min(maxHp, hp + heal);
        stamina = maxStamina;
        System.out.println("Recovered 30% HP and full stamina!");
    }

    protected void performAttack(Character target, int min, int max, int cost, String skillName) {
        if (stamina < cost) {
            System.out.println(name + " lacks stamina for " + skillName + "!");
            return;
        }
        stamina -= cost;
        int dmg = min + random.nextInt(max - min + 1);
        target.takeDamage(dmg);
        System.out.println(name + " uses " + skillName + " dealing " + dmg + " damage!");
    }

    public abstract void basicAttack(Character target);
    public abstract void skillAttack(Character target);
    public abstract void ultimateAttack(Character target);

    public void takeDamage(int dmg) { hp = Math.max(0, hp - dmg); }
    public boolean isAlive() { return hp > 0; }

    public String getName() { return this.name; }

    /**
     * Sets the character's name. This is used to add suffixes like (You) or (Player 1).
     * @param name The new name for the character.
     */
    public void setName(String name) { this.name = name; }
    public int getHealth() { return this.hp; }
    public int getMaxHealth() { return this.maxHp; }
    public int getStamina() { return this.stamina; }
    public int getMaxStamina() { return this.maxStamina; }
}