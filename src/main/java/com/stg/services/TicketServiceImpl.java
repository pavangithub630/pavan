package com.stg.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.stg.entity.Ticket;
import com.stg.entity.User;
import com.stg.exception.TicketException;
import com.stg.exception.UserException;
import com.stg.repository.TicketRepository;
import com.stg.repository.UserRepository;

@Service
public class TicketServiceImpl implements TicketServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository2;

	int seatNo = 0;

	@Override
	public User createUser(User user) {

		return userRepository.save(user);

	}

	@Override
	public List<Ticket> bookTicket(int userId, String password, String tier, int numberOfTickets) throws UserException {

		User user = userRepository.findByUserId(userId);

		if (user == null) {

			throw new UserException("User did not present in the databse");

		} else {

			if (user.getPassword().equals(password)) {
				for (int i = 0; i < numberOfTickets; i++) {

					Ticket ticket = new Ticket();
					ticket.setSeatNo(seatNo);
					ticket.setTicketType(tier);
					ticket.setUser(user);
					seatNo = seatNo + 1;

					ticketRepository2.save(ticket);

				}

				return findTicketsByID(userId);

			} else {
				throw new UserException("Password mismatch");
			}

		}

	}

	@Override
	public User findUserByUserId(int userId) throws UserException {

		User user1 = userRepository.findById(userId).orElse(null);

		System.out.println(user1);

		if (user1 == null) {
			throw new UserException("User did not present in the databse");
		} else {
			return user1;
		}

	}

	@Override
	public String deleteUser(int userid) {

		User user = userRepository.findByUserId(userid);

		if (user == null) {

			throw new UserException("User did not present in the databse");

		} else {
			userRepository.delete(user);

			return "User deleted";
		}
	}

	@Override
	public String deleteTicketByID(int userid, String password, int ticketId) {

		User user = userRepository.findByUserId(userid);

		if (user == null) {

			throw new UserException("User did not present in the databse");

		} else {
			if (user.getPassword().equals(password)) {
				Ticket ticket = ticketRepository2.findByTicketId(ticketId);

				if (ticket == null) {

					throw new TicketException("Ticket with this ticket id did not found in the database");
				} else {
					ticketRepository2.delete(ticket);
					return "Ticket deleted";
				}

			} else {

				throw new UserException("password mismatch");
			}

		}
	}

	@Override
	public List<Ticket> findTicketsByID(int userid) {

		User user1 = userRepository.findById(userid).orElse(null);

		if (user1 == null) {
			throw new UserException("User did not present in the databse");
		}

		List<Ticket> tickets = ticketRepository2.findTicketById(userid);

		if (tickets.size() == 0) {
			throw new TicketException("No tickets book by the user");

		} else {
			return tickets;
		}

	}

	@Override
	public User updateUser(User user) throws UserException {

		User user1 = userRepository.findById(user.getUserId()).orElse(null);

		if (user1 == null) {

			throw new UserException("User did not present in the databse");
		} else {
			return userRepository.save(user);

		}

	}

}
