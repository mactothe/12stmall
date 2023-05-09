package stmall.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;
import lombok.Data;
import stmall.DeliveryApplication;
import stmall.domain.DeliveryStarted;
import stmall.domain.DeliveryStopped;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long productId;

    private String productName;

    private Integer qty;

    private String status;

    private String address;

    @PostPersist
    public void onPostPersist() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        DeliveryStopped deliveryStopped = new DeliveryStopped(this);
        deliveryStopped.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static void deliveryStart(OrderPlaced orderPlaced) {
        Delivery delivery = new Delivery();
        delivery.setAddress(orderPlaced.getAddress());
        delivery.setStatus("ready");
        delivery.setProductId(orderPlaced.getProductId());
        delivery.setProductName(orderPlaced.getProductName());
        delivery.setQty(orderPlaced.getQty());
        delivery.setOrderId(orderPlaced.getId());
        repository().save(delivery);
    }

    public static void deliveryStop(OrderCanceled orderCanceled) {
        Optional<Delivery> deliveryOptional = repository().findByOrderId(orderCanceled.getId());
        if (deliveryOptional.isPresent()) {
            Delivery delivery = deliveryOptional.get();
            delivery.setStatus("retrieve");
            repository().save(delivery);
        }
    }
}
