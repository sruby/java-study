package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.entity.Company;
import org.springframework.stereotype.Service;

/**
 * @description: user
 * @author: sruby
 * @create: 2020-12-07 11:54
 */
@Service
public class CompanyService {
    public Company get(String companyId){
        return Company.builder().companyId(companyId).companyName(companyId+"_Company").build();
    }
}
