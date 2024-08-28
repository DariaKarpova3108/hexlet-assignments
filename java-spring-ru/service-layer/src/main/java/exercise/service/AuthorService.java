package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        var dtoList = authors.stream()
                .map(authorMapper::map)
                .toList();
        return dtoList;
    }

    public AuthorDTO getAuthor(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var dto = authorMapper.map(author);
        return dto;
    }

    public AuthorDTO create(AuthorCreateDTO createDTO) {
        var author = authorMapper.map(createDTO);
        authorRepository.save(author);
        var dto = authorMapper.map(author);
        return dto;
    }

    public AuthorDTO update(AuthorUpdateDTO updateDTO, Long id) {
        var model = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found " + id));
        authorMapper.update(updateDTO, model);
        authorRepository.save(model);
        return authorMapper.map(model);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    // END
}
