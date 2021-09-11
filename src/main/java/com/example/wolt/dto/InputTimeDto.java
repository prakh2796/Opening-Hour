package com.example.wolt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputTimeDto {
    @NotNull
    private ArrayList<TimeDto> Monday;
    @NotNull
    private ArrayList<TimeDto> Tuesday;
    @NotNull
    private ArrayList<TimeDto> Wednesday;
    @NotNull
    private ArrayList<TimeDto> Thursday;
    @NotNull
    private ArrayList<TimeDto> Friday;
    @NotNull
    private ArrayList<TimeDto> Saturday;
    @NotNull
    private ArrayList<TimeDto> Sunday;
}
