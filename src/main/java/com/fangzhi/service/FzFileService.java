package com.fangzhi.service;

import java.util.List;

import com.fangzhi.domain.FzFile;

public interface FzFileService{

    List findAll();

    FzFile findById(Long id);

    void deleteById(Long id);
    
    void save(FzFile fzFile);
}