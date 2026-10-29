package com.supportdesk.repository;




import com.supportdesk.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Filtering, Searching, and Sorting using Spring Data JPA Specification or Custom Query
    @Query("SELECT t FROM Ticket t WHERE " +
            "(:search IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:priority IS NULL OR t.priority = :priority)")
    Page<Ticket> searchTickets(@Param("search") String search,
                               @Param("status") Ticket.Status status,
                               @Param("priority") Ticket.Priority priority,
                               Pageable pageable);
}