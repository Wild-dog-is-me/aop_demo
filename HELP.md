🏠 B站    https://space.bilibili.com/35305219

## 一、什么是AOP？

![image.png](https://cdn.nlark.com/yuque/0/2023/png/29016777/1688307503835-b61a118c-7b92-4868-89a5-241255c5ebc4.png#averageHue=%23f5f5f5&clientId=u27f4bb1a-93f0-4&from=paste&height=271&id=u567253c3&originHeight=542&originWidth=1672&originalType=binary&ratio=2&rotation=0&showTitle=false&size=157206&status=done&style=none&taskId=u83503d1c-9fb4-4799-ba51-7f1a2df8d5b&title=&width=836)
AOP中文名为面向切面编程，那么什么是切面？对象与对象之间，方法与方法之间，模块与模块之间其实都是一个个切面。然后我们贯穿于其中，在方法执行前、执行中、执行后进行一些额外的操作，避免了重复性的劳动。
开发低耦合系统是软件设计的终极目标之一。AOP这种面向切面编程的的方式就体现了这样的理念。将一些重复的、和业务主逻辑不相关的功能性代码通过切面模块化地抽离出来进行封装，实现关注点分离、模块解耦，使得整个系统更易于维护管理。
AOP 要实现的是在我们原来写的代码的基础上，进行一定的包装，如在方法执行前、方法返回后、方法抛出异常后等地方进行一定的拦截处理或者叫增强处理。
比如说，我们做预约的时候，都会首先校验预约人数是否满了，当前用户是否有资格预约此次活动。
那么正常情况下，我们可能会这么干。
![](https://cdn.nlark.com/yuque/0/2023/jpeg/29016777/1688308277770-c8b1aa86-d682-4740-9b5a-b10a8656397d.jpeg)
通过上图我们看到，无论做什么，我们始终要校验这两个条件，如果我们每次写代码都要带上这两个条件判断，虽然是复制粘贴，但代码总是不太优雅的，那么我们抽象一个活动校验工具类出来，每次都使用工具类里的方法判断一下，看起来合理些了，那么在我们返回数据之前，其实这就是一个切面。
![](https://cdn.nlark.com/yuque/0/2023/jpeg/29016777/1688308969273-053dbec9-3752-4262-8f03-caf0a2fd8495.jpeg)
同样有个问题，我虽然不用每次都copy代码了，但是，每个方法总得要调用这个工具类。于是就有了切面的概念，将方法注入到接口调用的某个地方，这个地方就被称之为切点。
![](https://cdn.nlark.com/yuque/0/2023/jpeg/29016777/1688349685859-d6c3a810-b21f-4dbe-8c5c-ea57c04964f7.jpeg)

## 二、AOP的相关术语

通过上面的例子，我们对AOP有了一个大概的了解，AOP中有一些术语，我们在这里详细描述解释一下：

- 1. 切面（Aspect）：切面是指横跨多个模块的关注点的模块化单元。它可以描述一个特定的功能或关注点，比如日志记录、性能监测或事务管理。切面定义了在何处和何时应该应用这些功能。
- 2. 连接点（Join Point）：连接点是程序执行过程中可以插入切面的特定点。它可以是方法调用、方法执行、异常抛出或字段访问等。
- 3. 通知（Advice）：通知是切面在特定连接点执行的代码。它定义了在连接点何时执行以及执行前、后或周围执行的操作。常见的通知类型包括前置通知（before advice）、后置通知（after advice）、异常通知（after-throwing advice）、返回通知（after-returning advice）和环绕通知（around advice）。
- 4. 切点（Pointcut）：切点是连接点的一个子集。它定义了在哪些连接点上应该应用通知。通常，切点使用表达式或模式匹配来选择连接点。
- 5. 引入（Introduction）：引入是在现有类上添加新方法或字段的一种方式。它允许向现有类动态添加功能。
- 6. 织入（Weaving）：织入是将切面应用到目标对象中创建新的代理对象的过程。织入可以在编译时、类加载时或运行时进行。
- 7. 目标对象（Target Object）：目标对象是切面所织入的原始对象。它是包含业务逻辑的对象。

> 概念看起来还是有些抽象，下面我们再举个例子吧。

小张年轻时候是个勤勤恳恳的打工仔，一直有一个忘不掉的姑娘。后来小张什么都有了，有车有房有才华有事业，只是再也看不到那个夏天的她仰头时在眼眸里的光。小张感慨从来没有与谁相伴过青春，从此之后爱的都是爱过别人的人。但没有办法，小张家里逼着小张相亲去了，小张努力描述出那个女孩的模样，长长的马尾，灵动的双眼，嘴角总挂着一抹微笑，希望相亲的对象能与她有几分相似。
离相亲见面的日子越来越近了，小张也越来越忐忑，多希望那个女孩就是她。相亲的日子很快到了，小张下班也没来得及收拾下自己就去了，到了地方，一个熟悉的身影映入眼帘，是她！“常回来看看，但是我更希望是我去看你”，女孩笑着说，小张的思绪一下回到了那年，那个下午，女孩对他说的话，两个人紧紧抱在一下，小张激动的流出了眼泪。
梦醒了，小张从床上坐了起来，擦干了泪。
（没错，国产爱情片就是我这种byd写的）我们看一下上面的一个催泪感人的小故事和 AOP 到底有什么关系？
首先 在 `Spring AOP` 中 `Joint point` 指代的是所有方法的执行点, 而 `point cut` 是一个描述信息, 它修饰的是 Joint point, 通过 `point cut`，我们就可以76确定哪些 `Joint point` 可以被织入 `Advice`. 对应到我们在上面举的例子, 我们可以做一个简单的类比, `joint point` 就相当于 相亲市场的所有女性,`pointcut` 就相当于 小张所描述的记忆中她的模样, 而 `Advice` 则是施加在符合小张所描述的相亲市场中女孩子的动作: 安排与小张见面，相亲。
为什么可以这样类比呢?
`Joint point `： 相亲市场上所有的女性: 因为根据定义, `Joint point` 是所有可能被织入 `Advice` 的候选的点, 在 Spring AOP中, 则可以认为所有方法执行点都是 `Joint point`. 而在我们上面的例子中, 小张是要相亲，那么目标范围就是相亲市场上所有的女性，小张既不可能是`NTR`，也不会是`南桐`。
`Pointcut` ：女性, 长长的马尾，灵动的双眼，嘴角总挂着一抹微笑: 我们知道, 所有的方法`(joint point)` 都可以织入 `Advice`, 但是我们并不希望在所有方法上都织入 `Advice`, 而 `Pointcut` 的作用就是提供一组规则来匹配`joinpoint`, 给满足规则的 `joinpoint` 添加 Advice`.` 同理, 对于红娘来说来说，女性, 长长的马尾，灵动的双眼，嘴角总挂着一抹微笑。就是一个修饰谓语, 它限定了女孩的范围, 满足此修饰规则的女孩都有可能会是让小张魂牵梦绕的人，需要挨个安排和小张见面。
`Advice` ：安排与小张相亲，`Advice` 是一个动作, 即一段 Java 代码, 这段 Java 代码是作用于` point cut `所限定的那些 `Joint point `上的. 同理, 对比到我们的例子中, 安排与小张相亲，这个动作就是对作用于那些满足 女性, 长长的马尾，灵动的双眼，嘴角总挂着一抹微笑 这几个条件的女孩们。
`Aspect` : `Aspect` 是 `point cut` 与 `Advice` 的组合, 因此在这里我们就可以类比: “根据小张提供的对面容的描述，需要安排长相类似的女孩与小张见面” 这一整个动作可以被认为是一个 `Aspect`.
最后附上一张图帮助大家理解。
![image.png](https://cdn.nlark.com/yuque/0/2023/png/29016777/1688349876917-02b0a7ad-4238-4855-8965-048768a38b6c.png#averageHue=%23ededed&clientId=ubd92b1af-4e4c-4&from=paste&height=608&id=u8fd2e8ec&originHeight=608&originWidth=720&originalType=binary&ratio=1&rotation=0&showTitle=false&size=250774&status=done&style=none&taskId=u4a5f7625-9bf1-4a7c-ad56-7ad0ffd841a&title=&width=720)

## 三、什么是注解

AOP和注解在软件开发中可以结合使用，以便更方便地定义和应用切面。
使用注解可以更直观地将切面的逻辑与代码相关联，使得 AOP 的配置更加简洁和可读性更高。
通过使用注解，可以在源代码中直接标记切面、连接点和通知。
常见的 AOP 注解包括：

1. @Aspect：用于标记一个类为切面类。
2. @Pointcut：定义一个切点，指定在何处应用切面的通知。
3. @Before：在连接点之前执行的前置通知。
4. @After：在连接点之后执行的后置通知。
5. @AfterReturning：在方法成功返回后执行的返回通知。
6. @AfterThrowing：在方法抛出异常后执行的异常通知。
7. @Around：在连接点周围执行的环绕通知。
   下面来看一段注解示例：

- 定义一个注解

```java
[@Target]     //限定注解可以标注的位置：ANNOTATION_TYPE、CONSTRUCTOR 、FIELD 、LOCAL_VARIABLE 、METHOD 、PACKAGE 、PARAMETER 、TYPE
[@Retention]  //说明了这个注解的存活时间	:SOURCE，CLASS ，RUNTIME
[@Documented] // 将注解中的元素包含到 Javadoc 中去	
[@Inherited]  //子类自动拥有父类的注解	
public @interface [名称] {
     // 元素
     String value() default "xxx";
}
```

- 定义一个切面

```java
@Aspect    //标注增强处理类（切面类）
@Component //交由Spring容器管理
@Order(0)  //设置优先级，值越低优先级越高
public class MyAspectJ {

    //定义增强，pointcut连接点使用@annotation（xxx）进行定义
    @Pointcut(value = "@annotation(xx.xx.xx.xx)")
    public void methodPointcut(){}

    // 匹配方法执行连接点方式
    @Around("methodPointcut()")
    public Object myAspectJ(ProceedingJoinPoint point) throws Throwable {
    
        // 获取方法上的注解
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        AnnotationDemo annotation = method.getAnnotation(AnnotationDemo.class);
        //获取key
        String key = annotation.key();

        object = point.proceed();   // 1. 目标方法的调用
        return null;
    }
}

```

- 方法上添加这个注解，就会对方法进行增强操作。

```
@AnnotationDemo(key = "自定义key值")
public String getAllType(){

   return "";
}
```

## 四、AOP总结

在系统运行的时候，动态的向系统中添加代码的行为，就是面向切面编程 AOP。

- 前置通知：在目标方法执行之前执行。
- 后置通知：在目标方法执行之后执行。
- 异常通知：当目标方法抛出异常的时候执行。
- 返回通知：当目标方法有返回值的时候执行。
- 环绕通知：这是一个集大成者，包含了上面的四种情况。

在实际项目中，更多的是通过自定义注解+AOP解决各种项目问题。

## 五、AOP 原理

AOP 就是基于动态代理，但是动态代理有两种实现方式：

1. 基于 JDK 的动态代理：要求被代理的对象要有接口。
2. 基于 CGLIB 的动态代理：不需要被代理的对象有接口。

在 Spring 中：

1. 如果被代理的对象有接口，那么默认就是用 JDK 动态代理。
2. 如果被代理的对象没有接口，那么就使用 CGLIB 动态代理。

在 Spring Boot 中：

1. 在 Spring Boot 2.0 之前（不含）：
    1. 如果开发者没有配置 `spring.aop.proxy-target-class`属性，默认会使用 JDK 动态代理。
    2. 如果 `spring.aop.proxy-target-class`属性设置为 true，那么对于有接口的对象，也会使用 CGLIB 动态代理。
    3. 如果  `spring.aop.proxy-target-class` 属性为 false，对于有接口的属性，会使用 JDK 动态代理。
2. 在 Spring Boot 2.0 之后（含）：
    1. 默认情况下，就是使用 CGLIB 动态代理，无论被代理的对象是否有接口，都使用 CGLIB 动态代理。

总结：

- Spring 中，有接口用 JDK 动态代理，没接口，用 CGLIB 动态代理。
- Spring Boot 中，2.0 之前，和 Spring 一样；2.0 之后，首选 CGLIB，如果想用 JDK 动态代理，需要开发者手动配置。
