package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//inject customer DAO 
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomer(Model model)
	{
		List<Customer> customers=customerService.getCustomers();
		
		//add customers to model
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model)
	{
		Customer customer=new Customer();
		model.addAttribute("customer",customer);
		return "add-customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer customer)
	{
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model)
	{
		//get customer from service
		Customer customer=customerService.getCustomer(customerId);
				
		model.addAttribute("customer",customer);
		
		return "add-customer-form";
	}
	
	
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId,Model model)
	{
		customerService.deleteCustomer(customerId);
				
		return "redirect:/customer/list";

	}
	
	
}
