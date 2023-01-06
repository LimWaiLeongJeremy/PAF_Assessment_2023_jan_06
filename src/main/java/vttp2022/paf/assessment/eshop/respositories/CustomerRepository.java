package vttp2022.paf.assessment.eshop.respositories;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import vttp2022.paf.assessment.eshop.models.Customer;

import static vttp2022.paf.assessment.eshop.respositories.Query.*;

public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// You cannot change the method's signature
	public Optional<Customer> findCustomerByName(String name) {
		// TODO: Task 3 
		Customer customer = new Customer();
		customer.setName(name);
		SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_CUSTOMER_EXISTS, name);
        while(rs.next()) {
            if (rs.getInt("valid") == 1) {
				return Optional.of(customer);
            }
        }
		return Optional.empty();
	}
}
