package org.example.VacationCalc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "holidays-config")
public class HolidayConfig {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private List<LocalDate> holidays;

    public List<LocalDate> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<LocalDate> holidays) {
        this.holidays = holidays;
    }
}
