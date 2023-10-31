package repository.Impl;

import hibernate.Config;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Author;
import model.Books;
import model.Publisher;
import model.Reader;
import repository.BooksRepository;

import java.util.ArrayList;
import java.util.List;

public class BooksRepositoryImpl implements BooksRepository {
    private final EntityManagerFactory entityManagerFactory = Config.entityManagerFactory();
    private EntityManager entityManager;

    @Override
    public Books saveBook(Books books,Long authorId,Long publisherId) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Author author = entityManager.find(Author.class,authorId);
        List<Books>books1 = new ArrayList<>();
        books1.add(books);
        author.setBooks(books1);
        Publisher publisher =entityManager.find(Publisher.class,publisherId);
        publisher.setBooks(books1);
        books.setPublisher(publisher);
        books.setAuthor(author);
        entityManager.persist(books);
        entityManager.getTransaction().commit();
        entityManager.close();
        return books;
    }

    @Override
    public String updateBookAuthor(Long id, Books books) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Books books1 = entityManager.find(Books.class, id);
        if (books1 != null) {
            books1.setName(books.getName());
            books1.setCountry(books.getCountry());
            books1.setPublished_year(books.getPublished_year());
            books1.setPrice(books.getPrice());
            books1.setGenre(books.getGenre());
            books1.setPublisher(books.getPublisher());
            entityManager.merge(books1);
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
    public void getBookAndPublisherByBookId(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Books book = entityManager.find(Books.class, id);
        if (book != null) {
            Publisher publisher = book.getPublisher();
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println(book);
            System.out.println(publisher);
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            System.out.println("Book not found");
        }
    }

    @Override
    public String deleteBookByAuthorId(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        if (author != null) {
            List<Publisher>publishers =author.getPublishers();
            for (Publisher p:publishers) {
                p.setBooks(null);
                entityManager.merge(p);
            }

            List<Books> books = author.getBooks();
            for (Books book : books) {
                List<Reader>readers = book.getReaders();
                for (Reader r:readers) {
                    r.setBooks(null);
                    entityManager.merge(r);
                }
                entityManager.remove(book);
                List<Books>books1=new ArrayList<>();
                author.setBooks(books1);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Books of the author successfully deleted";
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "Author not found";
        }
    }
}
