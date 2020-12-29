package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.dto.IpoDTO;
import io.sruby.github.test.unit.entity.Ipo;
import io.sruby.github.test.unit.entity.IpoCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
class IpoServiceMockInSpringTest3 {
    @MockBean
    IpoCompanyService ipoCompanyService;
    @MockBean
    IpoService ipoService;

    @Test
    void testGet() {
        String code = "8888";
        String companyId = "00001";
        when(ipoCompanyService.get(companyId)).thenReturn(new IpoCompany(1,companyId, code+"_Company"));
        when(ipoService.get(any())).thenCallRealMethod();
        IpoDTO result = ipoService.get(code,companyId);
        Assertions.assertEquals(new IpoDTO(code, companyId, code+"_Company"), result);
    }

    @Test
    public void testInsert(){
        ipoService.insert(Ipo.builder().id(4).code("111").companyId("8888").build());
    }

    @Test
    public void test(){
        int id = 1;
        when(ipoService.get(id)).thenReturn(Ipo.builder().id(1).code("111").companyId("8888").build());
        Ipo ipo = ipoService.getIpoWithBusinuss(id);
        assertThat(ObjectUtils.isEmpty(ipo)).isFalse();
        assertThat(ipo.getId()).isEqualTo(id);
        assertThat(ipo.getCode()).startsWith("Businuss");
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme