class Christopher extends Character {
    public Christopher() {
        super("Christopher the Tank", 160, 100, 10);
    }

    // One-time second life flag
    private boolean secondLifeUsed = false;

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 8, 12, 0, "Heroic Roar");
        onBasicAttack();
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 16, 22, 20, "Shield Bash");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 25, 45, 40, "Unyielding Stand");
    }
     @Override
    public int getSkillCost() { 
                        return 20; 
    }
    @Override
    public int getUltimateCost() { 
                        return 40; 
    }

    @Override
    public void takeDamage(int dmg) {
        super.takeDamage(dmg);
        if (hp == 0 && !secondLifeUsed) {
            hp = Math.max(1, maxHp / 2);
            secondLifeUsed = true;
            System.out.println(CYAN + BOLD + name + " activates Immortal Will! Restored to " + hp + " HP." + RESET);
        }
    }
}