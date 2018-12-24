package com.hessianclient;


public class ClientTest {
	/* 实验：测试Hessian技术进行远程服务调用
	 * 原理：从Hessian原代码来看，底层是用Servlet来实现的。
	 * 要求：hessian-4.0.7-src.jar需要放到Tomcat下的lib文件夹中，这样服务端才能正常运行。
	 * 问题：在Eclipse中可以进行run执行，但在Dos命令行下却报错：
	 * 	Caused by: java.lang.ClassNotFoundException: com.caucho.hessian.client.HessianProxyFactory
	 */
    public static String url = "http://127.0.0.1:8080/testweb2/Hello";
    public static void  main(String[] args){
        /*HessianProxyFactory factory = new HessianProxyFactory();
        try {
            IHello iHello = (IHello) factory.create(IHello.class, url);
            System.out.println(iHello.sayHello());
            Person p2 = iHello.getPerson();//获取成功，说明Hessian可以进行对象传输。
            System.out.println(p2.getName()+" say:"+p2.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /* 注意：Person类必须放到com.fh下，这是要求与Server上的Person类一致，否则这里就会报找不到类的错误。
         */
    }
    /* Hessian：写一个Hessian需要注意的问题：
     1、JAVA服务器端必须具备以下几点：
	        包含Hessian的jar包
	        设计一个接口，用来给客户端调用
	        实现该接口的动能
	        配置web.xml,配置相应的servlet
	        对象必须实现Serializable接口
	        对于复杂对象可以使用Map的方法传递
	     2、客户端必须具备以下几点：
	        java客户端包含Hessian.jar包
	        具有和服务器端结构一样的接口和实体类。包括命名空间都最好一样。利用HessianProxyFactory调用远程接口
	 */
}