import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @author 黄文杰
 * @Description: 目前只实现了求arctan(x)的运算
 * @date 2023/11/19 23:22
 */
public class Operation_2{
    /**
     * @Description 一些计算即将需要用到的变量，这些变量都是根据需要来确定精度的，所以无法先赋值，只能根据需要来确定精度
     * @param accuracy 这是精度，用于最后保留结果
     * @param accracyNum 精度所代表的的小数，比如accracy=3，则accracyNum=0.001
     * @param atan05 这是acrtan(0.5)的BigDecimal类型，用于计算atan
     * @param PI2 π的一半，即π/2
     * @param PI π
     **/
    private final int accuracy;
    private final BigDecimal accuracyNum;
    private final BigDecimal atan05; 
    private final BigDecimal PI2;
    private final static BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");



    /**
     * @Description: 构造方法，为上述终变量进行赋值
     * @param ac 指所需要的精度，计算时真正的精度在此基础上加2
     * @author 黄文杰
     * @date 2023/11/19 23:48
     **/
    Operation_2(int ac){         
        accuracy = ac + 2;
        accuracyNum = BigDecimal.ONE.divide(BigDecimal.TEN.pow(accuracy));
        atan05 = new BigDecimal(atan("0.5"));
        PI2 = PI.divide(new BigDecimal("2"),accuracy,RoundingMode.HALF_EVEN);
    }
    /**
     * 
     * @Description 计算arctan(xx),精度由最开始的ac+2确定，针对计算方法做了改进(有借鉴)
     * @param xx 自变量，以String类型表示
     * @return java.lang.String 返回结果，精度由最开始的ac+2确定
     * @author 黄文杰
     * @date 2023/11/19 23:54
     **/    
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
