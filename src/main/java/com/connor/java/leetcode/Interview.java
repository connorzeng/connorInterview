package com.connor.java.leetcode;


import java.util.List;
import java.util.Map;

public class Interview {
    // 股票表 stock(stk_no(PK), symbol, status(A有效/I无效)),
    // 股价表 price(date, stk_no(FK), cl_price) PK(date, stk_no),
    // 注意有效股票也可能停牌，导致price表中当日数据没有对应的记录。
    // 我们需要查询所有{有效}股票在{某日}的价格, 停牌的股票也希望保留，价格可以留NULL
    //比如
    //stock表:
    //1, AAPL, A
    //2, FCBK, I
    //3, FUTU, A
    //4, 0700, A
    //5, SIVB, A

    //price表:
    //2023-04-01, 1, $450.23
    //2023-04-01, 3, $34.12
    //2023-04-01, 4, $50.12
    //2023-04-02, 1, $445.23
    //2023-04-02, 3, $36.12
    //2023-04-02, 4, $48.12、

    //查询日期2023-04-01预期结果:
    //AAPL - $450.23
    //FUTU - $34.12
    //0700 - $50.12
    //SIVB - NULL


    // select s.symbol, p.price from price p left join stock s on s.stk_no = p.stk_no where p.date = '2023-04-01'
    // 不记得语法了。。。
    // case when s.status == 'I' NULL
    // case when s.status == 'A' p.price

    /*游戏角色, 有技能列表和魔法值, 求能造成的最大伤害, 例如:
    输入skill_list: [{mana_cost:10,damage:10}, {mana_cost:12,damage:13}], current_mana: 20, 输出max_damage: 20
    输入skill_list: [{mana_cost:10,damage:10}, {mana_cost:12,damage:13}], current_mana: 25, 输出max_damage: 26
    输入skill_list: [{mana_cost:2,damage:5}, {mana_cost:4,damage:11}, {mana_cost:7,damage:20}], current_mana: 13,
    输出max_damage: 36*/

    class Skill{
        public int mana;
        public int damage;
        public int rate;
    }

}
