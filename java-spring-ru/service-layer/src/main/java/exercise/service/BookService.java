package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper mapper;

    // BEGIN
    public List<BookDTO> getAll() {
        var listModels = repository.findAll();
        return listModels.stream().map(mapper::map).toList();
    }

    public BookDTO getBook(Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found " + id));
        var dto = mapper.map(book);
        return dto;
    }

    public BookDTO create(BookCreateDTO createDTO) {
        Book book = mapper.map(createDTO);
        repository.save(book);
        var dto = mapper.map(book);
        return dto;
    }

    public BookDTO update(BookUpdateDTO updateDTO, Long id) {
        Book model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found " + id));
        mapper.update(updateDTO, model);
        repository.save(model);
        return mapper.map(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
