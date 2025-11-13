class Ren extends Character {
    public Ren() { super("Ren the Boxer", 140, 120, 10); }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 12, 18, 0, "Quick Jab");
        onBasicAttack(); 
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 22, 30, 20, "Uppercut");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 45, 60, 45, "Slam");
    }
    @Override
    public int getSkillCost() { 
                        return 20; 
    }
    @Override
    public int getUltimateCost() { 
                        return 45; 
    }
}