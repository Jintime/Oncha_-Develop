package com.oncha.oncha_web.feature.product_board.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.oncha.oncha_web.config.JpaTestConfig;
import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.productBoard.model.TeaCategory;
import com.oncha.oncha_web.feature.product.productBoard.repository.ProductBoardQueryRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql("classpath:/sql/mockProductBoard.sql")
@Import(JpaTestConfig.class)
public class ProductBoardQueryRepositoryTest {

    @Autowired
    private ProductBoardQueryRepository productBoardQueryRepository;

    @Test
    @DisplayName("차의 정렬조건이 주어지면 정렬 조건에 맞게 데이터가 정렬 되어야 한다")
    public void test1 () {
        //given
        TeaCategory teaCategory = new TeaCategory("test", "민트초코", "에티오피아", true, false, "탕수육");
        Pageable pageable = PageRequest.of(0, 10);

        //when
        Page<ProductBoard> productBoards = productBoardQueryRepository.findAllByTeaCategory(pageable, teaCategory);
        List<ProductBoard> productBoardList = productBoards.getContent();

        //then
        assertThat(productBoardList).isNotEmpty();
        assertThat(productBoardList.get(0)).isNotNull();
        ProductBoard productBoard =  productBoardList.get(0);
        assertThat(productBoard.getDetail()).isEqualTo("target");

        TeaCategory resultTeaCategory = productBoard.getTeaCategory();
        assertThat(resultTeaCategory.getFlavor()).isEqualTo(teaCategory.getFlavor());
        assertThat(resultTeaCategory.getOriginNation()).isEqualTo(teaCategory.getOriginNation());
        assertThat(resultTeaCategory.isCaffeine()).isEqualTo(teaCategory.isCaffeine());
        assertThat(resultTeaCategory.isBlended()).isEqualTo(teaCategory.isBlended());
        assertThat(resultTeaCategory.getCategory()).isEqualTo(teaCategory.getCategory());
    }
}

