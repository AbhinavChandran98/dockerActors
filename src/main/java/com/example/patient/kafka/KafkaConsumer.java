package com.example.patient.kafka;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import utils.AppConstants;

@Service
public class KafkaConsumer {
    KafkaProducer kafkaProducer;

    public KafkaConsumer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    /*these are the consumer services in patient*/

    @KafkaListener(topics = AppConstants.TOPIC_NAME3,groupId = AppConstants.GROUP_ID)
    public void confirmAppointment(String message) throws JSONException {
        System.out.println(message);
        JSONObject object=new JSONObject(message);
        String patientId=object.getString("patientId");
        String receptionMessage=object.getString("receptionMessage");
        System.out.println(receptionMessage);
        String appointmentComplete= "Appointment Completed";
        String result="{\"patientId\":\""+patientId+"\",\"appointmentComplete\":\""+appointmentComplete+"\"}";
        kafkaProducer.sendMessage(AppConstants.TOPIC_NAME5,result);

    }

    @KafkaListener(topics = AppConstants.TOPIC_NAME2,groupId = AppConstants.GROUP_ID)
    public void medicineDeliveredConsumer(String message) throws JSONException {
        JSONObject obj=new JSONObject(message);
        String patientId=obj.getString("patientId");
        String pharmacyMessage=obj.getString("pharmacyMessage");
        System.out.println(pharmacyMessage+"hii");
        String val="hospital visit complete";
        String data="{\"patientId\":\""+patientId+"\",\"val\":\""+val+"\"}";
        kafkaProducer.sendMessage(AppConstants.TOPIC_NAME4,data);
    }

}
