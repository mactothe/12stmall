package stmall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import stmall.OrderApplication;
import stmall.domain.OrderCanceled;
import stmall.domain.OrderPlaced;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long productId;

    private String productName;

    private Integer qty;

    private String address;

    private String status;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public static void notificationInventoryIncreased(
        StockIncreased stockIncreased
    ) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(stockIncreased.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }

    public static void notificationOrderStatusUpdate(
        DeliveryStarted deliveryStarted
    ) {
        repository().findById(deliveryStarted.getOrderId()).ifPresent(order->{
            order.setStatus(deliveryStarted.getStatus());
            repository().save(order);
         });
    }

    public static void notificationOrderStatusUpdate(
        DeliveryStopped deliveryStopped
    ) {
        repository().findById(deliveryStopped.getOrderId()).ifPresent(order->{
            order.setStatus(deliveryStopped.getStatus());
            repository().save(order);
        });
    }
}
