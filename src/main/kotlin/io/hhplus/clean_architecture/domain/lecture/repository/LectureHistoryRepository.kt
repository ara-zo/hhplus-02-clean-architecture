package io.hhplus.clean_architecture.domain.lecture.repository

import io.hhplus.clean_architecture.domain.lecture.LectureHistory
import io.hhplus.clean_architecture.domain.lecture.LectureSchedule

interface LectureHistoryRepository {
    fun findLectureHistoryByLectureScheduleAndUserId(lectureSchedule: LectureSchedule, userId: Long): List<LectureHistory>

    fun save(lectureHistory: LectureHistory): LectureHistory
}