package rpg;

public class Character {
    protected Ability ability = new Ability();
    private int kind;  //暫時等於地圖種類，也等於怪物種類

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public boolean isDead() {
        Boolean isDead = false;
        if (ability.getHp() <= 0) {
            isDead = true;
        }
        return isDead;
    }
}
