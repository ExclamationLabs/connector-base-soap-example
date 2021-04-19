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

package com.exclamationlabs.connid.base.soapexample.driver.soap;

import com.exclamationlabs.connid.base.connector.configuration.ConnectorProperty;
import com.exclamationlabs.connid.base.connector.driver.soap.BaseSoapDriver;
import com.exclamationlabs.connid.base.connector.driver.soap.DefaultSoapFaultProcessor;
import com.exclamationlabs.connid.base.connector.driver.soap.SoapFaultProcessor;
import com.exclamationlabs.connid.base.soapexample.generated.BLZService;
import com.exclamationlabs.connid.base.soapexample.generated.BLZServicePortType;
import com.exclamationlabs.connid.base.soapexample.generated.GetBankResponseType;
import com.exclamationlabs.connid.base.soapexample.generated.GetBankType;
import com.exclamationlabs.connid.base.soapexample.model.SoapExampleBank;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

import java.util.Set;

public class SoapExampleDriver
        extends BaseSoapDriver<BLZService, BLZServicePortType> {

    public SoapExampleDriver() {
        addInvocator(SoapExampleBank.class, new SoapExampleBankInvocator());
    }

    @Override
    protected BLZService getService() {
        return new BLZService();
    }

    @Override
    protected BLZServicePortType getServicePort(BLZService service) {
        return service.getBLZServiceSOAP11PortHttp();
    }


    protected GetBankResponseType getBank(GetBankType bankInput) throws ConnectorException {
        return (GetBankResponseType) executeAndHandleFault(
                (serviceInput) -> getServicePort(getService()).getBank(bankInput));
    }

    @Override
    protected SoapFaultProcessor getFaultProcessor() {
        return new DefaultSoapFaultProcessor();
    }

    @Override
    public Set<ConnectorProperty> getRequiredPropertyNames() {
        return null;
    }

    @Override
    public void test() throws ConnectorException {

    }

    @Override
    public void close() {

    }
}
