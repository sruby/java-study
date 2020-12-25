package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.entity.IpoCompany;
import org.springframework.stereotype.Service;

/**
 * @description: user
 * @author: sruby
 * @create: 2020-12-07 11:54
 */
@Service
public class IpoCompanyService {
    public IpoCompany get(String companyId){
        return IpoCompany.builder().companyId(companyId).companyName(companyId+"_Company").build();
    }


}
