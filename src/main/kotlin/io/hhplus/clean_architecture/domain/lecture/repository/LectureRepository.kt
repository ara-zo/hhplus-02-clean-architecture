package io.hhplus.clean_architecture.domain.lecture.repository

import io.hhplus.clean_architecture.domain.lecture.Lecture

interface LectureRepository {

    // 특강 조회
    fun findById(lectureId: Long): Lecture

    // 특강 목록 조회
    fun findAll(): List<Lecture>
}