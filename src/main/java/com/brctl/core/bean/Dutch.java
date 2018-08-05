package com.brctl.core.bean;

import com.brctl.core.enumeration.CardType;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 荷官
 * @author duanxiaoxing
 * @created 2018/8/4
 */
@Slf4j
public class Dutch {

    private static final List<Card> ORIGIN_CARDS;  // 一副新扑克牌

    static {
        ORIGIN_CARDS = Lists.newArrayList();
        for(int i = 0; i < 4; i++) {
            CardType cardType = CardType.values()[i];
            for(int j = 0; j < 13; j++) {
                ORIGIN_CARDS.add(new Card(j + 1, cardType));
            }
        }
    }

    @Getter
    private List<Card> cards = Lists.newArrayList();
    @Getter
    @Setter
    private Table table;

    /**
     * 洗牌
     */
    public void refreshCards() {
        cards.clear();
        List<Card> copyOriginCards = Lists.newArrayList(ORIGIN_CARDS);
        for(int i = 0; i < 52; i++) {
            cards.add(copyOriginCards.remove(ThreadLocalRandom.current().nextInt(copyOriginCards.size())));
        }
    }


    /**
     * 叫注
     */
    public void callChip() {
        log.info("当前牌面: {} {} {}", Arrays.toString(table.getCurrentGame().getFlop()),
                table.getCurrentGame().getTurn(), table.getCurrentGame().getRiver());
        log.info("请下注");
    }


    /**
     * 翻牌前阶段荷官动作
     */
    public void preflopAction() {
        refreshCards();
        List<Player> activePlayers = table.getCurrentGame().getActivePlayers();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < activePlayers.size(); j++) {
                activePlayers.get(j).fetchCard(cards.remove(0));
            }
        }
        callChip();
    }


    /**
     * 翻牌阶段荷官动作
     */
    public void flopAction() {
        for (int i = 0; i < 3; i++) {
            table.getCurrentGame().getFlop()[i] = cards.remove(0);
        }
        callChip();
    }


    /**
     * 转牌阶段荷官动作
     */
    public void turnAction() {
        table.getCurrentGame().setTurn(cards.remove(0));
        callChip();
    }


    /**
     * 河牌阶段荷官动作
     */
    public void riverAction() {
        table.getCurrentGame().setRiver(cards.remove(0));
        callChip();
    }


    /**
     * 为每位玩家发牌
     */
    public void showPrivateCard() {

    }


    /**
     * 发公共牌
     */
    public void showPublicCard() {

    }


    /**
     * 切牌
     */
    public void discardCard() {

    }


    public static void main(String[] args) {
//        Game game = new Game(0);
//        log.info(Arrays.toString(game.getCards()));
//        log.info(Arrays.toString(game.getCards()));
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.CLUBS));
        cards.add(new Card(10, CardType.DIAMONDS));
        log.info("{}", cards.remove(0));
        log.info("{}", cards.remove(0));
        log.info("{}", cards.remove(0));
    }

}
