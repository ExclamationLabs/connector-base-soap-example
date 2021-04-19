package com.exclamationlabs.connid.base.soapexample.driver.soap;

import com.exclamationlabs.connid.base.connector.driver.DriverInvocator;
import com.exclamationlabs.connid.base.soapexample.generated.BLZService;
import com.exclamationlabs.connid.base.soapexample.generated.DetailsType;
import com.exclamationlabs.connid.base.soapexample.generated.GetBankResponseType;
import com.exclamationlabs.connid.base.soapexample.generated.GetBankType;
import com.exclamationlabs.connid.base.soapexample.model.SoapExampleBank;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

import java.util.List;
import java.util.Map;

public class SoapExampleBankInvocator implements DriverInvocator<SoapExampleDriver, SoapExampleBank> {

    static BLZService service;

    public SoapExampleBankInvocator() {
        service = new BLZService();
    }

    @Override
    public String create(SoapExampleDriver driver, SoapExampleBank model) throws ConnectorException {
        return null;
    }

    @Override
    public void update(SoapExampleDriver driver, String userId, SoapExampleBank userModel) throws ConnectorException {

    }

    @Override
    public void delete(SoapExampleDriver driver, String userId) throws ConnectorException {

    }

    @Override
    public List<SoapExampleBank> getAll(SoapExampleDriver driver, Map<String,Object> pagingData) throws ConnectorException {
        return null;
    }

    @Override
    public SoapExampleBank getOne(SoapExampleDriver driver, String objectId, Map<String,Object> pagingData) throws ConnectorException {
        SoapExampleBank result = new SoapExampleBank();

        GetBankType requestType = new GetBankType();
        if (!"bad".equalsIgnoreCase(objectId)) {
            requestType.setBlz(objectId);
        }

        GetBankResponseType responseType = driver.getBank(requestType);
        DetailsType detailsType = responseType.getDetails();
        result.setId(objectId);
        result.setName(detailsType.getBic());
        return result;
    }
}
