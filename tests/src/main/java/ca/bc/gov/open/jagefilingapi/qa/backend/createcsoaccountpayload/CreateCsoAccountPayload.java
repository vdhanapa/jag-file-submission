package ca.bc.gov.open.jagefilingapi.qa.backend.createcsoaccountpayload;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CreateCsoAccountPayload {

    public String createCsoAccountPayload() throws IOException {
        CsoPayload csoPayload = new CsoPayload("Bob ", "Alan ", "Ross", "bob.ross@example.com");

        ObjectMapper csoAccountPayload = new ObjectMapper();
        return csoAccountPayload.writeValueAsString(csoPayload);
    }

    public String updateCsoAccountInternalClientNumber() throws IOException {
        CsoPayload updateClientNumber = new CsoPayload("23423");

        ObjectMapper updateCsoAccountPayload = new ObjectMapper();
        return updateCsoAccountPayload.writeValueAsString(updateClientNumber);
    }
}
