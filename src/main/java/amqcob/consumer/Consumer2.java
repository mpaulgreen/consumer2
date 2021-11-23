package amqcob.consumer;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;


public class Consumer2 {
    public static void main(String[] args) throws Exception{
        Connection connection = null;
        try{
            Topic topic = ActiveMQJMSClient.createTopic("exampleTopic");
            ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61617");
            connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            MessageConsumer consumer = session.createConsumer(topic);

            final int numMessages = 10;

            for (int i = 0; i < numMessages; i++) {
                TextMessage message0 = (TextMessage) consumer.receive(5000);
                System.out.println("Got message: " + message0.getText() + " from node 0");
            }
        }finally {
            if(connection != null){
                connection.close();
            }
        }
    }
}
