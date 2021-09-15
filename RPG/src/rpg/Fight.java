package rpg;

import java.util.Random;

public class Fight {
    private Random random = new Random();
    int round = 0;
    //以下第二週新增
    private boolean isEscap = false;

    public boolean isEscap() {
        return isEscap;
    }

    public void setEscap(boolean escap) {
        isEscap = escap;
    }
    //以上第二週新增


    public boolean isEscaping(Player player, Monster monster) { //是否逃跑成功
        setEscap(false);
        if (random.nextDouble() <= (player.getAbility().getDex() - monster.getAbility().getDex()) * 0.4) { //(自身敏捷-怪物敏捷)*40%為逃跑率
            setEscap(true);
        }
        return isEscap();
    }

    public void startFight(Player player, Monster monster) throws InterruptedException {  //戰鬥開始
        player.setFighting(true); //處於戰鬥狀態
        player.buffTakeEffect(monster); //buff生效
        if (player.getAbility().getDex() >= monster.getAbility().getDex()) { //判斷誰敏捷高誰先攻擊
            System.out.println(player.getAbility().getName() + "先攻");
//            while (!player.isDead() && !monster.isDead()) {//沒有人死的話會一直打下去
                round++;
                System.out.println("\n第" + round + "回合");
                System.out.println(player.getAbility().getName() + "攻擊!");
                attack(player, monster);
                if (!monster.isDead()) {  //死了就不用打下去
                    System.out.println(monster.getAbility().getName() + "攻擊!");
                    attack(monster, player);
                }
                player.buffCountDown(); //玩家buff扣除一回合
//            }
        } else {
            System.out.println(monster.getAbility().getName() + "先攻");
//            while (!player.isDead() && !monster.isDead()) {
                round++;
                System.out.println("\n第" + round + "回合");
                System.out.println(monster.getAbility().getName() + "攻擊!");
                attack(monster, player);
                if (!player.isDead()) {
                    System.out.println(player.getAbility().getName() + "攻擊!");
                    attack(player, monster);
                }
                player.buffCountDown();
//            }
        }
//        round = 0;//round 歸零 //第二週：移到overFight內
//        player.removeBuff(); //buff移除 //第二週：移到overFight內
//        player.setFighting(false); //離開戰鬥狀態 //第二週：移到overFight內
//        overFight(player, monster); //結算
    }

    public boolean isHit(Character atker, Character defer) {
        return random.nextDouble() >= (defer.getAbility().getDex() - atker.getAbility().getHit()) * 0.2;///判斷如果隨機(0~1)大於(被攻擊方敏捷-攻擊方命中*20%)，就攻擊成功
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
                System.out.println(atker.getAbility().getName() + "對" + defer.getAbility().getName() + "造成了" + damage + "傷害\n");
                if (defer.isDead()) { //死亡就跳出
                    return;
                }
            } else {
                Thread.sleep(1500);
                defer.getAbility().addHp(-1);
                System.out.println("被擋下來了，只扣一滴\n");  //被擋下，也有一滴傷害
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
        round = 0;//round 歸零
        player.removeBuff(); //buff移除
        player.setFighting(false); //離開戰鬥狀態
        if (player.isDead()) {
            System.out.println("你死了QQ，請重新來過吧\n\n");
        } else {
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
}