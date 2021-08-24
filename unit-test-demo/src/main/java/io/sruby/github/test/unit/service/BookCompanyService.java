package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.entity.BookCompany;
import org.springframework.stereotype.Service;

/**
 * @description: user
 * @author: sruby
 * @create: 2020-12-07 11:54
 */
@Service
public class BookCompanyService {
    public BookCompany get(String companyId){
        return BookCompany.builder().companyId(companyId).companyName(companyId+"_Company").build();
    }


}
