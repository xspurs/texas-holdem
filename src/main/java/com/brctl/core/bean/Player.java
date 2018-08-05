package com.brctl.core.bean;

import com.brctl.core.exception.IllegalChipAmountException;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 玩家
 * @author duanxiaoxing
 * @created 2018/8/4
 */
public class Player {

    @Getter
    private String name;
    @Getter
    private Chip chip;
    @Getter @Setter
    private Table table;
    private List<Card> handCards = newArrayList();  // 手牌
    private CardGroup currentCardGroup;  // 当前牌型(手牌+公共牌组合的最大牌型
    private boolean isAllIn;
    private boolean isFold;
    @Getter @Setter
    private PlayerState playerState;


    public Player(String name, Chip chip) {
        this.name = name;
        this.chip = chip;
    }


    /**
     * 玩家是否可以在game中继续行动
     * @return {@code true} 可以行动 {@code false} 不能行动
     */
    public boolean isActive() {
        return chip.getAmount() > 0 && playerState == PlayerState.COMPETE
                && !isAllIn && !isFold;
    }


    /**
     * 接收荷官发的牌
     */
    public void fetchCard(Card card) {
        handCards.add(card);
    }


    /**
     * check
     */
    public void check() {

    }


    /**
     * call
     */
    public void call() throws IllegalChipAmountException {
        Game game = table.getCurrentGame();
        chip.minusAmount(game.getFormerChips());
        game.updatePlot(game.getFormerChips());
    }


    /**
     * raise
     * @param amount
     */
    public void raise(int amount) throws IllegalChipAmountException {
        Game game = table.getCurrentGame();
        chip.minusAmount(amount);
        game.updatePlot(amount);
        game.setFormerChips(amount);
    }


    /**
     * re-raise
     * @param amount
     */
    public void reRaise(int amount) throws IllegalChipAmountException {
        raise(amount);
    }


    /**
     * fold
     */
    public void fold() {
        isFold = true;
    }


    /**
     * all-in
     */
    public void allIn() throws IllegalChipAmountException {
        Game game = table.getCurrentGame();
        if (chip.getAmount() > game.getFormerChips()) {
            game.setFormerChips(chip.getAmount());
        }
        game.updatePlot(chip.getAmount());
        chip.allIn();
        isAllIn = true;
    }


    public CardGroup getCurrentCardGroup() {
        Game game = table.getCurrentGame();
        List<Card> totalCards = new ArrayList<Card>(handCards);
        switch (game.getCurrentPhase()) {
            case PREFLOP:

                break;
            case FLOP:
                break;
            case TURN:
                break;
            case RIVER:
                break;
        }
        totalCards.add(game.getTurn());


        return null;
    }

}

enum PlayerState {

    LOOK_ON("旁观"),
    WAIT("等待比赛"),
    COMPETE("正在比赛");

    @Getter
    private final String name;

    PlayerState(String name) {
        this.name = name;
    }
}
