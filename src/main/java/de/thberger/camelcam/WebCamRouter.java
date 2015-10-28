package de.thberger.camelcam;

import io.rhiot.component.webcam.WebcamComponent;
import io.rhiot.utils.process.ProcessManager;
import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    @Value("${cam.width:320}")
    public long camWidth;

    @Value("${cam.height:240}")
    public long camHeight;
    
    @Autowired
    private CamelContext camelContext;
    
    @Bean(name = "webcam")
    public WebcamComponent getWebcamComponent(){

        WebcamComponent webcam = new WebcamComponent();
        webcam.setProcessManager(new CustomProcessManager());
        camelContext.addComponent("webcam", webcam);
        return webcam;
    }
    
    @Bean
    public ProcessManager getProcessManager(){
        return new CustomProcessManager();
    }
    

    @Override
    public void configure() throws Exception {
        from("webcam:cam?width=" + camWidth + "&height=" + camHeight + "&consumer.delay=1000")
                .to("log://io.rhiot?showAll=true")
                .to("file://webapp");
    }


}

