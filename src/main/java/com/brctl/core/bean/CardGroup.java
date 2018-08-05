package com.brctl.core.bean;

import com.brctl.core.enumeration.CardGroupType;
import com.brctl.core.enumeration.CardType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * 牌型
 * @author duanxiaoxing
 * @created 2018/8/4
 */
@Data
public class CardGroup implements Comparable<CardGroup> {

    private List<Card> cards;
    private CardGroupType cardGroupType;


    public CardGroup(List<Card> cards) {
        boolean isPair = false;
        boolean isTwoPairs = false;
        boolean isThreeOfAKind = false;
        boolean isStraight = false;
        boolean isFlush = false;
        boolean isFullHouse = false;
        boolean isFourOfAKind = false;
        boolean isStraightFlush = false;
        boolean isRoyalFlush = false;

        Map<Integer, List<Card>> indexCardsMap = Maps.newHashMap();
        Map<CardType, Integer> typeCountMap = Maps.newHashMap();

        for (Card card: cards) {
            if (indexCardsMap.get(card.getIndex()) == null) {
                indexCardsMap.put(card.getIndex(), Lists.newArrayList(card));
            } else {
                indexCardsMap.get(card.getIndex()).add(card);
            }
            typeCountMap.put(card.getCardType(), typeCountMap.get(card.getCardType()) == null ? 0 :
                    typeCountMap.get(card.getCardType()) + 1);
        }


        // 对子的个数
        int pairCount = 0;
        for (Map.Entry<Integer, List<Card>> entry: indexCardsMap.entrySet()) {
            if (entry.getValue().size() == 2) {
                pairCount++;
            } else if (entry.getValue().size() == 3) {
                isThreeOfAKind = true;
            } else if (entry.getValue().size() == 4) {
                isFourOfAKind = true;
                this.cards.addAll(entry.getValue());
                // TODO removeAll后遍历取最大
                // 金刚／四条的情况下，不会再出现比金刚更大的牌型，可以直接break
                break;
            }
        }
        if (pairCount == 1) {
            isPair = true;
        } else if (pairCount == 2) {
            isTwoPairs = true;
        }
        if (isThreeOfAKind && (isPair || isTwoPairs)) {
            isFullHouse = true;
        }
        for (Map.Entry<CardType, Integer> entry: typeCountMap.entrySet()) {
            if (entry.getValue() >= 5) {
                isFlush = true;
                break;
            }
        }


        // 判断顺子
        int accumulateTimes = 0;
        for (int i = 1; i < 11; i++) {
            if (i == 10) {
                if (indexCardsMap.get(10) == null || indexCardsMap.get(11) == null || indexCardsMap.get(12) == null
                        || indexCardsMap.get(13) == null || indexCardsMap.get(1) == null) {
                    break;
                } else {
                    isStraight = true;
                }
            } else {
                for (int j = 0; j < 5; j++) {
                    if (indexCardsMap.get(i + j) == null) {
                        if (this.cards != null) {
                            this.cards.clear();
                        }
                        accumulateTimes = 0;
                        break;
                    } else {
                        if (++accumulateTimes >= 5) {
                            isStraight = true;
                        }
                        // FIXME 直接取0 index,可能错过同花顺
//                        this.cards.add(indexCardsMap.get(i + j).get(0));
                    }
                }
            }
        }

        if (isRoyalFlush) {
            this.cardGroupType = CardGroupType.ROYAL_FLUSH;
        } else if (isStraightFlush) {
            this.cardGroupType = CardGroupType.STRAIGHT_FLUSH;
        } else if (isFourOfAKind) {
            this.cardGroupType = CardGroupType.FOUR_OF_A_KIND;
        } else if (isFullHouse) {
            this.cardGroupType = CardGroupType.FULL_HOUSE;
        } else if (isFlush) {
            this.cardGroupType = CardGroupType.FLUSH;
        } else if (isStraight) {
            this.cardGroupType = CardGroupType.STRAIGHT;
        } else if (isThreeOfAKind) {
            this.cardGroupType = CardGroupType.THREE_OF_A_KIND;
        } else if (isTwoPairs) {
            this.cardGroupType = CardGroupType.TWO_PAIRS;
        } else if (isPair) {
            this.cardGroupType = CardGroupType.PAIR;
        } else {
            this.cardGroupType = CardGroupType.HIGH_CARD;
        }
    }


    public int compareTo(CardGroup o) {
        return o.cardGroupType.getAnchor() - this.cardGroupType.getAnchor();
    }


}

