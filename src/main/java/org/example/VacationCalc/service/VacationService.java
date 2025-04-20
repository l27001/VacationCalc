package org.example.VacationCalc.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.example.VacationCalc.config.HolidayConfig;

@Service
public class VacationService {

    private final List<LocalDate> holidays;

    public VacationService(HolidayConfig holidayConfig) {
        this.holidays = holidayConfig.getHolidays();
    }

    public double calculate(double avgSalary, int vacationDays) {
        double avgDaysInMonth = 29.3;
        double avgDaySalary = avgSalary / 12 / avgDaysInMonth;
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

        return calculate(avgSalary, payableDays);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }
}
