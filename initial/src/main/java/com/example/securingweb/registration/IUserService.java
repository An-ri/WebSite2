package com.example.securingweb.registration;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public interface IUserService {
    User registerNewUserAccount(UserDto userDto)
            throws UserAlreadyExistException;
}