package com.brctl.core.bean;

import com.brctl.core.exception.IllegalPlayerNumberException;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * 游戏桌
 * @author duanxiaoxing
 * @created 2018/8/4
 */
@Getter
//@ToString  // java.lang.StackOverflowError
public class Table {

    private Game currentGame;  // 当前比赛
    private Dutch dutch;  // 荷官
    private List<Player> players;

    // 游戏桌规则相关字段
    // TODO 通过配置驱动
    private int preChips;  // 前注
    private boolean isInfinite;  // 是否为无限德州
    private boolean isTournament;  // 是否为锦标赛

    public Table(List<Player> players) {
        // 默认规则
        // 1. 无前注
        // 2. 无限德州
        // 3. 非锦标赛
        this(players, 0, true, false);
    }

    public Table(List<Player> players, int preChips, boolean isInfinite, boolean isTournament) {
        if (players.size() < 2 || players.size() > 22) {
            throw new IllegalPlayerNumberException();
        }
        this.dutch = new Dutch();
        this.players = players;
        this.preChips = 0;
        this.isInfinite = isInfinite;
        this.isTournament = isTournament;
        // 荷官落座
        this.dutch.setTable(this);
        // 玩家落座
        for (Player player : players) {
            player.setTable(this);
        }
    }


    public Game newGame() {
        int dealerIndex = this.currentGame == null ? 0 :
                this.currentGame.getDealerIndex() > this.players.size() ? 0 : this.currentGame.getDealerIndex() + 1;
        this.currentGame = new Game(dealerIndex);
        // TODO 桌上玩家并非都是活跃玩家(玩家可能有旁观/站起状态)
        this.currentGame.setActivePlayers(this.players);
        return this.currentGame;
    }

}
