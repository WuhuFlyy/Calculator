import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * 这个类用来实现三角函数、对数指数之类的函数运算.
 * 
 * 只使用了BigDecimal类，方法均以String类型作为返回值和参数.
 * 
 * @author 黄文杰
 * @date 2023/11/19 23:22
 * 
 */
public class Operation_2{

    /** 
    * 这是精度，用于最后保留结果
    */
    private final int accuracy;
    /** 
    * 精度所代表的的小数，比如accracy=3，则accracyNum=0.001
    */
    private final BigDecimal accuracyNum;
    /** 
    * 这是acrtan(0.5)的BigDecimal类型，用于计算atan
    */
    private final BigDecimal atan05;
    /** 
    * π的一半，即π/2
    */ 
    private final BigDecimal PI2;
    /** 
    * 就是π，由于尚且不知道要多少位，暂定保留为100位
    */ 
    private final static BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");



    /**
     * 
     * 这个类的构造方法，只会为上述final变量进行赋值
     * @param ac 指明所需要的精度，计算时真正的精度在此基础上加2
     * @author 黄文杰
     * @date 2023/11/19 23:48
     */
    Operation_2(int ac){         
        accuracy = ac + 2;
        accuracyNum = BigDecimal.ONE.divide(BigDecimal.TEN.pow(accuracy));
        atan05 = new BigDecimal(atan("0.5"));
        PI2 = PI.divide(new BigDecimal("2"),accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * 
     * 计算arctan(xx),精度由最开始的ac+2确定，针对运行速度做了改进(有借鉴)
     * @param xx 自变量，以String类型表示
     * @return java.lang.String 返回结果，精度由最开始的ac+2确定
     * @author 黄文杰
     * @date 2023/11/19 23:54
     */    
    public String atan(String xx)
    {
        BigDecimal x =new BigDecimal(xx);
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
        return res.setScale(accuracy,RoundingMode.HALF_EVEN).toString(); 
    }

}
