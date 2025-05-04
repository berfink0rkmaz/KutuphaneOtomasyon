

package org.example.kutuphaneotomasyon.Controller;
import org.example.kutuphaneotomasyon.Dto.DtoCategoryIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/api/Category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/save")

    public GenericResponse<?> saveCategory(@RequestBody DtoCategoryIU dto) {
        return categoryService.saveCategory(dto);
    }

    @GetMapping("/listAllCategories")

    public GenericResponse<?> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/list/{id}")

    public GenericResponse<?> getCategoryById(@PathVariable(name = "id") Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/update/{id}")

    public GenericResponse<?> updateCategory(@PathVariable(name = "id") Integer id,
                                             @RequestBody DtoCategoryIU dto) {
        return categoryService.updateCategory(id, dto);
    }

    @DeleteMapping("/delete/{id}")

    public GenericResponse<?> deleteCategory(@PathVariable(name = "id") Integer id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/{categoryId}/books")

    public GenericResponse<?> getBooksByCategoryId(@PathVariable(name = "categoryId") Integer categoryId) {
        return categoryService.getBooksByCategoryId(categoryId);
    }
}
