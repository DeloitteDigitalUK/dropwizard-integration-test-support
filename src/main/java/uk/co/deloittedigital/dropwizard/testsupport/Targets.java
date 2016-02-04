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

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.apache.http.client.utils.URIBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import java.net.URISyntaxException;

/**
 * Simple Jersey Client Invocation.Builder provider for apps instantiated with Dropwizard Testing's DropwizardAppRule.
 */
public abstract class Targets {

    public static Invocation.Builder localTarget(Client client, DropwizardAppRule<?> app, String path) throws URISyntaxException {
        return client.target(new URIBuilder("http://localhost").setPort(app.getLocalPort()).setPath(path).build()).request();
    }

    public static Invocation.Builder adminTarget(Client client, DropwizardAppRule<?> app, String path) throws URISyntaxException {
        return client.target(new URIBuilder("http://localhost").setPort(app.getAdminPort()).setPath(path).build()).request();
    }
}
