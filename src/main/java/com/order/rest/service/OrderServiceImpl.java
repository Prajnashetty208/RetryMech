package com.order.rest.service;

import com.order.rest.model.Order;
import com.order.rest.repository.OrderRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements com.order.rest.service.OrderService {

    //private DiscoveryClient discoveryClient;

    //private FeignClientInt feignClient;

    private RestTemplate restTemplate;

    @Autowired
    private OrderRepo orderDAO;

    @Override
    //@CircuitBreaker(name="orderservice")
    @Retry(name="retryservice")
    public Order save(Order obj) throws IOException {
        //finds all existing orders using discovery client
        //discoveryClient();
//        loadBalancedUsingRestTemplate();
//        System.out.println("Fetching the counter value using Feign client ::"+
//                this.feignClient.fetchItemsCount());
        System.out.println("tryib");
        throw new IOException();
        //return this.orderDAO.save(obj);
    }

    @Override
    public Set<Order> findAll() {
        System.out.println("findAll");
        return new HashSet<>(this.orderDAO.findAll());
    }

    @Override
    public Order findById(Long id) {
        return this.orderDAO.findById(id)
                .orElseThrow(OrderServiceImpl::invalidOrderId);
    }

    private static IllegalArgumentException invalidOrderId() {
        return new IllegalArgumentException("Invalid Order Id");
    }

    @Override
    public void deleteOrder(Long id) {
        this.orderDAO.deleteById(id);
    }

    private void loadBalancedUsingRestTemplate() {
        Object response = this.restTemplate.getForEntity("http://orderservice/api/order/getOrder",
                null, Object.class);

    }

    public Order fallBackImplementation(Order order, Throwable throwable){
        System.out.println("Came inside the fallback implementation method ::::");
        return null;
    }

//    private void discoveryClient(){
//        List<ServiceInstance> services = this.discoveryClient.getInstances("ORDERSERVICE");
//        System.out.println("Services = "+ services);
//        if (services != null && services.size() > 0 ) {
//            //invoke find all
//            String uri = services.get(0).getUri().toString() + "/api/order/getOrder";
//            Object response = this.restTemplate.getForEntity(uri, null, Object.class);
//            System.out.println("Response = "+ response);
//        }
//    }

}
