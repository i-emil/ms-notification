package com.troojer.msnotification.dao.respository;

import com.troojer.msnotification.dao.InnerNotificationEntity;
import com.troojer.msnotification.dao.SmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface InnerNotificationRepository extends JpaRepository<InnerNotificationEntity, Long> {

    List<InnerNotificationEntity> findAllByUserId(String userId);

}
