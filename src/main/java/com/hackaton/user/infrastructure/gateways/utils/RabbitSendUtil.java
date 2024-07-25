package com.hackaton.user.infrastructure.gateways.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackaton.user.Enums.Operacao;
import com.hackaton.user.entities.Doctor;
import com.hackaton.user.entities.Pacient;
import com.hackaton.user.infrastructure.gateways.utils.dtos.RabbitDoctorDTO;
import com.hackaton.user.infrastructure.gateways.utils.dtos.RabbitPacientDTO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitSendUtil {


    private final String HOST = "ec2-18-214-88-66.compute-1.amazonaws.com";
    private static final String RABBITMQ_QUEUE_USUARIO_PACIENTE = "usuario-paciente";
    private static final String RABBITMQ_QUEUE_USUARIO_MEDICO = "usuario-medico";

    public void SendToPacientList(Pacient pacient, Operacao operacao) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(RABBITMQ_QUEUE_USUARIO_PACIENTE, true, false, false, null);
            RabbitPacientDTO rabbitPacientDTO = new RabbitPacientDTO(pacient, operacao);
            ObjectMapper objectMapper = new ObjectMapper();
            String mensagem = objectMapper.writeValueAsString(rabbitPacientDTO);
            // Publica a mensagem na fila
            channel.basicPublish("", RABBITMQ_QUEUE_USUARIO_PACIENTE, null, mensagem.getBytes());
        } catch (TimeoutException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SendToDoctorList(Doctor doctor, Operacao operacao) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("ec2-18-214-88-66.compute-1.amazonaws.com");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(RABBITMQ_QUEUE_USUARIO_MEDICO, true, false, false, null);
            RabbitDoctorDTO rabbitDTO = new RabbitDoctorDTO(doctor, operacao);
            ObjectMapper objectMapper = new ObjectMapper();
            String mensagem = objectMapper.writeValueAsString(rabbitDTO);
            // Publica a mensagem na fila
            channel.basicPublish("", RABBITMQ_QUEUE_USUARIO_MEDICO, null, mensagem.getBytes());
        } catch (TimeoutException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
