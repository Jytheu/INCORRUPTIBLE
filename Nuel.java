class Nuel extends Character {
    public Nuel() { super("Nuel the Combo Hero", 120, 100, 10); }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 15, 18, 0, "Justice Combo");
        onBasicAttack();
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 20, 22, 20, "Strategic Uppercut");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 40, 50, 60, "Power Debate");
    }
}