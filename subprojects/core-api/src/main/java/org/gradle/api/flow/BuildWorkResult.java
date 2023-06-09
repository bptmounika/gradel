/*
 * Copyright 2022 the original author or authors.
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

package org.gradle.api.flow;

import org.gradle.api.Incubating;

import java.util.Optional;

/**
 * Summary result of the execution of the work scheduled for the current build.
 *
 * @see FlowProviders#getBuildWorkResult()
 * @since 8.1
 */
@Incubating
public interface BuildWorkResult {

    /**
     * A summary of the configuration time and execution time failure(s) that occurred as Gradle tried to execute the
     * scheduled work.
     *
     * @return {@link Optional#empty() empty} when no failures occur.
     */
    Optional<Throwable> getFailure();
}
