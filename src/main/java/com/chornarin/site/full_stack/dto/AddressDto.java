package com.chornarin.site.full_stack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
    private String Street;
    private String City;
    private String State;
    private String Zipcode;

}
