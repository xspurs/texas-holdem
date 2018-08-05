package com.brctl.core.test;

import com.brctl.core.bean.Card;
import com.brctl.core.bean.CardGroup;
import com.brctl.core.enumeration.CardGroupType;
import com.brctl.core.enumeration.CardType;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * 牌型测试类
 * @author duanxiaoxing
 * @created 2018/8/5
 */
@Slf4j
public class CardGroupTypeTest extends AbstractTest {


    /**
     * 高牌牌型测试
     */
    @Test
    public void highCardTest() {
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.HEARTS));
        cards.add(new Card(3, CardType.SPADES));
        cards.add(new Card(5, CardType.HEARTS));
        cards.add(new Card(7, CardType.SPADES));
        cards.add(new Card(9, CardType.CLUBS));
        cards.add(new Card(11, CardType.CLUBS));
        cards.add(new Card(13, CardType.HEARTS));

        CardGroup cardGroup = new CardGroup(cards);
        log.info("{}", cardGroup.getCardGroupType().getName());
        assertEquals(CardGroupType.HIGH_CARD, cardGroup.getCardGroupType());
    }


    /**
     * 对子牌型测试
     */
    @Test
    public void pairTest() {
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.HEARTS));
        cards.add(new Card(1, CardType.SPADES));
        cards.add(new Card(2, CardType.HEARTS));
        cards.add(new Card(3, CardType.SPADES));
        cards.add(new Card(5, CardType.CLUBS));
        cards.add(new Card(6, CardType.CLUBS));
        cards.add(new Card(7, CardType.HEARTS));

        CardGroup cardGroup = new CardGroup(cards);
        log.info("{}", cardGroup.getCardGroupType().getName());
        assertEquals(CardGroupType.PAIR, cardGroup.getCardGroupType());
    }


    /**
     * 两对牌型测试
     */
    @Test
    public void twoPairsTest() {
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.HEARTS));
        cards.add(new Card(1, CardType.SPADES));
        cards.add(new Card(7, CardType.HEARTS));
        cards.add(new Card(7, CardType.SPADES));
        cards.add(new Card(8, CardType.CLUBS));
        cards.add(new Card(9, CardType.CLUBS));
        cards.add(new Card(13, CardType.HEARTS));

        CardGroup cardGroup = new CardGroup(cards);
        log.info("{}", cardGroup.getCardGroupType().getName());
        assertEquals(CardGroupType.TWO_PAIRS, cardGroup.getCardGroupType());
    }


    /**
     * 三条牌型测试
     */
    @Test
    public void threeOfAKindTest() {
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.HEARTS));
        cards.add(new Card(1, CardType.SPADES));
        cards.add(new Card(1, CardType.CLUBS));
        cards.add(new Card(7, CardType.HEARTS));
        cards.add(new Card(7, CardType.SPADES));
        cards.add(new Card(7, CardType.CLUBS));
        cards.add(new Card(13, CardType.HEARTS));

        CardGroup cardGroup = new CardGroup(cards);
        log.info("{}", cardGroup.getCardGroupType().getName());
        assertEquals(CardGroupType.THREE_OF_A_KIND, cardGroup.getCardGroupType());
    }


    /**
     * 顺子牌型测试
     * A~5
     */
    @Test
    public void straightATest() {
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.HEARTS));
        cards.add(new Card(2, CardType.CLUBS));
        cards.add(new Card(3, CardType.DIAMONDS));
        cards.add(new Card(4, CardType.DIAMONDS));
        cards.add(new Card(5, CardType.SPADES));
        cards.add(new Card(11, CardType.SPADES));
        cards.add(new Card(13, CardType.CLUBS));

        CardGroup cardGroup = new CardGroup(cards);
        log.info("{}", cardGroup.getCardGroupType().getName());
        assertEquals(CardGroupType.STRAIGHT, cardGroup.getCardGroupType());
    }


    /**
     * 顺子牌型测试
     * 10~A
     */
    @Test
    public void straightBTest() {
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.HEARTS));
        cards.add(new Card(2, CardType.CLUBS));
        cards.add(new Card(3, CardType.DIAMONDS));
        cards.add(new Card(10, CardType.DIAMONDS));
        cards.add(new Card(11, CardType.SPADES));
        cards.add(new Card(12, CardType.SPADES));
        cards.add(new Card(13, CardType.HEARTS));

        CardGroup cardGroup = new CardGroup(cards);
        log.info("{}", cardGroup.getCardGroupType().getName());
        assertEquals(CardGroupType.STRAIGHT, cardGroup.getCardGroupType());
    }


    /**
     * 同花牌型测试
     */
    @Test
    public void flushTest() {
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.HEARTS));
        cards.add(new Card(3, CardType.HEARTS));
        cards.add(new Card(5, CardType.HEARTS));
        cards.add(new Card(7, CardType.HEARTS));
        cards.add(new Card(9, CardType.HEARTS));
        cards.add(new Card(11, CardType.HEARTS));
        cards.add(new Card(13, CardType.HEARTS));

        CardGroup cardGroup = new CardGroup(cards);
        log.info("{}", cardGroup.getCardGroupType().getName());
        assertEquals(CardGroupType.FLUSH, cardGroup.getCardGroupType());
    }


    /**
     * 葫芦牌型测试
     */
    @Test
    public void fullHouseTest() {
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.HEARTS));
        cards.add(new Card(1, CardType.CLUBS));
        cards.add(new Card(1, CardType.DIAMONDS));
        cards.add(new Card(2, CardType.HEARTS));
        cards.add(new Card(2, CardType.SPADES));
        cards.add(new Card(5, CardType.HEARTS));
        cards.add(new Card(5, CardType.SPADES));

        CardGroup cardGroup = new CardGroup(cards);
        log.info("{}", cardGroup.getCardGroupType().getName());
        assertEquals(CardGroupType.FULL_HOUSE, cardGroup.getCardGroupType());
    }


    /**
     * 金刚牌型测试
     */
    @Test
    public void fourOfAKindTest() {
        List<Card> cards = Lists.newArrayList();
        cards.add(new Card(1, CardType.HEARTS));
        cards.add(new Card(1, CardType.CLUBS));
        cards.add(new Card(1, CardType.DIAMONDS));
        cards.add(new Card(1, CardType.SPADES));
        cards.add(new Card(2, CardType.SPADES));
        cards.add(new Card(5, CardType.HEARTS));
        cards.add(new Card(5, CardType.SPADES));

        CardGroup cardGroup = new CardGroup(cards);
        log.info("{}", cardGroup.getCardGroupType().getName());
        assertEquals(CardGroupType.FOUR_OF_A_KIND, cardGroup.getCardGroupType());
    }


    /**
     * 同花顺牌型测试
     */


    /**
     * 皇家同花顺牌型测试
     */

}
