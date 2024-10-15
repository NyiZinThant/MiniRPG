package com.steven.rpg;

import java.util.Scanner;

public class ConsoleV1 implements Console{
    Scanner scanner;

    public ConsoleV1(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showHUD(Character player, Character opponent, int gameLevel, int remainingEnemy){
        this.printLine();
        this.print("        Game Level: " + gameLevel);
        this.printLine();
        this.printPerson("Player", player);
        this.printPerson("Enemy", opponent);
        this.print("Remaining Enemy: " + remainingEnemy);
        this.printLine();
    }
    @Override
    public void print(String output){
        System.out.println(output);
    }
    public void printLine(){
        this.print("========================================");
    }
    @Override
    public void showOptions(String[] options){
        int optLength = options.length;
        if(optLength == 0){
            return;
        }
        for(int i = 0; i < optLength; i++) {
            this.print(i + ")" + options[i]);
        }
    }
    @Override
    public int selectControl(int min,int max){
        int result;
        do {
            System.out.print("Type to select option: ");
            result = scanner.nextInt();
        }while(result < min || result > max);
        return result;
    }
    @Override
    public String readString(String message){
        String result;
        do {
            System.out.print(message +": ");
            result = scanner.nextLine();
        }while(result.isEmpty());
        return result;
    }
    @Override
    public void exit(){
        System.exit(0);
    }
    private void printPerson(String type, Character character){
        this.print(type + ": " + character.getName() + "  Level: " + character.getLevel() + "  HP: " + character.getCurrentHp() + " / " + character.getTotalHp());
    }

}
