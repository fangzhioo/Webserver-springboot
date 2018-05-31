package com.fangzhi.service.impl;

import java.util.List;

import com.fangzhi.domain.Article;
import com.fangzhi.domain.repository.ArticleRepository;
import com.fangzhi.service.ArticleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

	@Override
	public List findAll() {
		return articleRepository.findAll();
	}

	@Override
	public Article findById(Long id) {
		return articleRepository.findById(id);
	}

	@Override
	public void save(Article article) {
        articleRepository.save(article);
        return;
	}

	@Override
	public void deleteById(Long id) {
        articleRepository.deleteById(id);
        return;
	}

	@Override
	public Page<Article> findByPage(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}

}