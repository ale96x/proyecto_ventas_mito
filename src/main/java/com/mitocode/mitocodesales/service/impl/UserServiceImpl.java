package com.mitocode.mitocodesales.service.impl;

import com.mitocode.mitocodesales.model.User;
import com.mitocode.mitocodesales.repository.IUserRepo;
import com.mitocode.mitocodesales.repository.IGenericRepo;
import com.mitocode.mitocodesales.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    @Autowired
    private IUserRepo repo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return this.repo;
    }
}
