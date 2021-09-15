package rpg;

public class Armor extends Item {

    private boolean initial;

    public Armor() {
        super.setArmor(true);
    }


    public void woodenArmor() {
        ability.setDef(1);
        ability.setEquipmentWeight(10);
        this.setInitial(true);
        ability.setName("木甲");
    }

    public void chainMail() {
        ability.setDef(2);
        ability.setEquipmentWeight(15);
        this.setInitial(true);
        ability.setName("鎖子甲");
    }

    public void plateArmor() {
        ability.setDef(4);
        ability.setEquipmentWeight(20);
        this.setInitial(true);
        ability.setName("板甲");
    }

    public void leatherArmor() {
        ability.setDef(2);
        ability.setEquipmentWeight(9);
        this.setInitial(false);
        ability.setName("皮甲");
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public boolean getInitial() {
        return this.initial;
    }

    public void printItem() {
        System.out.println("名稱:" + this.ability.getName()
                + "\t防禦力:" + this.ability.getDef()
                + "\t重量:" + this.ability.getEquipmentWeight());
    }
}
