package kurlyk.controllers;

import kurlyk.services.login.LoginService;
import kurlyk.transfer.LoginDto;
import kurlyk.transfer.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        TokenDto tokenDto = new TokenDto();
        try {
            tokenDto = loginService.login(loginDto);
        } catch (IllegalArgumentException e) {
            tokenDto.setValue("");
        }
        return ResponseEntity.ok(tokenDto);
    }
}
