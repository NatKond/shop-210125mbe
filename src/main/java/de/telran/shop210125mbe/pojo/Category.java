package de.telran.shop210125mbe.pojo;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor // конструктор с инициализацией всех параметров
@NoArgsConstructor // конструктор без аргументов
@Setter // для создания всех set
@Getter // для создания всех get
@ToString // для создания метода toString
@EqualsAndHashCode // для создания метода toString()

//@Data // заменяет набор выше указанных аннотаций (не всегда корректно!!)
@Component
public class Category {

    private Long categoryId;

    private String name;

}
