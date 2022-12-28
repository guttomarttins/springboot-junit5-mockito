package br.com.cams.mainapi.config;

import br.com.cams.mainapi.domain.User;
import br.com.cams.mainapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        User u1 = new User(null, "Carlos 1", "carlos01@gmail.com", "123");
        User u2 = new User(null, "Carlos 2", "carlos02@gmail.com", "123");

        repository.saveAll(List.of(u1, u2));

    }
}
