package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrackersDAO {

    @Autowired
     CrackersRepository crackersRepository;

    // Method to save a bill
    public void saveBill(Crackers bill) {
        crackersRepository.save(bill);
    }

    // Method to retrieve all bills
    public List<Crackers> getAllBills() {
        return crackersRepository.findAll();
    }

    // Additional custom methods can be added here
    // For example, a method to find bills by bill type
    public List<Crackers> findBillsByType(String billType) {
        return crackersRepository.findAll().stream()
            .filter(bill -> bill.getBillType().equals(billType))
            .toList();
    }

    // Example method to delete a bill by ID
    public void deleteBill(Long id) {
        crackersRepository.deleteById(id);
    }
}
