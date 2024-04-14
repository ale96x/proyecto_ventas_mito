package com.mitocode.mitocodesales.service.impl;

import com.mitocode.mitocodesales.model.Client;
import com.mitocode.mitocodesales.repository.IClientRepo;
import com.mitocode.mitocodesales.repository.IGenericRepo;
import com.mitocode.mitocodesales.service.IClientService;
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
