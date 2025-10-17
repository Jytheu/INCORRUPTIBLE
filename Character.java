import java.util.*;

public abstract class Character {
    protected String name;
    protected int hp, maxHp;
    protected int stamina, maxStamina;
    protected int regen;
    protected Random random = new Random();
   
        private static final double STARTING_STAMINA_RATIO = 0.375; // start at 37.5% of max
        private static final double BASIC_RESTORE_RATIO = 0.20; // basic attack restores 20% of max

    public Character(String name, int hp, int stamina, int regen) {
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.maxStamina = stamina;
      
        this.stamina = Math.max(1, (int)Math.round(this.maxStamina * STARTING_STAMINA_RATIO));
        this.regen = regen;
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

    //Added Oct 17
    protected void onBasicAttack() {
        int restore = Math.max(1, (int)Math.round(maxStamina * BASIC_RESTORE_RATIO));
        stamina = Math.min(maxStamina, stamina + restore);
        System.out.println(name + " regains " + restore + " stamina from a basic attack.");
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