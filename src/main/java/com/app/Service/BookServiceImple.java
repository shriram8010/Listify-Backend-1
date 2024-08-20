package com.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapter;
import org.springframework.stereotype.Service;

import com.app.Dao.BookDao;
import com.app.Dao.UserDao;
import com.app.customexception.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.entity.Book;
import com.app.entity.User;

@Service
@Transactional
public class BookServiceImple implements BookService{

	@Autowired
	private BookDao bookDao;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;
	

	
	
	@Override
	public ApiResponse addBook(BookDto bookDto) {
		User user = userDao.findById(bookDto.getUserId())
				.orElseThrow(()->new ResourceNotFoundException("Invalid product id"));
		Book book = modelMapper.map(bookDto,Book.class);
		user.addUser(book);
		bookDao.save(book);
		return new ApiResponse("product added successfully");
	}

	@Override
	public List<BookDto> getBooks() {	
		List<Book> books= bookDao.findAll();
		
		List<BookDto> bookDto= convertToDto(books);
		
	       for(int i=0;i<bookDto.size();i++) {
	    	   bookDto.get(i).setUserId(books.get(i).getUser().getId());
	       }
	       
	       return bookDto;	
	}
	
	public List<BookDto> convertToDto(List<Book> books) {
        return books.stream()
                    .map(book -> modelMapper.map(book, BookDto.class))
                    .collect(Collectors.toList());
    }
	
    public ApiResponse updateBook(Long id, BookDto bookDto) {
        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            modelMapper.map(bookDto, book);
           bookDao.save(book);
           return new ApiResponse("Book updated successfully");
        } else {
            throw new RuntimeException("Book not found with id " + id);
        }
    }
    
   public BookDto getBookById(Long Id) {	
    Book book=bookDao.findById(Id).orElseThrow(()->new ResourceNotFoundException("Invalid product id"));
    
    BookDto bookDto= modelMapper.map(book,BookDto.class);
    bookDto.setUserId(book.getUser().getId());
    return bookDto; 	
    }
   
   public ApiResponse deleteBook(Long id) {
	   Optional<Book> optionalBook = bookDao.findById(id);
       if (optionalBook.isPresent()) {
           Book book = optionalBook.get();
           User user=userDao.findById(book.getUser().getId()).orElseThrow(()->new ResourceNotFoundException("Invalid id"));
           user.removeUser(book);
          bookDao.delete(book);
          return new ApiResponse("Book deleted successfully");
       } else {
           throw new RuntimeException("Book not found with id " + id);
       }
   }
   
   
   

   
}
