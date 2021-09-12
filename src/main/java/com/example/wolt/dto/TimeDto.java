package com.example.wolt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDto {
    @ApiModelProperty(notes = "OPENING time or CLOSING time of the restaurant")
    private String type;
    @ApiModelProperty(notes = "This is the Value of time:" +
            "An Integer with value within the range of 0 to 86399")
    private Integer value;

    @Override
    public String toString() {
        return "Time{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
