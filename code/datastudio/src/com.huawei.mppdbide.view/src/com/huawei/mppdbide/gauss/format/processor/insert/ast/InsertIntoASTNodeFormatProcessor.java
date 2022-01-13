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

package com.huawei.mppdbide.gauss.format.processor.insert.ast;

import com.huawei.mppdbide.gauss.format.processor.listimpl.ASTStartNodeNoNewlineFormatProcessorListener;
import com.huawei.mppdbide.gauss.format.processor.listimpl.AddEmptyPreTextFormatProcessorListener;
import com.huawei.mppdbide.gauss.format.processor.listimpl.NewlineWithIndentFormatProcessorListener;
import com.huawei.mppdbide.gauss.format.processor.listimpl.NoPreTextFormatProcessorListener;
import com.huawei.mppdbide.gauss.format.processor.listimpl.ResultListFormatProcessorListener;
import com.huawei.mppdbide.gauss.format.processor.select.AbstractASTNodeFormatProcessor;
import com.huawei.mppdbide.gauss.sqlparser.stmt.astnode.TBasicASTNode;
import com.huawei.mppdbide.gauss.sqlparser.stmt.astnode.insert.TInsertIntoASTNode;

/**
 * 
 * Title: InsertIntoASTNodeFormatProcessor
 *
 * @since 3.0.0
 */
public class InsertIntoASTNodeFormatProcessor extends AbstractASTNodeFormatProcessor {

    /**
     * Before process.
     *
     * @param node the node
     */
    public void beforeProcess(TBasicASTNode node) {
        super.beforeProcess(node);

        TInsertIntoASTNode insertNode = (TInsertIntoASTNode) node;

        addFormatProcessListener(insertNode.getKeywordNode(), new ASTStartNodeNoNewlineFormatProcessorListener());

        addFormatProcessListener(insertNode.getTableName(), new AddEmptyPreTextFormatProcessorListener());

        addFormatProcessListener(insertNode.getStartInsertAstBracket(), new NewlineWithIndentFormatProcessorListener());

        ResultListFormatProcessorListener processListener = new ResultListFormatProcessorListener();
        processListener.setAddPreSpace(false);
        addFormatProcessListener(insertNode.getItemList(), processListener);

        addFormatProcessListener(insertNode.getEndInsertAstBracket(), new NoPreTextFormatProcessorListener());

    }

}
