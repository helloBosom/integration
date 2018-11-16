package fun.peri.annotation;

@HelloAnnotation
public class Test {

    public static void main(String[] args) {
        //将jvm生成的Proxy类写入文件 保存路径为:com/sun/proxy
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloAnnotation annotation = Test.class.getAnnotation(HelloAnnotation.class);
        annotation.say();
    }
}
