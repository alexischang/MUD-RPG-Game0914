package rpg;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Fight {
    private Random random = new Random();
    int round = 0;

    private boolean isEscap = false;

    public boolean isEscap() {
        return isEscap;
    }

    public void setEscap(boolean escap) {
        isEscap = escap;
    }



    public boolean isEscaping(Player player, Monster monster) { //是否逃跑成功
        setEscap(false);
        if (random.nextDouble() <= (player.getAbility().getDex() - monster.getAbility().getDex()) * 0.4) { //(自身敏捷-怪物敏捷)*40%為逃跑率
            setEscap(true);
        }
        return isEscap();
    }

    public void startFight(Player player, Monster monster) throws InterruptedException {  //戰鬥開始
        Scanner sc = new Scanner(System.in);
        player.setFighting(true); //處於戰鬥狀態
        player.buffTakeEffect(monster); //buff生效
        //印出遇到怪物的資料


        //判斷誰敏捷高誰先攻擊 //角色先攻
        if (player.getAbility().getDex() >= monster.getAbility().getDex()) {
            System.out.println("== " + player.getAbility().getName() + " 先攻==");

            while (true){
                round++;
                int playerSkills = 3;
                System.out.println("\n=====第" + round + "回合=====");
                //角色先攻 選擇普通攻擊或技能
                System.out.println("請選擇：\n" +
                        "1.普通攻擊\n" +
                        "2.技能選擇");
                int choose;
                try {
                    choose =  sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("沒有這個功能啦!快回去重來!");
                    sc.next();
                    continue;
                }  // 防呆(抓取可能輸入非數字的錯誤


                int whichSkill=0;
                for(int i = 0 ; i < playerSkills; i++){
                    if(i == whichSkill-1){
                        continue;
                    }
                    else{
                        int a = player.getAbility().getSkill().get(i).getCdTime();
                        int b = player.getAbility().getSkill().get(i).getInitCdTime();
                        if( a != b ){
                            if( a == -1 ){
                                player.getAbility().getSkill().get(i).resetCdTime();
                            }
                            else{
                                player.getAbility().getSkill().get(i).addCdTime(-1);
                            }
                        }
                    }
                }

                switch (choose){
                    //主角先攻 且選擇普通攻擊
                    case 1:
                        //普通攻擊
                        System.out.println(player.getAbility().getName() + "攻擊!");
                        attack(player, monster);
                        if (!monster.isDead()) {  //主角先攻 怪物沒死 怪物反擊
                            System.out.println(monster.getAbility().getName() + "攻擊!");
                            skillAttack(monster, player, 0);
                            player.buffCountDown(); //玩家buff扣除一回合
                            overFight(player, monster);//判定角色是否死亡
                            break;
                        }else{
                            player.buffCountDown(); //玩家buff扣除一回合
                            overFight(player, monster);//主角先攻 怪物死亡 戰鬥結算
                            break;
                        }
                    //主角先攻 且選擇技能
                    case 2:
                        //技能攻擊
                        System.out.println("請輸入技能編號選擇技能");
                        //week2 判斷技能冷卻時間
                        boolean allCd = true;
                        ArrayList<Integer> skillSet = new ArrayList<Integer>();
                        //判斷印出目前可選的技能
                        for(int i=0; i<playerSkills; i++){
                            if(player.getAbility().getSkill().get(i).getCdTime()==player.getAbility().getSkill().get(i).getInitCdTime()){
                                System.out.println(player.getAbility().getSkill().get(i).getName()+player.getAbility().getSkill().get(i).getInfo());
                                // System.out.println("編號"+(i+1)+player.getAbility().getSkill().get(i).getName());
                                skillSet.add(i+1);
                                allCd = false;
                            }
                            else{
                                int cdRound = player.getAbility().getSkill().get(i).getCdTime();
                                System.out.println(player.getAbility().getSkill().get(i).getName()+
                                player.getAbility().getSkill().get(i).getInfo()+
                                " (冷卻回合:)"+(cdRound+2));
                                // System.out.println("編號"+(i+1)+player.getAbility().getSkill().get(i).getName()+"(冷卻回合:)"+(cdRound+2));
                            }
                        }
                        if(allCd==true){
                            System.out.println("全部技能CD中，無法使用任何技能");
                        }
                        else{
                            // int whichSkill;
                            boolean containSkill;
                            while(true){
                                whichSkill = sc.nextInt();
                                containSkill = skillSet.contains(whichSkill);
                                if(containSkill == false){
                                    System.out.print("該技能冷卻中，請重新選擇: ");
                                }
                                else{
                                    break;
                                }
                            }
                            // do{
                            //     whichSkill = sc.nextInt();
                            //     containSkill = skillSet.contains(whichSkill);
                            // }
                            // while(containSkill==false);

                            System.out.println(player.getAbility().getName() + "施放" + "技能");
                            skillAttack(player, monster, whichSkill);
                        }
                        //主角先攻 怪物沒死 怪物反擊
                        if (!monster.isDead()) {
                            System.out.println(monster.getAbility().getName() + "攻擊!");
                            skillAttack(monster, player, 0);
                            player.buffCountDown(); //玩家buff扣除一回合
                            overFight(player, monster);//判定角色是否死亡
                            break;
                        //主角先攻 怪物死亡
                        }else{
                            player.buffCountDown(); //玩家buff扣除一回合
                            overFight(player, monster);//怪物死亡 戰鬥結算
                            // reset 技能cd時間
                            for(int i = 0 ; i < playerSkills; i++){
                                player.getAbility().getSkill().get(i).resetCdTime();
                            }
                            break;
                        }
                    default:
                        System.out.println("角色先攻 選擇普通攻擊還是技能的功能異常");
                }
                if (choose == 1 || choose ==2){
                    break;
                }
            }

        //怪物先攻
        }else {
            System.out.println("== " + monster.getAbility().getName() + " 先攻==");
            int playerSkills = 3;

            while (true){
                round++;
                System.out.println("\n=====第" + round + "回合=====");
                System.out.println(monster.getAbility().getName() + "攻擊!");
                skillAttack(monster, player, 0);

                //角色後攻且沒死 選擇普通攻擊或技能
                if (!player.isDead()) {
                    System.out.println("請選擇：\n" +
                            "1.普通攻擊\n" +
                            "2.技能選擇");

                    int whichSkill=0;
                    for(int i = 0 ; i < playerSkills; i++){
                        if(i == whichSkill-1){
                            continue;
                        }
                        else{
                            int a = player.getAbility().getSkill().get(i).getCdTime();
                            int b = player.getAbility().getSkill().get(i).getInitCdTime();
                            if( a != b ){
                                if( a == -1 ){
                                    player.getAbility().getSkill().get(i).resetCdTime();
                                }
                                else{
                                    player.getAbility().getSkill().get(i).addCdTime(-1);
                                }
                            }
                        }
                    }
                    int choose;
                    try {
                        choose =  sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("沒有這個功能啦!快回去重來!");
                        sc.next();
                        continue;
                    }  // 防呆(抓取可能輸入非數字的錯誤)
                    //角色後攻且沒死 反擊選擇普通攻擊
                    switch (choose){
                        case 1:
                            //普通攻擊
                            System.out.println(player.getAbility().getName() + "攻擊!");
                            attack(player, monster);
                            if (!monster.isDead()) {  //反擊沒殺死怪物 雙方都沒死 無需結算
                                player.buffCountDown(); //玩家buff扣除一回合
                                break;
                            }else{
                                player.buffCountDown(); //玩家buff扣除一回合
                                overFight(player, monster);//反擊殺死怪物 勝利結算
                                break;
                            }
                        //角色後攻且沒死 反擊選擇技能攻擊
                        case 2:
                            //技能攻擊
                            System.out.println("請輸入技能編號選擇技能");
                            //week2 判斷技能冷卻時間
                            boolean allCd = true;
                            ArrayList<Integer> skillSet = new ArrayList<Integer>();
                            //判斷印出目前可選的技能
                            for(int i=0;i<playerSkills;i++){
                                if(player.getAbility().getSkill().get(i).getCdTime()==player.getAbility().getSkill().get(i).getInitCdTime()){
                                    System.out.println(player.getAbility().getSkill().get(i).getName()+player.getAbility().getSkill().get(i).getInfo());
                                    // System.out.println("編號"+(i+1)+player.getAbility().getSkill().get(i).getName());
                                    skillSet.add(i+1);
                                    allCd = false;
                                }
                                else{
                                    int cdRound = player.getAbility().getSkill().get(i).getCdTime();
                                    System.out.println(player.getAbility().getSkill().get(i).getName()+
                                    player.getAbility().getSkill().get(i).getInfo()+
                                    " (冷卻回合:)"+(cdRound+2));
                                    // System.out.println("編號"+(i+1)+player.getAbility().getSkill().get(i).getName()+"(冷卻回合:)"+(cdRound+2));
                                }
                            }
                            if(allCd==true){
                                System.out.println("全部技能CD中，無法使用任何技能");
                            }
                            else{
                                // int whichSkill;
                                boolean containSkill;
                                while(true){
                                    whichSkill = sc.nextInt();
                                    containSkill = skillSet.contains(whichSkill);
                                    if(containSkill == false){
                                        System.out.print("該技能冷卻中，請重新選擇: ");
                                    }
                                    else{
                                        break;
                                    }
                                }
                                // do{
                                //     whichSkill = sc.nextInt();
                                //     containSkill = skillSet.contains(whichSkill);
                                // }
                                // while(containSkill==false);
                            }
                            System.out.println(player.getAbility().getName() + "施放" + "技能");
                            skillAttack(player, monster, whichSkill);


                            //主角後攻 怪物沒死 雙方都沒死 無需結算
                            if (!monster.isDead()) {
                                player.buffCountDown(); //玩家buff扣除一回合
                                break;
                            //主角後攻 怪物死亡
                            }else{
                                player.buffCountDown(); //玩家buff扣除一回合
                                overFight(player, monster);//勝利結算
                                for(int i = 0 ; i < playerSkills; i++){
                                    player.getAbility().getSkill().get(i).resetCdTime();
                                }
                                break;
                            }

                        default:
                            System.out.println("主角後攻 選擇普通攻擊還是技能的功能異常");
                    }if (choose == 1 || choose ==2){
                        break;
                    }
                }else{ //角色後攻 角色死亡（被第一下打死）
                    overFight(player, monster);//判定角色有沒有死
                    break;
                }
           }
//            player.buffCountDown();
        }
    }

    public boolean isHit(Character atker, Character defer) {
        ///判斷如果隨機(0~1)大於(被攻擊方敏捷-攻擊方命中*20%)，就攻擊成功
        return random.nextDouble() >= (defer.getAbility().getDex() - atker.getAbility().getHit()) * 0.2;
    }

    public void attack(Character atker, Character defer) throws InterruptedException {
        //先手攻擊
        if (isHit(atker, defer)) { //判定命中
            Thread.sleep(1500);
            System.out.println("命中了");
            int damage = atker.getAbility().getStr() - defer.getAbility().getDef(); //計算傷害
            if (damage > 0) { //判定傷害是否>0
                defer.getAbility().addHp(damage * (-1)); //扣血(+負的血量)
                Thread.sleep(1500);
                System.out.println(atker.getAbility().getName() + "對" + defer.getAbility().getName() + "造成了" + damage + "傷害\n" +
                        defer.getAbility().getName() + " 剩下 " + defer.getAbility().getHp() + "HP \n");
                if (defer.isDead()) { //死亡就跳出
                    return;
                }
            } else {
                Thread.sleep(1500);
                defer.getAbility().addHp(-1);
                System.out.println("被擋下來了，只扣一滴 " + defer.getAbility().getName() + "剩下"  + defer.getAbility().getHp() + "HP \n");  //被擋下，也有一滴傷害
                if (defer.isDead()) { //死亡就跳出
                    return;
                }
            }
        } else {
            Thread.sleep(1500);
            System.out.println("Miss!");
        }
    }

    public void overFight(Player player, Monster monster) throws InterruptedException {

        if (player.isDead()) {
            round = 0;//round 歸零
            player.removeBuff(); //buff移除
            player.setFighting(false); //離開戰鬥狀態
            System.out.println("你死了QQ，請重新來過吧\n\n");
        }else if(monster.isDead()) {
            round = 0;//round 歸零
            player.removeBuff(); //buff移除
            player.setFighting(false); //離開戰鬥狀態

            int exp = monster.getAbility().getExp();
            Thread.sleep(1500);
            System.out.println("恭喜擊敗 " + monster.getAbility().getName() + " !");
            Thread.sleep(1500);
            player.getAbility().addExp(exp);
            System.out.print("獲得" + exp + "EXP   ");
            player.lvelUp();
            player.getItem(monster.getDropItem());
            System.out.println();
        }
    }

    public void skillAttack(Character atker, Character defer, int whichSkill) throws InterruptedException {
        //技能攻擊
        if (isHit(atker, defer)) { //判斷命中
            Thread.sleep(1500);
            System.out.println("命中了");
            int damage;
            if(whichSkill!=0){ //玩家指定技能
                damage = atker.getAbility().getSkill().get(whichSkill - 1).doItEffect(atker.getAbility().getSkill().get(whichSkill - 1).getId(), atker) - defer.getAbility().getMigDef();
                System.out.println(atker.getAbility().getSkill().get(whichSkill - 1).getName());
                //冷卻時間--
                atker.getAbility().getSkill().get(whichSkill-1).addCdTime(-1);
            }
            else{ //怪物觸發技能
                if(atker.getAbility().getSkill().size()==1){ //只有一項技能
                    // random num<機率
                    int randomNum = random.nextInt((100 - 1) + 1) + 1;
                    if(randomNum<atker.getAbility().getSkill().get(0).getProbability()){ //技能觸發
                        damage = atker.getAbility().getSkill().get(0).doItEffect(atker.getAbility().getSkill().get(0).getId(), atker) - defer.getAbility().getDef();
                        System.out.println(atker.getAbility().getSkill().get(0).getName());
                    }
                    else{ //普攻
                        damage = atker.getAbility().getStr() - defer.getAbility().getDef();
                        System.out.println("沒有施放技能，採用普通攻擊");
                    }
                }
                else{ // 怪物有多個技能
                    int randomIndex = random.nextInt(atker.getAbility().getSkill().size());
                    int randomNum = random.nextInt((100 - 1) + 1) + 1;
                    if(randomNum < atker.getAbility().getSkill().get(randomIndex).getProbability()){
                        damage = atker.getAbility().getSkill().get(randomIndex).doItEffect(atker.getAbility().getSkill().get(randomIndex).getId(), atker) - defer.getAbility().getDef();
                        System.out.println(atker.getAbility().getSkill().get(randomIndex).getName());
                    }
                    else{
                        damage = atker.getAbility().getStr() - defer.getAbility().getDef();
                        System.out.println("沒有施放技能，採用普通攻擊");
                    }
                }

            }
            if (damage > 0) { //判定傷害是否>0
                defer.getAbility().addHp(damage * (-1)); //扣血(+負的血量)
                Thread.sleep(1500);
                System.out.println(atker.getAbility().getName() + "對" + defer.getAbility().getName() + "造成了" + damage + "傷害\n" +
                        defer.getAbility().getName() + " 剩下 " + defer.getAbility().getHp() + "HP \n");
                if (defer.isDead()) { //死亡就跳出
                    return;
                }
            } else {
                Thread.sleep(1500);
                defer.getAbility().addHp(-1);
                System.out.println("被擋下來了，只扣一滴 " + defer.getAbility().getName() + "剩下" + defer.getAbility().getHp() + "HP \n");  //被擋下，也有一滴傷害
                if (defer.isDead()) { //死亡就跳出
                    return;
                }
            }
        }
        else{
            Thread.sleep(1500);
            System.out.println("Miss");
        }
    }
}
