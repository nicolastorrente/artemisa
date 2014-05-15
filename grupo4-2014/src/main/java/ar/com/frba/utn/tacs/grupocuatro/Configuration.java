package ar.com.frba.utn.tacs.grupocuatro;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

@org.springframework.context.annotation.Configuration
public class Configuration {

	private final int port = 8443;
	private final String keystoreFile = "C:/keystore.jks";
	private final String keystorePass = "grupocuatro";
	private final String keystoreType = "pkcs12";
//	private final String keystoreProvider = "SunJSSE";
	private final String keystoreAlias = "tomcat";

	public Configuration() {
	}
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory(this.port);
		factory.addConnectorCustomizers( new TomcatConnectorCustomizer() {
				@Override
				public void customize(Connector con) {
			        Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();
			        proto.setSSLEnabled(true);
			        con.setScheme("https");
			        con.setSecure(true);
			        proto.setKeystoreFile(keystoreFile);
			        proto.setKeystorePass(keystorePass);
			        proto.setKeystoreType(keystoreType);
//			        proto.setProperty("keystoreProvider", keystoreProvider);
			        proto.setKeyAlias(keystoreAlias);
			    }
			});
		return factory;
	}

}
