package kr.ac.hansung.cse.hellospringdatajpa;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Product;
import kr.ac.hansung.cse.hellospringdatajpa.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest // integration test (test 용 spring IoC Container <- beans)
//    @WebMvcTest // unit test for controller (컨트롤러만)
@Transactional // 테스트하고 나서 원래 상태로 rollback
class HelloSpringDataJpaApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Test1: findProductById")
    public void findProductById() {
        Optional<Product> product = productRepository.findById(1L);
        System.out.println(product.get());
        assertNotNull(product.get());
    }

    @Test
    @DisplayName("Test2: findAllProducts")
    public void findAllProducts() {
        List<Product> products = productRepository.findAll();
        assertNotNull(products);
    }

    @Test
    @DisplayName("Test3: createProduct")
    public void createProduct() { // save 와 관련된 테스트
        Product product = new Product("OLED TV", "LG 전자", "korea", 300.0);
        Product savedProduct = productRepository.save(product);

        Product newProduct = productRepository.findById(savedProduct.getId()).get();
        assertNotNull("OLED TV", newProduct.getName()); // 기댓값과 실제 DB 에 저장되어 있는 값이 일치한지 테스트
    }
}
