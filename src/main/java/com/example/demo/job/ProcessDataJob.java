package com.example.demo.job;

import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProcessDataJob {

    private final CompanyService companyService;

    @Scheduled(fixedRate = 300 * 1000)
    public void test() {
        companyService.processCompanyDetails();
    }

}
