package main;

import genre.Gender;
import genre.Genre;
import hibernate.Config;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Author;
import model.Books;
import model.Publisher;
import model.Reader;
import org.hibernate.SessionFactory;
import service.AuthorService;
import service.BooksService;
import service.Impl.AuthorServiceImpl;
import service.Impl.BooksServiceImpl;
import service.Impl.PublisherServiceImpl;
import service.Impl.ReaderServiceImpl;
import service.PublisherService;
import service.ReaderService;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Config.entityManagerFactory();
        AuthorService authorService = new AuthorServiceImpl();
        BooksService booksService = new BooksServiceImpl();
        PublisherService publisherService = new PublisherServiceImpl();
        ReaderService readerService = new ReaderServiceImpl();


        while (true) {
            switch (new Scanner(System.in).nextLine()) {
                case "1" -> {
                    //save +
                    Author author = new Author("erkin", "toigonbaev", "erkin@gamil.com", LocalDate.of(2002, 9, 14), "KG", Gender.Male);
                    System.out.println(authorService.saveAuthor(author));
                    Author author1 = new Author("alyona", "ivanova", "alyona@gmail.com", LocalDate.of(1995, 5, 10), "RU", Gender.Female);
                    Author author2 = new Author("john", "smith", "john@gmail.com", LocalDate.of(1980, 3, 15), "US", Gender.Male);
                    Author author3 = new Author("maria", "petrova", "maria@gmail.com", LocalDate.of(1992, 8, 20), "RU", Gender.Female);
                    Author author4 = new Author("david", "johnson", "david@gmail.com", LocalDate.of(1985, 11, 7), "US", Gender.Male);
                    System.out.println(authorService.saveAuthor(author1));
                    System.out.println(authorService.saveAuthor(author2));
                    System.out.println(authorService.saveAuthor(author3));
                    System.out.println(authorService.saveAuthor(author4));
                }
                case "2" -> {
                    //update +
                    Author author = new Author("Erkin", "Toigonbaev", "Erkin@gamil.com", LocalDate.of(2003, 10, 28), "KG", Gender.Male);
                    System.out.println(authorService.updateAuthor(1L, author));
                    Author author1 = new Author("Alyona", "Ivanova", "alyona@gmail.com", LocalDate.of(1995, 5, 10), "RU", Gender.Female);
                    Author author2 = new Author("John", "Smith", "john@gmail.com", LocalDate.of(1980, 3, 15), "US", Gender.Male);
                    Author author3 = new Author("Maria", "Petrova", "maria@gmail.com", LocalDate.of(1992, 8, 20), "RU", Gender.Female);
                    Author author4 = new Author("David", "Johnson", "david@gmail.com", LocalDate.of(1985, 11, 7), "US", Gender.Male);
                    System.out.println(authorService.updateAuthor(2L, author1));
                    System.out.println(authorService.updateAuthor(3L, author2));
                    System.out.println(authorService.updateAuthor(4L, author3));
                    System.out.println(authorService.updateAuthor(5L, author4));
                }
                case "3" -> {
                    //getById +
                    System.out.println(authorService.getAuthorById(1L));
                }
                case "4" -> {
                    //delete +
                    System.out.println(authorService.deleteAuthorById(2L));
                }
                case "5" -> {
                    //assign +
                    authorService.assignAuthorToPublisher(1L, 1L);
                    authorService.assignAuthorToPublisher(2L, 2L);
                    authorService.assignAuthorToPublisher(3L, 3L);
                    authorService.assignAuthorToPublisher(4L, 4L);
                    authorService.assignAuthorToPublisher(5L, 5L);
                }
                case "6" -> {
                    // +
                    System.out.println(authorService.getAuthorsByPublisherId(1L));
                }
                case "7" -> {
                    // +
                    Publisher publisher = new Publisher("Kyrgyzstan OCO", "Гражданская 119");
                    publisherService.savePublisher(publisher);
                    Publisher publisher1 = new Publisher("Океанский мир", "123 Main Street, Город1");
                    Publisher publisher2 = new Publisher("История искусства", "456 Elm Avenue, Город2");
                    Publisher publisher3 = new Publisher("Научные открытия", "789 Oak Road, Город3");
                    Publisher publisher4 = new Publisher("Путешествия и приключения", "101 Pine Lane, Город4");
                    Publisher publisher5 = new Publisher("Кулинарные секреты", "202 Cedar Drive, Город5");
                    System.out.println(publisherService.savePublisher(publisher1));
                    System.out.println(publisherService.savePublisher(publisher2));
                    System.out.println(publisherService.savePublisher(publisher3));
                    System.out.println(publisherService.savePublisher(publisher4));
                    System.out.println(publisherService.savePublisher(publisher5));

                }
                case "8" -> {
                    // +
                    System.out.println(publisherService.getPublisherById(1L));
                }
                case "9" -> {
                    Publisher publisher = new Publisher("Kyrgyzstan OOO", "Гражданская 100");
                    System.out.println(publisherService.updatePublisher(1L, publisher));
                }
                case "10" -> {
                    System.out.println(publisherService.deletePublisherByName("Kyrgyzstan OOO"));
                }
                case "11" -> {
                    //
                    System.out.println(publisherService.getAllPublishers());
                }
                case "12" -> {
                    Books book1 = new Books("Путешествия сквозь время", "Россия", 2023, 19.99, Genre.Adventure);
                    Books book2 = new Books("Тайны древнего Египта", "Египет", 2022, 24.95, Genre.ScienceFiction);
                    Books book3 = new Books("Шедевры кулинарии", "Франция", 2021, 29.99, Genre.Drama);
                    Books book4 = new Books("Психология счастья", "США", 2023, 18.50, Genre.ScienceFiction);
                    Books book5 = new Books("Загадки Вселенной", "Космос", 2022, 22.75, Genre.ScienceFiction);
                    booksService.saveBook(book1, 1L, 2L);
                    booksService.saveBook(book2, 2L, 2L);
                    booksService.saveBook(book3, 3L, 3L);
                    booksService.saveBook(book4, 4L, 4L);
                    booksService.saveBook(book5, 5L, 5L);
                }
                case "13" -> {
                    Books book2 = new Books("Тайны древнего Египта", "Египет", 2022, 24.95, Genre.Romance);
                    System.out.println(booksService.updateBookAuthor(4L, book2));
                }
                case "14" -> {
                    booksService.getBookAndPublisherByBookId(2L);
                }
                case "15" -> {
                    booksService.deleteBookByAuthorId(3L);
                }
                case "16"->{
                    Reader reader1 = new Reader("Alice", 25, "alice@gmail.com");
                    System.out.println(readerService.saveReader(4L, reader1));

                    Reader reader2 = new Reader("Bob", 30, "bob@gmail.com");
                    System.out.println(readerService.saveReader(5L, reader2));

                    Reader reader3 = new Reader("Charlie", 28, "charlie@gmail.com");
                    System.out.println(readerService.saveReader(4L, reader3));

                    Reader reader4 = new Reader("David", 22, "david@gmail.com");
                    System.out.println(readerService.saveReader(4L, reader4));

                    Reader reader5 = new Reader("Eve", 27, "eve@gmail.com");
                    System.out.println(readerService.saveReader(5L, reader5));
                    Reader reader6 = new Reader("Frank", 32, "frank@gmail.com");
                    System.out.println(readerService.saveReader(4L, reader6));

                    Reader reader7 = new Reader("Grace", 29, "grace@gmail.com");
                    System.out.println(readerService.saveReader(5L, reader7));

                    Reader reader8 = new Reader("Henry", 26, "henry@gmail.com");
                    System.out.println(readerService.saveReader(4L, reader8));

                    Reader reader9 = new Reader("Isabel", 23, "isabel@gmail.com");
                    System.out.println(readerService.saveReader(5L, reader9));

                    Reader reader10 = new Reader("Jack", 24, "jack@gmail.com");
                    System.out.println(readerService.saveReader(5L, reader10));

                }
                case "17"->{
                    System.out.println(readerService.getReaderByBookId(2L));
                }
                case "18"->{
                    System.out.println(readerService.getReadersByAuthorId(1L));
                }
                case "19"->{
                    Reader reader10 = new Reader("Update Jack", 24, "jack@gmail.com");
                    readerService.updateReader(10L,reader10);
                }
                case "20"->{
                    readerService.deleteReaderById(10L);
                }
            }
        }
    }
}
