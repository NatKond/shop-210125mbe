package de.telran.shop210125mbe.pojo;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {

    private Long orderId;

    private Timestamp createdAt;

    private String deliveryAddress;

    private String contactPhone;

    private String deliveryMethod;

    private Status status;

    private Timestamp updatedAt;

    public Order() {
    }

    public Order(Long orderId, Timestamp createdAt, String deliveryAddress, String contactPhone, String deliveryMethod, Status status, Timestamp updatedAt) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.deliveryAddress = deliveryAddress;
        this.contactPhone = contactPhone;
        this.deliveryMethod = deliveryMethod;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(createdAt, order.createdAt)
                && Objects.equals(deliveryAddress, order.deliveryAddress)
                && Objects.equals(contactPhone, order.contactPhone)
                && Objects.equals(deliveryMethod, order.deliveryMethod)
                && status == order.status && Objects.equals(updatedAt, order.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, createdAt, deliveryAddress, contactPhone, deliveryMethod, status, updatedAt);
    }
}
