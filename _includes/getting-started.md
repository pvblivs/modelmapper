# Getting Started

This section will guide you through the setup and basic usage of ModelMapper.

### Setting Up

If you're a Maven user just add the `modelmapper` library as a dependency:

{:.prettyprint .lang-xml}
	<dependency>
	  <groupId>org.modelmapper</groupId>
	  <artifactId>modelmapper</artifactId>
	  <version>0.5.0</version>
	</dependency>

Otherwise you can [download](https://github.com/jhalterman/modelmapper/downloads) the latest ModelMapper jar and add it to your classpath.

### Mapping

Let's try mapping some objects. Consider the following object model:

{:.prettyprint .lang-java}
	// Assume getters and setters on each class

	class Order {
	  private Customer customer;
	  private Address billingaddress;
	}
	
	class Customer {
	  Name name;
	}
	
	class Name {
	  String firstName;
	  String lastName;
	}
	
	class Address {
	  String street;
	  String city;
	}
	
	class OrderDTO {
	  String customerFirstName;
	  String customerLastName;
	  String billingStreet;
	  String billingCity;
	}

Mapping an `order` to an `OrderDTO` is simple and requires _zero_ configuration:

{:.prettyprint .lang-java}
	ModelMapper modelMapper = new ModelMapper();
	OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

We can check that properties are mapped as expected:

{:.prettyprint .lang-java}
	assert orderDTO.getCustomerFirstName().equals(order.getCustomer().getName().getFirstName());
	assert orderDTO.getCustomerLastName().equals(order.getCustomer().getName().getLastName());
	assert orderDTO.getBillingStreet().equals(order.getBillAddress().getStreet());
	assert orderDTO.getBillingCity().equals(order.getBillAddress().getCity());

Similarly, mapping the other direction works as expected with zero configuration:

{:.prettyprint .lang-java}
    Order order = modelMapper.map(orderDTO, Order.class);

### How It Works

When the `map` method is called, the source and destination types are analyzed to determine which properties match each other based on current [configuration](/user-manual/configuration). Data is then mapped according to these matches.

### Handling Mismatches

For properties that cannot be matched automatically:

 * ModelMapper's [Property Mapping](/user-manual/property-mapping) API can be used to define explicit mappings
 * [Configuration](/user-manual/configuration) can be adjusted to allow different properties to match each other based on naming, accessibility and other conventions 

[Validation](/user-manual/validation) can be performed to ensure that all destination properties are mapped as expected.