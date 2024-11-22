package m320.projekt.controller;

import jakarta.persistence.EntityNotFoundException;
import m320.projekt.lib.enums.Method;
import m320.projekt.lib.interfaces.ApiLogger;
import m320.projekt.model.User;
import m320.projekt.payload.dto.request.UserReqDTO;
import m320.projekt.payload.dto.response.UserResDTO;
import m320.projekt.payload.mapper.UserMapper;
import m320.projekt.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static m320.projekt.lib.constants.Controller.*;

@RestController
@RequestMapping(USER_PATH)
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final ApiLogger apiLogger;

    public UserController(UserService userService, UserMapper userMapper, ApiLogger apiLogger) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.apiLogger = apiLogger;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll();
        List<UserResDTO> resDTOs = users.stream().map(userMapper::toDTO).toList();
        apiLogger.logResponse(Method.GET.getMethod(), USER_PATH, resDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(resDTOs);
    }

    @GetMapping(USER_GET_PATH)
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            User user = userService.findById(id);
            UserResDTO resDTO = userMapper.toDTO(user);
            apiLogger.logResponse(Method.GET.getMethod(), USER_GET_PATH, resDTO);
            return ResponseEntity.status(HttpStatus.OK).body(resDTO);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping(USER_PATCH_PATH)
    public ResponseEntity<?> patch(
            @RequestBody UserReqDTO reqDTO,
            @PathVariable Integer id
    ) {
        try {
            User patchUser = userMapper.fromDTO(reqDTO);
            User savedUser = userService.update(patchUser, id);
            UserResDTO resDTO = userMapper.toDTO(savedUser);
            apiLogger.logResponse(Method.PATCH.getMethod(), USER_PATCH_PATH, resDTO);
            return ResponseEntity.status(HttpStatus.OK).body(resDTO);
        } catch (EntityNotFoundException e) {
            apiLogger.logError(Method.PATCH.getMethod(), USER_PATCH_PATH, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DataIntegrityViolationException e) {
            apiLogger.logError(Method.PATCH.getMethod(), USER_PATCH_PATH, e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(USER_DELETE_PATH)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            userService.delete(id);
            apiLogger.logResponse(Method.DELETE.getMethod(), USER_DELETE_PATH, null);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
