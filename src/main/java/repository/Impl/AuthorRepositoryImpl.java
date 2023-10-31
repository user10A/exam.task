package repository.Impl;

import hibernate.Config;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Author;
import model.Books;
import model.Publisher;
import org.hibernate.Hibernate;
import repository.AuthorRepository;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {
    private final EntityManagerFactory entityManagerFactory = Config.entityManagerFactory();
    private EntityManager entityManager;

    @Override
    public Author saveAuthor(Author author) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }

    @Override
    public String updateAuthor(Long id, Author author) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author1 = entityManager.find(Author.class, id);
        if (author1 != null) {
            author1.setFirst_name(author.getFirst_name());
            author1.setLast_name(author.getLast_name());
            author1.setEmail(author.getEmail());
            author1.setDate_of_birth(author.getDate_of_birth());
            author1.setCountry(author.getCountry());
            author1.setGender(author.getGender());
            entityManager.merge(author1);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Author successfully updated";
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "Author not found";
        }
    }

    @Override
    public Author getAuthorById(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }

    @Override
    public List<Author> getAuthorsByPublisherId(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher = entityManager.find(Publisher.class, id);
        Hibernate.initialize(publisher.getAuthors());
        entityManager.close();
        return publisher.getAuthors();
    }

    @Override
    public String deleteAuthorById(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);

        if (author != null) {
            List<Books> books = author.getBooks();
            for (Books b : books) {
                b.setAuthor(null);
                entityManager.merge(b);
            }
            List<Publisher> publishers = author.getPublishers();
            for (Publisher p : publishers) {
                p.setBooks(null);
                p.getAuthors().remove(author);
                entityManager.merge(p);
            }
            entityManager.remove(author);
            entityManager.getTransaction().commit();
            entityManager.close();

            return "Author successfully deleted";
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "Author not found";
        }
    }


    @Override
    public void assignAuthorToPublisher(Long authorId, Long publisherId) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, authorId);
        Publisher publisher = entityManager.find(Publisher.class, publisherId);
        if (!publisher.getAuthors().contains(author)) {
            publisher.getAuthors().add(author);
            author.getPublishers().add(publisher);
            entityManager.merge(publisher);
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("Author To Publisher successfully assign");
        }
    }
}
