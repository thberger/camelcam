package de.thberger.camelcam;

import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Camel application with webcam route
 *
 * Taken from:
 * http://gautric.github.io/blog/2015/10/22/rhiot-0.1.2-camel-webcam-macos-x.html
 *
 * Configured as Spring Boot application.
 *
 * @author Thomas Berger
 */
@SpringBootApplication
public class WebCamRouter extends FatJarRouter {

    @Override
    public void configure() throws Exception {
        from("webcam:cam?consumer.delay=1000")
                .to("log://io.rhiot?showAll=true")
                .to("file://webapp");
    }


}

