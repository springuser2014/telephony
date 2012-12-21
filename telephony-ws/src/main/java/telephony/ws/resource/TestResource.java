package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Sample test resource that supports both JSON and XML representations of its
 * own data.
 */
public class TestResource extends ServerResource {

    /**
     * Map that stores the state of the resource.
     */
    private Map<Object, Object> map;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        this.map = new HashMap<Object, Object>();
        map.put("key1", "value1");
        map.put("key2", "value2");
    }

    /**
     * Returns the resource's representation with the JSON format.
     *
     * @return The resource's representation with the JSON format.
     * @throws ResourceException
     */
    @Get("json")
    public Representation toJson() throws ResourceException {
        return new JsonRepresentation(map);
    }

    /**
     * Returns the resource's representation with the XML format.
     *
     * @return The resource's representation with the XML format.
     * @throws ResourceException
     */
    @Get("xml")
    public Representation toXml() throws ResourceException {
        try {
            DomRepresentation rep = new DomRepresentation();
            Document doc = rep.getDocument();

            Element root = doc.createElement("main");
            doc.appendChild(root);
            for (Entry<Object, Object> entry : this.map.entrySet()) {
                Element element = doc.createElement((String) entry.getKey());
                element.appendChild(doc.createTextNode((String) entry
                        .getValue()));
                root.appendChild(element);
            }

            return rep;
        } catch (IOException ioe) {
            throw new ResourceException(ioe);
        }
    }
}