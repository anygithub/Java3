import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class TestingClass {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface BeforeSuite {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface AfterSuite {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Test {
        int value();
    }

    private static void performTests(Class cls) throws RuntimeException {
        TestingClass testingObj = null;
        
        try {
            testingObj = (TestingClass)cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        Method beforeMethod = null;
        Method afterMethod = null;
        Method[] methods = cls.getMethods();
        List<MethodWithPriority> testingMethods = new ArrayList<>();

        for (Method method : methods)
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeMethod != null)
                    throw new RuntimeException("Only one method can have @BeforeSuite annotation");
                beforeMethod = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterMethod != null)
                    throw new RuntimeException("Only one method can have @AfterSuite annotation");
                afterMethod = method;
            } else if (method.getAnnotation(Test.class) != null){
                Test annotationTst = method.getAnnotation(Test.class);
                testingMethods.add(new MethodWithPriority(method, annotationTst.value()));
            }

        testingMethods.sort(
            Comparator.comparing(MethodWithPriority::getPriority));

        try {
            if (beforeMethod != null)
                beforeMethod.invoke(testingObj);

            for (MethodWithPriority methodWP : testingMethods)
                methodWP.method.invoke(testingObj);

            if (afterMethod != null)
                afterMethod.invoke(testingObj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void start(Class cls) {
        performTests(cls);
    }
    
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite");
    }
    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite");
    }
    
    @Test(value = 5)
    public void test5() {
        System.out.println("Test 5");
    }
    @Test(value = 3)
    public void test3(){
        System.out.println("Test 3");
    }
    @Test(value = 1)
    public void test1(){
        System.out.println("Test 1");
    }
    @Test(value = 500)
    public void test500(){
        System.out.println("Test 500");
    }  
    

}


