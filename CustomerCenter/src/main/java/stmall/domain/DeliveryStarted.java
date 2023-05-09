package stmall.domain;

import java.util.*;
import lombok.Data;
import stmall.infra.AbstractEvent;

@Data
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Integer qty;
    private String status;
    private String address;
}
