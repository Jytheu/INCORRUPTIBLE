class Nino extends Character {
    public Nino() {
        super("Nino de Kidlat", 115, 105, 10);
    }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 10, 16, 0, "Quick Jab");
        onBasicAttack();
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 20, 25, 25, "Lightning Strike");
    }

    @Override
    public void ultimateAttack(Character target) {
        executeCombo(target, 2,2,30, 35, 45, "Flash Combo");
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