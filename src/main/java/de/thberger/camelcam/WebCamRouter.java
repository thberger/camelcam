package de.thberger.camelcam;

import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Camel application with webcam route
 * <p>
 * Taken from:
 * http://gautric.github.io/blog/2015/10/22/rhiot-0.1.2-camel-webcam-macos-x.html
 * <p>
 * Configured as Spring Boot application.
 *
 * @author Thomas Berger
 */
@SpringBootApplication
public class WebCamRouter extends FatJarRouter {

    /**
     * Default resolution of Webcam endpoint is 320x240, which throws
     * error if used on MacBook with iSight camera.
     */
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    @Override
    public void configure() throws Exception {
        from("webcam:cam?width=" + WIDTH + "&height=" + HEIGHT + "&consumer.delay=1000")
                .to("log://io.rhiot?showAll=true")
                .to("file://webapp");
    }


}

