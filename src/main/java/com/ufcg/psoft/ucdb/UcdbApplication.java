package com.ufcg.psoft.ucdb;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ufcg.psoft.ucdb.api.http.repositories.SubjectRepository;
import com.ufcg.psoft.ucdb.core.UcdbController;
import com.ufcg.psoft.ucdb.core.models.Subject;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class UcdbApplication implements CommandLineRunner{

    @Autowired
    private SubjectRepository subjectRepository;

    @Bean
    public UcdbController ucdbController(){
        UcdbController ucdbController = new UcdbController();
        return ucdbController;
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UcdbApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter("./bin/shutdown.pid"));
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Subject> subjectList = loadSubjects();
        subjectRepository.saveAll(subjectList);
    }

    private List<Subject> loadSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try {
            File resource = new ClassPathResource("database.json").getFile();
            String dataBasePath = resource.getPath();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(dataBasePath), "UTF-8"));
            Type listType = new TypeToken<ArrayList<Subject>>() {}.getType();
            subjects = new Gson().fromJson(bufferedReader, listType);
        } catch (IOException e) {
            System.exit(1);
        }
        return subjects;
    }
}
