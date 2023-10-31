package service.Impl;

import model.Reader;
import repository.Impl.ReaderRepositoryImpl;
import repository.ReaderRepository;
import service.ReaderService;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository repository = new ReaderRepositoryImpl();
    @Override
    public Reader saveReader(Long id ,Reader reader) {
        return repository.saveReader(id ,reader);
    }

    @Override
    public String updateReader(Long id, Reader reader) {
        return repository.updateReader(id,reader);
    }

    @Override
    public List<Reader> getReaderByBookId(Long id) {
        return repository.getReaderByBookId(id);
    }

    @Override
    public String deleteReaderById(Long id) {
        return repository.deleteReaderById(id);
    }

    @Override
    public List<Reader> getReadersByAuthorId(Long id) {
        return repository.getReadersByAuthorId(id);
    }
}
