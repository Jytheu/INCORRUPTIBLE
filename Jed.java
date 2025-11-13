class Jed extends Character {
    // Jed is a "glass cannon" character with lower HP but high damage potential.
    public Jed() {
        // super(name, maxHp, maxStamina, regenStamina)
        super("Jed the Gadgeteer", 110, 100, 8);
    }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 10, 14, 0, "Prank Trap");
        onBasicAttack();
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 15, 20, 30, "Gadget Barrage");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 40, 50, 50, "Viral Meme");
    }
     @Override
    public int getSkillCost() { 
                        return 30; 
    }
    @Override
    public int getUltimateCost() { 
                        return 50; 
    }
}