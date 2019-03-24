package kurlyk.services.login;


import kurlyk.transfer.LoginDto;
import kurlyk.transfer.TokenDto;

public interface LoginService {
    TokenDto login(LoginDto loginDto);
}
