package de.telran.shop210125mbe.pojo;

import java.text.DecimalFormat;
import java.util.Objects;

public class OrderItem {

    private Long orderItemId;

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private DecimalFormat priceAtPurchase;

    public OrderItem() {
    }

    public OrderItem(Long orderItemId, Long orderId, Long productId, Integer quantity, DecimalFormat priceAtPurchase) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public DecimalFormat getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(DecimalFormat priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(orderItemId, orderItem.orderItemId) && Objects.equals(orderId, orderItem.orderId)
                && Objects.equals(productId, orderItem.productId) && Objects.equals(quantity, orderItem.quantity)
                && Objects.equals(priceAtPurchase, orderItem.priceAtPurchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, orderId, productId, quantity, priceAtPurchase);
    }
}
