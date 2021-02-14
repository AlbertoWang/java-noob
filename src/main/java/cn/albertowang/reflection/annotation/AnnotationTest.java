package cn.albertowang.reflection.annotation;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/12 下午2:38
 * @description 注解代码测试类
 **/

/*
 * 输出：
 * @cn.albertowang.reflection.annotation.MyAnnotation(stringArray=[default, string, array], defaultVal=-1, color=Red, metaAnnotation=@cn.albertowang.reflection.annotation.MetaAnnotation(value=default), value=ThisIsNoneDefaultValue)
 * @cn.albertowang.reflection.annotation.NoneInheritedAnnotation(value=ThisIsNoneInheritedAnnotation, metaAnnotation=@cn.albertowang.reflection.annotation.MetaAnnotation(value=MetaAnnotationValue))
 * @cn.albertowang.reflection.annotation.MyAnnotation(stringArray=[default, string, array], defaultVal=-1, color=Red, metaAnnotation=@cn.albertowang.reflection.annotation.MetaAnnotation(value=default), value=ThisIsNoneDefaultValue)
 * 子类未继承NoneInheritedAnnotation
 */


// value可以省略
@MyAnnotation("ThisIsNoneDefaultValue")
// 没给出缺省的元素需要指定
@NoneInheritedAnnotation(metaAnnotation = @MetaAnnotation("MetaAnnotationValue"))
public class AnnotationTest {

    // AnnotationTest的子类，由于MyAnnotation注解被@Inherited标注，该子类可以继承AnnotationTest的注解
    public class AnnotationSubTest extends AnnotationTest {

    }

    public static void main(String[] args) {
        Class<AnnotationTest> annotationTestClass = AnnotationTest.class;
        Class<AnnotationSubTest> annotationSubTestClass = AnnotationSubTest.class;
        Class<MyAnnotation> myAnnotationClass = MyAnnotation.class;
        Class<NoneInheritedAnnotation> noneInheritedAnnotationClass = NoneInheritedAnnotation.class;
        // 判断MyAnnotation与NoneInheritedAnnotation是否被应用到AnnotationTest类上
        if (annotationTestClass.isAnnotationPresent(myAnnotationClass)) {
            // 获取修饰AnnotationTest类的注解：MyAnnotation
            MyAnnotation myAnnotation = annotationTestClass.getAnnotation(myAnnotationClass);
            System.out.println(myAnnotation);

            NoneInheritedAnnotation noneInheritedAnnotation = annotationTestClass.getAnnotation(noneInheritedAnnotationClass);
            System.out.println(noneInheritedAnnotation);
        }

        // 判断MyAnnotation是否被应用到AnnotationTest的子类AnnotationSubTest上
        if (annotationSubTestClass.isAnnotationPresent(myAnnotationClass)) {
            // 获取修饰AnnotationSubTest类的注解：MyAnnotation
            MyAnnotation myAnnotation = annotationSubTestClass.getAnnotation(myAnnotationClass);
            System.out.println(myAnnotation);
        }

        // 判断NoneInheritedAnnotation是否被应用到AnnotationTest的子类AnnotationSubTest上
        if (annotationSubTestClass.isAnnotationPresent(noneInheritedAnnotationClass)) {
            // 获取修饰AnnotationSubTest类的注解：NoneInheritedAnnotation
            NoneInheritedAnnotation noneInheritedAnnotation = annotationSubTestClass.getAnnotation(noneInheritedAnnotationClass);
            System.out.println(noneInheritedAnnotation);
            /*
            由于NoneInheritedAnnotation注解没有被@Inherited修饰，子类无法继承该注解，此处无输出
            */
        } else {
            System.out.println("子类未继承NoneInheritedAnnotation");
        }
    }
}
