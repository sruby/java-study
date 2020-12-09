package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.dto.IpoDTO;
import io.sruby.github.test.unit.entity.Company;
import io.sruby.github.test.unit.entity.Ipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
//@Transactional
class IpoServiceTest {
    @Mock
    CompanyService companyService;
    @InjectMocks
    IpoService ipoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGet() {
        String code = "8888";
        String companyId = "00001";
        when(companyService.get(companyId)).thenReturn(new Company(1,companyId, code+"_Company"));

        IpoDTO result = ipoService.get(code,companyId);
        Assertions.assertEquals(new IpoDTO(code, companyId, code+"_Company"), result);
    }

    @Test
    public void testInsert(){
        ipoService.insert(Ipo.builder().id(111).code("111").companyId("8888").build());
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme