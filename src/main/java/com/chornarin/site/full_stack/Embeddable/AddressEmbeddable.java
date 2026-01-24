package com.chornarin.site.full_stack.Embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class AddressEmbeddable {
    private String street;
    private String city;
    private String state;
    private String zipcode;
}
