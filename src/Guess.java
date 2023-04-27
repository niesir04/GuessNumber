import javax.swing.*;

import java.awt.event.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Guess extends JFrame implements ActionListener { //Guess继承JFrame类，实现 ActionListener接口；

    private JLabel userlabel; //私有属性；

    private JTextField usertext;

    private Random random = new Random(); //调用Ramdom()方法，获取随机数；

    public int m=(int)(Math.random()*100)+1;

    public int n = 1;

    public Guess() { //猜数游戏函数；

        setTitle("GuessGame"); //设置文本框标题；

        setLocation(300,400);//设置整个文本框位置

        setSize(400, 200); //设置文本框的大小；

        System.out.println("程序随机分配的值为："+m);

        getContentPane().setLayout(null); //设置布局管理器

        userlabel = new JLabel("请输入一个猜想数(1-100)");

                userlabel.setBounds(20, 20, 180, 40);//设置组件(请输入一个猜想数(1-100))并调整其大小。由 x(左边坐标) 和 y(上边坐标) 指定左上角的新位置，由 width(宽度) 和 height(高度)指定新的大小。

        usertext = new JTextField(10);

        usertext.setBounds(100, 60, 150, 40); //设置组件(输入方框)并调整其大小。由 x(左边坐标) 和 y(上边坐标) 指定左上角的新位置，由 width(宽度) 和 height(高度)指定新的大小。

        getContentPane().add(userlabel); // contentPane 对象,往容器添加上述两个组件；

        getContentPane().add(usertext);

        usertext.addActionListener(this); // 添加指定的操作侦听器以从此文本字段接收操作事件；

        setVisible(true); //图形界面设置为可见

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                save.saveManN(m, n);//将随机数和猜测次数保存

                System.exit(0);

            }

        });

    }

    public void actionPerformed(ActionEvent e) { //游戏内容；

        int text = Integer.parseInt(usertext.getText());

        if (n != 10) { //10次及以下；

            if (text == m) {//当用户猜对时，根据所用次数不同，显示不同内容；

                if(n == 1){

                    JOptionPane.showMessageDialog(null,n+"次就猜对了，你太有才了! 优秀");

                    save.saveManN(m, n);

                    System.exit(0);

                }

                else if((n >=2) && (n<=6)){

                    JOptionPane.showMessageDialog(null,"猜了" + n +"次猜出来了，还不错哦！ 良好");

                    save.saveManN(m, n);

                    System.exit(0);

                }

                else if(n >6){

                    JOptionPane.showMessageDialog(null,"猜了" + n +"次才猜出来，尚需努力啊！合格");

                    save.saveManN(m, n);

                    System.exit(0);

                }

            }

            else if(text<1 || text>100)

                JOptionPane.showMessageDialog(null, "输入数字非法哦！请重新输入一个1~100之间的数。");

            else if (text < m) //对用户输入数据的提醒；

                JOptionPane.showMessageDialog(null, "很遗憾！偏小了，请重新再猜。");

            else if (text > m)

                JOptionPane.showMessageDialog(null, "很遗憾！偏大了，请重新再猜。");

            n++;

        } else{

            JOptionPane.showMessageDialog(null, "10次都猜不出来...,猜测次数已用尽"); //超过十次，自动结束游戏；

            save.saveManN(m, n);

            System.exit(0);

        }

    }

    public static void main(String[] args) {

        Guess mathgame = new Guess();

        mathgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //退出整个程序

    }

}

 class save {

    public static void saveManN(int m, int n)

    {

        try {

            FileWriter fw = new FileWriter("d:/guessgame.txt", true); //定义FileWriter变量

            fw.write("随机数: "+m+"\n"+"猜测所用次数 : "+n+"\n");//写入数据

            fw.close();//关闭文件

        } catch (IOException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

    }

}
