package cn.edu.nju.jungle.plan;

import cn.edu.nju.jungle.util.Constant;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanAborted;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanContextCondition;
import jadex.bdiv3.annotation.PlanFailed;
import jadex.bdiv3.annotation.PlanPassed;
import jadex.bdiv3.runtime.IPlan;

/**
 * 
 * @author Jungle
 *
 */
@Plan
public class BuyPlan {
    public BuyPlan(){
        
    }
    
    @Belief
    protected boolean canBuy = true;
    
    @PlanAPI
    protected IPlan plan;
    
    @PlanContextCondition(beliefs="canBuy")
    public boolean checkCondition()
    {
        return canBuy;
    }
    
    @PlanBody
    public int buyGoods(){
        int quantity = generateRandom(Constant.NORMAL_RANDOM);
        //throw new PlanFailureException();
        return quantity;
    }
    
    @PlanPassed
    public void passed()
    {
      //System.out.println("Plan finished successfully.");
    }
    
    @PlanAborted
    public void aborted()
    {
      //System.out.println("Plan aborted.");
    }
    
    @PlanFailed
    public void failed(Exception e)
    {
      //System.out.println("Plan failed: "+e);
    }
    protected int generateRandom(int randType){
        double r = 0;
        switch(randType){
            case Constant.NORMAL_RANDOM:
                r = normalRand(100, 100);
                break;
            case Constant.POISSON_RANDOM:
                r = poissonRand(100);
                break;
            case Constant.POSITIVE_RANDOM:
            default:
                r = positiveRand(150);
                break;
        }
        return (int) Math.floor(r);
    }
    private double normalRand(double miu, double sigma2){
        double N = 12;
        double x=0,temp=N;
        while(x<=0){
            x=0;
            for(int i=0;i<N;i++)
            x=x+(Math.random());
            x=(x-temp/2)/(Math.sqrt(temp/12));
            x=miu+x*Math.sqrt(sigma2);
         }
         return x;
    }
    private double poissonRand(double lamda){
        double x=0,b=1,c=Math.exp(-lamda),u;
        while(b>=c){
            u=Math.random();
            b *=u;
            if(b>=c)
                x++;
         }
        return x;
    }
    private double positiveRand(double top){
        return Math.random()*top;
    }
}