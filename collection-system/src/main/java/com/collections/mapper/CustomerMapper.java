package com.collections.mapper;

import com.collections.dto.CustomerRequestDTO;
import com.collections.dto.CustomerResponseDTO;
import com.collections.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());

        return customer;
    }

    public static CustomerResponseDTO toResponseDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());

        return dto;
    }
}
