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

package com.huawei.mppdbide.view.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.custom.CTabFolder;

import com.huawei.mppdbide.view.uidisplay.UIDisplayFactoryProvider;
import com.huawei.mppdbide.view.utils.UIElement;
import com.huawei.mppdbide.view.utils.consts.UIConstants;

/**
 * 
 * Title: class
 * 
 * Description: The Class ActivateObjectBrowserWindow.
 *
 * @since 3.0.0
 */
public class ActivateObjectBrowserWindow {

    /**
     * Execute.
     *
     * @param modelService the model service
     * @param application the application
     */
    @Execute
    /**
     * Will activate the Object Browser window
     * 
     * @param application
     * @param service
     */
    public void execute(EModelService modelService, MApplication application) {
        MPartStack partStack = (MPartStack) modelService.find(UIConstants.UI_PARTSTACK_OBJECTBROWSER, application);
        if (partStack != null && partStack.getWidget() != null) {
            boolean isMinimized = ((CTabFolder) partStack.getWidget()).getMinimized();
            UIElement uiElementInstance = UIElement.getInstance();
            if (!isMinimized) {
                uiElementInstance.togglePart(UIConstants.UI_PART_OBJECT_BROWSER_ID, false,
                        UIConstants.UI_TOGGLE_OBJECT_BROWSER_MENU);
            } else {
                uiElementInstance.togglePart(UIConstants.UI_PART_OBJECT_BROWSER_ID, true,
                        UIConstants.UI_TOGGLE_OBJECT_BROWSER_MENU);
            }
        }
    }

    /**
     * Can execute.
     *
     * @return true, if successful
     */
    @CanExecute
    public boolean canExecute() {
        return UIDisplayFactoryProvider.getUIDisplayStateIf().isVersionCompatibile();
    }

}
