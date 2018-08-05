package com.brctl.core.enumeration;

import lombok.Getter;

/**
 * 扑克牌类型
 * @author duanxiaoxing
 * @created 2018/8/5
 */
public enum CardType {

    HEARTS("红桃"),
    DIAMONDS("方块"),
    SPADES("黑桃"),
    CLUBS("梅花");

    @Getter
    private final String name;

    CardType(String name) {
        this.name = name;
    }
}
