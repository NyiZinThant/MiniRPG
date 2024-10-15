package com.steven.rpg;

import java.util.LinkedList;

public class GameEngineV1 implements GameEngine{
    final private Console console;
    private Player player;
    final private LinkedList<Enemy>[] gameLevels;
    private boolean needRetry;
    public GameEngineV1(Console console, LinkedList<Enemy>[] gameLevels) {
        this.console = console;
        this.gameLevels = gameLevels;
    }

    private void initializeGame(){
        console.printLine();
        console.print("     Welcome to the Java Mini RPG \uD83D\uDC4B");
        console.printLine();
        console.print("In this world of adventure, your journey begins!");
        console.print("Will you emerge as a legendary hero or fall in battle?");
        console.printLine();

        // input player name
        String name = console.readString("Enter your character's name to begin");
        player = new Player(name, 1);
        console.printLine();
        console.print("");
    }
    private void gameLoop(){
        String[] playerActions = {"Attack", "Defend", "Heal", "Exit"};
        // for level
        for(int currentGameLevel = 0; currentGameLevel < gameLevels.length; currentGameLevel++){
            battleStage(currentGameLevel, playerActions);
            console.print("+++Congratulations! You complete game level - " + (currentGameLevel + 1));
        }
    }
    // for each enemy
    private void battleStage(int currentGameLevel, String[] playerActions) {
        Enemy currentEnemy = null;
        LinkedList<Enemy> currentGame = new LinkedList<Enemy>();
        for (Enemy enemy: gameLevels[currentGameLevel]){
            currentGame.add(new Enemy(enemy.getName(), enemy.getLevel()));
        }
        outerLoop:
        while (!currentGame.isEmpty()) {
            if (currentEnemy == null) {
                currentEnemy = currentGame.getFirst();
            }
            if(!currentEnemy.isAlive()){
                console.printLine();
                console.print("☠☠ The enemy is defeated!! ☠☠");
                console.printLine();
                currentGame.removeFirst();
                if(currentGame.isEmpty()){
                    break;
                }
                currentEnemy = currentGame.getFirst();
                processDefeatOutcome(player);
            }
            console.showHUD(player, currentEnemy, currentGameLevel + 1, currentGame.size());
            console.showOptions(playerActions);
            // user actions
            int userOption = console.selectControl(0, playerActions.length - 1);
            switch(playerActions[userOption]){
                case "Exit":
                    this.exitGame();
                    break;
                case "Defend":
                    player.doAction(playerActions[userOption], currentEnemy, console);
                    currentEnemy.doAction(player, console);
                    if(processActionOutcome(player)){
                        break outerLoop;
                    }
                    break;
                default:
                    currentEnemy.doAction(player, console);
                    if(processActionOutcome(player)){
                        break outerLoop;
                    }
                    player.doAction(playerActions[userOption], currentEnemy, console);
            }
        }
        if (this.needRetry){
            this.needRetry = false;
            player.resetHp();
            this.battleStage(currentGameLevel, playerActions);
        }

    }
    private boolean processActionOutcome(Character character){
        if(character.isAlive()) return false;
        String[] userOptions = {"Retry", "Exit"};
        console.showOptions(userOptions);
        int userChoice = console.selectControl(0, 1);
        if (userOptions[userChoice].equals("Retry")) {
            retryGame();
            return true;
        }
        exitGame();
        return false;
    }
    private void processDefeatOutcome(Player player){
        if (player.addExp(42) == 1){
            console.print("⬆⬆ Level up!! Now your level is " + player.getLevel());
            final String[] options = {"Add 1 point to Attack Point", "Add 1 point to Defend Point", "Add 1 point to Heal Point"};
            console.showOptions(options);
            int userChoice = console.selectControl(0, 2);
            switch (userChoice){
                case 0:
                    player.setAttackPoint(1);
                    console.print("⚔⚔ Your attack point is now " + player.getAttackPoint());
                    break;
                case 1:
                    player.setDefendPoint(1);
                    console.print("\uD83D\uDEE1\uD83D\uDEE1 Your defend point is now " + player.getDefendPoint());
                    break;
                default:
                    player.setHealPoint(1);
                    console.print("➕➕ Your healing point is now " + player.getHealPoint());
            }
        }
    }
    private void retryGame(){
        this.needRetry = true;
    }
    private void exitGame(){
        console.print("❤❤ Thank you for playing Java Mini RPG ❤❤");
        console.exit();
    }
    public void startGame(){
        this.initializeGame();
        this.gameLoop();
        this.exitGame();
    }
}
