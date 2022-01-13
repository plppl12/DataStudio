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

package com.huawei.mppdbide.view.autosave;

import java.util.List;

import com.huawei.mppdbide.bl.autosave.AutoSaveMetadata;
import com.huawei.mppdbide.bl.util.ExecTimer;
import com.huawei.mppdbide.bl.util.IExecTimer;
import com.huawei.mppdbide.utils.IMessagesConstants;
import com.huawei.mppdbide.utils.exceptions.DatabaseCriticalException;
import com.huawei.mppdbide.utils.exceptions.DatabaseOperationException;
import com.huawei.mppdbide.utils.exceptions.MPPDBIDEException;
import com.huawei.mppdbide.utils.loader.MessageConfigLoader;
import com.huawei.mppdbide.view.ui.autosave.IAutoSaveObject;
import com.huawei.mppdbide.view.utils.UIElement;
import com.huawei.mppdbide.view.workerjob.UIWorkerJob;

/**
 * 
 * Title: class
 * 
 * Description: The Class AutoSaveLazyLoader.
 *
 * @since 3.0.0
 */
public class AutoSaveLazyLoader extends UIWorkerJob {

    /**
     * The autosave MD list.
     */
    List<AutoSaveMetadata> autosaveMDList;

    /**
     * The part object list.
     */
    List<IAutoSaveObject> partObjectList;

    /**
     * The timer.
     */
    IExecTimer timer;

    /**
     * Instantiates a new auto save lazy loader.
     *
     * @param autosaveMDList1 the autosave MD list 1
     * @param partObjectList1 the part object list 1
     */
    public AutoSaveLazyLoader(List<AutoSaveMetadata> autosaveMDList1, List<IAutoSaveObject> partObjectList1) {
        super(MessageConfigLoader.getProperty(IMessagesConstants.PRESERVESQL_STARTUP_LAZY_JOB_NAME), null);
        autosaveMDList = autosaveMDList1;
        partObjectList = partObjectList1;
    }

    /**
     * Do job.
     *
     * @return the object
     * @throws DatabaseOperationException the database operation exception
     * @throws DatabaseCriticalException the database critical exception
     * @throws MPPDBIDEException the MPPDBIDE exception
     * @throws Exception the exception
     */
    @Override
    public Object doJob() throws DatabaseOperationException, DatabaseCriticalException, MPPDBIDEException, Exception {
        timer = new ExecTimer("AutoSaveLazyLoader Job");
        timer.start();
        AutoSaveManager.getInstance().readAutoSaveFilesForLazyLoad(autosaveMDList, partObjectList);
        return null;
    }

    /**
     * On operational exception UI action.
     *
     * @param exception the exception
     */
    @Override
    public void onOperationalExceptionUIAction(DatabaseOperationException exception) {
        showFailedStatus();
    }

    /**
     * On success UI action.
     *
     * @param obj the obj
     */
    @Override
    public void onSuccessUIAction(Object obj) {
        UIElement.getInstance().setStatusBarMessage(
                MessageConfigLoader.getProperty(IMessagesConstants.PRESERVESQL_STARTUP_LOADING_FINISHED));
    }

    /**
     * Final cleanup.
     *
     * @throws MPPDBIDEException the MPPDBIDE exception
     */
    @Override
    public void finalCleanup() throws MPPDBIDEException {
        timer.stopAndLog();
    }

    /**
     * Final cleanup UI.
     */
    @Override
    public void finalCleanupUI() {
    }

    private void showFailedStatus() {
        UIElement.getInstance().setStatusBarMessage(
                MessageConfigLoader.getProperty(IMessagesConstants.PRESERVESQL_STARTUP_LOADING_FAILED));
    }

    /**
     * On critical exception UI action.
     *
     * @param exception the exception
     */
    @Override
    public void onCriticalExceptionUIAction(DatabaseCriticalException exception) {
        showFailedStatus();
    }

    /**
     * On MPPDBIDE exception UI action.
     *
     * @param exception the exception
     */
    @Override
    public void onMPPDBIDEExceptionUIAction(MPPDBIDEException exception) {
        super.onMPPDBIDEExceptionUIAction(exception);
        showFailedStatus();
    }
}
