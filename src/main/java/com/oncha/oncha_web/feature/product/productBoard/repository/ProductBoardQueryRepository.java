package com.oncha.oncha_web.feature.product.productBoard.repository;

import static com.oncha.oncha_web.domain.productBoard.model.QProductBoard.productBoard;
import static com.oncha.oncha_web.domain.productBoard.model.QProductFile.productFile;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.productBoard.model.TeaCategory;
import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.util.Querydsl4RepositorySupport;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProductBoardQueryRepository extends Querydsl4RepositorySupport {

    public ProductBoardQueryRepository() {
        super(ProductBoard.class);
    }

    public Page<ProductBoard> findAllByPageable(Pageable pageable) {
        return applyPagination(pageable, query -> getFindAllQuery());
    }

    public Optional<ProductBoard> findById(Long id) {
        return Optional.ofNullable(selectFrom(productBoard)
            .where(productBoard.id.eq(id))
            .leftJoin(productBoard.productFileList, productFile)
            .fetchJoin().fetchOne());
    }

    public Page<ProductBoardDTO> findAllByTeaCategory(Pageable pageable, TeaCategory teaCategory) {
        JPAQuery<ProductBoard> jpaQuery = getFindAllQuery();

        if (teaCategory == null) {
            jpaQuery.orderBy(productBoard.recommend.desc());
            return applyPagination(pageable, query -> jpaQuery);
        }

        jpaQuery
            .orderBy(
                new OrderSpecifier<>(Order.DESC, productBoard.teaCategory.flavor.eq(teaCategory.getFlavor())),
                new OrderSpecifier<>(Order.DESC, productBoard.teaCategory.origin_nation.eq(teaCategory.getOriginNation())),
                new OrderSpecifier<>(Order.DESC, productBoard.teaCategory.caffeine.eq(teaCategory.isCaffeine())),
                new OrderSpecifier<>(Order.DESC, productBoard.teaCategory.blended.eq(teaCategory.isBlended())),
                new OrderSpecifier<>(Order.DESC, productBoard.teaCategory.category.eq(teaCategory.getCategory()))
            );

        return applyPagination(pageable, query -> jpaQuery);
    }

    public JPAQuery<ProductBoard> getFindAllQuery() {
        return selectFrom(productBoard)
            .leftJoin(productBoard.productFileList, productFile)
            .fetchJoin();
    }
}
