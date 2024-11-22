package m320.projekt.lib.api;

import m320.projekt.lib.interfaces.ApiLogger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
@Component
public class ApiLoggerImpl implements ApiLogger {

    private static final Logger log = LoggerFactory.getLogger(ApiLoggerImpl.class);

    @Override
    public void logRequest(String method, String endpoint, Object request) {
        log.info("Request at {} using method {}, provided body: {}", endpoint, method, request);
    }

    @Override
    public void logResponse(String method, String endpoint, Object response) {
        log.info("Response at {} using method {} provided body: {}", endpoint, method, response);
    }

    @Override
    public void logError(String method, String endpoint, Object error) {
        log.error("Error in {} {}: {}", method, endpoint, error);
    }
}
