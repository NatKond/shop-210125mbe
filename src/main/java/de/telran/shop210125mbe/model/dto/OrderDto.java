package de.telran.shop210125mbe.model.dto;

import de.telran.shop210125mbe.pojo.Status;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class OrderDto {

    private Long orderId;

    private Timestamp createdAt;

    private String deliveryAddress;

    private String contactPhone;

    private String deliveryMethod;

    private Status status;

    private Timestamp updatedAt;
}
