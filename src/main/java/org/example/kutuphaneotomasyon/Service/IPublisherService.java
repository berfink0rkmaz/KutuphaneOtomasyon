package org.example.kutuphaneotomasyon.Service;

import org.example.kutuphaneotomasyon.Dto.DtoPublisherIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

public interface IPublisherService {
        GenericResponse<?> savePublisher(DtoPublisherIU dto);
        GenericResponse<?> getAllPublishers();
        GenericResponse<?> getPublisherbyId(Integer id);
        GenericResponse<?> updatePublisher(Integer id, DtoPublisherIU dto);
        GenericResponse<?> deletePublisher(Integer id);
        GenericResponse<?> getBooksByPublisher(Integer publisherId);
}
