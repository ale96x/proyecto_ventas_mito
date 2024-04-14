package com.mitocode.mitocodesales.repository;

import com.mitocode.mitocodesales.model.User;

public interface IUserRepo extends IGenericRepo<User,Integer> {
    User findOneByUsername(String username);
}
