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

package com.exclamationlabs.connid.base.soapexample.adapter;

import com.exclamationlabs.connid.base.connector.adapter.AdapterValueTypeConverter;
import com.exclamationlabs.connid.base.connector.adapter.BaseAdapter;
import com.exclamationlabs.connid.base.connector.attribute.ConnectorAttribute;
import com.exclamationlabs.connid.base.soapexample.model.SoapExampleBank;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.ObjectClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.exclamationlabs.connid.base.connector.attribute.ConnectorAttributeDataType.STRING;
import static com.exclamationlabs.connid.base.soapexample.attribute.SoapExampleBankAttribute.*;
import static org.identityconnectors.framework.common.objects.AttributeInfo.Flags.NOT_UPDATEABLE;

public class SoapExampleBanksAdapter extends BaseAdapter<SoapExampleBank> {

    @Override
    public ObjectClass getType() {
        return ObjectClass.ACCOUNT;
    }

    @Override
    public Class<SoapExampleBank> getIdentityModelClass() {
        return SoapExampleBank.class;
    }

    @Override
    public List<ConnectorAttribute> getConnectorAttributes() {
        List<ConnectorAttribute> result = new ArrayList<>();
        result.add(new ConnectorAttribute(BANK_ID.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(BANK_NAME.name(), STRING, NOT_UPDATEABLE));
        return result;
    }

    @Override
    protected SoapExampleBank constructModel(Set<Attribute> attributes, boolean creation) {
        SoapExampleBank bank = new SoapExampleBank();
        bank.setId(AdapterValueTypeConverter.getIdentityIdAttributeValue(attributes));
        bank.setName(AdapterValueTypeConverter.getIdentityNameAttributeValue(attributes));
        return bank;
    }

    @Override
    protected List<Attribute> constructAttributes(SoapExampleBank user) {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(AttributeBuilder.build(BANK_ID.name(), user.getId()));
        attributes.add(AttributeBuilder.build(BANK_NAME.name(), user.getName()));
        return attributes;
    }
}
