package com.nick.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nick.bookclub.models.Book;
import com.nick.bookclub.models.LoginUser;
import com.nick.bookclub.models.User;
import com.nick.bookclub.services.BookService;
import com.nick.bookclub.services.UserService;


    

    
@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;
    @Autowired
    private BookService bookServ;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/home";
    }
//    Success Route
    @GetMapping("/home")
    public String home(HttpSession sesh, Model model) {
//    	Retrieving from session
    	Long userID = (Long) sesh.getAttribute("user_id");
//    	Route guard - check if user Id is not null and redirect
    	if(userID == null) {
    		return "redirect:/";
    	}else {
    		User thisUser = userServ.findOne(userID);
    		List<Book> books = bookServ.allBooks();
    		model.addAttribute("books", books);
    		model.addAttribute("name", thisUser);
    		return "books.jsp";
    	}
    	
    }
//    Logout Route
    @GetMapping("logout")
    public String logout(HttpSession sesh) {
    	sesh.invalidate();
    	return "redirect:/";
    }
    
//    Books Routes
    @GetMapping("/books/new")
    public String newBook(HttpSession sesh,@ModelAttribute("book") Book book, Model model) {
    	Long userID = (Long) sesh.getAttribute("user_id");
//    	Route guard - check if user Id is not null and redirect
    	if(userID == null) {
    		return "redirect:/";
    	}else {
    	User seshUser = userServ.findOne(userID);
		model.addAttribute("user", seshUser);
    	return "newbook.jsp";
    	}
    }
    	
    @PostMapping("/newBook")
    public String bookNew(@Valid @ModelAttribute("newBook") Book book, BindingResult result) {
        if(result.hasErrors()) {
            return "newbook.jsp";
        }else {
        
        bookServ.createBook(book);
        return "redirect:/home";
        }
    }
    
    @GetMapping("/{id}/showBook")
    public String showBook(HttpSession sesh,@PathVariable("id") Long id, Model model) {
    	Long userID = (Long) sesh.getAttribute("user_id");
//    	Route guard - check if user Id is not null and redirect
//    	if(userID == null) {
//    		return "redirect:/";
//    	}else {
    	User currentUser = userServ.findOne(userID);
    	Book book = bookServ.findBook(id);
		model.addAttribute("user", currentUser);
		model.addAttribute("book", book);
    	return "showbook.jsp";
    	}
    
    @RequestMapping("/{id}/editBook")
    public String editBook(HttpSession sesh,@PathVariable("id") Long id, Model model) {
    	Long userID = (Long) sesh.getAttribute("user_id");
    	Book book = bookServ.findBook(id);
//    	Route guard - check if user Id is not null and redirect
    	if(userID == null) {
    		return "redirect:/";
    	}else {
    	model.addAttribute("userID", userID);
		model.addAttribute("book", book);
    	return "editbook.jsp";
    	}
    }
    @RequestMapping(value="/{id}/book", method=RequestMethod.PUT)
    public String editOneBook(@Valid @ModelAttribute("book")Book book, BindingResult result) {
    	if(result.hasErrors()) {
            return "editbook.jsp";
        }else {
        
        bookServ.updateBook(book);
        return "redirect:/home";
        }
    }
    
    @RequestMapping(value="/deleteBook/{id}", method=RequestMethod.DELETE)
    	public String destroy(@PathVariable("id") Long id) {
    	bookServ.deleteBook(id);
    	return "redirect:/home";
    }
}
