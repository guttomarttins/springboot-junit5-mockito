package br.com.cams.mainapi.services.impl;

import br.com.cams.mainapi.domain.User;
import br.com.cams.mainapi.domain.dto.UserDTO;
import br.com.cams.mainapi.repositories.UserRepository;
import br.com.cams.mainapi.services.UserService;
import br.com.cams.mainapi.services.exceptions.DataIntegrityViolationException;
import br.com.cams.mainapi.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository repository;
    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO dto) {
        findByEmail(dto);
        return repository.save(mapper.map(dto, User.class));
    }

    @Override
    public User update(UserDTO dto) {
        findByEmail(dto);
        return repository.save(mapper.map(dto, User.class));
    }
    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
    private void findByEmail(UserDTO dto){
        Optional<User> user = repository.findByEmail(dto.getEmail());
        if(user.isPresent() && !user.get().getId().equals(dto.getId())){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
