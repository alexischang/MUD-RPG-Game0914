package rpg;

public class Weapon extends Item {

    private boolean initial;

    public Weapon() {
        super.setWeapon(true);
    }

    public void sword() {
        this.ability.setStr(2);
        this.ability.setEquipmentWeight(10);
        this.setInitial(true);
        ability.setName("劍");
        this.ability.setIntelli(-1);
    }

    public void axe() {
        this.ability.setStr(4);
        this.ability.setEquipmentWeight(15);
        this.setInitial(true);
        ability.setName("斧頭");
        this.ability.setIntelli(-3);
    }

    public void wand() {
        this.ability.setStr(-2);
        this.ability.setEquipmentWeight(7);
        this.setInitial(true);
        ability.setName("法杖");
        this.ability.setIntelli(5);
    }

    public void bow() {
        this.ability.setStr(3);
        this.ability.setEquipmentWeight(9);
        this.setInitial(false);
        ability.setName("弓箭");
        this.ability.setIntelli(0);
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public boolean getInitial() {
        return this.initial;
    }

    public void printItem() {
        System.out.println(ability.getName() + " 攻擊力: " + ability.getStr() + " 重量: " + ability.getEquipmentWeight()+ " 魔法攻擊力: " + ability.getIntelli());
    }
}
