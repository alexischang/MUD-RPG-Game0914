package rpg;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    ArrayList<Item> itemList = new ArrayList<Item>();
    public enum OptionType {
        i1(new Item().wolfLeg()),
        i2(new Item().lionClaw()),
        i3(new Item().pork()),
        i4(new Item().wolfTooth()),
        i5(new Item().glass()),
        i6(new Item().strongAcid()),
        i7(new Item().leather()),
        i8(new Item().healingPotion()),
        i9(new Item().heartOfGhost()),
        i10(new Item().PowerBurst()),
        i11(new Item().WisdomBurst()),
        i12(new Item().intelliIncreasePotion()),
        i13(new Item().powerIncreasePotion()),
        i14(new Item().defenceIncreasePotion()),
        i15(new Item().demomSlayerMark()),
        i16(new Item().amimalSlayerMark());


        Item item;
        OptionType(Item item){
            this.item = item;
        }

    }
    public interface Action{
        Item exec(Item item);
    }
    public  void printAllItem () {//印出所有Item
        int size = 16;
        OptionType[] array = OptionType.values();
        for (int i = 0 ; i< array.length ; i ++){
            Item temp = array[i].item;
            System.out.print(i+1+".");
            temp.printItem();
            itemList.add(temp);
        }
    }
    ////買商品
    public void buy(Player player){
        Scanner sc=new Scanner(System.in);
        System.out.println("錢包:"+player.getAbility().getMoney());
        System.out.println("請輸入購買商品編號");
        int buyNum=sc.nextInt();
        Item buyItem= new Item();
        buyItem=itemList.get(buyNum-1);
        if(buyItem.getPrice()<=player.getAbility().getMoney()) {
            System.out.println("購買成功");
            player.ability.setMoney(-1 * (buyItem.getPrice()));
            player.getItem(buyItem);
        }else{
            System.out.println("金額不足無法購買");
        }
    }
    //賣商品
    public void sell(Player player){
        Scanner sc=new Scanner(System.in);
        System.out.println("錢包:"+player.getAbility().getMoney());
        System.out.println("請輸入販賣商品編號");
        int buyNum=sc.nextInt();
        Item buyItem= new Item();
        buyItem=itemList.get(buyNum-1);
        if(buyItem.getPrice()<=player.getAbility().getMoney()) {
            System.out.println("購買成功");
            player.ability.setMoney(-1 * (buyItem.getPrice()));
            player.getItem(buyItem);
        }else{
            System.out.println("金額不足無法購買");
        }
    }
}






//顯示所有i商品
//買商品
//賣商品

//原始版本
//        Item i1 = new Item();
//        i1.healingPotion();
//        i1.printItem();
//        itemList.add(i1);
//
//        Item i2 = new Item();
//        i2.powerIncreasePotion();
//        i2.printItem();
//        itemList.add(i2);
//
//        Item i3 = new Item();
//        i3.defenceIncreasePotion();
//        i3.printItem();
//        itemList.add(i3);
//
//        Item i4 = new Item();
//        i4.leather();
//        i4.printItem();
//        itemList.add(i4);
//
//        Item i5 = new Item();
//        i5.wolfLeg();
//        i5.printItem();
//        itemList.add(i5);
//
//        Item i6 = new Item();
//        i6.lionClaw();
//        i6.printItem();
//        itemList.add(i6);
//
//        Item i7 = new Item();
//        i7.pork();
//        i7.printItem();
//        itemList.add(i7);
//
//        Item i8 = new Item();
//        i8.amimalSlayerMark();
//        i8.printItem();
//        itemList.add(i8);
//
//        Item i9 = new Item();
//        i9.heartOfGhost();
//        i9.printItem();
//        itemList.add(i9);
//
//        Item i10 = new Item();
//        i10.wolfTooth();
//        i10.printItem();
//        itemList.add(i10);
//
//        Item i11 = new Item();
//        i11.glass();
//        i11.printItem();
//        itemList.add(i11);
//
//        Item i12 = new Item();
//        i12.demomSlayerMark();
//        i12.printItem();
//        itemList.add(i12);
//
//        Item i13 = new Item();
//        i13.healingPotion();
//        i13.printItem();
//        itemList.add(i13);
//
//        Item i14 = new Item();
//        i14.intelliIncreasePotion();
//        i14.printItem();
//        itemList.add(i14);
//
//        Item i15 = new Item();
//        i15.strongAcid();
//        i15.printItem();
//        itemList.add(i15);
//
//        Item i16 = new Item();
//        i16.PowerBurst();
//        i16.printItem();
//        itemList.add(i16);
//
//        Item i17 = new Item();
//        i17.WisdomBurst();
//        i17.printItem();
//        itemList.add(i17);
