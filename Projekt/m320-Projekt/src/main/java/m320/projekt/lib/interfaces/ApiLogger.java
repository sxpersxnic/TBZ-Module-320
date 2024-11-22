package m320.projekt.lib.interfaces;

import org.springframework.lang.Nullable;

public interface ApiLogger {
    void logRequest(String method, String endpoint, Object request);
    void logResponse(String method, String endpoint,@Nullable Object response);
    void logError(String method, String endpoint, Object error);
}
