package com.oncha.oncha_web.config;

import com.oncha.oncha_web.feature.product.productBoard.repository.ProductBoardQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class JpaTestConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public ProductBoardQueryRepository productBoardQueryRepository () {
        ProductBoardQueryRepository productBoardQueryRepository = new ProductBoardQueryRepository();
        productBoardQueryRepository.setEntityManager(entityManager);
        return productBoardQueryRepository;
    }
}