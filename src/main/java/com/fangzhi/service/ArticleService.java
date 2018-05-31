package com.fangzhi.service;

import java.util.List;

import com.fangzhi.domain.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService{
    
    List findAll();

    Article findById(Long id);

    void save(Article article);

    void deleteById(Long id);

    Page<Article> findByPage(Pageable pageable);
}