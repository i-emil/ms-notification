package com.troojer.msnotification.dao.respository;

import com.troojer.msnotification.dao.EmailMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EmailMessageRepository extends JpaRepository<EmailMessageEntity, Long> {

    @Query(value="SELECT em FROM EmailMessageEntity em WHERE em.status='PENDING' AND (em.sendingDate <= :before OR em.sendingDate is NULL)")
    List<EmailMessageEntity> getMessagesToSend(LocalDateTime before);

}
