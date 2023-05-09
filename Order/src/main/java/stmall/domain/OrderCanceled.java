package stmall.domain;

import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

@Data
@ToString
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private String userId;
    private Long productId;
    private Integer qty;
    private String address;
    private String status;

    public OrderCanceled(Order aggregate) {
        super(aggregate);
    }

    public OrderCanceled() {
        super();
    }
}
