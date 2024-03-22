package org.alisonnguyen.flyercapstone.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReadCalendarsDTO {
    @NotEmpty
    private Long id;

    @NotEmpty
    private String name;

    private List<ReadCalendarsDTO> calendarsDTOList;
}