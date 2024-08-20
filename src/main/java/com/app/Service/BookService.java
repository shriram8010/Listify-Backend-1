package com.app.Service;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.entity.Book;


@Service
@Transactional
public interface BookService {
  public ApiResponse addBook(BookDto bookDto);
  public List<BookDto> getBooks();
  public ApiResponse updateBook(Long id, BookDto bookDto);
  public BookDto getBookById(Long Id);
  public ApiResponse deleteBook(Long id);
}
