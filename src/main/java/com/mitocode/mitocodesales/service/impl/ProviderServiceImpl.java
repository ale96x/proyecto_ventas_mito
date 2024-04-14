package com.mitocode.mitocodesales.service.impl;

import com.mitocode.mitocodesales.model.Provider;
import com.mitocode.mitocodesales.repository.IProviderRepo;
import com.mitocode.mitocodesales.repository.IGenericRepo;
import com.mitocode.mitocodesales.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {

    @Autowired
    private IProviderRepo repo;

    @Override
    protected IGenericRepo<Provider, Integer> getRepo() {
        return this.repo;
    }
}
