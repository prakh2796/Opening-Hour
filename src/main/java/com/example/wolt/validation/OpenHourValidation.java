package com.example.wolt.validation;

import com.example.wolt.dto.InputTimeDto;
import com.example.wolt.dto.TimeDto;
import com.example.wolt.enums.Type;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class OpenHourValidation implements OpenCloseValidation, TypeValidation, ValueValidation {

    private List<List<TimeDto>> getOpenHoursList(InputTimeDto inputTimeDto) {
        List<List<TimeDto>> openHoursList = new ArrayList<>();
        openHoursList.add(inputTimeDto.getMonday());
        openHoursList.add(inputTimeDto.getTuesday());
        openHoursList.add(inputTimeDto.getWednesday());
        openHoursList.add(inputTimeDto.getThursday());
        openHoursList.add(inputTimeDto.getFriday());
        openHoursList.add(inputTimeDto.getSaturday());
        openHoursList.add(inputTimeDto.getSunday());
        return openHoursList;
    }

    @Override
    public boolean validateOpenCloseHour(InputTimeDto inputTimeDto) {
        List<List<TimeDto>> openHoursList = getOpenHoursList(inputTimeDto);
        List<String> openCloseList = openHoursList.stream()
                .flatMap(list -> list.stream().map(k -> k.getType()))
                .collect(Collectors.toList());
        String prevStatus = openCloseList.get(0);
        for(int i = 1; i < openCloseList.size(); i++) {
            String type = openCloseList.get(i);
            if(type.equalsIgnoreCase(Type.OPEN.name()) && prevStatus.equalsIgnoreCase(Type.OPEN.name()))
                return false;
            if(type.equalsIgnoreCase(Type.CLOSE.name()) && prevStatus.equalsIgnoreCase(Type.CLOSE.name()))
                return false;
            prevStatus = type;
        }
        return true;
    }

    @Override
    public boolean validateType(InputTimeDto inputTimeDto) {
        List<List<TimeDto>> openHoursList = getOpenHoursList(inputTimeDto);
        Set<String> typeList = openHoursList.stream()
                .flatMap(list -> list.stream().map(k -> k.getType().toLowerCase()))
                .collect(Collectors.toSet());
        return typeList.equals(new HashSet<>(Arrays.asList("open", "close")));
    }

    @Override
    public boolean validateValue(InputTimeDto inputTimeDto) {
        List<List<TimeDto>> openHoursList = getOpenHoursList(inputTimeDto);
        List<Integer> valueList = openHoursList.stream()
                .flatMap(list -> list.stream().map(k -> k.getValue()))
                .collect(Collectors.toList());
        return valueList.stream().allMatch(i -> i >= 0 && i <= 86399);
    }
}
