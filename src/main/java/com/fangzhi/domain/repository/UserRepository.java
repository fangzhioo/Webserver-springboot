package com.fangzhi.domain.repository;

import javax.transaction.Transactional;

import com.fangzhi.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
    
    User findByUsername(String username);

    @Query("select user from User user where user.id=:id")
    User findUser(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("delete from User user where user.id=:userid")
    void deleteUserById(@Param("userid") long userid);

	User findByUsernameAndPassword(String username, String pass);

}