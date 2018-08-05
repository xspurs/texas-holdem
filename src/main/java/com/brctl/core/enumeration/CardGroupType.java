package com.brctl.core.enumeration;

import lombok.Getter;

/**
 * 牌型类型
 * @author duanxiaoxing
 * @created 2018/8/5
 */
public enum CardGroupType {

    HIGH_CARD("高牌", 0),  // 高牌
    PAIR("对子", 1),  // 对子
    TWO_PAIRS("两对", 2),  // 两对
    THREE_OF_A_KIND("三条", 3),
    STRAIGHT("顺子", 4),
    FLUSH("同花", 5),
    FULL_HOUSE("葫芦", 6),
    FOUR_OF_A_KIND("四条", 7),
    STRAIGHT_FLUSH("同花顺", 8),
    ROYAL_FLUSH("皇家同花顺", 9);

    @Getter
    private final String name;
    // 牌型大小锚(TODO 命名及比较方式优化)
    @Getter
    private final int anchor;

    CardGroupType(String name, int anchor) {
        this.name = name;
        this.anchor = anchor;
    }

}
