package HMX.bean.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

//表明这是一个切面类
@Aspect
public class LogAspects {
    //抽取公共的切入点表达式
    //1.本类引用
    //2.其他的切面引用
    @Pointcut("execution(public int HMX.bean.AOP.MathCalculator.*(..))")
    public void pointCut(){

    }
    //目标方法之前切入(切入表达式)
    @Before("pointCut()")
    //JoinPoint 一定要出现在参数表的第一位
    public void logStart(JoinPoint joinPoint){
       Object[] args= joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+"除法运算......参数列表是:{"+args+"}");
    }
    @After("pointCut()")
    public void logEnd(){
        System.out.println("除法运算结束...");
    }
    @AfterReturning(value="pointCut()",returning = "result")
    public void logReturn(Object result){
        System.out.println("除法正常返回....运行结果:{"+result+"}");
    }
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logEXception(Exception exception){
        System.out.println("除法异常....异常信息:{"+exception+"}");
    }
}
