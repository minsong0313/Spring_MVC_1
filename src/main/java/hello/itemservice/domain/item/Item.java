package hello.itemservice.domain.item;

import lombok.Data;

//상품 객체
@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price; //Integer: 값이 안들어갈 수도 있는 상황 때문
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
