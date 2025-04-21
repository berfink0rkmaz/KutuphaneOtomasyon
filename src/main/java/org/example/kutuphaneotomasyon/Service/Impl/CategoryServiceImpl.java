package org.example.kutuphaneotomasyon.Service.Impl;

import org.example.kutuphaneotomasyon.Dto.DtoBook;
import org.example.kutuphaneotomasyon.Dto.DtoCategory;
import org.example.kutuphaneotomasyon.Dto.DtoCategoryIU;
import org.example.kutuphaneotomasyon.Entity.Book;
import org.example.kutuphaneotomasyon.Entity.Category;
import org.example.kutuphaneotomasyon.Mapper.BookMapperView;
import org.example.kutuphaneotomasyon.Mapper.CategoryMapper;
import org.example.kutuphaneotomasyon.Repository.BookRepository;
import org.example.kutuphaneotomasyon.Repository.CategoryRepository;
import org.example.kutuphaneotomasyon.ResponseMessage.Constants;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    private final BookMapperView bookMapperView = new BookMapperView();

    @Override
    public GenericResponse<?> saveCategory(DtoCategoryIU dto) {
        System.out.println("saveCategory called...");

        Category category = CategoryMapper.dtoToCategory(dto);
        Category saved = categoryRepository.save(category);
        return GenericResponse.success(CategoryMapper.categoryToDto(saved));
    }

    @Override
    public GenericResponse<?> getAllCategories() {
        System.out.println("getAllCategories called...");

        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return GenericResponse.error(Constants.EMPTY_LIST);
        } else {
            List<DtoCategory> dtoList = categories.stream()
                    .map(CategoryMapper::categoryToDto)
                    .collect(Collectors.toList());
            return GenericResponse.success(dtoList);
        }
    }

    @Override
    public GenericResponse<?> getCategoryById(Integer id) {
        System.out.println("getCategoryById called...");

        Optional<Category> optional = categoryRepository.findById(id);
        return optional.map(category -> GenericResponse.success(CategoryMapper.categoryToDto(category)))
                .orElseGet(() -> GenericResponse.error(Constants.EMPTY_ID));
    }

    @Override
    public GenericResponse<?> updateCategory(Integer id, DtoCategoryIU dto) {
        System.out.println("updateCategory called...");

        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category dbCategory = optional.get();
            CategoryMapper.updateCategoryFromDto(dto, dbCategory);
            Category updated = categoryRepository.save(dbCategory);
            return GenericResponse.success(CategoryMapper.categoryToDto(updated));
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> deleteCategory(Integer id) {
        System.out.println("deleteCategory called...");

        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            categoryRepository.delete(optional.get());
            return GenericResponse.success("Kategori başarıyla silindi.");
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> getBooksByCategoryId(Integer categoryId) {
        System.out.println("getBooksByCategoryId called...");

        if (!categoryRepository.existsById(categoryId)) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }

        List<Book> books = bookRepository.findByCategoryId(categoryId);
        if (books.isEmpty()) {
            return GenericResponse.error("Bu kategoriye ait kitap bulunamadı.");
        }

        List<DtoBook> dtoList = books.stream()
                .filter(book -> book.getAuthor() != null && book.getPublisher() != null && book.getCategory() != null)
                .map(bookMapperView::bookToDto)
                .collect(Collectors.toList());

        return GenericResponse.success(dtoList);
    }
}