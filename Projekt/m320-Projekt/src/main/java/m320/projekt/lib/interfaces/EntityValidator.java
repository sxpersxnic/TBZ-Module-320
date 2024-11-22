package m320.projekt.lib.interfaces;

import jakarta.xml.bind.ValidationException;

public interface EntityValidator<T, ID> {
    void validateCreate(T entity);
    void validateUpdate(ID id, T entity);
    void validateMerge(T existing, T changing);

    default void throwIfInvalid(boolean condition, String message) throws ValidationException {
        if (condition) {
            throw new ValidationException(message);
        }
    }
}
