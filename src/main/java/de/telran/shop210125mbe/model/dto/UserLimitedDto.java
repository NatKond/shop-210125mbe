package de.telran.shop210125mbe.model.dto;

import de.telran.shop210125mbe.pojo.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "User transfer object")
public class UserLimitedDto {

    @Schema(description = "Unique user identification")
    private Long userId;

    private String name;

    private String email;

    private String phoneNumber;

}
