package com.mitocode.mitocodesales.repository;

import com.mitocode.mitocodesales.model.Category;
import com.mitocode.mitocodesales.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepo extends IGenericRepo<Client,Integer> { }
