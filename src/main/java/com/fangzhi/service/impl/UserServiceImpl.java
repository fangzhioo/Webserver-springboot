package com.fangzhi.service.impl;

import java.util.List;

import com.fangzhi.domain.User;
import com.fangzhi.domain.repository.UserRepository;
import com.fangzhi.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
        LOGGER.info("查询"+username);
		return userRepository.findByUsername(username);
	}
    
    @Override
    public User findUser(Long id){
        return userRepository.findUser(id);
    }
    
    @Override
    public List findAll(){
        return userRepository.findAll();
    }
    
    @Override
    public void save(User user){
        userRepository.save(user);
        return; 
    }

	@Override
	public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
        return;
	}

	@Override
	public User findByUsernameAndPassword(String username, String pass) {     
        return userRepository.findByUsernameAndPassword(username, pass);
	}

	@Override
	public Page<User> findByPage(Pageable pageable) {
        LOGGER.info("\n 分页查询用户：" + "PageNumber = " + pageable.getPageNumber() + "PageSize = " + pageable.getPageSize());
		return userRepository.findAll(pageable);
	}

}