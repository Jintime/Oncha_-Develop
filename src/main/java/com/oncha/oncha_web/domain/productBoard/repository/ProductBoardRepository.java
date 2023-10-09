package com.oncha.oncha_web.domain.productBoard.repository;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;

import java.util.List;
import java.util.Optional;

import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBoardRepository extends JpaRepository<ProductBoard,Long> {

    @EntityGraph(attributePaths = {"productFiles", "productFiles.fileInfo"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<ProductBoard> findAllByDeletedAtIsNull(Pageable pageable);

    @EntityGraph(attributePaths = {"productFiles", "productFiles.fileInfo"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<ProductBoard> findWithProductFileAAndFileInfoById(Long id);

    Page<ProductBoard> findByCategory(String category, Pageable pageable);
}
