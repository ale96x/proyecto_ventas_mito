package com.mitocode.mitocodesales.service.impl;

import com.mitocode.mitocodesales.model.Product;
import com.mitocode.mitocodesales.repository.IProductRepo;
import com.mitocode.mitocodesales.repository.IGenericRepo;
import com.mitocode.mitocodesales.service.IProductService;
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
