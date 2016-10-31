import com.gome.pangu.virtual.dubbo.model.ResultDO;
import com.gome.pangu.virtual.dubbo.model.VirtualResult;
import com.gome.pangu.virtual.dubbo.service.VirtualQueryService;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.junit.Before;
import org.junit.Test;

import clientinit.Dubbo_init;

/**
 * Created by zhuhewei on 2015/9/6.
 */
public class TVirtualQueryServiceFindVirtualAccountByPasswordAdapter_PFT {
    private long start = 0;
    private long end = 0;
    private  String userId="userId";
    private  String homeSite="homeSite";
    private  String password="password";


    private Arguments params;
    private  static VirtualQueryService virtualQueryService;
    //    private CreateVirtualParam VirtualParam;
    @Before
    public void setupTest() {
        Dubbo_init init = new Dubbo_init();
        init.beforeInit();
        virtualQueryService = (VirtualQueryService)init.getBean("virtualQueryService");
    }

    public Arguments getDefaultParameters() {
        params=new  Arguments();
        params.addArgument("userId",userId);
        params.addArgument("homeSite",homeSite);
        params.addArgument("password",password);

        return params;
    }

    public void SetValues(JavaSamplerContext arg0)
    {
        userId=arg0.getParameter("userId","");
        homeSite=arg0.getParameter("homeSite","homeSite");
        password=arg0.getParameter("password","password");
    }
    @Test
    public void runTest()
    {
        userId="3142053";
        homeSite="homeSite";
        password="123456";

        SampleResult sr = new SampleResult();
//        SetValues(arg0);
        sr.sampleStart();
        start=System.currentTimeMillis();
        try{
//            ResultDO<Boolean> result = virtualProcService.createVirtualAccount(userId, txId, homeSite, VirtualParam);
            ResultDO<VirtualResult> result = virtualQueryService.findVirtualAccountByPassword(userId, homeSite,password);
            if(result.isSuccess())
            {
                sr.setSuccessful(true);
                System.out.println("Error in findVirtualAccountByUserId======>>>>>>>>" + result.getErrCode() + result.getErrMsg());
                System.out.println("Error in findVirtualAccountByUserId======>>>>>>>>" + userId + "," + homeSite + ",");
            }
            else
            {
                sr.setSuccessful(false);
                System.out.println("Error in findVirtualAccountByUserId======>>>>>>>>" + result.getErrCode() + result.getErrMsg());
                System.out.println("Error in findVirtualAccountByUserId======>>>>>>>>" + userId + "," + homeSite + ",");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        sr.sampleEnd();
        // return  sr;
    }

    //    @Override
    public void teardownTest(JavaSamplerContext arg0) {
        end=start=System.currentTimeMillis();
        System.out.println("cost time:" + (end - start) / 1000);
    }
}
