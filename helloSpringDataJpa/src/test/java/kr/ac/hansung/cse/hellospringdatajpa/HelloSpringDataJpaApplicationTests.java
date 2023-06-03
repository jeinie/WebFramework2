package kr.ac.hansung.cse.hellospringdatajpa;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Product;
import kr.ac.hansung.cse.hellospringdatajpa.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    @DisplayName("Test4: findByName")
    public void findByName() {
        Product product = productRepository.findByName("Galaxy S21");
        assertEquals("Galaxy S21", product.getName());
    }

    @Test
    @DisplayName("Test5: findByNameContainingWithPaging")
    public void findByNameContainingWithPaging() {
        Pageable pageable = PageRequest.of(0, 3);
        List<Product> productList = productRepository.findByNameContaining("MacBook", pageable);

        System.out.println("====findByNameContainingWithPaging: MacBook====");
        for (Product product: productList) {
            System.out.println("-->" + product.toString());
        }
    }

    @Test
    @DisplayName("Test6: findByNameContainingWithPagingAndSort")
    public void findByNameContainingWithPagingAndSort() {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        List<Product> productList = productRepository.findByNameContaining("Galaxy", pageable);
        System.out.println("===findByNameContainingWithPagingAndSort: Galaxy===");

        for (Product product: productList) {
            System.out.println("-->" + product.toString());
        }
    }

    @Test
    @DisplayName("Test7: searchByNameUsingQuery")
    public void searchByName() {
        List<Product> productList = productRepository.searchByName("Air");

        System.out.println("====searchByNameUsingQuery: Air====");
        for (Product product: productList) {
            System.out.println("-->" + product.toString());
        }
    }
}
