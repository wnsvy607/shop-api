package com.app.domain.common;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@RequiredArgsConstructor
public class Address {

    @Column(length = 6)
    private String zipcode;
    @Column(length = 30)
    private String street;
    @Column(length = 30)
    private String detail;
}
