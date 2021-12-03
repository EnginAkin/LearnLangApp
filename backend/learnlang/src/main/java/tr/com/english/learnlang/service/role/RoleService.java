package tr.com.english.learnlang.service.role;

import tr.com.english.learnlang.entity.role.Role;

public interface RoleService {

    Role saveRole(Role role);
    Role findRoleByName(String rolename);
}
