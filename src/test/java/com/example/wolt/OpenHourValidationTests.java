package com.example.wolt;

import com.example.wolt.dto.InputTimeDto;
import com.example.wolt.validation.OpenHourValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
public class OpenHourValidationTests {
    private OpenHourValidation openHourValidation;
    private ObjectMapper mapper;

    @Before
    public void initialize() {
        openHourValidation = new OpenHourValidation();
        mapper = new ObjectMapper();
    }

    @Test
    public void testValidOpenCloseHour() throws IOException {
        InputTimeDto inputTimeDto = mapper.readValue(new File("src/test/resources/ValidJSONFormat.json"), InputTimeDto.class);
        assertEquals(true, openHourValidation.validateOpenCloseHour(inputTimeDto));
    }

    @Test
    public void testInvalidOpenCloseHour() throws IOException {
        InputTimeDto inputTimeDto = mapper.readValue(new File("src/test/resources/InvalidOpenCloseHour.json"), InputTimeDto.class);
        assertEquals(false, openHourValidation.validateOpenCloseHour(inputTimeDto));
    }

    @Test
    public void testValidType() throws IOException {
        InputTimeDto inputTimeDto = mapper.readValue(new File("src/test/resources/ValidJSONFormat.json"), InputTimeDto.class);
        assertEquals(true, openHourValidation.validateType(inputTimeDto));
    }

    @Test
    public void testInvalidType() throws IOException {
        InputTimeDto inputTimeDto = mapper.readValue(new File("src/test/resources/InvalidType.json"), InputTimeDto.class);
        assertEquals(false, openHourValidation.validateType(inputTimeDto));
    }

    @Test
    public void testInvalidValue() throws IOException {
        InputTimeDto inputTimeDto = mapper.readValue(new File("src/test/resources/InvalidValue.json"), InputTimeDto.class);
        assertEquals(false, openHourValidation.validateValue(inputTimeDto));
    }
}
