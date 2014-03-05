package cn.edu.nju.jungle.agent;

import cn.edu.nju.jungle.data.PeriodData;
import cn.edu.nju.jungle.plan.BuyPlan;
import cn.edu.nju.jungle.ui.UIManager;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Plans;
import jadex.commons.future.IResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Description;

/**
 * 
 * @author Jungle
 *
 */
@Agent
@Description("The Agent that represents the customer")
@Plans(@Plan(body=@Body(BuyPlan.class)))
public class Customer1BDI {
    @AgentCreated
    public void init(){
        UIManager.showFrame();
    }
    @Agent
    BDIAgent agent;
    @Belief
    boolean reposFull = false;
    @AgentBody
    public void body(){
        while(PeriodData.periodCount<PeriodData.MAX_PERIOD_LIMIT){
            agent.waitForDelay(200).get();
            PeriodData.periodCount++;
            agent.adoptPlan(new BuyPlan()).addResultListener(new IResultListener<Object>() {
                
                @Override
                public void resultAvailable(Object arg0) {
                    int t = (Integer) arg0;
                    UIManager.updateTextLabel(t);
                }
                
                @Override
                public void exceptionOccurred(Exception arg0) {
                    
                }
            });
        }
    }
}
