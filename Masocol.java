class Masocol extends Character {
    public Masocol() {
        super("Masocol the Trialburner", 100, 100, 10);
    }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 12, 15, 0, "Objection Strike");
        onBasicAttack();
    }

    @Override
    public void skillAttack(Character target) {
        executeCombo(target, 3,6,5, 5, 25, "Scorched Verdict");
    }

    @Override
    public void ultimateAttack(Character target) {
        executeCombo(target, 6,8,6, 12, 60, "Trial by Fire");
    }

     @Override
    public int getSkillCost() { 
                        return 25; 
    }
    @Override
    public int getUltimateCost() { 
                        return 60; 
    }
}