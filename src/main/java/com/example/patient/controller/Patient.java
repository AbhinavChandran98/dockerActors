package com.example.patient.controller;


import com.example.patient.kafka.KafkaProducer;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import utils.AppConstants;

@RestController
@RequestMapping
public class Patient {
    //producers of patient

    private KafkaProducer kafkaProducer;
    public Patient(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    //book appointment producer

    /* Api for the  booking appointment*/
    @PostMapping("patientBookAppointment")
    public String processAppointmentConfirmation(@RequestBody String message) throws JSONException {
        System.out.println(message);
        JSONObject obj=new JSONObject(message);
        String patientId= obj.getString("patientId");
        String patientMessage= obj.getString("patientMessage");

        String var="{\"patientId\":\""+patientId+"\",\"patientMessage\":\""+patientMessage+"\"}";


        kafkaProducer.sendMessage(AppConstants.TOPIC_NAME,var);
        return "appointment send";
    }
    @PostMapping("reachedHospital")
    public String reachedHospital(@RequestBody String message) throws JSONException {
        JSONObject obj =new JSONObject(message);
        String patientId=obj.getString("patientId");
        String hospitalVisit="visit hospital";
        String data="{\"patientId\":\""+patientId+"\",\"hospitalVisit\":\""+hospitalVisit+"\"}";
        kafkaProducer.sendMessage(AppConstants.TOPIC_NAME1,data);
        return "Done";

    }

}
