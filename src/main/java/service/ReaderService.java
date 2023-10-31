package service;

import model.Reader;

import java.util.List;

public interface ReaderService {
    Reader saveReader(Long id ,Reader reader);
    String updateReader(Long id,Reader reader);
    List<Reader> getReaderByBookId(Long id);
    String deleteReaderById(Long id);
    List<Reader>getReadersByAuthorId(Long id);
}
