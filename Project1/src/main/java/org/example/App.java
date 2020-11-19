package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "First console output." );
        ApplicationContext context = SpringApplication.run(App.class);
        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        helloWorld.sayHello();
        System.out.println( "Last console output." );
    }
}
