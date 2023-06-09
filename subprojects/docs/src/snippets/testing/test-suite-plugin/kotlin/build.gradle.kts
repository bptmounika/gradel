/*
 * Copyright 2021 the original author or authors.
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

// tag::apply-jvm-test-suite[]
plugins {
    java
    `jvm-test-suite`
}
// end::apply-jvm-test-suite[]

version = "1.0.2"
group = "org.gradle.sample"

repositories {
    mavenCentral()
}

// tag::configure-testing-extension[]
testing {
    suites { // <1>
        val test by getting(JvmTestSuite::class) { // <2>
            useJUnitJupiter() // <3>
        }

        register<JvmTestSuite>("integrationTest") { // <4>
            dependencies {
                implementation(project()) // <5>
            }

            targets { // <6>
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}

tasks.named("check") { // <7>
    dependsOn(testing.suites.named("integrationTest"))
}
// end::configure-testing-extension[]
