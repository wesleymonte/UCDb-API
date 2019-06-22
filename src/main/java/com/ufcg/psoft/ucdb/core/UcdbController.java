package com.ufcg.psoft.ucdb.core;

import com.ufcg.psoft.ucdb.core.constants.UcdbConstants;
import com.ufcg.psoft.ucdb.core.email.EmailTransporter;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UcdbController {

    private Properties properties;
    private EmailTransporter emailTransporter;
    private static final Logger LOGGER = LogManager.getLogger(UcdbController.class);

    public UcdbController() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(UcdbConstants.DEFAULT_UCDB_CONF_FILE_PATH));
            LOGGER.info("Configurations of file " + UcdbConstants.DEFAULT_UCDB_CONF_FILE_PATH + " was loaded with success.");
        } catch (Exception e){
            LOGGER.info("Configuration file was not founded or not loaded with success.");
            System.exit(1);
        }
        this.emailTransporter = new EmailTransporter(properties.getProperty("ucdb.email"), properties.getProperty("ucdb.password"));
    }

    public void sendRegistrationEmail(String email){
        new Thread(() -> emailTransporter.sendRegistrationEmail(email)).start();
    }
}
