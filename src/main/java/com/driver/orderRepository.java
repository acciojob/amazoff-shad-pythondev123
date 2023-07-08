package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class orderRepository {
   private Map<String,Order>orderMap= new HashMap<>();
    private Map<String,DeliveryPartner>partnerMap= new HashMap<>();
    private Map<String,String>orderPartnerMap= new HashMap<>();
    private Map<String, List<String>>partnerOrderMap= new HashMap<>();

    public void addOrder(Order order){
        orderMap.put(order.getId(), order);
    }
    public void addPartner(String id){
        partnerMap.put(id, new DeliveryPartner(id));
    }
    public void addOrderPartnerPair(String oId, String pId){
        if(orderMap.containsKey(oId) && partnerMap.containsKey(pId)){
            orderPartnerMap.put(pId, oId);
        }
        List<String> currentOrders= new ArrayList<>();
        if(partnerOrderMap.containsKey(pId)){
            currentOrders= partnerOrderMap.get(pId);
        }
        currentOrders.add(oId);
        partnerOrderMap.put(pId, currentOrders);
        DeliveryPartner deliveryPartner= partnerMap.get(pId);
        deliveryPartner.setNumberOfOrders(currentOrders.size());
    }
    public Order getOrderById(String oid){
        return orderMap.get(oid);
    }
    public DeliveryPartner getPartnerById(String pid){
        return partnerMap.get(pid);
    }
    public Integer getOrderCountByPartnerId(String pid){
        DeliveryPartner partner= partnerMap.get(pid);
        return partner.getNumberOfOrders();
    }
    public List<String> getOrdersByPartnerId(String pId){
        return partnerOrderMap.get(pId);
    }
    public List<String> getAllOrders(){
        List<String> allOrders= new ArrayList<>();
        for(String orders:orderMap.keySet()){
            allOrders.add(orders);
        }
        return allOrders;
    }
    public int getCountOfUnassignedOrders(){
        return orderMap.size()- orderPartnerMap.size();
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String pID){
        Integer count=0;
        String[] Time= time.split(":");
        int newTime= Integer.parseInt(Time[0]) * 60 + Integer.parseInt(Time[1]);
        List<String> orders= partnerOrderMap.get(pID);
        for(String Orders:orders){
            if(orderMap.get(Orders).getDeliveryTime()>newTime){
                count++;
            }
        }
        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String pId){
        int time=0;
        List<String> orders= partnerOrderMap.get(pId);
        for(String Orders:orders){
            if(orderMap.get(Orders).getDeliveryTime()>time){
                time= orderMap.get(Orders).getDeliveryTime();
            }
        }
        int hh= time/60;
        int mm= time%60;
        String HH= String.valueOf(hh);
        String MM= String.valueOf(mm);
        return HH+":"+MM;
    }
    public void deleteOrderById(String orderId){
        orderMap.remove(orderId);
        String partnerId = orderPartnerMap.get(orderId);
        orderPartnerMap.remove(orderId);

        partnerOrderMap.get(partnerId).remove(orderId);

        partnerMap.get(partnerId).setNumberOfOrders(partnerOrderMap.get(partnerId).size());
    }
    public void deletePartnerById(String pId){
        partnerMap.remove(pId);

        List<String> listOfOrders = partnerOrderMap.get(pId);
        partnerOrderMap.remove(pId);

        for(String order: listOfOrders){
            orderPartnerMap.remove(order);
        }
    }

}
