package com.example.demo.service;

import com.example.demo.AppTests;
import com.example.demo.model.CompanyEntity;
import com.example.demo.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CompanyServiceTest extends AppTests {
    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    void processCompanyDetails() {
        companyService.processCompanyDetails();
        List<CompanyEntity> result = companyRepository.getAllCompanies();
        assertThat(result).isNotNull();
        assertFalse(result.isEmpty());
    }

}
