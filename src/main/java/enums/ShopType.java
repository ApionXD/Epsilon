package enums;

import lombok.Getter;

@Getter
public enum ShopType {
    AMAZON      ("amazon"),
    BEST_BUY    ("best-buy");


    private final String shopName;
    ShopType(String shopName) {
        this.shopName = shopName;
    }
}
