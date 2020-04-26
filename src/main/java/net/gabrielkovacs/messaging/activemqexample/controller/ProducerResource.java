package net.gabrielkovacs.messaging.activemqexample.controller;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.apache.activemq.artemis.jms.client.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/publish")
public class ProducerResource {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ActiveMQQueue queue;

    private ActiveMQTopic topic;
    private JmsTemplate jmsTemplateTopic;

    public ProducerResource(ActiveMQTopic topic, JmsTemplate jmsTemplateTopic) {
        this.topic = topic;
        this.jmsTemplateTopic = jmsTemplateTopic;
    }

    @GetMapping("/{message}")
    public String publish(@PathVariable("message") final String message){

        jmsTemplate.convertAndSend(queue, message);
        return "Published Successfully";
    }
    @GetMapping("/topic/{message}")
    public String publishTopic(@PathVariable("message") final String message){

        jmsTemplateTopic.convertAndSend(topic, message);
        return "Published Successfully";
    }

}
