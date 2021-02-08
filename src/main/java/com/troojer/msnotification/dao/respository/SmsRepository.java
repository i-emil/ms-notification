package com.troojer.msnotification.dao.respository;

import com.troojer.msnotification.dao.SmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SmsRepository extends JpaRepository<SmsEntity, Long> {

    @Query(value = "SELECT se FROM SmsEntity se WHERE se.createdAt >= :point AND se.status='PENDING'")
    List<SmsEntity> getMessagesToSend(LocalDateTime point);
}
