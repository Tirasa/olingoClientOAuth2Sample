/*
 * Copyright (C) 2014 Tirasa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tirasa.olingooauth2;

import java.net.URI;
import java.util.Properties;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.v4.ODataClient;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.domain.v4.ODataEntity;
import org.apache.olingo.commons.api.domain.v4.ODataEntitySet;

public class Main {

    public static void main(final String[] args) throws Exception {
        final Properties oauth2Properties = new Properties();
        oauth2Properties.load(Main.class.getResourceAsStream("/oauth2.properties"));

        final AzureADOAuth2HttpClientFactory oauth2HCF = new AzureADOAuth2HttpClientFactory(
                oauth2Properties.getProperty("oauth2.authority"),
                oauth2Properties.getProperty("oauth2.clientId"),
                oauth2Properties.getProperty("oauth2.redirectURI"),
                oauth2Properties.getProperty("oauth2.resourceURI"),
                new UsernamePasswordCredentials(
                        oauth2Properties.getProperty("oauth2.username"),
                        oauth2Properties.getProperty("oauth2.password")));

        final ODataClient client = ODataClientFactory.getEdmEnabledV4("https://outlook.office365.com/ews/odata");
        client.getConfiguration().setHttpClientFactory(oauth2HCF);

        final ODataEntitySetRequest<ODataEntitySet> messages =
                client.getRetrieveRequestFactory().getEntitySetRequest(
                        URI.create("https://outlook.office365.com/ews/odata/Me/Folders('Inbox')/Messages"));
        for (ODataEntity message : messages.execute().getBody().getEntities()) {
            System.out.println("Message: " + message.getId());
        }
    }
}
