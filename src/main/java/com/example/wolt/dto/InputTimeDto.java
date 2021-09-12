package com.example.wolt.dto;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Opening and closing hour(s) on Monday")
    private List<TimeDto> Monday;
    @NotNull
    @ApiModelProperty(notes = "Opening and closing hour(s) on Tuesday")
    private List<TimeDto> Tuesday;
    @NotNull
    @ApiModelProperty(notes = "Opening and closing hour(s) on Wednesday")
    private List<TimeDto> Wednesday;
    @NotNull
    @ApiModelProperty(notes = "Opening and closing hour(s) on Thursday")
    private List<TimeDto> Thursday;
    @NotNull
    @ApiModelProperty(notes = "Opening and closing hour(s) on Friday")
    private List<TimeDto> Friday;
    @NotNull
    @ApiModelProperty(notes = "Opening and closing hour(s) on Saturday")
    private List<TimeDto> Saturday;
    @NotNull
    @ApiModelProperty(notes = "Opening and closing hour(s) on Sunday")
    private List<TimeDto> Sunday;
}
