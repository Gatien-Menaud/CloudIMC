package com.cloud.imc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImcRecordRepository extends JpaRepository<ImcRecord, Long> {
}