package io.hhplus.clean_architecture.controller.dto

import io.hhplus.clean_architecture.domain.lecture.LectureSchedule
import java.time.LocalDateTime


class FindLectureScheduleResponse(
    var lectureScheduleId: Long,
    var lectureName: String,
    var professorName: String,
    var lectureDateTime: LocalDateTime,
    var capacity: Int,
    var registerCnt: Int
) {
    companion object {
        fun of(lectureSchedule: LectureSchedule): FindLectureScheduleResponse {
            return FindLectureScheduleResponse(
                lectureSchedule.id,
                lectureSchedule.lecture.lectureName,
                lectureSchedule.lecture.professorName,
                lectureSchedule.lectureDateTime,
                lectureSchedule.capacity,
                lectureSchedule.registerCnt
            )
        }
    }
}