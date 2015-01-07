package telephony.ws.resource.contact.impl;

import org.restlet.ext.json.JsonRepresentation;
import telephony.core.service.dto.request.ContactDetailsRequest;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsDetailsResource;

public class ContactsDetailsResourceImpl
extends TelephonyServerResource
implements ContactsDetailsResource {

    @Override
    public JsonRepresentation details(ContactDetailsRequest req) {
        return null;
    }
}
