package com.example.wolt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputTimeDto {
    @NotNull
    private List<TimeDto> Monday;
    @NotNull
    private List<TimeDto> Tuesday;
    @NotNull
    private List<TimeDto> Wednesday;
    @NotNull
    private List<TimeDto> Thursday;
    @NotNull
    private List<TimeDto> Friday;
    @NotNull
    private List<TimeDto> Saturday;
    @NotNull
    private List<TimeDto> Sunday;
}
