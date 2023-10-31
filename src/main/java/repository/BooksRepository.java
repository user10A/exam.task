package repository;

import model.Books;

public interface BooksRepository {
    Books saveBook(Books books,Long authorId,Long publisherId);//(Book сакталып жатканда кандайдыр бир авторго тиешелуу болуп сакталуусу керек),
    String updateBookAuthor(Long id,Books books);
    void getBookAndPublisherByBookId(Long id);//(Бир Id ге тиешелуу book тун маалыматтары жана ага тиешелуу издательствосу чыксын),
    String deleteBookByAuthorId(Long id);
}
