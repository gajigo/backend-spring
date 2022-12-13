package br.com.uniamerica.gajigo.controller;

import br.com.uniamerica.gajigo.entity.AppUser;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RepositoryRestController
@CrossOrigin
public class UserController {
    @RequestMapping(method = GET, value = "/api/users/me")
    public ResponseEntity<User> getCurrentUser() {
        UsernamePasswordAuthenticationToken currentAuth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok().body(
                (User) currentAuth.getPrincipal()
        );
    }
}
