/*
 * Copyright (c) 2016 Deloitte MCS Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.deloittedigital.dropwizard.testsupport;

import io.dropwizard.testing.ConfigOverride;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Basic ConfigOverrides for Dropwizard testing to make it easier to run integrated tests when we don't care about
 * the port number used.
 *
 * Use with the DropwizardAppRule as follows:
 * <code>
 *     {@literal @}ClassRule
 *     public static final DropwizardAppRule<TestConfiguration> APP = new DropwizardAppRule<>(TestApp.class,
 *             ResourceHelpers.resourceFilePath("integration-test-config.yml"),
 *             ConfigOverrides.randomLocalPort(),
 *             ConfigOverrides.randomAdminPort());
 * </code>
 */
public abstract class ConfigOverrides {

    public static final int MIN_PORT = 8000;
    public static final int MAX_PORT = 10000;

    public static ConfigOverride randomLocalPort() {
        return ConfigOverride.config("server.applicationConnectors[0].port", findFreePort(0));
    }

    public static ConfigOverride randomAdminPort() {
        return ConfigOverride.config("server.adminConnectors[0].port", findFreePort(2000));
    }

    private static String findFreePort(int offset) {
        for (int port = MIN_PORT + offset; port < MAX_PORT + offset; port++) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.close();
                return String.valueOf(port);
            } catch (IOException ignored) {
            }
        }
        throw new IllegalStateException("Unable to find a free port for testing in the range " + MIN_PORT + " to " + MAX_PORT);
    }
}
