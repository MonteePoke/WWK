package kurlyk.services.role;

import kurlyk.model.Role;
import java.util.Optional;

public interface RoleService {


    Optional<Role> findByName(String roleName);
}
