package com.brctl.core.bean;

import com.brctl.core.enumeration.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 扑克牌
 * @author duanxiaoxing
 * @created 2018/8/4
 */
@Data
@AllArgsConstructor
public class Card {

    // TODO 枚举
//    private char name;  // A-K
    private int index;
    private CardType cardType;

}

