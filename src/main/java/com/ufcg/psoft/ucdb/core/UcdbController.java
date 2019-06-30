package com.ufcg.psoft.ucdb.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ufcg.psoft.ucdb.api.repositories.SubjectRepository;
import com.ufcg.psoft.ucdb.api.services.SubjectService;
import com.ufcg.psoft.ucdb.core.constants.UcdbConstants;
import com.ufcg.psoft.ucdb.core.email.EmailTransporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import com.ufcg.psoft.ucdb.core.models.Subject;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

public class UcdbController {

    private Properties properties;
    private EmailTransporter emailTransporter;
    private static final Logger LOGGER = LogManager.getLogger(UcdbController.class);

    @Autowired
    private SubjectService subjectService;

    public UcdbController() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(UcdbConstants.DEFAULT_UCDB_CONF_FILE_PATH));
            LOGGER.info("Configurations of file " + UcdbConstants.DEFAULT_UCDB_CONF_FILE_PATH
                + " was loaded with success.");
        } catch (Exception e) {
            LOGGER.info("Configuration file was not founded or not loaded with success.");
            System.exit(1);
        }

        this.emailTransporter = new EmailTransporter(properties.getProperty("ucdb.email"),
            properties.getProperty("ucdb.password"));
    }

    public void sendRegistrationEmail(String email) {
        new Thread(() -> emailTransporter.sendRegistrationEmail(email)).start();
    }
}
