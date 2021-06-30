package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.dto.IpoDTO;
import io.sruby.github.test.unit.entity.Ipo;
import io.sruby.github.test.unit.entity.IpoCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class IpoServiceMockInSpringTest {
    @Autowired
    IpoCompanyService ipoCompanyService;
    @Autowired
    IpoService ipoService;

    @Test
    void testGet() {
        String code = "8888";
        String companyId = "00001";
        when(ipoCompanyService.get(companyId)).thenReturn(new IpoCompany(1,companyId, code+"_Company"));

        IpoDTO result = ipoService.get(code,companyId);
        Assertions.assertEquals(new IpoDTO(code, companyId, code+"_Company"), result);
    }

    @Test
    public void testInsert(){
        ipoService.insert(Ipo.builder().id(4).code("111").companyId("8888").build());
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme