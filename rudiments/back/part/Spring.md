### Spring

###### 1.Spring框架
```
1.概述
    1.开源的轻量级Java开发应用框架，可以简化企业级应用开发。提供了功能强大IOC、AOP以及Web MVC等功能，且生态及其完善，是当前Java开发几乎不能缺少的框架之一。 
2.特点
    1.非侵入式:不会破还原有程序结构，反而可以进一步简化组件结构。
    2.面向切面编程(AOP):在不修改源代码的基础上增强代码功能。
    3.容器:Spring IOC是一个容器，包含并管理组件对象的生命周期。
    4.组件化:使用简单的组件配置组合成复杂的应用。
    5.一站式:在IOC和AOP的基础上可以整合各种企业应用的开源框架和优秀的第三方类库。
    6.控制反转(IOC):翻转资源获取方向，把自己创建资源变成环境将资源准备好，我们享受资源注入。
3.核心容器
    1.IOC(控制反转)
        1.概念
            1.是一种思想。
            2.降低程序耦合度，提高程序扩展力。
            3.将对象的创建权力以及对象与对象之间关系的维护权利交给第三方容器(IOC容器)负责。
            4.通过依赖注入实现思想。
        2.在Spring的实现
            1.概念
                1.Spring的IOC容器即IOC思想落地的产品实现，其中管理的组件被称为Bean，创建Bean之前，需先创建IOC容器。
            2.实现方式
                1.BeanFactory
                    1.IOC容器的基本实现，是内部使用接口，面向Spring本身，不提供给开发人员使用。
                2.ApplicationContext
                    1.BeanFactory的子接口，提供更多高级特性，面向Spring的使用者
                    2.实现类
                        1.ClassPathXmlApplicationContext:读取类路径下的XML配置文件创建IOC容器对象。
                        2.FileSystemXmlApplicationContext:通过文件系统路径读取XML配置文件创建IOC容器对象。
                        3.ConfigurableApplicationContext:ApplicationContext的子接口，让ApplicationContext具有启动、关闭和刷新上下文的能力。
                        4.WebApplicationContext:专为Web应用准备，基于Web环境创建IOC容器对象，并存入ServletContext域中。
        3.bean管理
            1.基于XML管理bean
                1.获取bean
                    1.根据id获取
                        1.在xml文件中创建对象：<bean id="别名" class="全类名"></bean>
                        2.在类中获取对象
                            1.调用ApplicationContext对应的实现类
                            2.调用getBean("id")获取bean
                    2.根据类型获取bean
                        1.在xml文件中创建对象：<bean id="别名" class="全类名"></bean>
                        2.在类中获取对象
                            1.调用ApplicationContext对应的实现类
                            2.调用getBean(类型.class)
                        3.注意事项
                            1.IOC容器指定类型的bean有且只能有一个
                    3.根据id和类型获取bean
                        1.在xml文件中创建对象：<bean id="别名" class="全类名"></bean>
                        2.在类中获取对象
                            1.调用ApplicationContext对应的实现类
                            2.调用getBean("id"，类型.class)
    2.依赖注入(DI)
        1.概念
            1.Spring创建对象的过程中，将对象依赖属性通过配置进行注入
        2.常见实现方式
            1.set注入
            2.构造注入
    
```
###### 2.Spring Boot
###### 3.Spring MVC
###### 4.Spring Security
###### 5.Spring Cloud