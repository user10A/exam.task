package service;

import model.Author;

import java.util.List;

public interface AuthorService {
    Author saveAuthor(Author author);
    String updateAuthor(Long id,Author author);
    Author getAuthorById(Long id);
    List<Author> getAuthorsByPublisherId(Long id); //(тиешелуу издательствонун авторлорун чыгарып беруу)
    String deleteAuthorById(Long id); //(автор очкондо, авторго тиешелуу издательство очпошу керек, китептер очуш керек),
    void assignAuthorToPublisher(Long authorId, Long publisherId); //(авторду издательствого кошуп коюу(назначить)).
}
