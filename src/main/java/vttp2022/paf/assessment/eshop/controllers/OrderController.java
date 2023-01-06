package vttp2022.paf.assessment.eshop.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.RequestHandledEvent;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

@Controller
@RequestMapping (path = "/index")
public class OrderController {

	@Autowired
	private WarehouseService warehouseService;

	@Autowired
	private CustomerRepository cusRepo;

	public HttpServletResponse saveOrder(HttpServletResponse response, @RequestBody MultiValueMap<String, String> form) {
		String name = form.getFirst("name");
		String item = form.getFirst("item");
        String quantity = form.getFirst("quantity");
		

		List<LineItem> lineItems = new LinkedList<>(String : String);
		lineItems.add(new LineItem(item, quantity));
	
		Optional<Customer> order = cusRepo.findCustomerByName(name);
		if (order != null) {
			return ;
		} else {
			JsonObjectBuilder job = Json.createObjectBuilder();
			String errorMsg = "Customer" + name + "not foung";
			job.add("error", errorMsg);
			
			return response.
					setStatus(HttpServletResponse.SC_NOT_FOUND).
					setContentType("application/json").
					getWriter().
					write(errorMsg.toString());;	
		}

		
	}

}
