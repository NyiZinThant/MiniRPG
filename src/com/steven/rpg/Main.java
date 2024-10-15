package com.steven.rpg;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        LinkedList<Enemy>[] gameLevels = new LinkedList[3];

        gameLevels[0] = new LinkedList<Enemy>();
        gameLevels[0].add(new Enemy("Goblin", 1));
        gameLevels[0].add(new Enemy("Goblin", 1));
        gameLevels[0].add(new Enemy("Orc", 2));

        gameLevels[1] = new LinkedList<Enemy>();
        gameLevels[1].add(new Enemy("Goblin", 1));
        gameLevels[1].add(new Enemy("Orc", 2));
        gameLevels[1].add(new Enemy("Troll", 3));

        gameLevels[2] = new LinkedList<Enemy>();
        gameLevels[2].add(new Enemy("Troll", 3));
        gameLevels[2].add(new Enemy("Dragon", 4));

        Console console = new ConsoleV1(new Scanner(System.in));
        GameEngine gameEngine = new GameEngineV1(console, gameLevels);
        gameEngine.startGame();
    }
}
