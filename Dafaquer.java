class Dafaquer extends Character {
    public Dafaquer() {
        super("Dafaquer the Sustainer", 140, 110, 10);
    }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 10, 14, 0, "Life Jab");
        onBasicAttack();
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 18, 26, 25, "Vital Combo");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 35, 50, 45, "Recovery Strike");
    }
     @Override
    public int getSkillCost() { 
                        return 25; 
    }
    @Override
    public int getUltimateCost() { 
                        return 45; 
    }
}