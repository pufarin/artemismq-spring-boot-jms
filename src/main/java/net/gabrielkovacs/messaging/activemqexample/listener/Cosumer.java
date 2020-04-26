package net.gabrielkovacs.messaging.activemqexample.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Cosumer {

    @JmsListener(destination = "theQueue", containerFactory = "jmsListenerContainerFactoryQueue")
    public void cosume(String message){
        System.out.println("Received Message: " + message);
    }

    @JmsListener(destination = "theQueue", containerFactory = "jmsListenerContainerFactoryQueue")
    public void cosume1(String message){
        System.out.println("Received Message cosume1: " + message);
    }

    @JmsListener(destination = "theQueue", containerFactory = "jmsListenerContainerFactoryQueue")
    public void cosume2(String message){
        System.out.println("Received Message cosume2: " + message);
    }

    @JmsListener(destination = "theTopic", containerFactory = "jmsListenerContainerFactory")
    public void cosumeTopic(String message){
        System.out.println("Received Message Topic: " + message);
    }

    @JmsListener(destination = "theTopic", containerFactory = "jmsListenerContainerFactory")
    public void cosumeTopic1(String message){
        System.out.println("Received Message Topic1: " + message);
    }

    @JmsListener(destination = "theTopic", containerFactory = "jmsListenerContainerFactory")
    public void cosumeTopic2(String message){
        System.out.println("Received Message Topic2: " + message);
    }
}
