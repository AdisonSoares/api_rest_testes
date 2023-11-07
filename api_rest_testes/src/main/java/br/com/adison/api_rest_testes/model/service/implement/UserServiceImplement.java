package br.com.adison.api_rest_testes.model.service.implement;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.service.UserService;
import br.com.adison.api_rest_testes.model.service.exceptions.ObjectNotFoundException;
import br.com.adison.api_rest_testes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> object = repository.findById(id);
        return object.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }
}
