package com.fangzhi.service.impl;

import java.util.List;

import com.fangzhi.domain.FzFile;
import com.fangzhi.domain.repository.FzFileRepository;
import com.fangzhi.service.FzFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FzFileServiceImpl implements FzFileService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private FzFileRepository fzFileRepository;


	@Override
	public List findAll() {
		return fzFileRepository.findAll();
	}

	@Override
	public FzFile findById(Long id) {
		return fzFileRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
        fzFileRepository.deleteById(id);
        return;
	}

	@Override
	public void save(FzFile fzFile) {
        fzFileRepository.save(fzFile);
        return;
	}


}