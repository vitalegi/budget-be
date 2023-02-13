package it.vitalegi.budget.resource;

import io.swagger.v3.oas.annotations.Operation;
import it.vitalegi.budget.metrics.Performance;
import it.vitalegi.budget.metrics.Type;
import it.vitalegi.budget.user.dto.User;
import it.vitalegi.budget.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Performance(Type.ENDPOINT)
public class UserResource {

    @Autowired
    UserService userService;

    @Operation(summary = "Retrieve user data")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser() {
        userService.importCurrentUser();
        return userService.getCurrentUser();
    }

    @Operation(summary = "Change username")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUsername(@RequestBody User user) {
        return userService.updateUsername(user.getUsername());
    }
}
