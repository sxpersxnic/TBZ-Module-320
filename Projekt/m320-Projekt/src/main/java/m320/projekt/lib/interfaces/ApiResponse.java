package m320.projekt.lib.interfaces;

import java.time.LocalDateTime;

public interface ApiResponse<T> {
    int getStatus();
    String getMessage();
    T getData();
    LocalDateTime getTimestamp();
}
