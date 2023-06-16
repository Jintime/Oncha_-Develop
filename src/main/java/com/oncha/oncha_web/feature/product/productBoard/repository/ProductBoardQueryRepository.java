package com.oncha.oncha_web.feature.product.productBoard.repository;

import static com.oncha.oncha_web.domain.productBoard.model.QProductBoard.productBoard;
import static com.oncha.oncha_web.domain.productBoard.model.QProductFile.productFile;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.util.Querydsl4RepositorySupport;
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
//         selectFrom(productBoard)
//            .leftJoin(productBoard.productFileList, productFile)
//            .offset((long) pageable.getPageNumber() * pageable.getPageSize())
//            .limit(pageable.getPageSize()).fetchJoin()
//            .transform(groupBy(productBoard.id).list(new QProductBoardDTO(
//                productBoard,
//                list(new QProductFileDTO(productFile))
//            )));

        return applyPagination(pageable, query -> selectFrom(productBoard)
            .leftJoin(productBoard.productFileList, productFile)
            .fetchJoin());
    }

    public Optional<ProductBoard> findById(Long id) {
        return Optional.ofNullable(selectFrom(productBoard)
            .where(productBoard.id.eq(id))
            .leftJoin(productBoard.productFileList, productFile)
            .fetchJoin().fetchOne());
    }

}
