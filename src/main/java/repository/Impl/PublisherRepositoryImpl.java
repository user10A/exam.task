package repository.Impl;

import hibernate.Config;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Author;
import model.Books;
import model.Publisher;
import org.hibernate.HibernateException;
import repository.PublisherRepository;

import java.util.ArrayList;
import java.util.List;

public class PublisherRepositoryImpl implements PublisherRepository {
    private final EntityManagerFactory entityManagerFactory = Config.entityManagerFactory();
    private EntityManager entityManager;

    @Override
    public Publisher savePublisher(Publisher publisher) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(publisher);
        entityManager.getTransaction().commit();
        entityManager.close();
        return publisher;
    }

    @Override
    public Publisher getPublisherById(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher = entityManager.find(Publisher.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return publisher;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        entityManager = entityManagerFactory.createEntityManager();
//        int id = 1;
        entityManager.getTransaction().begin();
//    List<Publisher> pub = new ArrayList<>();
//        for (Publisher p:pub) {
//            pub.add(entityManager.find(Publisher.class,id));
//            id++;
//        }

        Query query = entityManager.createQuery("select c from Publisher c order by c.name desc");
        List<Publisher> pubb = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return pubb;


    }

    @Override
    public String updatePublisher(Long id, Publisher publisher) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher1 = entityManager.find(Publisher.class, id);
        if (publisher1 != null) {
            publisher1.setName(publisher.getName());
            publisher1.setAddress(publisher.getAddress());
            publisher1.setBooks(publisher.getBooks());
            publisher1.setAuthors(publisher.getAuthors());
            entityManager.merge(publisher1);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Publisher successfully updated";
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "Publisher not found";
        }
    }

    @Override
    public String deletePublisherByName(String name) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT p FROM Publisher p WHERE p.name = :name", Publisher.class);
            query.setParameter("name", name);
            Publisher publisher = (Publisher) query.getSingleResult();
            if (publisher != null) {
                List<Author> authors = publisher.getAuthors();
                for (Author a : authors) {
                    a.setPublishers(null);
                    entityManager.merge(a);
                }
                List<Books> books = publisher.getBooks();
                for (Books b : books) {
                    b.setPublisher(null);
                    entityManager.merge(b);
                }
                entityManager.remove(publisher);
                entityManager.getTransaction().commit();
                return "Publisher successfully deleted";
            } else {
                entityManager.getTransaction().rollback();
                return "Publisher not found";
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}

