package kurlyk.transfer;

import kurlyk.model.Role;
import kurlyk.model.Token;
import kurlyk.model.Usver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String value;

    private Long usverId;
    private String usverLogin;
    private List<Role> usverRoles;

    private String usverFirstName;
    private String usverMiddleName;
    private String usverSecondName;
    private String usverStudyGroup;

    public Usver toUsver(){
        return Usver
                .builder()
                .id(usverId)
                .login(usverLogin)
                .roles(usverRoles)
                .firstName(usverFirstName)
                .middleName(usverMiddleName)
                .secondName(usverSecondName)
                .studyGroup(usverStudyGroup)
                .build();
    }

    public static TokenDto fromToken(Token token){
        return TokenDto
                .builder()
                .value(token.getValue())
                .usverId(token.getUsver().getId())
                .usverLogin(token.getUsver().getLogin())
                .usverRoles(token.getUsver().getRoles())
                .usverFirstName(token.getUsver().getFirstName())
                .usverMiddleName(token.getUsver().getMiddleName())
                .usverSecondName(token.getUsver().getSecondName())
                .usverStudyGroup(token.getUsver().getStudyGroup())
                .build();
    }
}
