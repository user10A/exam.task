package service.Impl;

import model.Author;
import repository.AuthorRepository;
import repository.Impl.AuthorRepositoryImpl;
import service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository = new AuthorRepositoryImpl();
    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.saveAuthor(author);
    }

    @Override
    public String updateAuthor(Long id, Author author) {
        return authorRepository.updateAuthor(id,author);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.getAuthorById(id);
    }

    @Override
    public List<Author> getAuthorsByPublisherId(Long id) {
        return authorRepository.getAuthorsByPublisherId(id);
    }

    @Override
    public String deleteAuthorById(Long id) {
        return authorRepository.deleteAuthorById(id);
    }

    @Override
    public void assignAuthorToPublisher(Long authorId, Long publisherId) {
        authorRepository.assignAuthorToPublisher(authorId,publisherId);
    }
}
