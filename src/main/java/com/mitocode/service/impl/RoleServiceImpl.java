package com.mitocode.service.impl;

import com.mitocode.model.Role;
import com.mitocode.repository.IRoleRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

    @Autowired
    private IRoleRepo repo;

    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return this.repo;
    }
}
