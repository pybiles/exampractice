package com.yjx;

public class StringConstPool {

    public static void main(String[] args) {

        String a1 = "haha";
        String a2 = "haha";

        //true  字符串常量池
        System.out.println( a1 == a2 );


        //用new之后强制去内存中开辟内存
        String a3 = new String("heihhei");
        String a4 = new String("heihhei");
        System.out.println(a3);
        System.out.println(a4);
//        Java中的8种基本数据类型（byte,short,char,int,long,float,double,boolean）比较他们之间的值是否相等。
//    引用数据类型，比较的是他们在堆内存地址是否相等。每新new一个引用类型的对象，会重新分配堆内存空间，使用==比较返回false。
        System.out.println( a3==a4);

        //intern  强制从常量池中获取一个与当前字符串内容相同的String对象
        // if(常量池中有"heihei"){
        //     返回常量池中的"heihei";
        //  } else{
        //     在常量池中创建"heihei",并返回
        //  }
        System.out.println(a3.intern() == a4.intern());

//   == 的作用：
//　　基本类型：比较值是否相等
//　　引用类型：比较内存地址值是否相等

//   equals 的作用:
//　　引用类型：默认情况下，比较内存地址值是否相等。可以按照需求逻辑，重写对象的equals方法

    }

}

