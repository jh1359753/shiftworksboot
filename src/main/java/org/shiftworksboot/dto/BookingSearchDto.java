package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookingSearchDto {

    private String searchBy;

    private String searchQuery = "";
}
