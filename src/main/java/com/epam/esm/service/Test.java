package com.epam.esm.service;


import com.epam.esm.config.SpringConfig;
import com.epam.esm.dao.connection.ConnectionPool;
import com.epam.esm.service.impl.CertificateServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    private static final Logger LOG = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TagServiceImpl tagService = context.getBean(TagServiceImpl.class);
        CertificateServiceImpl certificateService = context.getBean(CertificateServiceImpl.class);
        ConnectionPool.connectionPool = new ConnectionPool();
        try {
            //tagService.deleteTag(new Tag(13));
            //tagService.createTag(new Tag(11, "test"));
            tagService.getTags().forEach(System.out::println);
            //certificateService.createCertificates(new Certificate(11, "fw",  11.20, 5, "2021-09-29", "2021-09-30", "gwgwgrw"));
            //certificateService.deleteCertificates(new Certificate(11));
            certificateService.getCertificates().forEach(System.out::println);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "Error in tag/certificate operations.");
        }
    }
}
