package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrackersService {

    @Autowired
    private CrackersDAO crackersDAO;

    // Method to get all bills
    public List<Crackers> getAllBills() {
        return crackersDAO.getAllBills();
    }

    // Method to save a bill
    public void saveBill(Crackers bill) {
        crackersDAO.saveBill(bill);
    }

    // Additional methods can delegate to DAO as needed
}
