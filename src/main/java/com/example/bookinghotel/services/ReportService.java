package com.example.bookinghotel.services;

import com.example.bookinghotel.entities.Report;

public interface ReportService {

    Report save(Report report);

    void delete(Long id);

}
