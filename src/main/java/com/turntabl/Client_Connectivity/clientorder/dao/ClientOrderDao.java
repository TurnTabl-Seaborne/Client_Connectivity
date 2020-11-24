package com.turntabl.Client_Connectivity.clientorder.dao;

import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOrderDao extends JpaRepository<ClientOrder,Integer> {
    List<ClientOrder> findAllByClientOrderId(Integer id);
    ClientOrder findByClientOrderId(Integer id);

    @Query("select co from ClientOrder co where co.portfolio.user.UserId = ?1")
    List<ClientOrder> findAllByUserId(Long user_id);


}
