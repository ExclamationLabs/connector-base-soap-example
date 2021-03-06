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

package com.exclamationlabs.connid.base.soapexample.configuration;

import com.exclamationlabs.connid.base.connector.configuration.BaseConnectorConfiguration;
import com.exclamationlabs.connid.base.connector.configuration.ConnectorProperty;
import org.identityconnectors.framework.spi.ConfigurationClass;
import org.identityconnectors.framework.spi.ConfigurationProperty;

import java.util.Properties;

@ConfigurationClass(skipUnsupported = true)
public class SoapExampleConfiguration extends BaseConnectorConfiguration {

    public SoapExampleConfiguration() {
        super();
    }

    public SoapExampleConfiguration(String configurationName) {
        super(configurationName);
    }

    @Override
    @ConfigurationProperty(
            displayMessageKey = "SOAP Configuration File Path",
            helpMessageKey = "File path for the SOAP Configuration File",
            required = true)
    public String getConfigurationFilePath() {
        return innerGetMidPointConfigurationFilePath();
    }
}
