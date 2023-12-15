package modules.basic;
import java.util.ArrayList;
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

    private static final int accuracy=GlobalVariable.decimalScale + 2;
    private static final BigDecimal accuracyNum=BigDecimal.ONE.divide(BigDecimal.TEN.pow(accuracy));
    private static final BigDecimal atan05=atan(new BigDecimal("0.5"));
    private final static BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
    private static final BigDecimal PI2=PI.divide(new BigDecimal("2"),accuracy,RoundingMode.HALF_EVEN);
    private static final BigDecimal log10;
    private static final BigDecimal _105_095=new BigDecimal("1.05").divide(new BigDecimal("0.95"),accuracy,RoundingMode.HALF_EVEN);
    private static final BigDecimal log_105_095=log_095_105(_105_095);
    /**
     * @Description 静态代码块，类加载的时候就会对log10进行赋值
     * @author 黄文杰
     * @date 2023/11/19 23:48
     */
    static{
        BigDecimal log1;
        log1 = new BigDecimal("0");
        for(int i=0;i<10;i++){
            log1 = log1.add(log_095_105(new BigDecimal("1.25")));
        }
        log1 = log1.add(log_095_105(new BigDecimal("1.073741824")));
        log10 = log1;
    }

    /**
     * @Description 阶乘计算
     * @param n 用于计算的整数
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/27 10:03
    **/
    private static BigDecimal fac(int n){
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
     * @Description 计算arctan(xx),精度由最开始的ac+2确定，针对运行速度做了改进(有借鉴)
     * @param x 自变量，以String类型表示
     * @return java.lang.String 返回结果，精度由最开始的ac+2确定
     * @author 黄文杰
     * @date 2023/11/19 23:54
     */
    private static BigDecimal atan(BigDecimal x){
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
    private static BigDecimal log_095_105(BigDecimal x){
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
    private static BigDecimal log(BigDecimal x){
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
    private static BigDecimal log(BigDecimal a,BigDecimal x){
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
    private static BigDecimal pow(BigDecimal a,BigDecimal x){
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
    private static BigDecimal cos(BigDecimal x){
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
    private static BigDecimal sin(BigDecimal x){
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
    private static BigDecimal tan(BigDecimal x){

        if(cos(x).compareTo(BigDecimal.ZERO)==0)
        {
            throw new ArithmeticException("自变量不在定义域范围内!");
        }

        return sin(x).divide(cos(x),accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 反正弦函数计算，参数范围是[-1,1]
     * @param x
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/28 19:06
    **/
    private static BigDecimal asin(BigDecimal x){
        //反正弦函数使用反正切函数计算
        //arcsin(x) = arctan(x/sqrt(1-x^2))

        boolean isMinus = false;

        if(x.signum() == -1){ //参数为负，根据arcsin(x)=-arcsin(-x)，对参数和结果取反
            x = x.negate();
            isMinus = true;
        }

        if(x.compareTo(BigDecimal.ONE) > 0){ //限制区间为[-1, 1]
            throw new ArithmeticException("该值无意义,定义域为[-1,1]");
        }
        else if(x.compareTo(BigDecimal.ONE) == 0){ //arcsin(1)，直接返回PI/2，1为参数无法计算
            return PI2;
        }

        //利用上面公式计算arcsin(x)
        BigDecimal res = pow(x,new BigDecimal("2"));
        res = BigDecimal.ONE.subtract(res);
        res = pow(res,new BigDecimal("0.5"));
        res = x.divide(res,accuracy,RoundingMode.HALF_EVEN);
        res = atan(res);

        return (isMinus ? res.negate() : res).setScale(accuracy,RoundingMode.HALF_EVEN);
    }

    /**
     * @Description 反余弦函数，参数范围仍然是[-1,1]
     * @param x
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/11/28 19:31
    **/
    private static BigDecimal acos(BigDecimal x){
        //限制区间为[-1, 1]
        //arcsin(x)+arccos(x)=PI/2;
        return PI2.subtract(asin(x)).setScale(accuracy,RoundingMode.HALF_EVEN); //返回PI2-arcsin(x)
    }

    /**
     * @Description 用于把一个字符串变成一组数据，如果格式错误会返回异常
     * @param x
     * @return java.util.ArrayList<java.math.BigDecimal>
     * @author 黄文杰
     * @date 2023/12/13 17:08
    **/
    private static ArrayList<BigDecimal> changeToBigdecimal(String x) throws Exception {
        StringBuilder sb=new StringBuilder();
        String[] rawkeys=new String[20];
        String[] rawkeys2=new String[18];
        String str= x;
        String str1=str.trim();
        rawkeys2=str1.split(" ");//将原输入按空格划分为几个字符串
        for(int i=0;i<rawkeys2.length;i++)
        {
            if(i==rawkeys2.length-1)
            {
                sb.append(rawkeys2[i]);
                break;
            }
            if(rawkeys2[i].length()!=0)
            {
                sb.append(rawkeys2[i]);
                sb.append(" ");
            }
        }
        String result=sb.toString();
        rawkeys=result.split(" ");
        ArrayList<BigDecimal> arrayList=new ArrayList<>();
        for(int i=0;i<rawkeys.length;i++)
        {
            BigDecimal as;
            try
            {
                if(rawkeys[i].toCharArray()[rawkeys[i].length()-1]=='.')
                {
                    throw(new Exception("输入中含非法字符！"));
                }
                as=new BigDecimal(rawkeys[i]);
            }
            catch(NumberFormatException xa)
            {

                throw(new Exception("输入中含非法字符！"));
            }
            arrayList.add(as);

        }
        return arrayList;
    }
    /**
     * @Description 返回一元线性回归的斜率
     * @param x 自变量数据
     * @param y 因变量数据
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:12
    **/
    private static BigDecimal slope(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y) throws Exception {
        if(x.size()!=y.size())
        {
            throw (new Exception("x与y数据个数不一致!"));
        }
        BigDecimal ave_x=BigDecimal.ZERO;
        BigDecimal ave_y=BigDecimal.ZERO;
        BigDecimal ave_xy=BigDecimal.ZERO;
        BigDecimal ave_x2=BigDecimal.ZERO;
        for(int i=0;i<x.size();i++)
        {
            ave_x=ave_x.add(x.get(i));
        }
        ave_x=ave_x.divide(new BigDecimal(x.size()),accuracy,RoundingMode.HALF_EVEN);
        for(int i=0;i<y.size();i++)
        {
            ave_y=ave_y.add(y.get(i));
        }
        ave_y=ave_y.divide(new BigDecimal(y.size()),accuracy,RoundingMode.HALF_EVEN);
        for(int i=0;i<x.size();i++)
        {
            ave_xy=ave_xy.add(x.get(i).multiply(y.get(i)));
        }
        ave_xy=ave_xy.divide(new BigDecimal(x.size()),accuracy,RoundingMode.HALF_EVEN);
        for(int i=0;i<x.size();i++)
        {
            ave_x2=ave_x2.add(x.get(i).multiply(x.get(i)));
        }
        ave_x2=ave_x2.divide(new BigDecimal(x.size()),accuracy,RoundingMode.HALF_EVEN);
        BigDecimal xie=ave_x.multiply(ave_y).subtract(ave_xy).divide(ave_x.multiply(ave_x).subtract(ave_x2),accuracy,RoundingMode.HALF_EVEN);
        return xie.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 返回一元线性回归的截距
     * @param x
     * @param y
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:12
    **/
    private static BigDecimal intercept(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y) throws Exception {
        if(x.size()!=y.size())
        {
            throw (new Exception("x与y数据个数不一致!"));
        }
        BigDecimal ave_x=BigDecimal.ZERO;
        BigDecimal ave_y=BigDecimal.ZERO;
        for(int i=0;i<x.size();i++)
        {
            ave_x=ave_x.add(x.get(i));
        }
        ave_x=ave_x.divide(new BigDecimal(x.size()),accuracy,RoundingMode.HALF_EVEN);
        for(int i=0;i<y.size();i++)
        {
            ave_y=ave_y.add(y.get(i));
        }
        ave_y=ave_y.divide(new BigDecimal(y.size()),accuracy,RoundingMode.HALF_EVEN);
        BigDecimal xie=slope(x,y);
        BigDecimal jie=ave_y.subtract(xie.multiply(ave_x));
        if(jie.compareTo(BigDecimal.ZERO)==0)return BigDecimal.ZERO;
        return jie.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 返回一元线性回归的相关系数
     * @param x
     * @param y
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:12
    **/
    private static BigDecimal correlationCoefficient(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y) throws Exception {
        if(x.size()!=y.size())
        {
            throw (new Exception("x与y数据个数不一致!"));
        }
        BigDecimal ave_x=BigDecimal.ZERO;
        BigDecimal ave_y=BigDecimal.ZERO;
        BigDecimal ave_xy=BigDecimal.ZERO;
        BigDecimal ave_x2=BigDecimal.ZERO;
        BigDecimal ave_y2=BigDecimal.ZERO;
        for(int i=0;i<x.size();i++)
        {
            ave_x=ave_x.add(x.get(i));
            ave_y=ave_y.add(y.get(i));
            ave_xy=ave_xy.add(x.get(i).multiply(y.get(i)));
            ave_x2=ave_x2.add(x.get(i).multiply(x.get(i)));
            ave_y2=ave_y2.add(y.get(i).multiply(y.get(i)));
        }
        BigDecimal sq=pow(new BigDecimal(x.size()).multiply(ave_x2).subtract(ave_x.multiply(ave_x)).multiply(new BigDecimal(y.size()).multiply(ave_y2).subtract(ave_y.multiply(ave_y))),new BigDecimal("0.5"));

        BigDecimal rr=new BigDecimal(x.size()).multiply(ave_xy).subtract(ave_x.multiply(ave_y)).divide(sq,accuracy,RoundingMode.HALF_EVEN);
        return rr.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 返回一元线性回归的标准差
     * @param x
     * @param y
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:13
    **/
    private static BigDecimal standardDeviation(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y) throws Exception{
        if(x.size()!=y.size())
        {
            throw (new Exception("x与y数据个数不一致!"));
        }
        if(x.size()<=2)
        {
            throw (new Exception("数据个数不得小于3!"));
        }
        BigDecimal rns= BigDecimal.ZERO;
        BigDecimal a=slope(x,y);
        BigDecimal b=intercept(x,y);
        for(int i=0;i<x.size();i++)
        {
            rns = rns.add(y.get(i).subtract(b.add(a.multiply(x.get(i)))).pow(2));
        }
        rns = pow(rns.divide(new BigDecimal(x.size()-2),accuracy,RoundingMode.HALF_EVEN),new BigDecimal("0.5"));
        return rns.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 返回斜率的a类不确定度
     * @param x
     * @param y
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:13
    **/
    private static BigDecimal uaSlope(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y)throws Exception{

        if(x.size()!=y.size())
        {
            throw (new Exception("x与y数据个数不一致!"));
        }
        if(x.size()<=2)
        {
            throw (new Exception("数据个数不得小于3!"));
        }
        BigDecimal xie=slope(x,y);
        BigDecimal r=correlationCoefficient(x,y);
        BigDecimal rns= xie.multiply(pow(BigDecimal.ONE.divide(r.multiply(r),accuracy,RoundingMode.HALF_EVEN).subtract(BigDecimal.ONE).divide(new BigDecimal(x.size()-2),accuracy,RoundingMode.HALF_EVEN),new BigDecimal("0.5")));
        return rns.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 返回截距的a类不确定度
     * @param x
     * @param y
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:14
    **/
    private static BigDecimal uaIntercept(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y) throws Exception {
        BigDecimal ave_x2=BigDecimal.ZERO;
        for(int i=0;i<x.size();i++)
        {
            ave_x2=ave_x2.add(x.get(i).multiply(x.get(i)));
        }
        ave_x2=ave_x2.divide(new BigDecimal(x.size()),accuracy,RoundingMode.HALF_EVEN);


        BigDecimal Ua_xie=uaSlope(x,y);
        BigDecimal rns = pow(ave_x2,new BigDecimal("0.5")).multiply(Ua_xie);
        return rns.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 返回斜率的b类不确定度
     * @param x
     * @param y
     * @param fenduzhi 分度值，也就是仪器误差限
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:14
    **/
    private static BigDecimal ubSlope(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y,BigDecimal fenduzhi) {

        BigDecimal ave_x2=BigDecimal.ZERO;
        BigDecimal ave_x=BigDecimal.ZERO;
        for(int i=0;i<x.size();i++)
        {
            ave_x2=ave_x2.add(x.get(i).multiply(x.get(i)));
        }
        ave_x2=ave_x2.divide(new BigDecimal(x.size()),accuracy,RoundingMode.HALF_EVEN);
        for(int i=0;i<x.size();i++)
        {
            ave_x=ave_x.add(x.get(i));
        }
        ave_x=ave_x.divide(new BigDecimal(x.size()),accuracy,RoundingMode.HALF_EVEN);
        BigDecimal Ub_y=fenduzhi.divide(pow(new BigDecimal(3),new BigDecimal("0.5")),accuracy,RoundingMode.HALF_EVEN);
        BigDecimal rns=pow(BigDecimal.ONE.divide(new BigDecimal(x.size()).multiply(ave_x2.subtract(ave_x.multiply(ave_x))),accuracy,RoundingMode.HALF_EVEN),new BigDecimal("0.5"));
        rns =rns.multiply(Ub_y);
        return rns.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description  返回截距的b类不确定度
     * @param x
     * @param y
     * @param fenduzhi
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:14
    **/
    private static BigDecimal ubIntercept(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y,BigDecimal fenduzhi){
        BigDecimal ub_xie=ubSlope(x,y,fenduzhi);
        BigDecimal ave_x2=BigDecimal.ZERO;
        for(int i=0;i<x.size();i++)
        {
            ave_x2=ave_x2.add(x.get(i).multiply(x.get(i)));
        }
        ave_x2=ave_x2.divide(new BigDecimal(x.size()),accuracy,RoundingMode.HALF_EVEN);
        BigDecimal rns=ub_xie.multiply(pow(ave_x2,new BigDecimal("0.5")));
        return rns.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 返回斜率的合成不确定度
     * @param x
     * @param y
     * @param fenduzhi
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:15
    **/
    private static BigDecimal uSlope(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y,BigDecimal fenduzhi) throws Exception {
        BigDecimal rns=pow(ubSlope(x,y,fenduzhi).pow(2).add(uaSlope(x,y).pow(2)),new BigDecimal("0.5"));
        return rns.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description  返回截距的合成不确定度
     * @param x
     * @param y
     * @param fenduzhi
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/13 17:15
    **/
    private static BigDecimal uIntercept(ArrayList<BigDecimal> x,ArrayList<BigDecimal> y,BigDecimal fenduzhi) throws Exception {
        BigDecimal rns=pow(uaIntercept(x,y).pow(2).add(ubIntercept(x,y,fenduzhi).pow(2)),new BigDecimal("0.5"));
        return rns.setScale(accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * @Description 通过字符串返回分度值，会对异常情况进行处理
     * @param fenduzhi
     * @return java.math.BigDecimal
     * @author 黄文杰
     * @date 2023/12/14 0:24
    **/
    private  static BigDecimal division(String fenduzhi) throws Exception {
        BigDecimal s;
        try
        {
            if(fenduzhi.toCharArray()[fenduzhi.length()-1]=='.')
            {
                throw(new Exception("分度值输入格式错误！"));
            }
            s=new BigDecimal(fenduzhi);
        }
        catch(Exception a)
        {
            throw(new Exception("分度值输入格式错误！"));
        }
        return s;
    }
    /**
     * @Description 返回斜率
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:25
    **/
    public static String getSlope(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return slope(x,y).toString();
    }
    /**
     * @Description 返回截距
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:25
    **/
    public static String getIntercept(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return intercept(x,y).toString();
    }
    /**
     * @Description 返回相关系数r
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:26
    **/
    public static String getCorrelationCoefficient(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return correlationCoefficient(x,y).toString();
    }
    /**
     * @Description 返回标准差
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:26
    **/
    public static String getStandardDeviation(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return standardDeviation(x,y).toString();
    }
    /**
     * @Description 返回斜率的a类不确定度
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:27
    **/
    public static String getUaSlope(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return uaSlope(x,y).toString();
    }
    /**
     * @Description 返回截距的a类不确定度
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:27
    **/
    public static String getUaIntercept(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return uaIntercept(x,y).toString();
    }
    /**
     * @Description 返回斜率的b类不确定度
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:27
    **/
    public static String getUbSlope(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return ubIntercept(x,y,fendua).toString();
    }
    /**
     * @Description 返回截距的b类不确定度
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:28
    **/
    public static String getUbIntercept(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return ubIntercept(x,y,fendua).toString();
    }
    /**
     * @Description 返回斜率的合成不确定度
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:28
    **/
    public static String getUSlope(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return uSlope(x,y,fendua).toString();
    }
    /**
     * @Description 返回截距的合成不确定度
     * @param xx
     * @param yy
     * @param fenduzhi
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/12/14 0:28
    **/
    public static String getUIntercept(String xx,String yy,String fenduzhi) throws Exception {
        BigDecimal fendua=division(fenduzhi);
        ArrayList<BigDecimal> x=changeToBigdecimal(xx);
        ArrayList<BigDecimal> y=changeToBigdecimal(yy);
        return uIntercept(x,y,fendua).toString();
    }

    /**
     * @Description 计算arctan(x)
     * @Description 设置接口，接收string类型，返回string类型
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/27 10:17
    **/
    public static String getArctan(String x) {
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
    public static String getLog(String a,String x) {
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
    public static String getPow(String a,String x) {
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
    public static String getCos(String x) {
        return cos(new BigDecimal(x)).toString();
    }

    /**
     * @Description 计算sin(x)
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/27 13:15
    **/
    public static String getSin(String x) {
        return sin(new BigDecimal(x)).toString();
    }
    /**
     * @Description 计算tan(x)
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/27 13:22
    **/
    public static String getTan(String x) {
        return tan(new BigDecimal(x)).toString();
    }

    /**
     * @Description 计算arcsin(x)
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/28 19:08
    **/
    public static String getArcsin(String x)
    {
        return asin(new BigDecimal(x)).toString();
    }

    /**
     * @Description 计算arccos(x)
     * @param x
     * @return java.lang.String
     * @author 黄文杰
     * @date 2023/11/28 19:33
    **/
    public static String getArccos(String x)
    {
        return acos(new BigDecimal(x)).toString();
    }

}
