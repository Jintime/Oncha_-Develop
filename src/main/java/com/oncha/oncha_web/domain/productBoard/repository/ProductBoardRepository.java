package com.oncha.oncha_web.domain.productBoard.repository;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBoardRepository extends JpaRepository<ProductBoard,Long> {

    @Override
    @EntityGraph(attributePaths = {"productFileList", "productFileList.fileInfo"})
    Optional<ProductBoard> findById(Long id);

}
