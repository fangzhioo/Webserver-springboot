package com.fangzhi.service;

import java.util.List;

import com.fangzhi.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService{

    List findAll();
    
    User findByUsername(String username);

    User findUser(Long id);

    void save(User user);

    void deleteUserById(Long id);

    User findByUsernameAndPassword(String username,String pass);

    /**
     * 分页获取
     * 
     */
    Page<User> findByPage(Pageable pageable);
}