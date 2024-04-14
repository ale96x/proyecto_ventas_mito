package com.mitocode.mitocodesales.service.impl;

import com.mitocode.mitocodesales.model.Role;
import com.mitocode.mitocodesales.repository.IRoleRepo;
import com.mitocode.mitocodesales.repository.IGenericRepo;
import com.mitocode.mitocodesales.service.IRoleService;
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
