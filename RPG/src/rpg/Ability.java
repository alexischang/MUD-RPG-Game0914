package rpg;
import java.util.ArrayList;
public class Ability {
    private int maxHp = 0;
    private int hp = 0;
    private int str = 0;          ///目前攻擊力=力量 未來應該會改善
    private int def = 0;
    private int hit = 0;
    private int dex = 0;
    private int exp = 0;
    private int LV = 0;
    private int itemMaxmum = 0;
    private int weaponMaxmum = 0;
    private int armorMaxmum = 0;
    private int con = 0;
    private int equipmentWeight = 0;
    private int maxExp = 0;
    private String name;
    private int migDef =0; //week2 add 魔法防禦
    private int intelli =0; //week2 add 智慧
    private ArrayList<Skill> skill = new ArrayList<Skill>(); //week2 技能陣列


    public void setSkill(int id, int name, double probability, int cdTime, int level){
        Skill newSkill = new Skill();
        newSkill.setId(id);
        newSkill.setName(name);
        newSkill.setProbability(probability);
        newSkill.setCdTime(cdTime);
        newSkill.setLevel(level);
        skill.add(newSkill);
    }
    public ArrayList<Skill> getSkill(){
        return skill;
    }

    //week2 setMightDef
    public void setMigDef(int migDef) {
        this.migDef=migDef;
    }
    //week2 getMigDef
    public int getMigDef() {
        return migDef;
    }
    //week2 setIntelli
    public void setIntelli(int intelli) {
        this.intelli=intelli;
    }
    //week2 getIntelli
    public int getIntelli() {
        return intelli;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLV() {
        return LV;
    }

    public void setLV(int LV) {
        this.LV = LV;
    }

    public int getItemMaxmum() {
        return itemMaxmum;
    }

    public void setItemMaxmum(int itemMaxmum) {
        this.itemMaxmum = itemMaxmum;
    }

    public int getWeaponMaxmum() {
        return weaponMaxmum;
    }

    public void setWeaponMaxmum(int weaponMaxmum) {
        this.weaponMaxmum = weaponMaxmum;
    }

    public int getArmorMaxmum() {
        return armorMaxmum;
    }

    public void setArmorMaxmum(int armorMaxmum) {
        this.armorMaxmum = armorMaxmum;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getEquipmentWeight() {
        return equipmentWeight;
    }

    public void setEquipmentWeight(int equipmentWeight) {
        this.equipmentWeight = equipmentWeight;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public void addHp(int value) {
        hp += value;
    }

    public void addMaxHp(int value) {
        maxHp += value;
    }

    public void addStr(int value) {
        str += value;
        setCon(str * 10);
    }

    public void addDef(int value) {
        def += value;
    }

    public void addDex(int value) {
        dex += value;
    }

    public void addHit(int value) {
        hit += value;
    }

    public void addExp(int value) {
        exp += value;
    }

    public void lvUp() {
        LV++;
    }

    public String toString() {
        return "各項素質:\n" +
                "血量(當前/最大): " + getHp() + "/" + getMaxHp() + "\n" +
                "力量: " + getStr() + "\n" +
                "敏捷: " + getDex() + "\n" +
                "命中: " + getHit() + "\n" +
                "防禦: " + getDef() + "\n";
    }

    public void merge(Ability newAbility) {
        maxHp += newAbility.maxHp;
        hp += newAbility.hp;
        if (hp > maxHp) {
            hp = maxHp;
        }
        str += newAbility.str;
        dex += newAbility.dex;
        def += newAbility.def;
        hit += newAbility.hit;
        equipmentWeight += newAbility.equipmentWeight;
        setCon(str * 10);
    }

    public void unMerge(Ability newAbility) {
        hp -= newAbility.hp;
        str -= newAbility.str;
        dex -= newAbility.dex;
        def -= newAbility.def;
        hit -= newAbility.hit;
        equipmentWeight -= newAbility.equipmentWeight;
        setCon(str * 10);
    }
}
