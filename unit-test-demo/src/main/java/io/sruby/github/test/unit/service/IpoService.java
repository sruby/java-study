package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.dto.IpoDTO;
import io.sruby.github.test.unit.entity.IpoCompany;
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
    private IpoCompanyService ipoCompanyService;
    @Autowired
    private IpoMapper ipoMapper;

    public IpoDTO get(String code, String companyId){
        Ipo ipo = Ipo.builder().code(code).companyId(companyId).build();
        IpoCompany company = ipoCompanyService.get(companyId);
        IpoDTO ipoDTO = IpoDTO.builder().code(code).companyId(companyId).companyName(company.getCompanyName()).build();
        return ipoDTO;
    }

    public Ipo getIpoWithBusinuss(Integer id){
        Ipo ipo = get(id);
        ipo.setCode("Businuss"+ipo.getCode());
        return ipo;
    }

    public Ipo get(Integer id){
        return ipoMapper.selectById(id);
    }


    public int insert(Ipo ipo){
        return ipoMapper.insert(ipo);
    }
}
