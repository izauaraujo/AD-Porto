package com.secretaria.ADPorto.controller;

import com.secretaria.ADPorto.Repository.UserRepository;
import com.secretaria.ADPorto.dtos.Login;
import com.secretaria.ADPorto.dtos.Sessao;
import com.secretaria.ADPorto.entity.User;
import com.secretaria.ADPorto.security.JWTCreator;
import com.secretaria.ADPorto.security.JWTObject;
import com.secretaria.ADPorto.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    //@Autowired
    //private UserRepository repository;

    public LoginController() {
    }

    @PostMapping("/login")
    public Sessao login(@RequestBody Login login) throws ExecutionException, InterruptedException {
        User user = UserRepository.getUser(login.getUserName());//login.getUserName()
        if(user!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUserName());
            }

            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUserName());
            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
