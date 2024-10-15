package com.steven.rpg;

public class Player extends Character{
    private int exp = 0;
    public Player(String name, int level){
        super(name, level, 17, 8, 6, 3);
    }
    private void levelUp(){
        setLevel(getLevel() + 1);
        setTotalHp(getTotalHp() + 3);
        resetHp();
    }
    public int addExp(int exp) {
        this.exp += exp;
        if (this.exp >= 100){
            levelUp();
            this.exp = 0;
            return 1;
        }
        return 0;
    }
    public void doAction(String action, Character opponent, Console console){
        switch (action){
            case "Attack":
                this.attack(opponent, console);
                console.print("---> " + this.getName() + " attacks the " + opponent.getName() + ".");
                break;
            case "Defend":
                this.defend();
                console.print("---> " + this.getName() + " is defending.");
                break;
            default:
                this.heal();
                console.print("---> " + this.getName() + " is healing.");
                break;
        }
    }
}
