package com.stg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.stg.entity.Ticket;
import com.stg.entity.User;

public interface TicketServices {

	public abstract User createUser(User user);

	public abstract List<Ticket> bookTicket(int userId, String password, String tier, int numberOfTickets);

	public abstract User findUserByUserId(int userId);

	public abstract String deleteUser(int userid);

	public abstract String deleteTicketByID(int userid, String password, int ticketId);

	public abstract List<Ticket> findTicketsByID(int userid);

	public abstract User updateUser(User user);

}
