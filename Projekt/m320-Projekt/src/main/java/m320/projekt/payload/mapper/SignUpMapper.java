package m320.projekt.payload.mapper;

import m320.projekt.model.User;
import m320.projekt.payload.dto.request.SignUpReqDTO;
import m320.projekt.payload.dto.response.SignUpResDTO;

/**
 * Static class. DTO Mapper for Sign Up process.
 * Has methods to map {@link SignUpReqDTO} to {@link User}
 * and {@link User} to {@link SignUpResDTO}
 * */
public class SignUpMapper {

    /**
     * Maps {@link SignUpReqDTO} to {@link User} object
     * @param dto Sign up Request to be mapped
     * @return {@link User} with attributes of given {@link SignUpReqDTO}
     * */
    public static User fromDTO(SignUpReqDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    /**
     * Maps {@link User} to {@link SignUpResDTO} object
     * @param user User object to be mapped
     * @return {@link SignUpResDTO} with attributes of given {@link User}
     * */
    public static SignUpResDTO toDTO(User user) {
        return new SignUpResDTO(user.getId(), user.getUsername(), user.getEmail());
    }
}
