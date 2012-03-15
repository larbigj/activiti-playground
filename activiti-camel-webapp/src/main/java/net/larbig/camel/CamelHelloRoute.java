package net.larbig.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelHelloRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("activiti:camelProcessName:serviceTask1")
				.setBody()
				.property("var1")
				.setBody()
				.constant("ala")
				.to("log:service1 ##############################################");

		from("servlet:///start")
				.to("log:directStart ##############################################")
				.to("activiti:camelProcessName");

		from("servlet:///receive")
				.to("log:directReceive ##############################################")
				.to("activiti:camelProcessName:waitState");

	}

}
