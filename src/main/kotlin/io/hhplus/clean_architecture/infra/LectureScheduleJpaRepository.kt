package io.hhplus.clean_architecture.infra

import io.hhplus.clean_architecture.infra.entity.LectureScheduleEntity
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import java.time.LocalDateTime


interface LectureScheduleJpaRepository : JpaRepository<LectureScheduleEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findLectureScheduleById(lectureScheduleId: Long?): LectureScheduleEntity

    fun findByCreateDateTimeBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<LectureScheduleEntity>
}