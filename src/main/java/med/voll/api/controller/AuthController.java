package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.DataAuthRecord;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.DataTokenJwt;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity AcceptLogin(@RequestBody @Valid DataAuthRecord data){
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = manager.authenticate(authToken);
        var tokenJwt = tokenService.createToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new DataTokenJwt(tokenJwt));
    }

}
