package org.example.kutuphaneotomasyon.Service.Impl;

import org.example.kutuphaneotomasyon.Dto.DtoBook;
import org.example.kutuphaneotomasyon.Dto.DtoPublisher;
import org.example.kutuphaneotomasyon.Dto.DtoPublisherIU;
import org.example.kutuphaneotomasyon.Entity.Book;
import org.example.kutuphaneotomasyon.Entity.Publisher;
import org.example.kutuphaneotomasyon.Mapper.BookMapperView;
import org.example.kutuphaneotomasyon.Mapper.PublisherMapper;
import org.example.kutuphaneotomasyon.Repository.BookRepository;
import org.example.kutuphaneotomasyon.Repository.PublisherRepository;
import org.example.kutuphaneotomasyon.ResponseMessage.Constants;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.IPublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements IPublisherService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;
    private final BookMapperView bookMapperView = new BookMapperView();  // manuel sınıf başlatma
    private final PublisherMapper publisherMapper = new PublisherMapper(); // manuel sınıf başlatma

    public PublisherServiceImpl(PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public GenericResponse<?> savePublisher(DtoPublisherIU dto) {
        System.out.println("GELEN DTO: " + dto);
        System.out.println("DTO.getAd(): " + dto.getAd());

        Publisher publisher = PublisherMapper.dtoToPublisher(dto);  // PublisherMapper manuel kullanımı
        System.out.println("MAPPED ENTITY AD: " + publisher.getAd());

        Publisher saved = publisherRepository.save(publisher);
        return GenericResponse.success(PublisherMapper.publisherToDto(saved));  // PublisherMapper manuel kullanımı
    }

    @Override
    public GenericResponse<?> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        if (publishers.isEmpty()) {
            return GenericResponse.error(Constants.EMPTY_LIST);
        }
        List<DtoPublisher> dtoList = publishers.stream()
                .map(PublisherMapper::publisherToDto)  // PublisherMapper manuel kullanımı
                .collect(Collectors.toList());
        return GenericResponse.success(dtoList);
    }

    @Override
    public GenericResponse<?> getPublisherbyId(Integer id) {
        Optional<Publisher> optional = publisherRepository.findById(id);
        return optional.map(publisher -> GenericResponse.success(PublisherMapper.publisherToDto(publisher)))  // PublisherMapper manuel kullanımı
                .orElseGet(() -> GenericResponse.error(Constants.EMPTY_ID));
    }

    @Override
    public GenericResponse<?> updatePublisher(Integer id, DtoPublisherIU dto) {
        Optional<Publisher> optional = publisherRepository.findById(id);
        if (optional.isPresent()) {
            Publisher publisher = optional.get();
            PublisherMapper.updatePublisherFromDto(dto, publisher);  // PublisherMapper manuel kullanımı
            Publisher updated = publisherRepository.save(publisher);
            return GenericResponse.success(PublisherMapper.publisherToDto(updated));  // PublisherMapper manuel kullanımı
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> deletePublisher(Integer id) {
        Optional<Publisher> optional = publisherRepository.findById(id);
        if (optional.isPresent()) {
            publisherRepository.delete(optional.get());
            return GenericResponse.success("Yayınevi başarıyla silindi.");
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> getBooksByPublisher(Integer publisherId) {
        if (!publisherRepository.existsById(publisherId)) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
        List<Book> books = bookRepository.findByPublisherId(publisherId);
        if (books.isEmpty()) {
            return GenericResponse.error("Bu yayınevine ait kitap bulunamadı.");
        }
        List<DtoBook> dtoList = books.stream()
                .map(bookMapperView::bookToDto)  // bookMapperView manuel kullanımı
                .collect(Collectors.toList());
        return GenericResponse.success(dtoList);
    }
}
