package com.steven.rpg;

public class Enemy extends Character{
    private boolean isHealing;
    public Enemy(String name, int level) {
        super(name, level, 10, 6, 3, 2);
    }
    // randomize bot actions
    public void doAction(Character player, Console console){
        int random = (int)(Math.random() * (MAX - MIN + 1) + MIN);
        switch (Character.OPTIONS[random]){
            case "Attack":
                this.attack(player, console);
                console.print("---> " + this.getName() + " attacks the " + player.getName() + ".");
                break;
            case "Defend":
                this.defend();
                console.print("---> " + this.getName() + " is defending.");
                break;
            default:
                this.heal();
                if (this.isHealing){
                    this.isHealing = false;
                    console.print("---> " + this.getName() + " is healing.");
                }
        }
    }
    @Override
    public void heal(){
        if((this.getCurrentHp() + this.getHealPoint()) > this.getTotalHp()){
            this.defend();
            return;
        }
        super.heal();
        this.isHealing = true;
    }
}
