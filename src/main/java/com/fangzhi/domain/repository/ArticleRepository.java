package com.fangzhi.domain.repository;

import com.fangzhi.domain.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,String>{
    
    Article findById(Long id);

    void deleteById(Long id);

}
