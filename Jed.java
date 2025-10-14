class Jed extends Character {
    // Jed is a "glass cannon" character with lower HP but high damage potential.
    public Jed() {
        // super(name, maxHp, maxStamina, regenStamina)
        super("Jed the Gadgeteer", 100, 120, 8);
    }

    @Override
    public void basicAttack(Character target) {
        // A low-damage, no-cost attack.
        performAttack(target, 12, 15, 0, "Prank Trap");
    }

    @Override
    public void skillAttack(Character target) {
        // An unreliable attack with a wide damage range.
        performAttack(target, 18, 28, 20, "Gadget Barrage");
    }

    @Override
    public void ultimateAttack(Character target) {
        // A high-damage, high-cost ultimate.
        performAttack(target, 50, 60, 60, "Viral Meme");
    }
}