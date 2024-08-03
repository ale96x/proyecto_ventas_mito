package com.mitocode.service.impl;

import com.mitocode.model.Client;
import com.mitocode.repository.IClientRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    @Autowired
    private IClientRepo repo;

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return this.repo;
    }
}
