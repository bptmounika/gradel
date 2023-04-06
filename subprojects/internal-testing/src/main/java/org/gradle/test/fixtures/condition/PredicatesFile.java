/*
 * Copyright 2023 the original author or authors.
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

package org.gradle.test.fixtures.condition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredicatesFile {

    public static final String PREDICATE_PACKAGE_PREFIX = "org.gradle.test.preconditions.";

    /**
     * Streams a quasi-csv file containing comma-separated values.
     * <p>
     * Note, that for the sake of conciseness, we do not require in the file the whole package name.
     * All class names will be prefixed with {@link #PREDICATE_PACKAGE_PREFIX} when reading them up.
     * <p>
     * The csv is sudo in the sense, that you can comment by using "#"
     *
     * @param resource the resource file being used
     * @return a list of string arrays representing the values in the file
     */
    public static Stream<List<String>> streamCombinationsFile(String resource) {
        Stream<String> lineStream = new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(
                    PredicatesFile.class.getResourceAsStream(resource),
                    String.format("Predicate combination list resource '%s' cannot be found", resource)
                ),
                StandardCharsets.UTF_8
            )
        ).lines();

        return lineStream
            // If a line starts with #, it's a comment
            .filter(line -> !line.startsWith("#"))
            // We are not interested in whitespaces on the line level
            .map(String::trim)
            // If the line was empty, or only contained space (which was cut by the trim before), we skip the line
            .filter(line -> !line.isEmpty())
            // We separate the values
            .map(line -> line.split(","))
            // For each array of entries...
            .map(entries -> Arrays
                // We stream the entries
                .stream(entries)
                // Trim all unnecessary whitespaces off
                .map(String::trim)
                // Prefix the package with the implicit name of the predicates
                .map(name -> PREDICATE_PACKAGE_PREFIX + name)
                .collect(Collectors.toList())
            );
    }

}
