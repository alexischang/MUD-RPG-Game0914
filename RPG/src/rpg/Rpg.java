/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 *
 * @author mickytsai
 */
public class Rpg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Fight fight = new Fight();
        Monster monster = new Monster();
        Shop shop = new Shop();

        //角色命名
        Player newPlayer = new Player();
        
        System.out.println("歡迎進入這個冒險國度");
        Thread.sleep(1500);
        System.out.print("請告訴我你的名字是?  ->");
        Thread.sleep(1500);
        String name = sc.next();
        newPlayer.getAbility().setName(name);
        System.out.println("你好 " + newPlayer.getAbility().getName() + " 請享受你的冒險");
        Thread.sleep(1500);
        System.out.println("這是一點小心意 希望對你有幫助");
        Thread.sleep(1500);
        System.out.println();

        newPlayer.wearWeapon(chooseWeapon()); //穿上武器
        newPlayer.wearArmor(chooseArmor()); //穿上防具
        System.out.println();
        System.out.println();
        System.out.println("~~~請享受你的冒險~~~");
        Thread.sleep(1500);
        System.out.println();
        System.out.println();


        Kind[] kindList = new Kind[]{Kind.森林, Kind.深淵, Kind.煉獄森林};
        Kind[] passKindList = new Kind[3];
        Kind kind = Kind.森林;//預設地圖：森林
//        kind = 3;// 測試用
        int kindCount = 0; //過關的地圖數
        while(true){
            //如果初始進入 地圖選擇隨機
            if (passKindList[0] == null){
                kind = kindList[Random(0, 2)];
            }
            
//          
            if(newPlayer.isDead()){
                System.out.println("===你已重生 再接再厲!===");
                Thread.sleep(1500);
                System.out.println();
                System.out.println();
            }

            switch (kind){
                case 森林:
                    System.out.println("你已進入地圖：森林~ 暗藏危險動物");
                    shop.setIntoShop(false);  //是否進入過商店
                    Thread.sleep(1500);
                    System.out.println();
                    System.out.println();
                    break;
                case 深淵:
                    System.out.println("你已進入地圖：深淵~ 有著奇怪的惡魔盤據");
                    shop.setIntoShop(false);  //是否進入過商店
                    Thread.sleep(1500);
                    System.out.println();
                    System.out.println();
                    break;
                case 煉獄森林:
                    System.out.println("你已進入地圖：煉獄森林~ 有惡魔也有動物的party～我們懷念你");
                    shop.setIntoShop(false);  //是否進入過商店
                    Thread.sleep(1500);
                    System.out.println();
                    System.out.println();
                    break;
                default:
                    System.out.println("進入地圖出現錯誤");
                    break;
            }
            if(newPlayer.isDead()){
                shop.setIntoShop(false);  //是否進入過商店
                newPlayer.setPositon(0);//步數重算
                newPlayer = new Player(); //角色回到選完武器的初始
                newPlayer.ability.setName(name);
                newPlayer.wearWeapon(chooseWeapon()); //穿上武器
                newPlayer.wearArmor(chooseArmor()); //穿上防具
                System.out.println();
                System.out.println();
            }
            
            
            //進入地圖
            while (true){
                while(true){
                    if(newPlayer.isDead()){
                        break;
                    }
                    System.out.println("選擇行動");
                    System.out.println("1.繼續冒險");
                    System.out.println("2.顯示角色狀態 + 顯示裝備");
                    System.out.println("3.打開背包");

                    int choose;
                    try {
                        choose =  sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("沒有這個功能啦!快回去重來!");
                        sc.next();
                        continue;
                    }  // 防呆(抓取可能輸入非數字的錯誤)


                    switch (choose){
                        case 1:
                            System.out.println();
                            System.out.println("深入冒險" );
                            break;
                        case 2:
                            newPlayer.printAll();
                            break;
                        case 3:
                            if(newPlayer.getBag().size() == 0){
                                System.out.println("背包裡面空空如也");
                                System.out.println();
                                break;
                            }
                            System.out.println();
                            System.out.println();
                            newPlayer.supply();
                            System.out.println();
                            System.out.println("背包說明：");
                            System.out.println("請先選擇哪個道具(輸入數字)1~" + + newPlayer.getBag().size() +"不使用就輸入0");
                            int selectInt = sc.nextInt();;
                            System.out.println();
                            if(selectInt == 0){
                                System.out.println("輸入exit 來關閉背包");
                            }else{
                                System.out.println("顯示道具功能請輸入:status\n"
                                        + "使用道具請輸入:use\n"
                                        + "輸入exit 來關閉背包");
                            }
                            String selectStr = sc.next();
                            System.out.println();


                            if(selectStr.equals("use")){
                                boolean bo = newPlayer.use(selectInt);
                                //使用成功的訊息輸出已有寫在use方法 故只輸出 失敗使用
                                if(!bo){
                                   System.out.println("此道具無法使用");
                                }
                            }else if(selectStr.equals("status")){
                                newPlayer.getBag().get(selectInt - 1).printItem();
                            }else if(selectStr.equals("exit")){
                               break;
                            }
                            break;
                        default:
                            System.out.println("沒有這個功能啦!快回去重來!");
                    }
                    if(choose == 1){
                        break;// 因為選擇"1.繼續冒險" 離開while迴圈
                    }
                }

                //Boss戰
                if (newPlayer.getPositon() == 5){
                    System.out.println("遇到Boss !");
                    Thread.sleep(1500);

                    if (kind.equals(Kind.森林)){
                        Animal boss = new Animal();
                        boss.elephant();//森林Boss戰
                        System.out.println("Boss是" + boss.ability.getName() + "!" );
                        Thread.sleep(1500);
                        while(true){ //boss戰鬥
                            chooseOnFightWithBoss(newPlayer, boss, fight);
                            if(boss.isDead() || newPlayer.isDead()){ //怪物或角色一方死亡
//                                fight.overFight(newPlayer, boss);
                                break;
                            }else{
                                System.out.println("戰鬥選擇功能正常");
                            }
                        }
                    }else if (kind.equals(Kind.深淵)) {//深淵Boss戰
                        Demon boss = new Demon();
                        boss.bahamut();
                        System.out.println("Boss是" + boss.ability.getName() + "!" );
                        Thread.sleep(1500);
                        while(true){ //boss戰鬥
                            chooseOnFightWithBoss(newPlayer, boss, fight);
                            if(boss.isDead() || newPlayer.isDead()){ //怪物或角色一方死亡
//                                fight.overFight(newPlayer, boss);
                                break;
                            }else{
                                System.out.println("戰鬥選擇功能正常");
                            }
                        }
                    }else if (kind.equals(Kind.煉獄森林)){//煉獄森林Boss戰
                        Monster monsterKind3 = new Monster();
                        Monster boss = monsterKind3.bossList().get(Random(0,1));
                        System.out.println("Boss是" + boss.ability.getName() + "!" );
                        Thread.sleep(1500);
                        while(true){ //boss戰鬥
                            chooseOnFightWithBoss(newPlayer, boss, fight);
                            if(boss.isDead() || newPlayer.isDead()){ //怪物或角色一方死亡
//                                fight.overFight(newPlayer, boss);
                                break;
                            }else{
                                System.out.println("戰鬥選擇功能正常");
                            }
                        }
                    }


                    if(newPlayer.isDead()){
                        break;
                    }
                    passKindList[kindCount++] = kind; //將通關的地圖存進List
                    while (true) {
                        Kind tmp = kindList[Random(0, 2)];
                        if (!tmp.equals(passKindList[0]) && !tmp.equals(passKindList[1])) {//再次隨機取地圖，但是必須不存在於List當中
                            kind = tmp;
                            break;
                        }
                    }


                    newPlayer.setPositon(0);//步數重算
                    if(kindCount < 3){
                        System.out.println("你已征服這個地圖");
                        Thread.sleep(1500);
                        System.out.println();
                    }    
                    break;
                }

                //事件
                int event = Random(0, 5);
//               event = sc.nextInt();// 測試用

                if(shop.isIntoShop){        //如果已經進入shop則重新選擇1-4
                    event = Random(0, 4);
                }

                switch (event){
                    case 0://沒事發生
                        System.out.println();
                        System.out.println();
                        System.out.println("沒事發生，繼續走 ");
                        Thread.sleep(1500);
                        newPlayer.goOneStep();
                        System.out.println("你已經走了 " + newPlayer.getPositon() + " 步" );
                        System.out.println();
                        break;
                        
                    case 1://遇到被動怪物 要先抓出怪物
                        Monster monsterCase1 = new Monster();
                        if (kind.equals(Kind.森林)){
                            monsterCase1 = monster.genAnimal();//森林 隨機挑怪物
                        }else if (kind.equals(Kind.深淵)) {
                            monsterCase1 = monster.genDemon();//深淵 隨機挑怪物
                        }else if (kind.equals(Kind.煉獄森林)){
                            monsterCase1 = monsterCase1.monsterList().get(0);//煉獄森林 隨機挑怪物
                        }else{
                            System.out.println("被動怪事件 抓取怪物出現錯誤");
                        }

                        System.out.println("遇到 " + monsterCase1.ability.getName() + " 雙方大眼瞪小眼 你要逃跑嗎? ");
                        newPlayer.setFighting(true);//因為道具效果需要戰鬥狀態才能用 角色狀態先切入為：戰鬥

                        Thread.sleep(1500);
                        while(true){
                            chooseOnFight(newPlayer, monsterCase1, fight); //戰鬥選擇
                            //怪物或角色一方死亡 或逃跑成功 脫離戰鬥迴圈
                            if(monsterCase1.isDead() || newPlayer.isDead() || fight.isEscap()){
                                break;
                            }else{
//                                System.out.println("戰鬥選擇功能正常");
                            }
                        }
                        //如果結束戰鬥迴圈是因為死亡 後方步數不計算
                        if(newPlayer.isDead()){
                            break;
                        //如果結束戰鬥迴圈是因為逃跑成功或殺死怪物 步數＋＋
                        }else{
//                            System.out.println("角色判定是否存活功能正常");
                        }

                        newPlayer.goOneStep();
                        System.out.println("你已經走了 " + newPlayer.getPositon() + " 步" );
                        System.out.println();
                        break;
                        
                    case 2://遇到主動怪物
                        Monster monsterCase2;
                        if (kind.equals(Kind.森林)){
                            monsterCase2 = monster.genAnimal();//森林 隨機挑怪物
                        }else if (kind.equals(Kind.深淵)) {
                            monsterCase2 = monster.genDemon();//深淵 隨機挑怪物
                        }else if (kind.equals(Kind.煉獄森林)){
                            monsterCase2 = monster.genDemon();//煉獄森林 隨機挑怪物（尚未處理）
                        }else{
                            monsterCase2 = monster.genAnimal();//預設狀況 （森林 隨機挑怪物）
                            System.out.println("主動怪事件 抓取怪物出現錯誤");
                        }


                        System.out.println(monsterCase2.ability.getName() + "跑來攻擊你 逃不掉拉!");
                        Thread.sleep(1500);
                        System.out.println();
                        System.out.println("戰鬥開始");
                        fight.startFight(newPlayer, monsterCase2);
                        if(newPlayer.isDead()){
                            //如果遇到主動怪 怪先攻 第一次攻擊就被怪物殺死 後面迴圈不進入
                            break;
                        }else if(monsterCase2.isDead()) {
                            //如果遇到主動怪 角色先攻 第一次攻擊就將怪物殺死 後面迴圈不進入
                            //因為打贏 步數要計算
                            newPlayer.goOneStep();
                            System.out.println("你已經走了 " + newPlayer.getPositon() + " 步" );
                            System.out.println();
                            break;
                        }

                        while(true){
                            chooseOnFight(newPlayer, monsterCase2, fight); //戰鬥選擇
                            //怪物或角色一方死亡或逃跑成功 就結束戰鬥迴圈
                            if(monsterCase2.isDead() || newPlayer.isDead() || fight.isEscap()) {
                                break;
                            }else{
//                                System.out.println("戰鬥選擇功能正常 戰鬥繼續");//測試用
                            }
                        }
                        //如果結束戰鬥迴圈是因為死亡 後方步數不計算
                        if(newPlayer.isDead()){
                            break;
                        //如果結束戰鬥迴圈是因為逃跑成功或殺死怪物 步數＋＋
                        }else{
//                            System.out.println("角色判定是否存活功能正常");//測試用
                        }

                        newPlayer.goOneStep();
                        System.out.println("你已經走了 " + newPlayer.getPositon() + " 步" );
                        System.out.println();
                        break;    
                    
                    
                    case 3: //遇到岔路
                        System.out.println();
                        System.out.println();
                        System.out.println("發現一個小叉路! 但看起來好恐怖 還是繼續往前吧 ");
                        Thread.sleep(1500);
                        newPlayer.goOneStep();
                        System.out.println("你已經走了 " + newPlayer.getPositon() + " 步" );
                        System.out.println();
                        break;
                    
                    case 4://遇到寶箱
                        System.out.println();
                        System.out.println();
                        System.out.println("發現寶箱!!! ");
                        Thread.sleep(1500);
                        ArrayList<Item> treasureList = new ArrayList<Item>(); //寶箱list

                        if (kind.equals(Kind.森林)){ //森林寶箱list
                            Item healingPotion = new Item();
                            healingPotion.healingPotion();
                            treasureList.add(healingPotion);

                            Item powerIncreasePotion = new Item();
                            powerIncreasePotion.powerIncreasePotion();
                            treasureList.add(powerIncreasePotion);

                            Weapon bow = new Weapon();
                            bow.bow();
                            treasureList.add(bow);

                        }else if (kind.equals(Kind.深淵)) {  //深淵寶箱list
                            Item healingPotion = new Item();
                            healingPotion.healingPotion();
                            treasureList.add(healingPotion);

                            Armor leatherArmor = new Armor();
                            leatherArmor.leatherArmor();
                            treasureList.add(leatherArmor);

                            Item defenseIncreasePotion = new Item();
                            defenseIncreasePotion.defenceIncreasePotion();
                            treasureList.add(defenseIncreasePotion);

                        }else if (kind.equals(Kind.煉獄森林)){//煉獄森林寶箱list 先放森林的（尚未處理）
                            Item healingPotion = new Item();
                            healingPotion.healingPotion();
                            treasureList.add(healingPotion);

                            Item powerIncreasePotion = new Item();
                            powerIncreasePotion.powerIncreasePotion();
                            treasureList.add(powerIncreasePotion);

                            Weapon bow = new Weapon();
                            bow.bow();
                            treasureList.add(bow);
                        }else{
                            System.out.println("寶箱事件 抓取寶箱列表出現錯誤");
                            Item healingPotion = new Item();
                            healingPotion.healingPotion();
                            treasureList.add(healingPotion);

                            Item powerIncreasePotion = new Item();
                            powerIncreasePotion.powerIncreasePotion();
                            treasureList.add(powerIncreasePotion);

                            Weapon bow = new Weapon();
                            bow.bow();
                            treasureList.add(bow);
                        }

                        //隨機取得寶箱其中一個
                        newPlayer.getItem(treasureList.get(Random(0, 2)));
                        Thread.sleep(1500);
                        System.out.println();

                        newPlayer.goOneStep();
                        System.out.println("你已經走了 " + newPlayer.getPositon() + " 步" );
                        System.out.println();
                        break;
                    case 5:
                        shop.setIntoShop(true);  //已進入過商店
                        System.out.println("遇到流浪商人 ");
                        Thread.sleep(1500);
                        boolean tf = true;
                        while (tf) {
                            System.out.println("選擇行動");
                            System.out.println("1.買商品");
                            System.out.println("2.賣商品");
                            System.out.println("3.離開");
                            int choose;
                            try {
                                choose = sc.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("沒有這個功能啦!快回去重來!");
                                sc.next();
                                continue;
                            }  // 防呆(抓取可能輸入非數字的錯誤)
                            Thread.sleep(1500);
                            switch (choose) {
                                case 1:
                                    shop.printAllItem();
                                    shop.buy(newPlayer);
                                    break;
                                case 2:
                                    shop.sell(newPlayer);
                                    break;
                                case 3:
                                    tf = false;
                                    newPlayer.goOneStep();
                                    System.out.println("你已經走了 " + newPlayer.getPositon() + " 步");
                                    System.out.println();
                                    break;
                                //加了避免要求皆不符合，而無break
                                default:
                                    System.out.println("商人事件發生錯誤 ");
                                    break;
                            }
                        }
                }
                if(newPlayer.isDead()){
                    break;
                }
            }
            if (kindCount == 3){
                break;
            }    
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("恭喜通關!!!你是真正的勇者");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
    }
    public static int Random (int min, int max){
        return (int)(Math.random() * (max - min + 1) + min);
    }
    public static Weapon chooseWeapon(){
        //選擇起始武器（調用初始武器的ArrayList)
        Scanner sc = new Scanner(System.in);
        System.out.println("請選擇一個武器");
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
        Weapon w1 = new Weapon();
        w1.sword();
        System.out.print("選擇1 ");
        w1.printItem();
        weaponList.add(w1);
        
        Weapon w2 = new Weapon();
        w2.axe();
        System.out.print("選擇2 ");
        w2.printItem();
        weaponList.add(w2);
        
        Weapon w3 = new Weapon();
        w3.wand();
        System.out.print("選擇3 ");
        w3.printItem();
        weaponList.add(w3);
        
        System.out.print("選擇->");
        int chooseW;

        while (true) {
            try {
                chooseW = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("恭喜你獲得傳說之劍!\n...才怪沒有這個武器啦!快回去重選!");
                sc.next();
                continue;
            }

            if (0 < chooseW && chooseW < 4) {
                return weaponList.get(chooseW - 1);
            } else {
                System.out.println("恭喜你獲得傳說之劍!\n...才怪沒有這個武器啦!快回去重選!");
            }
        }
        
    } //選擇武器
    public static Armor chooseArmor(){
        //選擇起始防具（調用初始防具的ArrayList)
        Scanner sc = new Scanner(System.in);
        System.out.println("請選擇一個防具");
        ArrayList<Armor> armorList = new ArrayList<Armor>();
        
        Armor a1 = new Armor();
        a1.woodenArmor();
        System.out.print("選擇1 ");
        a1.printItem();
        armorList.add(a1);
        
        Armor a2 = new Armor();
        a2.chainMail();
        System.out.print("選擇2 ");
        a2.printItem();
        armorList.add(a2);
        
        Armor a3 = new Armor();
        a3.plateArmor();
        System.out.print("選擇3 ");
        a3.printItem();
        armorList.add(a3);
        
        System.out.print("選擇->");
        
        int chooseA;
        while (true) {
            try {
                chooseA = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("恭喜你獲得傳說之盾!\n...才怪沒有這個防具啦!快回去重選!");
                sc.next();
                continue;
            }
            if (0 < chooseA && chooseA < 4) {
                return armorList.get(chooseA - 1);
            } else {
                System.out.println("恭喜你獲得傳說之盾!\n...才怪沒有這個防具啦!快回去重選!");
            }
        }
        
    } //選擇防具

    //遇到怪物 每回合可以選擇
    public static void chooseOnFight (Player newPlayer, Monster animal, Fight fight) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        System.out.println("選擇1：逃跑\n" + "選擇2：戰鬥\n" + "選擇3：使用道具 ");
        int choose = sc.nextInt();
        switch (choose){
            case 1:
                System.out.println("你選擇逃跑");
                if(fight.isEscaping(newPlayer, animal)){
                    System.out.println("逃跑成功");
                    newPlayer.setFighting(false);//角色狀態切回為：非戰鬥

                }else{
                    System.out.println("逃跑失敗 開始戰鬥");
                    fight.startFight(newPlayer, animal);
                }
                break;
            case 2:
                System.out.println("你選擇拼了");
                fight.startFight(newPlayer, animal);
                break;
            case 3:
                if(newPlayer.getBag().size() == 0){
                    System.out.println("背包裡面空空如也");
//                    Thread.sleep(1500);
                    System.out.println("還想用道具!? 認命戰鬥吧");
//                    Thread.sleep(1500);
                    fight.startFight(newPlayer, animal);
                    break;
                }
                System.out.println();
                System.out.println();
                newPlayer.supply();
                System.out.println();
                System.out.println("請先選擇哪個道具(輸入數字)1~" + + newPlayer.getBag().size() +"不使用就輸入0");
                int selectInt = sc.nextInt();
                System.out.println();
                if(selectInt == 0){
                    System.out.println("輸入exit 來關閉背包");
                }else{
                    System.out.println("顯示道具功能請輸入:status\n"
                            + "使用道具請輸入:use\n"
                            + "輸入exit 來關閉背包");
                }
                String selectStr = sc.next();
                System.out.println();

                if(selectStr.equals("use")){
                    boolean bo = newPlayer.use(selectInt);
                    //使用成功的訊息輸出已有寫在use方法 故只輸出 失敗使用
                    if(!bo){
                        System.out.println("此道具無法使用");
                    }
                }else if(selectStr.equals("status")){
                    newPlayer.getBag().get(selectInt - 1).printItem();
                }else if(selectStr.equals("exit")){
                    System.out.println("背包關上");
                }
                System.out.println("還想用道具!? 認命戰鬥吧");
                fight.startFight(newPlayer, animal);
                break;

        }

    }

    //遇到怪物 每回合可以選擇 boss戰 不能逃跑
    public static void chooseOnFightWithBoss (Player newPlayer, Monster animal, Fight fight) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        System.out.println("選擇1：戰鬥\n" + "選擇2：使用道具 ");
        int choose = sc.nextInt();
        switch (choose){

            case 1:
                System.out.println("你選擇拼了");
                fight.startFight(newPlayer, animal);
                break;
            case 2:
                if(newPlayer.getBag().size() == 0){
                    System.out.println("背包裡面空空如也");
                    Thread.sleep(1500);
                    System.out.println("還想用道具!? 認命戰鬥吧");
                    Thread.sleep(1500);
                    fight.startFight(newPlayer, animal);
                    break;
                }
                System.out.println();
                System.out.println();
                newPlayer.supply();
                System.out.println();
                System.out.println("請先選擇哪個道具(輸入數字) 不使用就輸入0");
                int selectInt = sc.nextInt();
                System.out.println();
                if(selectInt == 0){
                    System.out.println("輸入exit 來關閉背包");
                }else{
                    System.out.println("顯示道具功能請輸入:status\n"
                            + "使用道具請輸入:use\n"
                            + "輸入exit 來關閉背包");
                }
                String selectStr = sc.next();
                System.out.println();

                if(selectStr.equals("use")){
                    boolean bo = newPlayer.use(selectInt);
                    //使用成功的訊息輸出已有寫在use方法 故只輸出 失敗使用
                    if(!bo){
                        System.out.println("此道具無法使用");
                    }
                }else if(selectStr.equals("status")){
                    newPlayer.getBag().get(selectInt - 1).printItem();
                }else if(selectStr.equals("exit")){
                    System.out.println("背包關上");
                }
                System.out.println("還想用道具!? 認命戰鬥吧");
                fight.startFight(newPlayer, animal);
                break;

        }

    }

    public enum Kind {
        森林,
        深淵,
        煉獄森林,
    }

}