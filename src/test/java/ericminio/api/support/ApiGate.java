package ericminio.api.support;

import ericminio.Gate;
import ericminio.domain.Customer;
import ericminio.domain.StorageFacade;
import ericminio.http.mapping.CustomerToJson;
import ericminio.http.mapping.JsonToCustomer;

import static ericminio.api.support.GetRequest.get;
import static ericminio.api.support.PostRequest.post;
import static java.lang.String.format;

public class ApiGate implements Gate {
    private int port;
    private StorageFacade storageFacade;

    public ApiGate(int port, StorageFacade storageFacade) {
        this.port = port;
        this.storageFacade = storageFacade;
    }

    @Override
    public void save(Customer customer) {
        try {
            String body = new CustomerToJson().please(customer);
            post(format("http://localhost:%d/customers/save", port), body);
        } catch (Exception raised) {
            throw new RuntimeException(raised);
        }
    }

    @Override
    public Customer find(String name) {
        try {
            HttpResponse httpResponse = get(format("http://localhost:%d/customers/find?name=%s", port, name));
            String json = httpResponse.getBody();
            Customer customer = new JsonToCustomer().please(json);
            customer.setStorageFacade(storageFacade);
            return customer;
        } catch (Exception raised) {
            throw new RuntimeException(raised);
        }
    }

    @Override
    public void acceptChoice(Customer customer, String label) {
        customer.chooses(label);
    }
}
