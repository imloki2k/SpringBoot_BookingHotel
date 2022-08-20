package com.example.bookinghotel.services;

import com.example.bookinghotel.entities.Report;
import com.example.bookinghotel.repositories.HotelReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private HotelReportRepository hotelReportRepository;



    @Override
    public Report save(Report report) {
        return hotelReportRepository.save(report);
    }

    @Override
    public void delete(Long id) {
        hotelReportRepository.deleteById(id);
    }


}
