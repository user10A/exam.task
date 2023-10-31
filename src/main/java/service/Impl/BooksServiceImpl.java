package service.Impl;

import model.Books;
import repository.BooksRepository;
import repository.Impl.BooksRepositoryImpl;
import service.BooksService;

public class BooksServiceImpl implements BooksService {
    private final BooksRepository booksRepository = new BooksRepositoryImpl();
    @Override
    public Books saveBook(Books books,Long id,Long publisherId) {
        return booksRepository.saveBook(books,id,publisherId);
    }

    @Override
    public String updateBookAuthor(Long id, Books books) {
        return booksRepository.updateBookAuthor(id,books);
    }

    @Override
    public void getBookAndPublisherByBookId(Long id) {
        booksRepository.getBookAndPublisherByBookId(id);
    }

    @Override
    public String deleteBookByAuthorId(Long id) {
        return booksRepository.deleteBookByAuthorId(id);
    }
}
