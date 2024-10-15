package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TallerCuatro;

@Repository
public interface TallerCuatroRepository extends JpaRepository<TallerCuatro, Long> {
    // Métodos personalizados si es necesario
}
