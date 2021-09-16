package rpg;

import java.util.ArrayList;
import java.util.Random;

public class Monster extends Character {
    private Random random = new Random();
    private ArrayList<Item> dropItems = new ArrayList<Item>();  // 掉落物清單
    //以下第二週新增
    private ArrayList<Monster> monsterList = new ArrayList<Monster>();
    private ArrayList<Monster> bossList = new ArrayList<Monster>();
    //以上第二週新增

    public ArrayList<Item> getDropItems() {
        return dropItems;
    }

    public Item getDropItem() {
        Item item = null;
        item = getDropItems().get(random.nextInt(getDropItems().size()));
        return item;
    }

    public  Demon genDemon() {
        int r = random.nextInt(3) + 1;
        Demon demon = new Demon();
        switch (r) {
            case 1:
                demon.mountainDemon();
                break;
            case 2:
                demon.demonWolf();
                break;
            case 3:
                demon.celestialHuang();
                break;
        }
        return demon;
    }

    public  Animal genAnimal() {
        int r = random.nextInt(3) + 1;
        Animal animal = new Animal();
        switch (r) {
            case 1:
                animal.wolf();
                break;
            case 2:
                animal.boar();
                break;
            case 3:
                animal.lion();
                break;
        }
        return animal;
    }

    //以下第二週新增
    public ArrayList<Monster> bossList (){
        Animal animal = new Animal();
        animal.elephant();
        bossList.add(animal);
        Demon demon = new Demon();
        demon.bahamut();
        bossList.add(demon);
        return bossList;
    }
    //以上第二週新增



}
