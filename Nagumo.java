class Nagumo extends Character {
    public Nagumo() {
        super("Nagumo the Trickster", 110, 120, 15);
    }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 14, 19, 0, "Sneaky Strike");
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 20, 25, 25, "Shadow Feint");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 28, 35, 45, "Illusion Breaker");
    }
}