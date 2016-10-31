package APIScript;

import com.gome.pangu.virtual.dubbo.model.CreateVirtualParam;
import com.gome.pangu.virtual.dubbo.model.ResultDO;
import com.gome.pangu.virtual.dubbo.service.VirtualProcService;
import clientinit.Dubbo_init;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.Date;

/**
 * Created by zhuhewei on 2015/09/06.
 * API脚本转换为性能测试自动化框架可运行脚本
 */
public class VirtualProcServiceCreateVirtualAccountAdapter_PFT extends AbstractJavaSamplerClient{

    private long start = 0;
    private long end = 0;
    private  String userId="userId";
    private  String txId="";
    private  String homeSite="homeSite";
    private Arguments params;
    private  static VirtualProcService virtualProcService;
    Dubbo_init init = new Dubbo_init();
    //    private CreateVirtualParam VirtualParam;

    @Override
    public void setupTest(JavaSamplerContext arg0) {
        Dubbo_init init = new Dubbo_init();
        init.beforeInit();
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

    public SampleResult runTest(JavaSamplerContext arg0)
    {

        CreateVirtualParam VirtualParam = new CreateVirtualParam();

        VirtualParam.setCreateDate(new Date());
        VirtualParam.setRemark("TestRemark");
        VirtualParam.setCreateUser("TestCreateUser");
        VirtualParam.setDealInfo("TestDealInfo");

        SampleResult sr = new SampleResult();
        SetValues(arg0);
        sr.sampleStart();
        start=System.currentTimeMillis();
        try{
            ResultDO<Boolean> result = virtualProcService.createVirtualAccount(userId, txId, homeSite, VirtualParam);
            if(result.isSuccess())
            {
                sr.setSuccessful(true);
            }
            else
            {
                sr.setSuccessful(false);
                this.getLogger().info("Error in createVirtualAccount======>>>>>>>>" +result.getErrCode()+result.getErrMsg());
                this.getLogger().info("Error in createVirtualAccount======>>>>>>>>" + userId + "," + txId + "," + homeSite + "," + VirtualParam);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        sr.sampleEnd();
        return  sr;
    }

    @Override
    public void teardownTest(JavaSamplerContext arg0) {
        end=System.currentTimeMillis();
        this.getLogger().info("cost time:" + (end - start) / 1000);
    }

}
