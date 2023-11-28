package modules.basic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import modules.GlobalVariable;
/**
 * @Description 这个类用来实现三角函数、对数指数之类的函数运算.
 *  * 只使用了BigDecimal类，公共方法均以String类型作为返回值和参数.
 *  * @author 黄文杰
 * @date 2023/11/19 23:22
 *
 * */
public class Operation_2{

    private final int accuracy;
    private final BigDecimal accuracyNum;
    private final BigDecimal atan05;
    private final BigDecimal PI2;
    private final static BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
    private BigDecimal log10;
    private final BigDecimal _105_095;
    private final BigDecimal log_105_095;
    /**
     * 
     * @Description 这个类的构造方法，只会为上述final变量进行赋值
     * @param ac 指明所需要的精度，计算时真正的精度在此基础上加2
     * @author 黄文杰
     * @date 2023/11/19 23:48
     */
    public Operation_2(int ac){
        accuracy = GlobalVariable.decimalScale + 2;
        accuracyNum = BigDecimal.ONE.divide(BigDecimal.TEN.pow(accuracy));
        _105_095 = new BigDecimal("1.05").divide(new BigDecimal("0.95"),accuracy,RoundingMode.HALF_EVEN);
        log_105_095 = log_095_105(_105_095);
        atan05 = atan(new BigDecimal("0.5"));
        PI2 = PI.divide(new BigDecimal("2"),accuracy,RoundingMode.HALF_EVEN);
        log10 = new BigDecimal("0");
        for(int i=0;i<10;i++){
            log10 = log10.add(log_095_105(new BigDecimal("1.25")));
        }
        log10 = log10.add(log_095_105(new BigDecimal("1.073741824")));
    }

    /**
     * @Description 阶乘计算
     * @param n 用于计算的整数
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/27 10:03
    **/
    private BigDecimal fac(int n){
        if(n < 0){//负数没有阶乘
            throw new ArithmeticException("负数无阶乘");
        }

        BigInteger res = new BigInteger("1");
        BigInteger Bn = BigInteger.valueOf(n); //将n转为BigInteger类型

        while(Bn.compareTo(BigInteger.ONE) > 0){ //大于1的时候累乘
            res = res.multiply(Bn);
            Bn = Bn.subtract(BigInteger.ONE); //自减1
        }
        return new BigDecimal(res); //阶乘结果一定为整数，不需要规定精度
    }

    /**
     *
     * @Decription 计算arctan(xx),精度由最开始的ac+2确定，针对运行速度做了改进(有借鉴)
     * @param x 自变量，以String类型表示
     * @return java.lang.String 返回结果，精度由最开始的ac+2确定
     * @author 黄文杰
     * @date 2023/11/19 23:54
     */
    private BigDecimal atan(BigDecimal x){
        boolean isMinus = false;
        boolean isGt1 = false;
        boolean isGt05 = false;
        if(x.signum() == -1){
            x = x.negate();
            isMinus = true;
        }
        if(x.compareTo(BigDecimal.ONE) > 0){
            x = BigDecimal.ONE.divide(x,accuracy,RoundingMode.HALF_EVEN);
            isGt1 = true;
        }

        if(x.compareTo(new BigDecimal("0.5")) > 0){
            BigDecimal fm = x.multiply(new BigDecimal("0.5")).add(BigDecimal.ONE);
            x = x.subtract(new BigDecimal("0.5")).divide(fm,accuracy,RoundingMode.HALF_EVEN);
            isGt05 = true;
        }

        BigDecimal res = new BigDecimal("0");
        BigDecimal term = new BigDecimal("0");
        int i = 0;

        do{
            term = x.pow(4*i+1).divide(new BigDecimal(4*i+1),accuracy,RoundingMode.HALF_EVEN);
            term = term.subtract(x.pow(4*i+3).divide(new BigDecimal(4*i+3),accuracy,RoundingMode.HALF_EVEN));
            res = res.add(term);
            i++;
        }while(term.compareTo(accuracyNum) > 0);

        if(isGt05){
            res = atan05.add(res);
        }
        if(isGt1){
            res = PI2.subtract(res);
        }
        if(isMinus){
            res = res.negate();
        }
        return res.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 快速计算，需要用到的函数，计算“当x∈[0.95,1.05]之间时”，lnx的值
     * @param x 自变量，x∈[0.95,1.05]
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/27 10:10
    **/
    private BigDecimal log_095_105(BigDecimal x){
        if(x.compareTo(BigDecimal.ZERO) <= 0){ //ln(x)中参数x必须大于0
            throw new ArithmeticException("参数必须大于0");
        }

        //ln(x)的泰勒展开：ln(x)=2y(y^0/1 + y^2/3 + y^4/5 + y^6/7 + ...)
        //其中：y=(x-1)/(x+1)
        BigDecimal y = (x.subtract(BigDecimal.ONE)).divide(x.add(BigDecimal.ONE),accuracy,RoundingMode.HALF_EVEN); //先计算出y
        BigDecimal res = new BigDecimal("0"); //累加的结果
        BigDecimal term = new BigDecimal("0"); //在循环中计算每一项，累加
        int i = 0; // 累加变量

        do{
            term = y.pow(2*i).divide(new BigDecimal(2*i+1),accuracy,RoundingMode.HALF_EVEN); //按照展开的通项公式计算第i项
            res = res.add(term); //每项累加
            i++; //第i项
        }while(term.compareTo(accuracyNum) > 0); //当累加的项小于精度要求时退出，代表已达到设定精度

        res = res.multiply(y);
        res = res.multiply(new BigDecimal("2")); //乘以前面的2y

        return res.setScale(accuracy,RoundingMode.HALF_EVEN); //将结果精度设为accuracy
    }
    /**
     * @Description 该函数调用log_095_105函数实现对(0,+无穷)范围的参数求ln(x)
     * @Description 思路：先缩放到(0.5,5]，这里会用到ln10,然后再缩放到(0.95,1.05],这里会用到ln(1.05/0.95)
     * @param x
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/27 10:25
    **/
    private BigDecimal log(BigDecimal x){
        if(x.compareTo(BigDecimal.ZERO) <= 0){
            throw new ArithmeticException("参数必须大于0");
        }

        BigDecimal res = new BigDecimal("0");
        int ln10Count = 0; //10倍缩放累加变量
        int ln1_1Count = 0; //1.1倍缩放累加变量

        //ln(x)=a*ln(10)+b*ln(1.1)+ln(y)
        //其中x=10a*1.1b*y
        while(x.compareTo(new BigDecimal("5")) > 0){ //参数大于5，10倍缩小，该循环执行xlog10次，即(以10为底x的对数)次
            x = x.divide(BigDecimal.TEN,accuracy,RoundingMode.HALF_EVEN); //将该参数除以10
            ln10Count++; //累加一次代表进行了一次10倍缩小
        }
        while(x.compareTo(new BigDecimal("0.5")) < 0){ //参数小于0.5,10倍放大，该循环执行xlog0.1，即(以0.1为底x的对数)次
            x = x.multiply(BigDecimal.TEN); //将参数乘以10
            ln10Count--; //累加一次代表进行了一次10倍放大
        }

        while(x.compareTo(new BigDecimal("1.05")) > 0){ //参数大于1.05，1.1倍缩小，该循环最多执行24次，即10/(1.1^24)<1.05
            x = x.divide(_105_095,accuracy,RoundingMode.HALF_EVEN);
            ln1_1Count++; //累加一次代表进行了一次1.1倍缩小
        }
        while(x.compareTo(new BigDecimal("0.95")) < 0){ //参数小于0.95，1.1倍放大，该循环最多执行7次，即0.5*(1.1^7)>0.95
            x = x.multiply(_105_095);
            ln1_1Count--; //累加一次代表进行了一次1.1倍放大
        }
        x = x.setScale(accuracy,RoundingMode.HALF_EVEN); //重新设置舍入位

        res = log10.multiply(new BigDecimal(ln10Count)); //计算ln10Count*ln(10)的值
        res = res.add(log_105_095.multiply(new BigDecimal(ln1_1Count))); //计算ln1_1Count*ln(1.1)的值

        return res.add(log_095_105(x)).setScale(accuracy,RoundingMode.HALF_EVEN); //计算缩放到[0.95, 1.05]区间的ln(x)的值
    }

    /**
     * @Description
     * @param a 底数a
     * @param x 自变量x,必须大于0
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/27 10:43
    **/
    private BigDecimal log(BigDecimal a,BigDecimal x){
        //以a为底x的对数=log(x)/log(a)
        BigDecimal res = log(x).divide(log(a),accuracy,RoundingMode.HALF_EVEN);
        return res.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 计算a^x
     * @param a 底数a
     * @param x 指数x
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/27 11:05
    **/
    private BigDecimal pow(BigDecimal a,BigDecimal x){
        if(a.compareTo(BigDecimal.ZERO)==0 && x.compareTo(BigDecimal.ZERO)==0)
        {
            throw new ArithmeticException("无意义的0^0!");
        }
        else if(x.compareTo(BigDecimal.ZERO)==0)
        {
            return BigDecimal.ONE.setScale(accuracy,RoundingMode.HALF_EVEN);
        }

        boolean isMinus = false;

        if(x.signum() == -1){ //x为负数，先将x取反然后对结果取倒数
            x = x.negate();
            isMinus = true;
        }

        try{ //判断x是否为整数，为整数的话直接使用BigDecimal的pow函数
            x.intValueExact();
        }catch(Exception e){ //x不为整数，使用a^x的泰勒展开式计算，以下计算过程参考a^x的泰勒展开式
            if(a.compareTo(BigDecimal.ZERO)<0)
            {
                throw new ArithmeticException("无意义的负数的小数幂次!");
            }
            if(a.compareTo(BigDecimal.ZERO)==0)
            {
                return BigDecimal.ZERO.setScale(accuracy,RoundingMode.HALF_EVEN);
            }

            BigDecimal xlna = x.multiply(log(a)).setScale(accuracy,RoundingMode.HALF_EVEN); //计算x*ln(a)
            BigDecimal res = new BigDecimal("0");
            BigDecimal term = new BigDecimal("0");
            int i = 0;

            do{
                term = xlna.pow(i).divide(fac(i),accuracy,RoundingMode.HALF_EVEN);
                res = res.add(term);
                i++;
            }while(term.abs().compareTo(accuracyNum) > 0); //此处必须用绝对值比较，因为xlna可能为负数

            return isMinus ? BigDecimal.ONE.divide(res,accuracy,RoundingMode.HALF_EVEN) : res.setScale(accuracy,RoundingMode.HALF_EVEN);
        }

        BigDecimal res = a.pow(x.intValue()).setScale(accuracy,RoundingMode.HALF_EVEN); //x为整数，直接调用BigDecimal的pow函数
        return isMinus ? BigDecimal.ONE.divide(res,accuracy,RoundingMode.HALF_EVEN) : res.setScale(accuracy,RoundingMode.HALF_EVEN);
    }


    /**
     * @Description 计算cos(x) ，利用泰勒公式
     * @param x 自变量
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/27 13:05
    **/
    private BigDecimal cos(BigDecimal x){
        //先将参数转换到[0,2PI]区间
        //再将参数转换到[0,PI/2]区间
        //对于任意参数都使用[0,PI/2]区间的值等效计算

        boolean isMinus = false;

        x = x.abs(); //全取正值，因为cos(x) = cos(-x)
        int quotient = x.divideToIntegralValue(PI2).intValue(); //计算参数除以PI/2的商，舍去余数
        x = x.remainder(PI); //取x/(PI/2)的余数

        switch(quotient % 4){ //判断参数等效于[0,PI/2],[PI/2,PI],[PI,3PI/2],[3PI/2,2PI]哪个区间
            case 1:x = PI.subtract(x);isMinus = true;break; //[PI/2,PI]区间
            case 2:isMinus = true;break; //[PI,3PI/2]区间
            case 3:x = PI.subtract(x);break; //[3PI/2,2PI]区间
        }

        BigDecimal res = new BigDecimal("0");
        BigDecimal term = new BigDecimal("0");
        int i = 0;

        do{ //根据cos(x)的泰勒展开式来
            term = x.pow(2*i).divide(fac(2*i),accuracy,RoundingMode.HALF_EVEN);
            res = res.add(i%2==1 ? term.negate() : term);
            i++;
        }while(term.compareTo(accuracyNum) > 0);

        return (isMinus ? res.negate() : res).setScale(accuracy,RoundingMode.HALF_EVEN);
    }

    /**
     * @Description 计算sin(x)
     * @param x
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/27 13:13
    **/
    public BigDecimal sin(BigDecimal x){
        //计算过程参考cos(x)，只是泰勒展开式不一样

        boolean isMinus = false;

        if(x.compareTo(BigDecimal.ZERO) < 0){
            x = x.negate();
            isMinus = !isMinus;
        }

        int quotient = x.divideToIntegralValue(PI2).intValue();
        x = x.remainder(PI);

        switch(quotient % 4){
            case 1:x = PI.subtract(x);break;
            case 2:isMinus = !isMinus;break;
            case 3:x = PI.subtract(x);isMinus = !isMinus;break;
        }

        BigDecimal res = new BigDecimal("0");
        BigDecimal term = new BigDecimal("0");
        int i = 0;

        do{
            term = x.pow(2*i+1).divide(fac(2*i+1),accuracy,RoundingMode.HALF_EVEN);
            res = res.add(i%2==1 ? term.negate() : term);
            i++;
        }while(term.compareTo(accuracyNum) > 0);

        return (isMinus ? res.negate() : res).setScale(accuracy,RoundingMode.HALF_EVEN);
    }

    /**
     * @Description 计算tan(x),x要在定义域范围内
     * @param x
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/27 13:20
    **/
    public BigDecimal tan(BigDecimal x){

        if(cos(x).compareTo(BigDecimal.ZERO)==0)
        {
            throw new ArithmeticException("自变量不在定义域范围内!");
        }

        return sin(x).divide(cos(x),accuracy,RoundingMode.HALF_EVEN);
    }

    /**
     * @Description 计算arctan(x)
     * @Description 设置接口，接收string类型，返回string类型
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/27 10:17
    **/
    public String getArctan(String x) {
        BigDecimal a=new BigDecimal(x);
        return atan(a).toString();
    }
    /**
     * @Description 计算loga(x)
     * @Description 设置接口，接收string类型，返回string类型
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/27 10:30
    **/
    public String getLog(String a,String x) {
        BigDecimal b=new BigDecimal(x);
        BigDecimal c=new BigDecimal(a);
        return log(c,b).toString();
    }

    /**
     * @Description 计算a^x
     * @param a
     * @param x
     * @return
     */
    public String getPow(String a,String x) {
        BigDecimal A =new BigDecimal(a);
        BigDecimal X =new BigDecimal(x);
        return pow(A,X).toString();
    }
    /**
     * @Description 计算cos(x)
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/27 13:08
    **/
    public String getCos(String x) {
        return cos(new BigDecimal(x)).toString();
    }

    /**
     * @Description 计算sin(x)
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/27 13:15
    **/
    public String getSin(String x) {
        return sin(new BigDecimal(x)).toString();
    }
    /**
     * @Description 计算tan(x)
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/27 13:22
    **/
    public String getTan(String x) {
        return tan(new BigDecimal(x)).toString();
    }




}
