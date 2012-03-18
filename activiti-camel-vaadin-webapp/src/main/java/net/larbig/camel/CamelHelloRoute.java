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

		//use
		//jetty internal: http://localhost:8080/camel/start
		//tomcat deployment : http://localhost:8080/activiti-camel-webapp-1.0-SNAPSHOT/camel/start		
		from("servlet:///start")
				.to("log:directStart ##############################################")
				.to("activiti:camelProcessName");

		
		//use 
		//jetty internal: http://localhost:8080/camel/receive?laboprocid=9
		//tomcat deploy : http://localhost:8080/activiti-camel-webapp-1.0-SNAPSHOT/camel/receive?laboprocid=9
		from("servlet:///receive")
		        .to("log:ServletReceive?showHeaders=true")
		 		.setProperty("PROCESS_ID_PROPERTY").header("laboprocid")
				.to("log:directReceive ##############################################")
				.to("activiti:camelProcessName:waitState");

	}

}
