package io.hhplus.clean_architecture.infra

import io.hhplus.clean_architecture.infra.entity.LectureEntity
import org.springframework.data.jpa.repository.JpaRepository


interface LectureJpaRepository : JpaRepository<LectureEntity, Long>