package com.oncha.oncha_web.domain.productBoard.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.oncha.oncha_web.domain.productBoard.repository.ProductBoardRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(showSql = true)
public class ProductBoardAnnotationTest {

    @Autowired
    private ProductBoardRepository productBoardRepository;

    @BeforeEach
    public void init() {
        ProductBoard productBoard = new ProductBoard();
        ProductBoard productBoard2 = new ProductBoard();
        productBoardRepository.save(productBoard);
        productBoardRepository.save(productBoard2);
    }

    @Test
    @DisplayName(value = "데이터베이스 컬럼 삭제시 delete가 아닌 update가 동작해야한다.")
    public void test1() {
        //given
        Long id = 1L;
        Long id2 = 2L;

        // when
        Optional<ProductBoard> beforeProductBoard = productBoardRepository.findById(id);
        assertThat(beforeProductBoard).isPresent();
        productBoardRepository.delete(beforeProductBoard.get());
        productBoardRepository.flush();

        Optional<ProductBoard> productBoard = productBoardRepository.findById(id);
        Optional<ProductBoard> productBoard2 = productBoardRepository.findById(id2);

        //then
        assertThat(productBoard).isPresent();
        assertThat(productBoard.get().getId()).isEqualTo(id);
        assertThat(productBoard.get().getDeletedAt()).isNotNull();

        assertThat(productBoard2).isPresent();
        assertThat(productBoard2.get().getId()).isEqualTo(id2);
        assertThat(productBoard2.get().getDeletedAt()).isNull();
    }

    @Test
    @DisplayName(value = "데이터베이스 컬럼 삭제시 update가 동작되어야하지만 검색이 되어선 안된다")
    public void test2() {
        //given
        Long id = 1L;

        // when
        Optional<ProductBoard> beforeProductBoard = productBoardRepository.findById(id);
        assertThat(beforeProductBoard).isPresent();
        productBoardRepository.delete(beforeProductBoard.get());
        productBoardRepository.flush();

        Optional<ProductBoard> productBoard = productBoardRepository.findById(id);

        //then
        assertThat(productBoard).isEmpty();
    }

}
