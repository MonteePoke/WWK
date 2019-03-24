package kurlyk.communication;

import kurlyk.transfer.TokenDto;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserInfo {
    private TokenDto tokenDto;
}
