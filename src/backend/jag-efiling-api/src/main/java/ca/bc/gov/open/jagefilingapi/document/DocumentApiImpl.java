package ca.bc.gov.open.jagefilingapi.document;

import ca.bc.gov.open.api.DocumentApi;
import ca.bc.gov.open.api.model.GenerateUrlRequest;
import ca.bc.gov.open.api.model.GenerateUrlResponse;
import ca.bc.gov.open.jagefilingapi.cache.RedisStorageService;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@RestController
public class DocumentApiImpl implements DocumentApi {

    Logger logger = LoggerFactory.getLogger(DocumentApiImpl.class);

    @Autowired
    RedisStorageService redisStorageService;

    @Override
    public ResponseEntity<GenerateUrlResponse> generateUrl(@Valid GenerateUrlRequest generateUrlRequest) {
        logger.info("Generate Url Request Recieved");
        //TODO: We should a service
        GenerateUrlResponse response = new GenerateUrlResponse();
        response.setStorageId(redisStorageService.put(SerializationUtils.serialize(generateUrlRequest.toString())));
        response.expiryDate(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10));
        response.setEFilingUrl("https://httpbin.org/");

        logger.debug("{}", response.toString());

        return ResponseEntity.ok(response);

    }
}
