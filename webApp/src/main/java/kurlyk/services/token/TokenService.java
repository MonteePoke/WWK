package kurlyk.services.token;

import kurlyk.model.Token;


public interface TokenService {
    void saveAndDeleteOld(Token token);
}
