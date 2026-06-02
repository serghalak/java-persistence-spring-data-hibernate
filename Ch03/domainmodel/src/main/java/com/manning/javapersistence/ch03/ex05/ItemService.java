package com.manning.javapersistence.ch03.ex05;



import javax.persistence.*;
import java.util.List;

public class ItemService {

//    @PersistenceContext
//    private EntityManager entityManager;

    public static void main(String[] args) {
        new ItemService().findItemsOrderByName().forEach(System.out::println);
    }

    public List<Item> findItemsOrderByName() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch03.metamodel");
        EntityManager em = emf.createEntityManager();
        try {
              em.getTransaction().begin();
//            List<Item> result = em.createNamedQuery("findItemsOrderByName", Item.class).getResultList();
//            System.out.println(result);

            Query query = em.createNamedQuery("findItemBuyNowPriceGreaterThan", Item.class);
            query.setParameter("name", "some data 02");
            List<Item> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } finally {
            em.close();
            emf.close();
        }

//        EntityManagerFactory entityManagerFactory = EntityManagerFactory;
//        List<Item> result = entityManager.createNamedQuery("findItemsOrderByName", Item.class)
//                .getResultList();
//        System.out.println(result);
//        return result;
    }
}
