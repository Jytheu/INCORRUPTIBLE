class TaroSakamoto extends Character {
    public TaroSakamoto() {
        super("Taro Sakamoto the Silent Marksman", 130, 90, 8);
    }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 16, 20, 0, "Precise Shot");
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 25, 28, 25, "Rapid Barrage");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 35, 40, 50, "One Shot Finish");
    }
}