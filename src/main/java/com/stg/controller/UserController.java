package com.stg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Ticket;
import com.stg.entity.User;
import com.stg.exception.GlobalException;
import com.stg.exception.UserException;
import com.stg.repository.TicketRepository;
import com.stg.repository.UserRepository;
import com.stg.services.TicketServiceImpl;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private TicketServiceImpl impl;
	@Autowired
	private TicketRepository ticketRepository2;

	@PostMapping(value = "/createuser", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User user) {

		User user1= impl.createUser(user);
		return new ResponseEntity<>(user1,HttpStatus.OK);
	}

	@GetMapping(value = "/getuser/{id}", consumes = org.springframework.http.MediaType.ALL_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findUserByUserId(@PathVariable("id") int userid) {

		User user1= impl.findUserByUserId(userid);
		return new ResponseEntity<>(user1,HttpStatus.OK);
	}

	
	@PostMapping(value = "/bookTicket", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ticket>> bookTicket(@RequestParam int  userId,@RequestParam String  password, @RequestParam String  tier,@RequestParam int numberOfTickets) {

		List<Ticket> tickets=  impl.bookTicket(userId,password,tier,numberOfTickets);
		
		return new ResponseEntity<>(tickets,HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/deleteuser")
	public ResponseEntity<String> deleteUser(@RequestParam int userid) {
		String str= impl.deleteUser(userid);
		 return new ResponseEntity<>(str,HttpStatus.OK);
	}
	@DeleteMapping(value = "/deleteuserticket")
	public ResponseEntity<String> deleteTicketByID(@RequestParam int userid,@RequestParam String password,@RequestParam int ticketId) {

		String str=impl.deleteTicketByID(userid, password, ticketId);
		return new ResponseEntity<>(str,HttpStatus.OK);
	}
	
	@GetMapping(value = "/findticketsbyid", consumes = org.springframework.http.MediaType.ALL_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ticket>> findTicketsByID(@RequestParam int userid) {

		List<Ticket> tickets= impl.findTicketsByID(userid);
		return new ResponseEntity<>(tickets,HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/updateuser", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User user1= impl.updateUser(user);
		return new ResponseEntity<>(user1,HttpStatus.OK);
		
	}

	
}
