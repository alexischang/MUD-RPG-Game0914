package rpg;

public class Skill {
    private int id; //技能編號
    private SkillName name; //技能名字
    private int probability; //技能施放機率
    private int cdTime; //冷卻時間
    private int initCdTime; //初始冷卻時間
    private int level; //等級

    public enum SkillName{
        爪擊,
        衝撞,
        輾壓,
        風刃,
        招魂,
        迷惑,
        火球術,
        風刃術,
        全力斬擊,
        魔法飛彈;

        private int value;
        public  static SkillName valueOf (int index){
            switch (index) {
                case 0:
                    return 爪擊;
                case 1:
                    return 衝撞;
                case 2:
                    return 輾壓;
                case 3:
                    return 風刃;
                case 4:
                    return 招魂;
                case 5:
                    return 迷惑;
                case 6:
                    return 火球術;
                case 7:
                    return 風刃術;
                case 8:
                    return 全力斬擊;
                case 9:
                    return 魔法飛彈;
                default:
                    // System.out.println("bug");
                    return 風刃術;
            }
        }
    }

    //switch function to apply skills
    //魔法飛彈 (智慧 + 2)
    public int doItEffect(int num,Character character){
        int result=0;
        switch (num){
            case 0: //爪擊 (力量＋2)
                result = character.getAbility().getStr()+2;
                break;
            case 1: //衝撞 (力量＋1)
                result = character.getAbility().getStr()+1;
                break;

            case 2: //輾壓 (防禦 +3)
                result = character.getAbility().getDef()+3;
                break;

            case 3: //風刃 (智慧 + 1)
                result = character.getAbility().getIntelli()+1;
                break;

            case 4: //招魂 (智慧 + 1)
                result = character.getAbility().getIntelli()+1;
                break;

            case 5: //迷惑 (智慧 + 2)
                result = character.getAbility().getIntelli()+2;
                break;

            case 6: //火球術 (智慧 * 2)
                result = character.getAbility().getIntelli()*2;
                break;
            case 7: //風刃術
                result = character.getAbility().getIntelli();
                break;
            case 8: //全力斬擊 (力量 + 2)
                result = character.getAbility().getStr()+2;
                break;
            case 9: //魔法飛彈 (智慧 + 2)
                result = character.getAbility().getIntelli()+2;
                break;
        }
        return result;
    }
    // public int magicMissile(Character character){
    //     int inteli = character.getAbility().getIntelli()+2;
    //     return inteli;
    // }

    // //火球術 (智慧 * 2)
    // public int fireBall(Character character){
    //     int inteli = character.getAbility().getIntelli()*2;
    //     return inteli;
    // }

    // //風刃術
    // public int windBladeJustsu(Character character){
    //     int inteli = character.getAbility().getIntelli();
    //     return inteli;
    // }

    // //爪擊 (力量＋2)
    // public int clawAttack (Character character){
    //     int str = character.getAbility().getStr()+2;
    //     return str;
    // }

    // //衝撞 (力量＋1)
    // public int ram(Character character){
    //     int str = character.getAbility().getStr()+1;
    //     return str;
    // }

    // //輾壓 (防禦 +3)
    // public int runOver(Character character){
    //     int def = character.getAbility().getDef()+3;
    //     return def;
    // }

    // //風刃 (智慧 + 1)
    // public int windBlade(Character character){
    //     int inteli = character.getAbility().getIntelli()+1;
    //     return inteli;
    // }

    // //招魂 (智慧 + 1)
    // public int conjuring(Character character){
    //     int inteli = character.getAbility().getIntelli()+1;
    //     return inteli;
    // }

    // //迷惑 (智慧 + 2)
    // public int enchanted(Character character){
    //     int inteli = character.getAbility().getIntelli()+2;
    //     return inteli;
    // }

    // //全力斬擊 (力量 + 2)
    // public int fullyStrike(Character character){
    //     int str = character.getAbility().getStr()+2;
    //     return str;
    // }
    public void setId(int id){
        this.id=id;
    }

    public void setName(int name){
        this.name= SkillName.valueOf(name);
    }

    public void setProbability(int probability){
        this.probability=probability;
    }
    public void setCdTime(int cdTime){
        this.cdTime=cdTime;
        this.initCdTime=cdTime;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public int getId(){
        return id;
    }
    public SkillName getName(){
        return name;
    }
    public int getProbability(){
        return probability;
    }
    public int getCdTime(){
        return cdTime;
    }
    public int getInitCdTime(){
        return initCdTime;
    }
    public int getLevel(){
        return level;
    }
    public void addCdTime(int num){
        cdTime+=num;
    }
    public void resetCdTime(){
        cdTime = initCdTime;
    }
}

