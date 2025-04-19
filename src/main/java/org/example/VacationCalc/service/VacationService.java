package org.example.VacationCalc.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.example.VacationCalc.config.HolidayConfig;

@Service
public class VacationService {

    private final double AVG_DAYS_IN_MONTH = 29.3;

    private final List<LocalDate> holidays;

    public VacationService(HolidayConfig holidayConfig) {
        this.holidays = holidayConfig.getHolidays();
        System.out.println(this.holidays);
    }

    public double calculate(double avgSalary, int vacationDays) {
        double avgDaySalary = avgSalary / 12 / AVG_DAYS_IN_MONTH;
        return Math.round(avgDaySalary * vacationDays * 100.0) / 100.0;
    }

    public double calculateWithHolidays(double avgSalary, int vacationDays, LocalDate startDate) {
        int payableDays = 0;
        LocalDate currentDate = startDate;

        while (payableDays < vacationDays) {
            if (!isWeekend(currentDate) && !isHoliday(currentDate)) {
                payableDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        double avgDaySalary = avgSalary / 12 / AVG_DAYS_IN_MONTH;
        return Math.round(avgDaySalary * payableDays * 100.0) / 100.0;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }
}
