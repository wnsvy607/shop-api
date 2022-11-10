package com.app.domain.attendance.repository;

import com.app.domain.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository<Attendance> extends JpaRepository<Attendance, Long> {
}
