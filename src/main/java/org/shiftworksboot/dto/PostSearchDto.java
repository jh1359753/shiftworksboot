package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostSearchDto {

    private String searchBy;

    private String searchQuery="";
}
