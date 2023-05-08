package stmall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='StockIncreased'"
    )
    public void wheneverStockIncreased_NotificationInventoryIncreased(
        @Payload StockIncreased stockIncreased
    ) {
        StockIncreased event = stockIncreased;
        System.out.println(
            "\n\n##### listener NotificationInventoryIncreased : " +
            stockIncreased +
            "\n\n"
        );

        // Sample Logic //
        Order.notificationInventoryIncreased(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStarted'"
    )
    public void wheneverDeliveryStarted_NotificationOrderStatusUpdate(
        @Payload DeliveryStarted deliveryStarted
    ) {
        DeliveryStarted event = deliveryStarted;
        System.out.println(
            "\n\n##### listener NotificationOrderStatusUpdate : " +
            deliveryStarted +
            "\n\n"
        );

        // Sample Logic //
        Order.notificationOrderStatusUpdate(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStopped'"
    )
    public void wheneverDeliveryStopped_NotificationOrderStatusUpdate(
        @Payload DeliveryStopped deliveryStopped
    ) {
        DeliveryStopped event = deliveryStopped;
        System.out.println(
            "\n\n##### listener NotificationOrderStatusUpdate : " +
            deliveryStopped +
            "\n\n"
        );

        // Sample Logic //
        Order.notificationOrderStatusUpdate(event);
    }
}
