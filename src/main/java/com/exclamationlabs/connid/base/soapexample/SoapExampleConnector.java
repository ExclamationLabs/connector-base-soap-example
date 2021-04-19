/*
    Copyright 2020 Exclamation Labs
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.exclamationlabs.connid.base.soapexample;

import com.exclamationlabs.connid.base.connector.BaseFullAccessConnector;
import com.exclamationlabs.connid.base.connector.authenticator.Authenticator;
import com.exclamationlabs.connid.base.connector.configuration.ConnectorConfiguration;
import com.exclamationlabs.connid.base.connector.configuration.ConnectorProperty;
import com.exclamationlabs.connid.base.soapexample.adapter.SoapExampleBanksAdapter;
import com.exclamationlabs.connid.base.soapexample.configuration.SoapExampleConfiguration;
import com.exclamationlabs.connid.base.soapexample.driver.soap.SoapExampleDriver;
import org.identityconnectors.framework.common.exceptions.ConnectorSecurityException;
import org.identityconnectors.framework.spi.ConnectorClass;

import java.util.Set;

@ConnectorClass(displayNameKey = "h2example.connector.display", configurationClass = SoapExampleConfiguration.class)
public class SoapExampleConnector extends BaseFullAccessConnector {

    public SoapExampleConnector() {

        setAuthenticator(new Authenticator() {
            @Override
            public Set<ConnectorProperty> getRequiredPropertyNames() {
                return null;
            }

            @Override
            public String authenticate(ConnectorConfiguration connectorConfiguration) throws ConnectorSecurityException {
                return "good";
            }
        });
        setDriver(new SoapExampleDriver());
        setAdapters(new SoapExampleBanksAdapter());
    }

}
