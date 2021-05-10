package dev.flaviosantos.minierp.config;

import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.MatchingStrategy;
import org.modelmapper.spi.NamingConvention;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.flaviosantos.minierp.dto.ItemDto;
import dev.flaviosantos.minierp.dto.OrderDto;
import dev.flaviosantos.minierp.model.Item;
import dev.flaviosantos.minierp.model.Order;

@Configuration
public class ApplicationConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration()
		  .setMatchingStrategy(MatchingStrategies.STANDARD);

		
		Converter<ItemDto, Item> itemc = new Converter<ItemDto, Item>() {

			@Override
			public Item convert(MappingContext<ItemDto, Item> context) {
				context.getDestination().setId(context.getSource().getId());
				context.getDestination().setQty(context.getSource().getQty());
				context.getDestination().setOrder(null);
				//context.getDestination().setDiscount(context.getSource().getDiscount());
				
				return context.getDestination();
			}
			
		};
		
		modelMapper.addConverter(itemc);
		
		return modelMapper;
	}

}
