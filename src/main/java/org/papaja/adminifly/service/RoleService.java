package org.papaja.adminifly.service;

import org.papaja.adminifly.dao.RoleDao;
import org.papaja.adminifly.entity.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao dao;

    public List<Role> getRoles() {
        return dao.getRoles();
    }

}
