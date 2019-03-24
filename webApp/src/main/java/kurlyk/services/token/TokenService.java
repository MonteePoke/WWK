package kurlyk.services.token;

import kurlyk.models.Token;


public interface TokenService {
    void saveAndDeleteOld(Token token);
}
