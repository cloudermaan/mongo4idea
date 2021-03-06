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

package org.codinjutsu.tools.mongo.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

class MongoCommandLineState extends CommandLineState {

    private final MongoRunConfiguration mongoRunConfiguration;

    public MongoCommandLineState(MongoRunConfiguration mongoRunConfiguration, ExecutionEnvironment environment) {
        super(environment);
        this.mongoRunConfiguration = mongoRunConfiguration;
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        GeneralCommandLine commandLine = generateCommandLine();
        final OSProcessHandler processHandler = new ColoredProcessHandler(commandLine);
        ProcessTerminatedListener.attach(processHandler);
        return processHandler;
    }

    private GeneralCommandLine generateCommandLine() {
        String exePath = "/usr/bin/mongo"; //TODO need to make it configurable

        final GeneralCommandLine commandLine = new GeneralCommandLine();
        VirtualFile scriptPath = mongoRunConfiguration.getScriptPath();
        commandLine.addParameter(scriptPath.getPath());
        commandLine.setExePath(exePath);
        System.out.println("commandLinePath = " + commandLine.getCommandLineString());
        return commandLine;
    }
}
