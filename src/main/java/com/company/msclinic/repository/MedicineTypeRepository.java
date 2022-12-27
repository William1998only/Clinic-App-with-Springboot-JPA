package com.company.msclinic.repository;

import com.company.msclinic.entity.MedicineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineTypeRepository extends JpaRepository<MedicineType, Long> {
}
