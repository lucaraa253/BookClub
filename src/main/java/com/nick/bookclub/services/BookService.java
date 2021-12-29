package com.nick.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nick.bookclub.models.Book;
import com.nick.bookclub.repositories.BookRepository;



@Service
public class BookService {
	private final BookRepository bookRepository;
	
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	// returns all the books
		public List<Book> allBooks() {
			return bookRepository.findAll();
		}

	//  Create
		// creates a book
		public Book createBook(Book e) {
			return bookRepository.save(e);
		}

	//  Read one
		// retrieves a book
		public Book findBook(Long id) {
			Optional<Book> optionalBook = bookRepository.findById(id);
			if (optionalBook.isPresent()) {
				return optionalBook.get();
			} else {
				return null;
			}
		}

		public void deleteBook(Long id) {
			bookRepository.deleteById(id);
		}



		public Book updateBook(Book book) {
			return bookRepository.save(book);

		}
}