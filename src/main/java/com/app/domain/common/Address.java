package com.app.domain.common;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@RequiredArgsConstructor
public class Address {

    private String zipcode;
    private String street;
    private String detail;
}
