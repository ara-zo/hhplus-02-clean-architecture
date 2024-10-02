package io.hhplus.clean_architecture.domain.lecture.repository

import io.hhplus.clean_architecture.domain.lecture.Lecture
import io.hhplus.clean_architecture.domain.lecture.LectureSchedule
import java.time.LocalDate

interface LectureScheduleRepository {
    // 특강 스케쥴 조회
    fun findById(lectureScheduleId: Long): LectureSchedule

    fun findAllLectureScheduleList(lecture: Lecture): List<LectureSchedule>

    fun findLectureScheduleByCreateDate(searchDate: LocalDate = LocalDate.now()): List<LectureSchedule>

    fun save(lectureSchedule: LectureSchedule): LectureSchedule
}