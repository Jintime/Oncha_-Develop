package com.oncha.oncha_web.feature.productBoard.repository;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.productBoard.model.ProductFileDTO;
import com.oncha.oncha_web.util.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.oncha.oncha_web.domain.productBoard.model.QProductBoard.productBoard;
import static com.oncha.oncha_web.domain.productBoard.model.QProductFile.productFile;

@Repository
public class ProductBoardQueryRepository extends Querydsl4RepositorySupport {

    public ProductBoardQueryRepository() {
        super(ProductBoard.class);
    }

    public Page<ProductBoardDTO> findAllByPageable(Pageable pageable) {
        return applyPagination(pageable, query -> select(Projections.constructor(
                ProductBoardDTO.class,
                productBoard.id,
                productBoard.title,
                productBoard.detail,
                productBoard.product_name,
                productBoard.origin_nation,
                productBoard.type,
                productBoard.flavor,
                productBoard.category,
                productBoard.weight,
                productBoard.price,
                productBoard.product_count,
                productBoard.view,
                productBoard.love,
                productBoard.blended,
                productBoard.caffeine,
                Projections.list(Projections.fields(
                        ProductFileDTO.class,
                        productFile.originalFileName,
                        productFile.storedFileName
                ))
        )).from(productBoard)
                .leftJoin(productFile).on(productFile.productBoard.
                        eq(productBoard)).fetchJoin());

    }
    public ProductBoardDTO findById(Long id) {
        return select(Projections.constructor(
                ProductBoardDTO.class,
                productBoard.id,
                productBoard.title,
                productBoard.detail,
                productBoard.product_name,
                productBoard.origin_nation,
                productBoard.type,
                productBoard.flavor,
                productBoard.category,
                productBoard.weight,
                productBoard.price,
                productBoard.product_count,
                productBoard.view,
                productBoard.love,
                productBoard.blended,
                productBoard.caffeine,
                Projections.list(Projections.constructor(
                        ProductFileDTO.class,
                        productFile.originalFileName,
                        productFile.url
                ))
        )).from(productBoard)
                .leftJoin(productFile).on(productFile.productBoard.
                        eq(productBoard))
                .where(productBoard.id.eq(id))
                .fetchJoin().fetchOne();

    }

}
