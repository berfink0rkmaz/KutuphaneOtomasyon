package org.example.kutuphaneotomasyon.Controller.Impl;


import org.example.kutuphaneotomasyon.Controller.IPublisherController;
import org.example.kutuphaneotomasyon.Dto.DtoPublisherIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("rest/api/Publisher")
public class PublisherController implements IPublisherController {

    @Autowired
    private IPublisherService publisherService;

    @PostMapping("/save")
    @Override
    public GenericResponse<?> savePublisher(@RequestBody DtoPublisherIU dto) {
        return publisherService.savePublisher(dto);
    }

    @GetMapping("/listAllPublishers")
    @Override
    public GenericResponse<?> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/list/{id}")
    @Override
    public GenericResponse<?> getPublisherbyId(@PathVariable(name = "id") Integer id) {
        return publisherService.getPublisherbyId(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public GenericResponse<?> updatePublisher(@PathVariable(name = "id") Integer id,
                                              @RequestBody DtoPublisherIU dto) {
        return publisherService.updatePublisher(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public GenericResponse<?> deletePublisher(@PathVariable(name = "id") Integer id) {
        return publisherService.deletePublisher(id);
    }

    @GetMapping("/{publisherId}/books")
    @Override
    public GenericResponse<?> getBooksByPublisher(@PathVariable(name = "publisherId") Integer publisherId) {
        return publisherService.getBooksByPublisher(publisherId);
    }
}
