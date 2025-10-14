public class KingVoid extends Character { 
    public KingVoid() {
        super("King Void, the Corrupted Hero", 300, 150, 20);
    }

    @Override
    public void basicAttack(Character target) {
        performAttack(target, 20, 30, 0, "Shadow Swipe");
    }

    @Override
    public void skillAttack(Character target) {
        performAttack(target, 40, 55, 40, "Dark Surge");
    }

    @Override
    public void ultimateAttack(Character target) {
        performAttack(target, 80, 100, 70, "VOID COLLAPSE");
    }
} 