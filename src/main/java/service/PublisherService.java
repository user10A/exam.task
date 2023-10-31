package service;

import model.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher savePublisher(Publisher publisher);
    Publisher getPublisherById(Long id);
    List<Publisher> getAllPublishers(); //аты боюнча сорттоп чыгаруу
    String updatePublisher(Long id,Publisher publisher);
    String deletePublisherByName(String name); //(издательствону очургондо, ага тиешелуу китептер жана авторлор  очпошу керек), методдорун тузуп, ишке ашыруу.
}
