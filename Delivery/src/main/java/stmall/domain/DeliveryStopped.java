package stmall.domain;

import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

@Data
@ToString
public class DeliveryStopped extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer qty;
    private String status;
    private String address;

    public DeliveryStopped(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryStopped() {
        super();
    }
}
