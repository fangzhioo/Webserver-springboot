package com.fangzhi.domain.repository;

import javax.transaction.Transactional;

import com.fangzhi.domain.FzFile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FzFileRepository extends JpaRepository<FzFile,String>{

    FzFile findById(Long id);

    @Transactional
    void deleteById(Long id);
}