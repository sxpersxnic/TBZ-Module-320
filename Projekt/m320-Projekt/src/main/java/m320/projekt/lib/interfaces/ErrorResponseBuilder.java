package m320.projekt.lib.interfaces;

import org.springframework.web.ErrorResponse;
import java.util.List;
public interface ErrorResponseBuilder {
    ErrorResponse buildErrorResponse(Exception ex);
    ErrorResponse buildValidationErrorResponse(List<String> errors);
}
