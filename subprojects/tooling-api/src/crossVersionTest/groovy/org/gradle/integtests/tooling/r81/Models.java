/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.integtests.tooling.r81;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Models implements Serializable {
    private final boolean runsInParallel;
    private final List<ToolchainModel> projects;

    public Models(boolean runsInParallel, Collection<ToolchainModel> projects) {
        this.runsInParallel = runsInParallel;
        this.projects = new ArrayList<ToolchainModel>(projects);
    }

    public boolean isMayRunInParallel() {
        return runsInParallel;
    }

    public List<ToolchainModel> getProjects() {
        return projects;
    }
}
