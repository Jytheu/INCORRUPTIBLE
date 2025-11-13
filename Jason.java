class Jason extends Character {
    public Jason() {
        super("Jason the Sniper", 100, 90, 10);
    }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 10, 18, 0, "Eagle Eye");
        onBasicAttack();
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 22, 35, 30, "Pinpoint Strike");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 60, 90, 65, "Expose Shot");
    }
     @Override
    public int getSkillCost() { 
                        return 30; 
    }
    @Override
    public int getUltimateCost() { 
                        return 65; 
    }
}