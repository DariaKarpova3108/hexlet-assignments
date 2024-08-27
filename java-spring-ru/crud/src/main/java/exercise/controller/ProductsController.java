package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;


    /*
    GET /products – просмотр списка всех товаров
    GET /products/{id} – просмотр конкретного товара
    POST /products – добавление нового товара.
При указании id несуществующей категории должен вернуться ответ с кодом 400 Bad request
    PUT /products/{id} – редактирование товара.
При редактировании мы должны иметь возможность поменять название, цену и категорию товара.
При указании id несуществующей категории также должен вернуться ответ с кодом 400 Bad request
    DELETE /products/{id} – удаление товара
    * */

    // BEGIN
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getList() {
        var models = productRepository.findAll();
        var dtoList = models.stream()
                .map(productMapper::map)
                .toList();
        return dtoList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO show(@PathVariable Long id) {
        var model = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        var dto = productMapper.map(model);
        return dto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO createDTO) {
        var model = productMapper.map(createDTO);
        productRepository.save(model);
        var dto = productMapper.map(model);
        return dto;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@Valid @RequestBody ProductUpdateDTO updateDTO, @PathVariable Long id) {
        var model = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        productMapper.update(updateDTO, model);
        productRepository.save(model);
        var dto = productMapper.map(model);
        return dto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    // END
}
