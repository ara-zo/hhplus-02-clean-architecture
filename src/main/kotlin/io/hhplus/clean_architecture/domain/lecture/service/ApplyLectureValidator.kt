package io.hhplus.clean_architecture.domain.lecture.service

import io.hhplus.clean_architecture.domain.lecture.LectureSchedule
import io.hhplus.clean_architecture.domain.lecture.repository.LectureHistoryRepository
import org.springframework.stereotype.Component


@Component
class ApplyLectureValidator(
    private val lectureHistoryRepository: LectureHistoryRepository
) : LectureValidator {
    override fun checkApplyLecture(lectureSchedule: LectureSchedule, userId: Long): Boolean {
        return lectureHistoryRepository.findLectureHistoryByLectureScheduleAndUserId(lectureSchedule, userId) != null
    }
}