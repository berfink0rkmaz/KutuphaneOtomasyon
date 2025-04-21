package org.example.kutuphaneotomasyon.Mapper;


import org.example.kutuphaneotomasyon.Dto.DtoPublisher;
import org.example.kutuphaneotomasyon.Dto.DtoPublisherIU;
import org.example.kutuphaneotomasyon.Entity.Publisher;
import org.springframework.stereotype.Component;


public class PublisherMapper {

    public static Publisher dtoToPublisher(DtoPublisherIU dto) {
        if (dto == null) return null;

        Publisher publisher = new Publisher();
        publisher.setAd(dto.getAd());
        return publisher;
    }

    public static DtoPublisher publisherToDto(Publisher publisher) {
        if (publisher == null) return null;

        DtoPublisher dto = new DtoPublisher();
        dto.setId(publisher.getId());
        dto.setAd(publisher.getAd());
        return dto;
    }

    public static void updatePublisherFromDto(DtoPublisherIU dto, Publisher publisher) {
        if (dto == null || publisher == null) return;

        if (dto.getAd() != null) {
            publisher.setAd(dto.getAd());
        }
    }
}
