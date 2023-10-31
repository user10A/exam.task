package service.Impl;

import model.Publisher;
import repository.Impl.PublisherRepositoryImpl;
import repository.PublisherRepository;
import service.PublisherService;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository = new PublisherRepositoryImpl();
    @Override
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.savePublisher(publisher);
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepository.getPublisherById(id);
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.getAllPublishers();
    }

    @Override
    public String updatePublisher(Long id, Publisher publisher) {
        return publisherRepository.updatePublisher(id,publisher);
    }

    @Override
    public String deletePublisherByName(String name) {
        return publisherRepository.deletePublisherByName(name);
    }
}
