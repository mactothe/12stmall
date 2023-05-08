package stmall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MyPage_table")
@Data
public class MyPage {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Integer orderId;
    private Integer customerId;
    private String productName;
    private Integer productId;
    private Integer qty;
    private String status;
}
