package io.hhplus.clean_architecture.domain.lecture

data class Lecture(
    val id: Long,
    val lectureName: String
) {
    companion object {
        fun create(id: Long, lectureName: String): Lecture {
            return Lecture(id, lectureName)
        }

    }

    fun toDomain(): Lecture {
        return Lecture(this.id, this.lectureName)
    }

}
