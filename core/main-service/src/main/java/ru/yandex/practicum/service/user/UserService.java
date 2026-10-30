package ru.yandex.practicum.service.user;

import ru.yandex.practicum.dto.user.NewUserRequest;
import ru.yandex.practicum.dto.user.UserDto;
import ru.yandex.practicum.dto.user.param_objects.AdminUserFilter;

import java.util.List;

public interface UserService {
    UserDto createUser(NewUserRequest newUserRequest);

    List<UserDto> getUsers(AdminUserFilter filter);

    void deleteUser(Long userId);
}