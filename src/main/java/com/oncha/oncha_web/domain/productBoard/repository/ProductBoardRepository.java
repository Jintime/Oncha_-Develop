package com.oncha.oncha_web.domain.productBoard.repository;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBoardRepository extends JpaRepository<ProductBoard,Long> {
}
