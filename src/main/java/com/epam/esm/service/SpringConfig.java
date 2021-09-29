package com.epam.esm.service;

import com.epam.esm.dao.impl.CertificatesDAOImpl;
import com.epam.esm.dao.impl.CommonCRUDOperationsImpl;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.service.impl.CertificateServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public CommonCRUDOperationsImpl getCertificateCRUDOperations(){
        return new CommonCRUDOperationsImpl();
    }

    @Bean
    public TagDAOImpl getTagDAOImpl(){
        return new TagDAOImpl(getCertificateCRUDOperations());
    }

    @Bean
    public TagServiceImpl getTagServiceImpl(){
        return new TagServiceImpl(getTagDAOImpl());
    }

    @Bean
    public CertificatesDAOImpl getCertificatesDAOImpl(){
        return new CertificatesDAOImpl(getCertificateCRUDOperations());
    }

    @Bean
    public CertificateServiceImpl getCertificateService(){
        return new CertificateServiceImpl(getCertificatesDAOImpl());
    }

}
