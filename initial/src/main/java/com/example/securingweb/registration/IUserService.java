package com.example.securingweb;

public interface IUserService {
    User registerNewUserAccount(UserDto userDto)
            throws UserAlreadyExistException;
}