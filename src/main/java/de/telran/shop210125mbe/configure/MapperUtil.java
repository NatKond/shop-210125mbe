package de.telran.shop210125mbe.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MapperUtil {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static <R, E> List<R> convertList(List<E> list, Function<E, R> converter) {
        return list.stream().map(converter).collect(Collectors.toList());
    }

    public static <R, E> Set<R> convertSet(Set<E> set, Function<E, R> converter) {
        return set.stream().map(converter).collect(Collectors.toSet());
    }
}
