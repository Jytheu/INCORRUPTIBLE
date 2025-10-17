class Ren extends Character {
    public Ren() { super("Ren the Boxer", 120, 100, 10); }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 15, 18, 0, "Quick Jab");
        onBasicAttack(); // Ensure onBasicAttack is called after performing the attack
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 22, 25, 20, "Uppercut");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 40, 50, 60, "Slam");
    }
}