package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.Order;
import guru.springframework.apifirst.model.OrderCreateDto;
import guru.springframework.apifirst.model.OrderDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper {
    Order dtoToOrder(OrderCreateDto dto);

    Order dtoToOrder(OrderDto dto);

    OrderDto orderToDto(Order order);
}
