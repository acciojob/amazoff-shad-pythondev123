package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class orderService {
//    @Autowired
    orderRepository OrderRepository= new orderRepository();
    public void addOrder(Order order){
        OrderRepository.addOrder(order);
    }
    public void addPartner(String id){
        OrderRepository.addPartner(id);
    }
    public void addOrderPartnerPair(String oId, String pId){
        OrderRepository.addOrderPartnerPair(oId, pId);
    }
    public Order getOrderByID(String oId){
        return OrderRepository.getOrderById(oId);
    }
    public DeliveryPartner getPartnerById(String pId){
        return OrderRepository.getPartnerById(pId);
    }
    public Integer getOrderCountByPartnerId(String pId){
        return OrderRepository.getOrderCountByPartnerId(pId);
    }
    public List<String> getOrdersByPartnerId(String pId){
       return OrderRepository.getOrdersByPartnerId(pId);
    }
    public List<String> getAllOrders(){

         return OrderRepository.getAllOrders();
    }
    public int getCountOfUnassignedOrders(){
        return OrderRepository.getCountOfUnassignedOrders();
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String pID){
        return getOrdersLeftAfterGivenTimeByPartnerId(time, pID);
    }
    public String getLastDeliveryTimeByPartnerId(String pId){
        return OrderRepository.getLastDeliveryTimeByPartnerId(pId);
    }
    public void deleteOrderById(String orderId){
        OrderRepository.deleteOrderById(orderId);
    }
    public void deletePartnerById(String pId){
        OrderRepository.deletePartnerById(pId);
    }
}
