package vttp2022.paf.assessment.eshop.services;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;

import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

public class WarehouseService {

	@Autowired
	private CustomerRepository cusRepo;

	@Autowired
	private OrderRepository orderRepo;

	// You cannot change the method's signature
	// You may add one or more checked exceptions
	public Optional<OrderStatus> dispatch(Order order) {
		
		OrderStatus os = new OrderStatus();
		os.setOrderId(order.getOrderId());
		os.setDeliveryId(order.getDeliveryId());
		os.setStatus(order.getStatus());
		
		
	 	if (orderRepo.saveOrderStatus(os) != null) {
			return orderRepo.saveOrderStatus(os);
		} else {
			os.setStatus("pending");
			orderRepo.saveOrderStatus(os);
			return Optional.empty();
		}
	}

}
