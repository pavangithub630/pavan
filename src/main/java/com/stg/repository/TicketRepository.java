package com.stg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stg.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query(value = "select * from ticket where user_user_id=?1", nativeQuery = true)
	public List<Ticket> findTicketById(int userId);

	public Ticket findByTicketId(int ticketId);

}
