/*
 * Copyright 2011 the original author or authors.
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
package org.gradle.util;

import groovy.lang.Closure;
import org.gradle.util.internal.AlwaysTrue;
import org.spockframework.runtime.extension.ExtensionAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@ExtensionAnnotation(TestPreconditionExtension.class)
public @interface Requires {
    TestPrecondition[] value() default {TestPrecondition.NULL_REQUIREMENT};

    /**
     * @deprecated Do not introduce new adhoc logic.
     * Consider avoiding it entirely or adding a new TestPrecondition enum value instead.
     * It is hard to ensure a test with this annotation would be run at CI at least once.
     * See <a href="https://github.com/gradle/gradle-private/issues/3616">issue</a> for more details.
     */
    @Deprecated
    Class<? extends Closure<?>> adhoc() default AlwaysTrue.class;
}
