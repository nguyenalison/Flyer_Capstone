package org.alisonnguyen.flyercapstone.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Getter
@Setter
public class ModifyCalendarsDTO {
    @NotEmpty
    private Long id;

    @NotEmpty
    private String name;
}
