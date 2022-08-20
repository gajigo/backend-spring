package br.com.uniamerica.gajigo.events;

import br.com.uniamerica.gajigo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.logging.Logger;

@RepositoryEventHandler
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UserEventHandler {

    private final PasswordEncoder passwordEncoder;

    @HandleBeforeSave
    public void handleUserBeforeSave(User user) {
        this.beforeSaveUser(user);
    }

    @HandleBeforeCreate
    public void handleUserBeforeCreate(User user) {
        this.beforeSaveUser(user);
    }

    private void beforeSaveUser(User user) {
        if (user.getPassword() != null) {
            user.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
        }
    }
}
