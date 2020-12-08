package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.dto.IpoDTO;
import io.sruby.github.test.unit.entity.Company;
import io.sruby.github.test.unit.entity.IPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: sruby
 * @create: 2020-12-07 11:53
 */
@Service
public class IpoService {
    @Autowired
    private CompanyService companyService;

    public IpoDTO get(String code,String companyId){
        IPO ipo = IPO.builder().code(code).companyId(companyId).build();
        Company company = companyService.get(companyId);
        IpoDTO ipoDTO = IpoDTO.builder().code(code).companyId(companyId).companyName(company.getCompanyName()).build();
        return ipoDTO;
    }
}
