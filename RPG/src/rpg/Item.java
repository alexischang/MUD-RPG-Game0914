package rpg;

public class Item {

    protected Ability ability = new Ability();
    private int buffTime;
    private boolean useable;
    private int price;
    private String useage = "";
    private boolean isPermanentBuff;
    private int againstAnimalOrDemon = 0;
    private boolean isWeapon = false;


    public boolean isWeapon() {
        return isWeapon;
    }

    public void setWeapon(boolean weapon) {
        isWeapon = weapon;
    }

    public boolean isArmor() {
        return isArmor;
    }

    public void setArmor(boolean armor) {
        isArmor = armor;
    }

    private boolean isArmor = false;

    public int getAgainstAnimalOrDemon() {
        return againstAnimalOrDemon;
    }

    public void setAgainstAnimalOrDemon(int againstAnimalOrDemon) {
        this.againstAnimalOrDemon = againstAnimalOrDemon;
    }

    public boolean isPermanentBuff() {
        return isPermanentBuff;
    }

    public void setPermanentBuff(boolean permanentBuff) {
        isPermanentBuff = permanentBuff;
    }

    public int getBuffTime() {
        return buffTime;
    }

    public void setUseage(String useage) {
        this.useage = useage;
    }

    public void setBuffTime(int buffTime) {
        this.buffTime = buffTime;
    }

    public void reduceBuffTime() {
        buffTime--;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public void setUseable(boolean initial) {
        this.useable = initial;
    }

    public boolean getUseable() {
        return this.useable;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public Item healingPotion() {
        ability.setName("治療藥水");
        setUseable(true);
        ability.setHp(3);
        setPrice(3);
        setUseage("回復3點血量");
        return this;
    }

    public Item powerIncreasePotion() {
        ability.setName("力量增強藥水");
        ability.setStr(2);
        setUseable(false);
        setPrice(2);
        setUseage("+2力量，持續兩次攻擊，戰鬥結束後消失");
        setBuffTime(2);
        return  this;
    }

    public Item defenceIncreasePotion() {
        ability.setName("防禦增強藥水");
        ability.setDef(2);
        setUseable(false);
        setPrice(2);
        setUseage("+2防禦，持續兩次攻擊，戰鬥結束後消失");
        setBuffTime(2);
        return this;
    }

    public Item leather() {
        ability.setName("動物皮");
        setUseable(false);
        setPrice(1);
        return  this;
    }

    public Item wolfLeg() {
        ability.setName("狼腿");
        setUseable(false);
        setPrice(1);
        return  this;
    }

    public Item lionClaw() {
        ability.setName("獅爪");
        setUseable(false);
        setPrice(1);
        return  this;
    }

    public Item pork() {
        ability.setName("豬肉");
        ability.setHp(2);
        ability.setDef(-1);
        setUseable(true);
        setPrice(2);
        setUseage("回復2點血量，-1防禦");
        return  this;
    }

    public Item amimalSlayerMark() {
        ability.setName("動物屠殺者徽章");
        ability.setHit(1);
        ability.setStr(1);
        setUseable(true);
        setPrice(4);
        setBuffTime(99);
        setUseage("與動物戰鬥時，+1攻擊力,+1命中");
        setPermanentBuff(true);
        setAgainstAnimalOrDemon(1);
        return  this;
    }

    public Item heartOfGhost() {
        ability.setName("幽魂之心");
        setUseable(false);
        setPrice(1);
        return  this;
    }

    public Item wolfTooth() {
        ability.setName("狼牙");
        setUseable(false);
        setPrice(1);
        return  this;
    }

    public Item glass() {
        ability.setName("玻璃");
        setUseable(false);
        setPrice(1);
        return  this;
    }

    public Item demomSlayerMark() {
        ability.setName("惡魔屠殺者徽章");
        ability.setStr(1);
        ability.setHit(1);
        setUseable(true);
        setPrice(4);
        setBuffTime(99);
        setUseage("與魔物戰鬥時，+1攻擊力,+1命中");
        setPermanentBuff(true);
        setAgainstAnimalOrDemon(2);
        return  this;
    }

    public void printItem() {
        System.out.print("名稱:" + ability.getName() + "\t價格:" + this.price);
        if (useable) {
            System.out.println("\t用途:" + useage);
            return;
        }
        System.out.println("無功能");
    }

    public String getUseage() {
        return useage;
    }

    /**
     * 更改的部分
     */
    public Item intelliIncreasePotion() {
        ability.setName("智慧增強藥水");
        setUseable(true);
        ability.setIntelli(2);
        setPrice(3);
        setUseage("+2智慧 持續兩次攻擊，戰鬥結束後消失");
        setBuffTime(2);
        return  this;
    }
    public Item strongAcid() {
        ability.setName("強酸");
        setUseable(true);
        ability.setStr(2);//怪物防禦力-2等於玩家攻擊+2
        setPrice(4);
        setUseage("怪物 防禦-2 持續三次攻擊，戰鬥結束後消失");
        setBuffTime(3);
        return  this;
    }
    public Item PowerBurst() {
        ability.setName("力量爆發");
        setUseable(true);
        ability.setStr(3);
        ability.setDef(-4);
        setPrice(5);
        setUseage("+3力量 -4防禦，持續兩次攻擊，戰鬥結束後消失");
        setBuffTime(2);
        return  this;
    }
    public Item WisdomBurst() {
        ability.setName("智慧爆發");
        setUseable(true);
        ability.setIntelli(3);
        ability.setHp(-2);
        setPrice(4);
        setUseage("+3智慧，持續兩次攻擊，戰鬥結束或者是效果結束後損失兩2滴血量，若血量歸0，則死亡");
        setBuffTime(2);
        return  this;
    }


}
