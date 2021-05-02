package com.order.rest.controller;

import com.order.rest.model.Order;
import com.order.rest.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/api/order/")
@AllArgsConstructor
public class Controller {

    @Autowired
    public OrderService service;

    @PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Order> publish(@RequestBody Order request) throws IOException {
        return new ResponseEntity<Order>(service.save(request), HttpStatus.OK);
    }

    @GetMapping(value = "/getOrder",produces = "application/json")
    public ResponseEntity<Set<Order>> getOrder(){
        return  new ResponseEntity<Set<Order>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/getOrderId/{id}",produces = "application/json")
    public ResponseEntity<Order> getOrderId(@PathVariable("id") long id){
        return  new ResponseEntity<Order>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteOrderId/{id}",produces = "application/json")
    public void deleteOrderId(@PathVariable("id") long id){
        this.service.deleteOrder(id);
    }


}
