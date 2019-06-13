package kurlyk.common;

import kurlyk.model.Usver;
import kurlyk.transfer.TokenDto;

public class RoleTester {

    public static boolean roleIsExist(Usver usver, String testRoleName){
        return Utils.nameIsExist(Utils.listToListCaster(usver.getRoles()), testRoleName);
    }

    public static boolean roleIsExist(TokenDto tokenDto, String testRoleName){
        return Utils.nameIsExist(Utils.listToListCaster(tokenDto.getUsverRoles()), testRoleName);
    }
}
