package com.brctl.core.bean;

import com.brctl.core.enumeration.CardType;
import com.brctl.core.exception.IllegalChipAmountException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 一局比赛
 * @author duanxiaoxing
 * @created 2018/8/4
 * @since 1.7
 */
@Slf4j
public class Game {

    @Getter
    private Card[] cards;
    @Getter
    private int dealerIndex;  // dealer所在的index
    @Getter
    @Setter
    private List<Player> activePlayers;  // 当前在桌上可以行动的玩家(弃牌／allin均不再active)
    @Getter
    private List<Player> winners;  // 获胜者(可能有多个)
    @Getter
    private Card[] flop;  // 翻牌
    @Getter @Setter
    private Card turn;  // 转牌
    @Getter @Setter
    private Card river;  // 河牌
    @Getter
    private GamePhase currentPhase;  // 当前所处游戏阶段
    @Getter
    private int pot;  // 底池
    // 各阶段分别有多少底池
    @Getter @Setter
    private int preflopPot;
    @Getter
    @Setter
    private int flopPot;
    @Getter
    @Setter
    private int turnPlot;
    @Getter
    @Setter
    private int riverPlot;
    @Getter
    @Setter
    private int formerChips;  // 上一玩家下注


    /**
     * 更新底池
     * @param amount
     */
    public void updatePlot(int amount) throws IllegalChipAmountException {
        if (amount < formerChips) {
            throw new IllegalChipAmountException();
        }
        switch (currentPhase) {
            case PREFLOP:
                preflopPot += amount;
                break;
            case FLOP:
                flopPot += amount;
                break;
            case TURN:
                turnPlot += amount;
                break;
            case RIVER:
                riverPlot += amount;
                break;
            default:
                break;
        }
    }


    public Game(int dealerIndex) {
        this.dealerIndex = dealerIndex;
        this.cards = new Card[52];
        this.flop = new Card[3];
        init();
    }


    /**
     * init hook
     */
    protected void init() {

    }


    /**
     * 比赛开始
     */
    public void begin() {

    }


    /**
     * 结算
     */
    private void statement() {

    }


}

/**
 * 游戏阶段枚举
 */
enum GamePhase {

    PREFLOP("翻牌前"),
    FLOP("翻牌"),
    TURN("转牌"),
    RIVER("河牌");

    @Getter
    private final String name;

    GamePhase(String name) {
        this.name = name;
    }

}
