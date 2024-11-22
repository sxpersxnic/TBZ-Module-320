package m320.projekt.lib.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import m320.projekt.lib.interfaces.ApiResponse;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class StandardApiResponse<T> implements ApiResponse<T> {
    private final int status;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;
}
