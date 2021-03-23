package com.troojer.msnotification.dao.respository;

import com.troojer.msnotification.dao.NotificationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InnerNotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findAllByUserId(String userId, Pageable pageable);

}
