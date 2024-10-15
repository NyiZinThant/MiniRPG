package com.steven.rpg;

public interface Console {
    void showHUD(Character player, Character opponent, int gameLevel, int remainingEnemy);
    void print(String output);
    void printLine();
    void showOptions(String[] options);
    int selectControl(int min, int max);
    String readString(String message);
    void exit();
}
