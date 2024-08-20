package com.app.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Electronics;


@Repository
public interface ElectronicsDao extends JpaRepository<Electronics, Long> {

}
