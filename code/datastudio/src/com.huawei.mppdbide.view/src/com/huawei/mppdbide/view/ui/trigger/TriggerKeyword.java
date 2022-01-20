/* 
 * Copyright (c) 2022 Huawei Technologies Co.,Ltd.
 *
 * openGauss is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *
 *           http://license.coscl.org.cn/MulanPSL2
 *        
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package com.huawei.mppdbide.view.ui.trigger;

/**
 * Title: class
 * Description: The enum TriggerKeyword.
 *
 * @since 3.0.0
 */
public enum TriggerKeyword {
    BEFORE("BEFORE"),
    AFTER("AFTER"),
    INSTEAD_OF("INSTEAD OF"),
    INSERT("INSERT"),
    DELETE("DELETE"),
    TRUNCATE("TRUNCATE"),
    UPDATE("UPDATE"),
    ROW("ROW"),
    STATEMENT("STATEMENT");

    /**
     * The keyword
     */
    public final String keyword;
    TriggerKeyword(String keyword) {
        this.keyword = keyword;
    }
}
