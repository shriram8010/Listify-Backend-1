package com.app.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.User;
import com.app.entity.Vehicle;



@Repository
public interface VehicleDao extends JpaRepository<Vehicle,Long>{

}
