package com.example.demo.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.AddressResponse;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeResponse;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private WebClient webClient;
	
	//@Autowired
	//RestTemplate restTemplate;
	
	
//	@Value("${addressservice.base.url}")
//	private String addressBaseUrl;
	
//	public EmployeeServiceImpl(@Value("${addressservice.base.url}") String addressBaseUrl , RestTemplateBuilder builder) {
//		this.restTemplate = builder.rootUri(addressBaseUrl).build();
//	}
	
	@Override
	public EmployeeResponse getEmployeeDetails(int id) {
		
		Employee employee = employeeRepository.findById(id).get();
//		EmployeeResponse employeeResponse = new EmployeeResponse();
//		employeeResponse.setId(employee.getId());
//		employeeResponse.setName(employee.getName());
//		employeeResponse.setEmail(employee.getEmail());
//		employeeResponse.setBloodGroup(employee.getBloodGroup());
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
//		AddressResponse addressResponse = callingRestTemplate(id);
		
		//using webclient
		AddressResponse addressResponse =webClient.
										get().
										uri("/address/"+id).
										retrieve().
										bodyToMono(AddressResponse.class).
										block();
		employeeResponse.setAddressResponse(addressResponse);
		return employeeResponse;
	}

//	private AddressResponse callingRestTemplate(int id) {
   //		AddressResponse addressResponse = restTemplate.getForObject(addressBaseUrl+"/address/{id}", AddressResponse.class,id);
//		return restTemplate.getForObject("/address/{id}", AddressResponse.class,id);
//	}

}
