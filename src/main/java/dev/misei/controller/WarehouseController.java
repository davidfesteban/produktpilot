package dev.misei.controller;

import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.payload.UserPayload;
import dev.misei.repository.OrganizationRepository;
import dev.misei.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.misei.domain.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping("/api/private/user")
public class WarehouseController extends BaseCrudController {

    //private final WarehouseProcessor warehouseProcessor;

    public WarehouseController(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, OrganizationRepository organizationRepository) {
        super(jwtTokenProvider, userRepository, organizationRepository);
    }
/*
    @PostMapping("/create")
    public ResponseEntity<UserPayload> create(@RequestBody UserPayload userPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> INSTANCE.toPayload(userProcessor.createUser(INSTANCE.toEntity(userPayload), user)), tokenRequest);
    }

    @PostMapping("/modify")
    public ResponseEntity<UserPayload> modify(@RequestBody UserPayload userPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> INSTANCE.toPayload(userProcessor.modifyUser(INSTANCE.toEntity(userPayload), user)), tokenRequest);
    }

    @GetMapping("/delete")
    public ResponseEntity<UserPayload> delete(String userName, @RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> INSTANCE.toPayload(userProcessor.deleteUser(userName, user)), tokenRequest);
    }
*/
}
