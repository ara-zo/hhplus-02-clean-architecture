package io.hhplus.clean_architecture.controller.dto

import io.hhplus.clean_architecture.domain.lecture.LectureSchedule


class ApplyLectureResponse(
    var lectureId: Long,
    var lectureScheduleId: Long,
    var name: String
) {
    companion object {
        fun of(lectureSchedule: LectureSchedule): ApplyLectureResponse {
            val lecture = lectureSchedule.lecture
            return ApplyLectureResponse(lecture.id, lectureSchedule.id, lecture.lectureName)
        }
    }
}