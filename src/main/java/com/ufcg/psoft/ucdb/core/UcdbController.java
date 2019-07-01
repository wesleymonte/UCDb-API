package com.ufcg.psoft.ucdb.core;

import com.ufcg.psoft.ucdb.core.email.EmailTransporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class UcdbController {

    @Autowired
    private EmailTransporter emailTransporter;
    private static final Logger LOGGER = LogManager.getLogger(UcdbController.class);

    public void sendRegistrationEmail(String email) {
        new Thread(() -> emailTransporter.sendRegistrationEmail(email)).start();
    }
}
