package io.hhplus.clean_architecture.domain.lecture.service

import io.hhplus.clean_architecture.domain.lecture.LectureSchedule

interface LectureValidator {
    fun checkApplyLecture(lectureSchedule: LectureSchedule, userId: Long): Boolean
}