class Masocol extends Character {
    public Masocol() {
        // Default stats. TODO: Customize them!
        super("Masocol", 100, 100, 10);
    }

    @Override
    public void basicAttack(Character target) {
        // Default attack values. TODO: Customize them!
        performAttack(target, 10, 15, 0, "Basic Attack");
    }

    @Override
    public void skillAttack(Character target) {
        // Default attack values. TODO: Customize them!
        performAttack(target, 20, 25, 20, "Skill Attack");
    }

    @Override
    public void ultimateAttack(Character target) {
        // Default attack values. TODO: Customize them!
        performAttack(target, 35, 40, 40, "Ultimate Attack");
    }
}