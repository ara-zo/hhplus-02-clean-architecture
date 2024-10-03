package io.hhplus.clean_architecture.controller.dto

import io.hhplus.clean_architecture.domain.lecture.Lecture

class FindLectureResponse(
    var lectureId: Long,
    var lectureName: String,
) {
    companion object {
        fun of(lecture: Lecture): FindLectureResponse {
            return FindLectureResponse(lecture.id, lecture.lectureName)
        }
    }
}