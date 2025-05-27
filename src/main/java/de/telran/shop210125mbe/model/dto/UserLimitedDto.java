package de.telran.shop210125mbe.model.dto;

import de.telran.shop210125mbe.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLimitedDto {

    private Long userId;

    private String name;

    private String email;

    private String phoneNumber;

}
