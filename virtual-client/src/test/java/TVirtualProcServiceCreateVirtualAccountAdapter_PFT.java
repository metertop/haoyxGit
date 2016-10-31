import com.gome.pangu.virtual.dubbo.model.CreateVirtualParam;
import com.gome.pangu.virtual.dubbo.model.ResultDO;
import com.gome.pangu.virtual.dubbo.service.VirtualProcService;
import clientinit.Dubbo_init;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

//import java.util.Date;

/**
 * Created by zhuhewei on 2015/9/6.
 */
public class TVirtualProcServiceCreateVirtualAccountAdapter_PFT {
    private long start = 0;
    private long end = 0;
    private  String userId="userId";
    private  String txId="";
    private  String homeSite="homeSite";
    private Arguments params;
    private  static VirtualProcService virtualProcService;

    //    private CreateVirtualParam VirtualParam;
//    @Override
    @Before
    public void setupTest() {
        Dubbo_init init = new Dubbo_init();
        init.beforeInit();
//        virtualProcService = (VirtualProcService)init.getBean("virtualService");
        virtualProcService = (VirtualProcService)init.getBean("virtualProcService");
    }

    public Arguments getDefaultParameters() {
        params=new  Arguments();
        params.addArgument("userId",userId);
        params.addArgument("txId",txId);
        params.addArgument("homeSite",homeSite);
        return params;
    }

    public void SetValues(JavaSamplerContext arg0)
    {
        userId=arg0.getParameter("userId","");
        txId=arg0.getParameter("txId","");
        homeSite=arg0.getParameter("homeSite","homeSite");

    }

    @Test
    public void runTest()
    {
        CreateVirtualParam VirtualParam = new CreateVirtualParam();

        VirtualParam.setCreateDate(new Date());
        VirtualParam.setRemark("TestRemark");
        VirtualParam.setCreateUser("TestCreateUser");
        VirtualParam.setDealInfo("TestDealInfo");
        txId=UUID.randomUUID().toString();
        SampleResult sr = new SampleResult();
//        SetValues(arg0);
        sr.sampleStart();
        start=System.currentTimeMillis();
        try{
            ResultDO<Boolean> result = virtualProcService.createVirtualAccount(txId, txId, homeSite, VirtualParam);
            System.out.print(txId);
            if(result.isSuccess())
            {
                sr.setSuccessful(true);

            }
            else
            {
                sr.setSuccessful(false);
                System.out.print("Error======>>>>>>>>" +result.getErrCode()+result.getErrMsg()+result);
                System.out.print("Error======>>>>>>>>" + userId + "," + txId + "," + homeSite + "," + VirtualParam);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        sr.sampleEnd();
        //return null;
    }

    //    @Test
    public void teardownTest() {
        end=start=System.currentTimeMillis();
        System.out.print("cost time:" + (end - start) / 1000);
    }
}
