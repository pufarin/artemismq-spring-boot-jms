package net.gabrielkovacs.messaging.activemqexample.configuration;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQDestination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.apache.activemq.artemis.jms.client.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import java.util.Queue;

@Configuration
public class ArtemisConfiguration {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.artemis.user}")
    private String userName;

    @Value("${spring.artemis.password}")
    private String userPassword;


    @Bean
    public ActiveMQTopic topic() {
        return new ActiveMQTopic("theTopic");
    }

    @Bean
    public ActiveMQQueue queue(){
        return new ActiveMQQueue("theQueue");
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        try {
            factory.setBrokerURL(brokerUrl);
          //  factory.setUser(userName);
          //  factory.setPassword(userPassword);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setPubSubDomain(true);

        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryQueue() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setPubSubDomain(false);

        return factory;
    }


    @Bean
    public JmsTemplate jmsTemplate() {

        JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory());
        jmsTemplate.setPubSubDomain(false);

        return jmsTemplate;
    }

    @Bean
    public JmsTemplate jmsTemplateTopic() {

        JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory());
        jmsTemplate.setPubSubDomain(true);

        return jmsTemplate;
    }
}
