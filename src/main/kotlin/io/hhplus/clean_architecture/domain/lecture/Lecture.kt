package io.hhplus.clean_architecture.domain.lecture

data class Lecture(
    val id: Long,
    val lectureName: String,
    val professorName: String
) {
    companion object {
        fun create(id: Long, lectureName: String, professorName: String): Lecture {
            return Lecture(id, lectureName, professorName)
        }

    }

    fun toDomain(): Lecture {
        return Lecture(this.id, this.lectureName, this.professorName)
    }

}
