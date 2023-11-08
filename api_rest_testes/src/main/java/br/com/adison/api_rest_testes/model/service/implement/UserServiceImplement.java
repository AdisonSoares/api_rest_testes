package br.com.adison.api_rest_testes.model.service.implement;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;
import br.com.adison.api_rest_testes.model.service.UserService;
import br.com.adison.api_rest_testes.model.service.exceptions.DataIntegratyViolationException;
import br.com.adison.api_rest_testes.model.service.exceptions.ObjectNotFoundException;
import br.com.adison.api_rest_testes.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> object = repository.findById(id);
        return object.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }

    @Override
    public Users create(UserDTO object) {
        findByEmail(object);
        return repository.save(mapper.map(object, Users.class));
    }

    @Override
    public Users update(UserDTO object) {
        return repository.save(mapper.map(object, Users.class));
    }

    private void findByEmail(UserDTO object){
        Optional<Users> users = repository.findByEmail(object.getEmail());
        if (users.isPresent()) throw new DataIntegratyViolationException("E-mail já cadastrado!");
    }
}
