package com.mitocode.service.impl;

import com.mitocode.model.Product;
import com.mitocode.repository.IProductRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

    @Autowired
    private IProductRepo repo;

    @Override
    protected IGenericRepo<Product, Integer> getRepo() {
        return this.repo;
    }
}
