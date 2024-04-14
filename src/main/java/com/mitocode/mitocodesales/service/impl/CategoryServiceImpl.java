package com.mitocode.mitocodesales.service.impl;

import com.mitocode.mitocodesales.model.Category;
import com.mitocode.mitocodesales.repository.ICategoryRepo;
import com.mitocode.mitocodesales.repository.IGenericRepo;
import com.mitocode.mitocodesales.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    @Autowired
    private ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return this.repo;
    }

    @Override
    public List<Category> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Category> findByNameLike(String name) {
        return repo.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Category> findByNameContains(String name) {
        return repo.findByNameContains(name);
    }

    @Override
    public List<Category> findByNameStartsWith(String name) {
        return repo.findByNameStartsWith(name);
    }

    @Override
    public List<Category> findByNameEndsWith(String name) {
        return repo.findByNameEndsWith(name);
    }

    @Override
    public List<Category> findByNameAndEnabled(String name, boolean enabled) {
        return repo.findByNameAndEnabled(name, enabled);
    }

    @Override
    public List<Category> findByNameOrEnabled(String name, boolean enabled) {
        return repo.findByNameOrEnabled(name, enabled);
    }

    @Override
    public List<Category> getByNameAndDescription3() {
        return repo.getByNameAndDescription3();
    }

    //Se debe obtener el Pageable desde el controller
    @Override
    public Page<Category> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Category> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction, "name"));
    }
}
