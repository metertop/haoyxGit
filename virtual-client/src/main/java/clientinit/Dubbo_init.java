package clientinit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Created by zhuhewei on 2015/6/26.
 */
public class Dubbo_init {
    private  static ApplicationContext context;
 //   private  static GomeDubboDemo gomeDubboDemo;

    public static void beforeInit(){
        context = new ClassPathXmlApplicationContext("classpath:/applicationContext-all.xml");

        if(context==null)
        {
            throw new IllegalArgumentException("spring init exception");
        }
    }

    public static Object getBean(String beanName) {

        return context.getBean(beanName);
    }
}
