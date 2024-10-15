package com.steven.rpg;

public abstract class Character{
    final public static String[] OPTIONS = {"Attack", "Defend", "Heal", "Quit"};
    final public int MIN = 0;
    final public int MAX = 2;

    final private String name;
    private int level;

    private int totalHp;
    private int currentHp;

    private int attackPoint;
    private int defendPoint;
    private int healPoint;

    private boolean isAlive = true;
    private boolean isDefending = false;

    public Character(String name, int level, int totalHp, int attackPoint, int defendPoint, int healPoint){
        this.name = name;
        this.attackPoint = attackPoint;
        this.defendPoint = defendPoint;
        this.healPoint = healPoint;
        this.totalHp = totalHp + (3 * level);
        this.currentHp = this.totalHp;
        this.level = level;
        // randomize attackPoint, defendPoint... values
        for(int i = 0; i < level; i++){
            int random = (int)(Math.random() * (MAX - MIN + 1) + MIN);
            switch (OPTIONS[random]){
                case "Attack":
                    attackPoint++;
                    break;
                case "Defend":
                    defendPoint++;
                    break;
                default:
                    healPoint++;
                    break;
            }
        }
    }

    public void attack(Character opponent, Console console){
        this.isDefending = false;
        opponent.takeDamage(this.attackPoint);
        if(opponent.isDefending()){
            console.print("---> " + opponent.getName() + " defends the " + this.getName() + "'s attack. " + opponent.getName() + " takes only " + (this.attackPoint - opponent.getDefendPoint()) + " damage.");
            opponent.setDefending(false);
        }
    }

    public void defend(){
        this.setDefending(true);
    }

    public void heal(){
        this.isDefending = false;
        this.currentHp += this.healPoint;
        if (this.currentHp > this.totalHp){
            this.currentHp = this.totalHp;
        }
    }

    public void takeDamage(int damage){
        this.currentHp = this.isDefending() ? this.currentHp - (damage - this.getDefendPoint()) : this.currentHp - damage;
        if (this.currentHp <= 0){
            this.isAlive = false;
        }
    }

    public void resetHp(){
        this.currentHp = this.totalHp;
        this.isAlive = true;
    }

    // getter and setter methods
    public boolean isAlive() {
        return isAlive;
    }
    public void setDefending(boolean defending) {
        isDefending = defending;
    }
    public boolean isDefending() {
        return isDefending;
    }
    public int getDefendPoint() {
        return defendPoint;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setTotalHp(int totalHp) {
        this.totalHp = totalHp;
    }
    public int getAttackPoint() {
        return attackPoint;
    }
    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }
    public void setDefendPoint(int defendPoint) {
        this.defendPoint = defendPoint;
    }
    public int getHealPoint() {
        return healPoint;
    }
    public void setHealPoint(int healPoint) {
        this.healPoint = healPoint;
    }
    public int getTotalHp() {
        return totalHp;
    }
    public int getCurrentHp() {
        return currentHp;
    }
    public String getName() {
        return name;
    }
}
