package org.example.VacationCalc.controller;

import java.time.LocalDate;

import org.example.VacationCalc.service.VacationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.example.VacationCalc.exception.ApiException;

@RestController
public class VacationController {

    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/calculate")
    public double calculate(
        @RequestParam double avgSalary,
        @RequestParam int vacationDays,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ) {
            if (avgSalary <= 0)
                throw new ApiException("Средняя зарплата должна быть больше нуля");

            if (vacationDays <= 0)
                throw new ApiException("Количество дней отпуска должно быть больше нуля");

            if (startDate == null) {
                return vacationService.calculate(avgSalary, vacationDays);
            } else {
                return vacationService.calculateWithHolidays(avgSalary, vacationDays, startDate);
            }
    }

}
