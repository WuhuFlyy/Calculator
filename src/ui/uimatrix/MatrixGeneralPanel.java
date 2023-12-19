package ui.uimatrix;

import javax.swing.*;
import java.awt.*;

import static ui.UIValues.createButton;

/**
 * @author 罗孝俊
 * @Description: 矩阵运算主界面的按钮板
 * @date 2023/12/8 13:24
 */
public class MatrixGeneralPanel extends JPanel {
    public JButton btnRelative;
    public JButton btnMultiply;
    public JButton btnPow;
    public JButton btnBack;

    /**
     * @param btnBack UI界面传入的返回按钮
     * @Description 按钮板构造
     * @author 罗孝俊
     * @date 2023/12/8 13:28
     **/
    public MatrixGeneralPanel(JButton btnBack) {
        this.btnBack = btnBack;
        setLayout(new GridLayout(3, 1, 0, 100));
        initButton();
        add(btnRelative);
        add(btnMultiply);
        add(btnPow);
    }

    /**
     * @Description 初始化按钮
     * @author 罗孝俊
     * @date 2023/12/8 13:32
     **/
    private void initButton() {
        btnRelative = createButton("矩阵求逆与转置", "宋体");
        btnRelative.addActionListener(event -> {
            new MatrixRelativeUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });

        btnMultiply = createButton("矩阵乘法", "宋体");
        btnMultiply.addActionListener(event -> {
            new MatrixMultiplyUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });

        btnPow = createButton("矩阵求幂", "宋体");
        btnPow.addActionListener(event -> {
            new MatrixPowUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });
    }
}
