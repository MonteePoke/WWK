package kurlyk.communication;

import kurlyk.transfer.TokenDto;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UsverInfo {
    private TokenDto tokenDto;
}
