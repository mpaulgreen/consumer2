package amqcob.consumer;


import javax.jms.*;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;


public class Consumer2 {
    public static void main(String[] args) throws Exception{
        Connection connection = null;
        InitialContext initialContext = null;
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("##########################################################");
        System.out.println("");
        System.out.println("");
        try{

            initialContext = new InitialContext();
            Queue queue = (Queue) initialContext.lookup("queue/exampleQueue");
            ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            MessageConsumer consumer = session.createConsumer(queue);
            System.out.println("");
            System.out.println("");
            System.out.println("##########################################################");
            System.out.println("");
            System.out.println("Waiting for new messages in broker2.......");

            while(true) {
                TextMessage message = (TextMessage) consumer.receive(50000);
                if(message != null) {
                    System.out.println();
                    System.out.println("Got Message: "+message.getText());
                }
            }

        }finally {
            if(connection != null){
                connection.close();
            }
        }
    }
}
