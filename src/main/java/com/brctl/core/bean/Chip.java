package com.brctl.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 筹码
 * @author duanxiaoxing
 * @created 2018/8/4
 */
@Data
@AllArgsConstructor
public class Chip {

    private int amount;


    public void addAmount(int amount) {
        this.amount += amount;
    }


    public void minusAmount(int amount) {
        this.amount -= amount;
    }

    public void allIn() {
        this.amount = 0;
    }

}

enum ChipUnit {

}
