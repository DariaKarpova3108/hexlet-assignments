package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping
    public List<ProductDTO> getList() {
        var listModel = productRepository.findAll();
        var result = new ArrayList<ProductDTO>();
        for (var model : listModel) {
            var dto = productMapper.map(model);
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable Long id) {
        var model = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        var dto = productMapper.map(model);
        return dto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO productCreateDTO) {
        var model = productMapper.map(productCreateDTO);
        productRepository.save(model);

        var dto = productMapper.map(model);
        return dto;
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductUpdateDTO updateDTO, @PathVariable Long id) {
        var model = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        productMapper.update(updateDTO, model);
        productRepository.save(model);
        var dto = productMapper.map(model);
        return dto;
    }

    // END
}
