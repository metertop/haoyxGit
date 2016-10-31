import com.gome.pangu.virtual.dubbo.model.FindVirtualParam;
import com.gome.pangu.virtual.dubbo.model.ResultDO;
import com.gome.pangu.virtual.dubbo.model.VirtualHistoryResult;
import com.gome.pangu.virtual.dubbo.service.VirtualQueryService;
import clientinit.Dubbo_init;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by zhuhewei on 2015/9/6.
 */
public class TVirtualQueryServiceFindVirtualAccountHistoryAdapter_PFT {
    private long start = 0;
    private long end = 0;
    private  String userId="userId";
    private  String homeSite="homeSite";
    private Integer pageNo=1;
    private Integer pageSize=100;
    private String itemId;
    private String status;
    private Date startCreateDate=new Date();
    private Date endCreateDate=new Date();

    private Arguments params;
    private FindVirtualParam findVirtualParam;

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
        params.addArgument("pageNo","pageNo");
        params.addArgument("pageSize","pageSize");
        params.addArgument("itemId","itemId");
        params.addArgument("status","status");

        return params;
    }

    public void SetValues(JavaSamplerContext arg0)
    {
        userId=arg0.getParameter("userId","");
        homeSite=arg0.getParameter("homeSite","homeSite");
        pageNo=arg0.getIntParameter("pageNo", 1);
        pageSize=arg0.getIntParameter("pageSize",100);
        itemId=arg0.getParameter("itemId","itemId");
        status=arg0.getParameter("status","status");
    }
    @Test
    public void runTest()
    {
//        FindVirtualParam findVirtualParam = new FindVirtualParam();
//        findVirtualParam.setItemId(itemId);
        userId= "3142053";

        SampleResult sr = new SampleResult();
//        SetValues(arg0);
        sr.sampleStart();
        start=System.currentTimeMillis();
        try{
//            ResultDO<Boolean> result = virtualProcService.createVirtualAccount(userId, txId, homeSite, VirtualParam);
            ResultDO<List<VirtualHistoryResult>> result = virtualQueryService.findVirtualAccountHistory(userId, homeSite,pageNo,pageSize,findVirtualParam);
            if(result.isSuccess())
            {
                sr.setSuccessful(true);
                System.out.println("Success in findVirtualAccountByUserId======>>>>>>>>" + result.getErrCode() + result.getErrMsg());
                //System.out.println("Error in findVirtualAccountByUserId======>>>>>>>>" + userId + "," + homeSite + ","+result.getData().toString());
            }
            else
            {
                sr.setSuccessful(false);
                System.out.println("Error in findVirtualAccountByUserId======>>>>>>>>" + result.getErrCode() + result.getErrMsg());
                //System.out.println("Error in findVirtualAccountByUserId======>>>>>>>>" + userId + "," + homeSite + ","+result.getData().toString());
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
