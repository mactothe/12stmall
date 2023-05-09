package stmall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import stmall.ProductApplication;
import stmall.domain.StockDecreased;
import stmall.domain.StockIncreased;

@Entity
@Table(name = "Inventory_table")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private Integer stock;

    @PostPersist
    public void onPostPersist() {
        StockDecreased stockDecreased = new StockDecreased(this);
        stockDecreased.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        StockIncreased stockIncreased = new StockIncreased(this);
        stockIncreased.publishAfterCommit();
    }

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = ProductApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    public static void stockDecrease(DeliveryStarted deliveryStarted) {
        Inventory inventory = repository().findById(
                deliveryStarted.getProductId()).get();
        inventory.setStock(inventory.getStock() - deliveryStarted.getQty());
        repository().save(inventory);
    }

    public static void stockIncrease(DeliveryStopped deliveryStopped) {
        Inventory inventory = repository().findById(
                deliveryStopped.getProductId()).get();
        inventory.setStock(inventory.getStock() + deliveryStopped.getQty());
        repository().save(inventory);
    }
}
