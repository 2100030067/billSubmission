package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class CrackersController {

    @Autowired
    private CrackersService crackersService;

    // Display the home page
    @GetMapping("/")
    public String home() {
        return "login";  // Return the name of the JSP file for the home page
    }
    
    @GetMapping("/index")
    public String Home2(@RequestParam(value = "timestamp", required = false) String timestamp, Model model) {
        return "index";  // Return the name of the JSP file for the home page
    }

    
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, 
                              @RequestParam String password, 
                              RedirectAttributes redirectAttributes) {
        // Hardcoded username and password for admin
        String adminUsername = "user";
        String adminPassword = "2085";

        // Check if the username and password match
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            return "index";  // Redirect to the home page on successful login
        } else {
            // Add an error message and redirect back to the login page
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password.");
            return "redirect:/login";
        }
    }

    // Display the bill submission form
    @GetMapping("/submitBill")
    public String showBillForm(Model model) {
        model.addAttribute("bill", new Crackers());  // Add an empty Crackers object to the model
        return "billSubmission";  // Return the name of the JSP file for the bill submission form
    }

    @PostMapping("/submitcrackers")
    public String submitBill(@ModelAttribute Crackers bill, RedirectAttributes redirectAttributes) {
        // Save the bill to the database
        crackersService.saveBill(bill);
        
        // Add success message
        redirectAttributes.addFlashAttribute("successMessage", "Bill updated successfully!");

        // Redirect with a unique query parameter to prevent caching
        return "redirect:/index?timestamp=" + System.currentTimeMillis();  // Use current timestamp
    }


    // Display all submitted bills
    @GetMapping("/crackers")
    public String showBills(Model model) {
        List<Crackers> bills = crackersService.getAllBills();  // Retrieve all bills from the service
        model.addAttribute("bills", bills);  // Add the bills to the model

        // Calculate total bill amount
        long totalAmount = bills.stream().mapToLong(Crackers::getBillamount).sum();
        model.addAttribute("totalAmount", totalAmount);  // Add total amount to the model

        return "crackers";  // Return the name of the JSP file that displays all bills
    }

}
