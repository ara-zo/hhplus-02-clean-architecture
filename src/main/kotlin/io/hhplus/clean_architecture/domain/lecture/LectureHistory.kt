package io.hhplus.clean_architecture.domain.lecture

data class LectureHistory(
    val id: Long?,
    val lectureSchedule: LectureSchedule,
    val userId: Long
) {
    companion object {
        fun create(id: Long?, lectureSchedule: LectureSchedule, userId: Long): LectureHistory {
            return LectureHistory(id, lectureSchedule, userId)
        }
    }
}

