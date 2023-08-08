package com.oncha.oncha_web.domain.admin.repository;

import com.oncha.oncha_web.domain.admin.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
}
