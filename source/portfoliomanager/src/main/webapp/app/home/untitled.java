@Configuration 
@ComponentScan(basePackages = "org.example") 
public class AppConfig 
{ 
...
@Bean 
public MyService myService() 
{ 
return new MyServiceImpl(); 
} 

}
