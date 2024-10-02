package io.hhplus.clean_architecture.infra

import io.hhplus.clean_architecture.infra.entity.LectureEntity
import io.hhplus.clean_architecture.infra.entity.LectureScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime


interface LectureScheduleJpaRepository : JpaRepository<LectureScheduleEntity, Long> {
    fun findAllByLecture(lectureEntity: LectureEntity): List<LectureScheduleEntity>

    fun findByCreateDateTimeBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<LectureScheduleEntity>
}