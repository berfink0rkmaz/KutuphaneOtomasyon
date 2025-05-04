

package org.example.kutuphaneotomasyon.Controller;

import org.example.kutuphaneotomasyon.Dto.DtoPublisherIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/api/Publisher")
public class PublisherController  {

    @Autowired
    private IPublisherService publisherService;

    @PostMapping("/save")

    public GenericResponse<?> savePublisher(@RequestBody DtoPublisherIU dto) {
        return publisherService.savePublisher(dto);
    }

    @GetMapping("/listAllPublishers")

    public GenericResponse<?> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/list/{id}")

    public GenericResponse<?> getPublisherbyId(@PathVariable(name = "id") Integer id) {
        return publisherService.getPublisherbyId(id);
    }

    @PutMapping("/update/{id}")

    public GenericResponse<?> updatePublisher(@PathVariable(name = "id") Integer id,
                                              @RequestBody DtoPublisherIU dto) {
        return publisherService.updatePublisher(id, dto);
    }

    @DeleteMapping("/delete/{id}")

    public GenericResponse<?> deletePublisher(@PathVariable(name = "id") Integer id) {
        return publisherService.deletePublisher(id);
    }

    @GetMapping("/{publisherId}/books")
  
    public GenericResponse<?> getBooksByPublisher(@PathVariable(name = "publisherId") Integer publisherId) {
        return publisherService.getBooksByPublisher(publisherId);
    }
}
