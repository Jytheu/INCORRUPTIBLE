import java.util.*;

public abstract class Character {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int stamina;
    protected int maxStamina;
    protected int regenStamina;
    protected Random random;

    public Character(String name, int maxHp, int maxStamina, int regenStamina){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxStamina = maxStamina;
        this.stamina = maxStamina;
        this.regenStamina = regenStamina;
        this.random = new Random();
    }

    public void regenerateStamina(){
        this.stamina = Math.min(this.maxStamina, this.stamina + this.regenStamina);
    }

    protected void performAttack(Character target, int minDamage, int maxDamage, int staminaCost, String skillName) {
        if (this.stamina < staminaCost) {
            System.out.println(this.name + " does not have enough stamina for " + skillName + "! (Costs " + staminaCost + ", has " + this.stamina + ")");
            return;
        }
        this.stamina -= staminaCost;
        int damage = minDamage + this.random.nextInt(maxDamage - minDamage + 1);
        target.takeDamage(damage);
        System.out.println(this.name + " uses " + skillName + " and deals " + damage + " damage to " + target.name + "!");
    }

    public abstract void basicAttack(Character target);
    public abstract void skillAttack(Character target);
    public abstract void ultimateAttack(Character target);

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) hp = 0;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

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