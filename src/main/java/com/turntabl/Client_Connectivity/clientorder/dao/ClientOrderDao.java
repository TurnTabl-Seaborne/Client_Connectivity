package com.turntabl.Client_Connectivity.clientorder.dao;

import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOrderDao extends JpaRepository<ClientOrder,Integer> {
    ClientOrder findAllById(Integer id);
}
