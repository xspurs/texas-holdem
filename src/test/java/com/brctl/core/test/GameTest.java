package com.brctl.core.test;

import com.brctl.core.bean.Card;
import com.brctl.core.bean.Chip;
import com.brctl.core.bean.Dutch;
import com.brctl.core.bean.Game;
import com.brctl.core.bean.Player;
import com.brctl.core.bean.Table;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 比赛测试类
 * @author duanxiaoxing
 * @created 2018/8/4
 */
@Slf4j
public class GameTest extends AbstractTest {

    @Test
    public void mainProcessingTest() {
        List<Player> players = Lists.newArrayList();
        // create 5 players
        for (int i = 0; i < 5; i++) {
            players.add(new Player("player" + i, new Chip(20)));
        }
        Table table = new Table(players);

        Game game = table.newGame();

        assertNotNull(players.get(0).getTable());
        log.info("curr table: {}", players.get(0).getTable());
        Dutch dutch = table.getDutch();
        // 翻牌前
        dutch.preflopAction();


        int gunIndex;
        if (players.size() < 4) {
            // 玩家人数小于4人
            gunIndex = players.size();
        } else {
            gunIndex = game.getDealerIndex() + 3;
        }
        // TODO 游戏流程编写，有荷官主导
//        players.get(game.getDealerIndex() + 3).
        // 枪口位
//        players.get(gunIndex - 1).call();

        // 翻牌
        dutch.flopAction();
        // 转牌
        dutch.turnAction();
        // 河牌
        dutch.riverAction();
    }


    /**
     * 测试洗牌
     */
    @Test
    public void refreshCardTest() {
        Dutch dutch = new Dutch();
        dutch.refreshCards();
        log.info("{}", dutch.getCards());
        List<Card> heartsCards = Lists.newArrayList();
        List<Card> diamondsCards = Lists.newArrayList();
        List<Card> spadesCards = Lists.newArrayList();
        List<Card> clubsCards = Lists.newArrayList();
        try {
            for (Card card : dutch.getCards()) {
                switch (card.getCardType()) {
                    case HEARTS:
                        heartsCards.add(card);
                        break;
                    case DIAMONDS:
                        diamondsCards.add(card);
                        break;
                    case SPADES:
                        spadesCards.add(card);
                        break;
                    case CLUBS:
                        clubsCards.add(card);
                        break;
                    default:
                        throw new Exception();
                }
            }
        } catch (Exception e) {
            log.error("error", e);
        }
        assertEquals(heartsCards.size(), 13);
        assertEquals(diamondsCards.size(), 13);
        assertEquals(spadesCards.size(), 13);
        assertEquals(clubsCards.size(), 13);
        log.info("hearts: {}", heartsCards);
        log.info("diamonds: {}", diamondsCards);
        log.info("spades: {}", spadesCards);
        log.info("clubs: {}", clubsCards);
    }

}
