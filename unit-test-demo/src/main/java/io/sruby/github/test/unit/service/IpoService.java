package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.dto.IpoDTO;
import io.sruby.github.test.unit.entity.Company;
import io.sruby.github.test.unit.entity.Ipo;
import io.sruby.github.test.unit.mapper.IpoMapper;
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
    @Autowired
    private IpoMapper ipoMapper;

    public IpoDTO get(String code, String companyId){
        Ipo ipo = Ipo.builder().code(code).companyId(companyId).build();
        Company company = companyService.get(companyId);
        IpoDTO ipoDTO = IpoDTO.builder().code(code).companyId(companyId).companyName(company.getCompanyName()).build();
        return ipoDTO;
    }

    public int insert(Ipo ipo){
        return ipoMapper.insert(ipo);
    }
}
