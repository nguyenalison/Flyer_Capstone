package org.alisonnguyen.flyercapstone.service;

import org.alisonnguyen.flyercapstone.model.Role;

import java.util.List;
public interface RoleService {
    public void saveRole(Role role);
    public Role findRoleByRoleName(String name);
    public List<Role> getAllRoles();
    public List<Role> getRolesByUser(long id);
}