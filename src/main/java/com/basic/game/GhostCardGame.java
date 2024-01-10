package com.basic.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GhostCardGame {
    private List<String> ghostCards = new ArrayList<>();
    private List<String> playerCards = new ArrayList<>();
    private List<String> computerCards = new ArrayList<>();
    private int playerScore = 0;
    private int computerScore = 0;

    public void initialize() {
        // 初始化鬼牌和玩家、电脑的牌
        ghostCards.add("Ghost Card 1");
        ghostCards.add("Ghost Card 2");
        ghostCards.add("Ghost Card 3");
        playerCards.add("Player Card 1");
        playerCards.add("Player Card 2");
        computerCards.add("Computer Card 1");
        computerCards.add("Computer Card 2");
        computerCards.add("Computer Card 3");

        // 洗牌
        shuffle();
    }

    public void playRound() {
        // 玩家出牌
        System.out.println("Player's turn:");
        playerCards.remove(0); // 取出一张牌
        System.out.println(playerCards.get(0)); // 输出玩家出的牌

        // 判断输赢
        if (isWinningMove(playerCards.get(0))) {
            playerScore++;
            System.out.println("Player wins this round!");
        } else {
            computerPlay();
            computerScore++;
            System.out.println("Computer wins this round!");
        }
    }

    public void computerPlay() {
        // 电脑随机出牌
        Random random = new Random();
        int index = random.nextInt(ghostCards.size());
        String card = ghostCards.get(index);
        ghostCards.remove(index); // 从鬼牌中移除这张牌
        playerCards.add(card); // 将这张牌加入玩家的牌堆中
        System.out.println("Computer played: " + card); // 输出电脑出的牌
    }

    public boolean isWinningMove(String card) {
        // 判断玩家出的牌是否胜利
        // TODO: 根据游戏规则实现胜利判断逻辑
        return false;
    }

    public void shuffle() {
        // 洗牌
        Random random = new Random();
        for (int i = 0; i < ghostCards.size(); i++) {
            int index = random.nextInt(ghostCards.size());
            String temp = ghostCards.get(i);
            ghostCards.set(i, ghostCards.get(index));
            ghostCards.set(index, temp);
        }
    }

    public static void main(String[] args) {
        GhostCardGame game = new GhostCardGame();
        game.initialize();
        while (true) {
            game.playRound();
        }
    }
}
