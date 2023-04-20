package kr.ac.hansung;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
//@ToString // category 호 (계속 무한반복됨)
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private int id;

    private String name;
    private int price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category=" + category.getName() +
                '}';
    }
}
