package vttp2022.paf.assessment.eshop.respositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;

import static vttp2022.paf.assessment.eshop.respositories.Query.*;

@Repository
public class OrderRepository {
	// TODO: Task 3

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public Optional<Order> saveOrder(String name, List<LineItem> lineItemLi) {
		Customer customer = new Customer();
		customer.setName(name);
		Order order = new Order();
		order.setOrderId(UUID
						.randomUUID()
						.toString()
						.substring(0, 8));
		order.setCustomer(customer);
		order.setLineItems(lineItemLi);
		for (LineItem li : lineItemLi) {
			int result = jdbcTemplate.update(
					SQL_INSERT_CUSTOMER_ORDERS, 
					order.getOrderId(),
					order.getDeliveryId(),
					order.getStatus(),
					li.getItem(),
					li.getQuantity(),
					order.getCustomer().getName());

			if (result == 1){
				return Optional.of(order);
			}
		}
		return Optional.empty();
	}

	public Optional<OrderStatus> saveOrderStatus(OrderStatus os) {
		int result = jdbcTemplate.update(
					SQL_INSERT_CUSTOMER_ORDERS_STATUS, 
					os.getOrderId(),
					os.getDeliveryId(),
					os.getStatus());

		if (result == 1){
			return Optional.of(os);
		}
		return Optional.empty();
	}


}
