package com.example.energyx.repository;

import com.example.energyx.entity.RelatoriosTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatoriosTurnoRepository extends JpaRepository<RelatoriosTurno, Long> {
}
