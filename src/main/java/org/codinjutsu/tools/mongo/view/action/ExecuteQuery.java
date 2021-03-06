/*
 * Copyright (c) 2013 David Boissier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codinjutsu.tools.mongo.view.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.codinjutsu.tools.mongo.view.MongoRunnerPanel;
import org.codinjutsu.tools.mongo.view.style.StyleAttributesUtils;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ExecuteQuery extends AnAction {
    private final MongoRunnerPanel mongoRunnerPanel;

    public ExecuteQuery(MongoRunnerPanel mongoRunnerPanel) {
        super("Execute query", "Execute query with options", StyleAttributesUtils.getInstance().getExecuteIcon());
        this.mongoRunnerPanel = mongoRunnerPanel;

        registerCustomShortcutSet(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK, mongoRunnerPanel);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        mongoRunnerPanel.executeQuery();
    }

    @Override
    public void update(AnActionEvent event) {
        event.getPresentation().setEnabled(mongoRunnerPanel.getMongoCollection() != null);
    }
}
