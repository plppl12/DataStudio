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

package com.huawei.mppdbide.gauss.format.processor.insert;

import com.huawei.mppdbide.gauss.format.consts.FormatItemsType;
import com.huawei.mppdbide.gauss.format.option.FmtOptionsIf;
import com.huawei.mppdbide.gauss.format.option.OptionsProcessData;
import com.huawei.mppdbide.gauss.format.processor.AbstractProcessor;
import com.huawei.mppdbide.gauss.sqlparser.stmt.custom.dml.TInsertSqlStatement;
import com.huawei.mppdbide.gauss.sqlparser.stmt.node.TParseTreeNode;

/**
 * 
 * Title: InsertStmtFormatProcessor
 *
 * @since 3.0.0
 */
public class InsertStmtFormatProcessor extends AbstractProcessor<TInsertSqlStatement> {

    /**
     * Gets the options process data.
     *
     * @param selectAstNode the select ast node
     * @param pData the data
     * @param options the options
     * @return the options process data
     */
    public OptionsProcessData getOptionsProcessData(TInsertSqlStatement selectAstNode, OptionsProcessData pData,
            FmtOptionsIf options) {

        int maxKeywordWidth = getMaxKeywordWidth(selectAstNode, options);

        OptionsProcessData lOptionsProcessData = getNewOptionsDataBean(pData, maxKeywordWidth);

        lOptionsProcessData.setFormatItemsType(FormatItemsType.INSERT);

        return lOptionsProcessData;
    }

    /**
     * Gets the start node.
     *
     * @param selectAstNode the select ast node
     * @return the start node
     */
    @Override
    public TParseTreeNode getStartNode(TInsertSqlStatement selectAstNode) {
        return selectAstNode.getStartNode();
    }

    /**
     * Checks if is clone data.
     *
     * @return true, if is clone data
     */
    public boolean isCloneData() {
        return true;
    }

}
