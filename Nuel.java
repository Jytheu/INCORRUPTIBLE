class Nuel extends Character {
    public Nuel() { super("Nuel the Combo Hero", 120, 130, 10); }

    @Override
    public void basicAttack(Character target) {
        executeCombo(target, 2, 4, 4,6,0, "Justice Combo");
        onBasicAttack();
    }

    @Override
    public void skillAttack(Character target) {
        executeCombo(target, 2, 2, 12, 17, 25, "Strategic Uppercut");
    }

    @Override
    public void ultimateAttack(Character target) {
       executeCombo(target, 3,3,20, 25, 50, "Power Debate");
    }
     @Override
    public int getSkillCost() { 
                        return 25; 
    }
    @Override
    public int getUltimateCost() { 
                        return 50; 
    }
}