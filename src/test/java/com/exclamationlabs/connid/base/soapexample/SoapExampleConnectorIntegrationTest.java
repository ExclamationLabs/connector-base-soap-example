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

import com.exclamationlabs.connid.base.connector.configuration.ConfigurationNameBuilder;
import com.exclamationlabs.connid.base.connector.test.IntegrationTest;
import com.exclamationlabs.connid.base.connector.test.util.ConnectorTestUtils;
import com.exclamationlabs.connid.base.soapexample.configuration.SoapExampleConfiguration;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
import org.identityconnectors.framework.common.objects.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SoapExampleConnectorIntegrationTest extends IntegrationTest {

    private static SoapExampleConnector connector;

    private static final String KNOWN_BANK_ID = "82055000";
    private static final String KNOWN_BANK_NAME = "HELADEF1KYF";

    @Override
    public String getConfigurationName() {
        return new ConfigurationNameBuilder().withConnector(() -> "SOAPEXAMPLE").build();
    }

    @BeforeClass
    public static void setup() {
        connector = new SoapExampleConnector();
        SoapExampleConfiguration configuration = new SoapExampleConfiguration();
        configuration.setTestConfiguration();
        connector.init(configuration);
    }

    @AfterClass
    public static void teardown() {
        connector.dispose();
    }

    @Test
    public void testConnection() {
        connector.test();
    }


    @Test
    public void test100BankGet() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);

        connector.executeQuery(ObjectClass.ACCOUNT, KNOWN_BANK_ID, resultsHandler, new OperationOptionsBuilder().build());
        assertEquals(1, idValues.size());
        assertEquals(KNOWN_BANK_NAME, nameValues.get(0));
    }

    @Test(expected = ConnectorException.class)
    public void test102BankGetUnknown() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);
        connector.executeQuery(ObjectClass.ACCOUNT, "bad", resultsHandler, new OperationOptionsBuilder().build());
    }


}