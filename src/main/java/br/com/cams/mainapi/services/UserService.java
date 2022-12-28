package br.com.cams.mainapi.services;

import br.com.cams.mainapi.domain.User;
import br.com.cams.mainapi.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO dto);
    User update(UserDTO dto);
    void delete(Integer id);
}
