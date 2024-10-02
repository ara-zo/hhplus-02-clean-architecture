package io.hhplus.clean_architecture.domain.lecture

data class LectureHistory(
    private val id: Long,
    private val lectureSchedule: LectureSchedule,
    private val userId: Long
) {
    fun create(id: Long, lectureSchedule: LectureSchedule, userId: Long): LectureHistory {
        return LectureHistory(id, lectureSchedule, userId)
    }
}

