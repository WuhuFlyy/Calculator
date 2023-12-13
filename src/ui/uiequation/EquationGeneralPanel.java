package ui.uiequation;

import static ui.UIValues.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author 罗孝俊
 * @Description: 解方程界面的按钮板
 * @date 2023/12/8 23:41
 */
public class EquationGeneralPanel extends JPanel {
    public JButton btnPolynomial;
    public JButton btnSimulEquation;
    public JButton btnBack;
    /**
     * @Description 按钮板构造
     * @param btnBack UI界面传入的返回按钮
     * @author 罗孝俊
     * @date 2023/12/8 23:45
    **/
    public EquationGeneralPanel(JButton btnBack){
        this.btnBack = btnBack;
        setLayout(new GridLayout(2, 1, 0, 300));
        initButton();
        add(btnPolynomial);
        add(btnSimulEquation);
    }

    /**
     * @Description 初始化按钮
     * @author 罗孝俊
     * @date 2023/12/8 23:46
    **/
    private void initButton(){
        btnPolynomial = createButton("一元多项式方程", "宋体");
        btnPolynomial.addActionListener(event -> {
            new PolynomialUI();
            this.setVisible(false);
            btnBack.setVisible(false);
        });

        btnSimulEquation = createButton("联立线性方程组", "宋体");
        btnSimulEquation.addActionListener(event -> {
            new MultivariateEquationUI();
            this.setVisible(false);
            btnBack.setVisible(false);
        });
    }
}
