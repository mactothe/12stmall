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
    public void wheneverStockIncreased_InventoryIncreased(
        @Payload StockIncreased stockIncreased
    ) {
        StockIncreased event = stockIncreased;
        System.out.println(
            "\n\n##### listener InventoryIncreased : " + stockIncreased + "\n\n"
        );

        // Sample Logic //
        Order.inventoryIncreased(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStarted'"
    )
    public void wheneverDeliveryStarted_StatusUpdate(
        @Payload DeliveryStarted deliveryStarted
    ) {
        DeliveryStarted event = deliveryStarted;
        System.out.println(
            "\n\n##### listener StatusUpdate : " + deliveryStarted + "\n\n"
        );

        // Sample Logic //
        Order.statusUpdate(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStopped'"
    )
    public void wheneverDeliveryStopped_StatusUpdate(
        @Payload DeliveryStopped deliveryStopped
    ) {
        DeliveryStopped event = deliveryStopped;
        System.out.println(
            "\n\n##### listener StatusUpdate : " + deliveryStopped + "\n\n"
        );

        // Sample Logic //
        Order.statusUpdate(event);
    }
}
