package kr.ac.hansung;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        /*Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();*/

        Category category1 = new Category();
        category1.setName("컴퓨터");

        Category category2 = new Category();
        category2.setName("자동차");

        Product product1 = new Product();
        product1.setName("노트북1");
        product1.setPrice(2000);
        product1.setDescription("Awesome Notebook");
        product1.setCategory(category1);
        category1.getProducts().add(product1); // 양방향 매핑

        Product product2 = new Product();
        product2.setName("노트북2");
        product2.setPrice(2000);
        product2.setDescription("Powerful Notebook");
        product2.setCategory(category1);
        category1.getProducts().add(product2); // 양방향 매핑

        Product product3 = new Product();
        product3.setName("소나타");
        product3.setPrice(20000);
        product3.setDescription("Popular Car");
        product3.setCategory(category2);
        category2.getProducts().add(product3); // 양방향 매핑

        // 위의 주석 3줄을 아래 1줄로 작성 가능
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        /*System.out.println("helloHibernate: " + "saving products");
        session.save(product1);
        session.save(product2);
        session.save(product3);
        System.out.println("helloHibernate: " + "saved products");*/

        Serializable cid1 = session.save(category1); // category1 -> product1, 2
        Serializable cid2 = session.save(category2); // category2 -> product3

//        session.delete(category2); // category2 -> product3
        /*product1.setCategory(null);
        session.delete(product1); // product -> category*/

        /*Product savedProduct = session.get(Product.class, product.getId());
        System.out.println("saved Product: " + savedProduct);*/

        /*Query<Product> aQuery = session.createQuery("from Product order by name", Product.class); // HQL
        List<Product> products = aQuery.getResultList();
        System.out.println(products);*/

        transaction.commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.beginTransaction();

        System.out.println("helloHibernate: " + "getting a Category");
        Category aCategory = session2.get(Category.class, cid1); // LAZY 이므로 category 읽을 때 product 까지는 안읽음

        System.out.println("helloHibernate: " + "getting Products");
        Set<Product> products = aCategory.getProducts();

        System.out.println("helloHibernate: " + "printing products");
        for (Product p: products)
            System.out.println(p.getName());

        tx2.commit();
        session2.close();

        sessionFactory.close();
    }
}
