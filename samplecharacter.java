class sampleCharacter extends Character {
    public sampleCharacter() { super("Sample Ra Ni", 120, 100, 10); }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 15, 18, 0, "Quick Jab");
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 22, 25, 20, "Uppercut");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 25, 30, 40, "Slam");
    }
}