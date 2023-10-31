package repository.Impl;

import hibernate.Config;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Author;
import model.Books;
import model.Publisher;
import model.Reader;
import repository.ReaderRepository;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class ReaderRepositoryImpl implements ReaderRepository {
    private final EntityManagerFactory entityManagerFactory = Config.entityManagerFactory();
    private EntityManager entityManager;

    @Override
    public Reader saveReader(Long bookId ,Reader reader) {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            // Сначала сохраните объект Reader в базу данных
            entityManager.persist(reader);

            // Затем найдите объект Books по его идентификатору
            Books books = entityManager.find(Books.class, bookId);

            if (books != null) {
                // Проверьте, что у книги существует список читателей и создайте его, если он отсутствует
                if (books.getReaders() == null) {
                    books.setReaders(new ArrayList<>());
                }

                // Добавьте читателя в список читателей у книги
                books.getReaders().add(reader);

                // Теперь, для удостоверения, что читатель имеет эту книгу, добавьте книгу в список книг у читателя
                if (reader.getBooks() == null) {
                    reader.setBooks(new ArrayList<>());
                }
                reader.getBooks().add(books);

                // Фиксация транзакции и закрытие EntityManager
                entityManager.getTransaction().commit();
                entityManager.close();

                return reader;
            } else {
                entityManager.getTransaction().rollback();
                entityManager.close();
                return null; // Или выполните другие действия при отсутствии книги
            }
    }

    @Override
    public String updateReader(Long id, Reader reader) {
        entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Reader reader1 = entityManager.find(Reader.class, id);
        reader1.setName(reader.getName());
        reader1.setAge(reader.getAge());
        reader1.setEmail(reader.getEmail());
        reader1.setBooks(reader1.getBooks());
        entityManager.merge(reader1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Reader successfully updated ";
    }

    @Override
    public List<Reader> getReaderByBookId(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Books books = entityManager.find(Books.class, id);
        if (books != null) {
            List<Reader> readers = books.getReaders();
            entityManager.close();
            return readers;
        } else {
            entityManager.close();
            return new ArrayList<>();
        }
    }

    @Override
    public String deleteReaderById(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Reader reader = entityManager.find(Reader.class, id);
        if (reader != null) {
            List<Books> books =reader.getBooks();
            for (Books b:books) {
                b.setReaders(null);
                entityManager.merge(b);
              Publisher publisher = b.getPublisher();
              publisher.setBooks(null);
            }
            entityManager.remove(reader);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Reader successfully deleted";
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "Reader not found";
        }
    }

    @Override
    public List<Reader> getReadersByAuthorId(Long id) {
        List<Reader> readers = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Author author = entityManager.find(Author.class, id);
            if (author != null) {
                List<Books> books = author.getBooks();
                for (Books book : books) {
                    List<Reader> bookReaders = book.getReaders();
                    readers.addAll(bookReaders);
                }
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return readers;
    }
}
