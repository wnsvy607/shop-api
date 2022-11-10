package com.app.domain.attendance.entity;

import com.app.domain.attendance.constant.AttendanceStatus;
import com.app.domain.common.BaseTimeEntity;
import com.app.domain.common.constant.GeneralStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendance extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private AttendanceStatus attendanceStatus;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private GeneralStatus generalStatus;


}
