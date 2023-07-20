<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Unit Test](#unit-test)
    - [1. 迁移到 Junit 5](#1-%E8%BF%81%E7%A7%BB%E5%88%B0-junit-5)
        - [1.1 使用@ExtendWith替代@RunWith](#11-%E4%BD%BF%E7%94%A8extendwith%E6%9B%BF%E4%BB%A3runwith)
        - [1.2 修改@Test注解的来源](#12-%E4%BF%AE%E6%94%B9test%E6%B3%A8%E8%A7%A3%E7%9A%84%E6%9D%A5%E6%BA%90)
    - [2. 类/方法级单元测试](#2-%E7%B1%BB%E6%96%B9%E6%B3%95%E7%BA%A7%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95)
        - [2.1 开启Mockito注解](#21-%E5%BC%80%E5%90%AFmockito%E6%B3%A8%E8%A7%A3)
        - [2.2 使用@Mock和@InjectMocks](#22-%E4%BD%BF%E7%94%A8mock%E5%92%8Cinjectmocks)
        - [2.3 使用@Spy](#23-%E4%BD%BF%E7%94%A8spy)
    - [3. WebTest 单元测试](#3-webtest-%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95)
        - [3.1 使用@WebMvcTest和MockMvc](#31-%E4%BD%BF%E7%94%A8webmvctest%E5%92%8Cmockmvc)
        - [3.2 使用@MockBean](#32-%E4%BD%BF%E7%94%A8mockbean)
    - [4. 准备输入数据](#4-%E5%87%86%E5%A4%87%E8%BE%93%E5%85%A5%E6%95%B0%E6%8D%AE)
        - [4.1 使用工具类准备输入数据](#41-%E4%BD%BF%E7%94%A8%E5%B7%A5%E5%85%B7%E7%B1%BB%E5%87%86%E5%A4%87%E8%BE%93%E5%85%A5%E6%95%B0%E6%8D%AE)
        - [4.2 与参数化用例结合（实验性功能）](#42-%E4%B8%8E%E5%8F%82%E6%95%B0%E5%8C%96%E7%94%A8%E4%BE%8B%E7%BB%93%E5%90%88%E5%AE%9E%E9%AA%8C%E6%80%A7%E5%8A%9F%E8%83%BD)
    - [5. 静态导入](#5-%E9%9D%99%E6%80%81%E5%AF%BC%E5%85%A5)
        - [5.1 静态导入Assertj](#51-%E9%9D%99%E6%80%81%E5%AF%BC%E5%85%A5assertj)
    - [6. SpringBootTest 集成测试](#6-springboottest-%E9%9B%86%E6%88%90%E6%B5%8B%E8%AF%95)
        - [6.1 注意事项（非常重要！）](#61-%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9%E9%9D%9E%E5%B8%B8%E9%87%8D%E8%A6%81)
        - [6.2 使用方法](#62-%E4%BD%BF%E7%94%A8%E6%96%B9%E6%B3%95)
        - [6.3 数据库](#63-%E6%95%B0%E6%8D%AE%E5%BA%93)
        - [6.4 其他中间件](#64-%E5%85%B6%E4%BB%96%E4%B8%AD%E9%97%B4%E4%BB%B6)
        - [6.5 排除自研组件](#65-%E6%8E%92%E9%99%A4%E8%87%AA%E7%A0%94%E7%BB%84%E4%BB%B6)
        - [6.6 依赖其他服务的Service](#66-%E4%BE%9D%E8%B5%96%E5%85%B6%E4%BB%96%E6%9C%8D%E5%8A%A1%E7%9A%84service)
    - [7. 获取算子配置](#7-%E8%8E%B7%E5%8F%96%E7%AE%97%E5%AD%90%E9%85%8D%E7%BD%AE)
        - [7.1 自动 Mock OperatorInfoService](#71-%E8%87%AA%E5%8A%A8-mock-operatorinfoservice)
        - [7.2 注入算子配置](#72-%E6%B3%A8%E5%85%A5%E7%AE%97%E5%AD%90%E9%85%8D%E7%BD%AE)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# Unit Test

## 1. 迁移到 Junit 5

spring-boot-starter-test当前基于Junit 5运行，与Junit 4相比变化较大。此处列举如何从Junit 4迁移到Junit 5

### 1.1 使用@ExtendWith替代@RunWith

在Junit 4中，我们使用@RunWith注解进行测试上下文准备

```java
// Junit 4
@RunWith(PowerMockRunner.class) // 使用PowerMock进行测试桩准备
// @RunWith(MockitoRunner.class) // 使用Mockito进行测试桩准备
public class HistoryFlowServiceTest {
}
```

在Junit5中，应使用@ExtendWith替换

```java
// Junit 5
@ExtendWith(MockitoExtension.class) // 使用Mockito进行测试桩准备
public class OperatorValidatorServiceTest {
}
```

使用@ExtendWith注解引入MockitoExtension类后，之前的@Mock, @InjectMock, @Spy等注解无需修改，正常使用

### 1.2 修改@Test注解的来源

Junit 5 相关类全部迁移到了新的包下，@Test注解也不例外。使用老的注解类会导致测试用例执行失败，因此需要替换Test注解类

```java
// import org.junit.Test; // Junit 4 ×

import org.junit.jupiter.api.Test; // Junit 5 √
```

## 2. 类/方法级单元测试

### 2.1 开启Mockito注解

推荐使用@ExtendWith开启Mockito注解能力

```java
// Junit 5
@ExtendWith(MockitoExtension.class) // 使用Mockito进行测试桩准备
public class MyTest {
}
```

另外，也可以使用静态方法开启Mockito注解能力

```java
public class MyTest {
    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
}
```

开启注解能力后，才可以使用下面的注解进行进一步的测试用例书写

### 2.2 使用@Mock和@InjectMocks

每个测试用例通常只会测试一个类（目标类）。当需要测试的类调用了其他不想测试的类与方法时，就需要通过@Mock模拟一个类，规定某些方法的返回值

通过@Mock创建的类，如果不进行插桩，调用其方法时会直接返回null。

而在目标类上使用@InjectMocks注解，可以自动将@Mock模拟的类注入进去，避免繁琐的反射操作

例如

```java
import org.mockito.*;

@ExtendWith(MockitoExtension.class)
public class OperatorValidatorServiceTest {
    @InjectMocks
    private OperatorValidatorService operatorValidatorService;

    @Mock
    private SystemConfigService systemConfigService;

    @Test
    public void testcase() {
        // 当systemConfigService调用isWhitelistChanged方法时，直接返回false
        when(systemConfigService.isWhitelistChanged()).thenReturn(false);
    }
}
```

指定被Mock类行为的操作称为插桩(Stubbing)。通过插桩，在测试目标类中的方法时，我们就无需关心目标类的依赖如何实现，如何运行，是否正常了

### 2.3 使用@Spy

@Spy注解与@Mock注解都返回了一个模拟类，区别在于@Spy标注的类，如果不进行插桩，默认调用真实方法。正因为如此，测试用例中调用@Spy类的方法是可以计入覆盖率的。

@Spy有两种打桩方式

```java
import org.mockito.*;

@ExtendWith(MockitoExtension.class)
public class SystemConfigService {
    @Spy
    private SystemConfigService systemConfigService;

    @Test
    public void testcase() {
        // 调用when中方法时，做真实调用，然后返回指定结果
        when(systemConfigService.isWhitelistChanged()).thenReturn(false);

        // 不调用when中方法，直接返回指定结果，类似@Mock行为
        doReturn(false).when(systemConfigService.isWhitelistChanged());
    }
}
```

## 3. WebTest 单元测试

### 3.1 使用@WebMvcTest和MockMvc

在测试中，启动了完整的Spring应用程序上下文，但没有服务器。 @WebMvcTest注解可以指定控制层进行初始化，@Autowire注解使用MockMvc，使Spring模拟HTTP请求并传入指定controller

```java

@WebMvcTest(value = PolicyRuleController.class)
class PolicyRuleControllerMVCTest {
    @Autowired
    private MockMvc mvc;
}
```

### 3.2 使用@MockBean

该注解可以创建并注入指定模拟，用于启动程序上下文，所有controller所需依赖应被注入。例如增加webMVC相关配置

```
    @MockBean
    private WebConfig webConfig;
```

案例： 边界条件检查输入有效性

问题单号

https://dts-szv.clouddragon.huawei.com/DTSPortal/ticket/DTS2021052107NWCZP0K00

```java

@WebMvcTest(value = PolicyRuleController.class)
class PolicyRuleControllerMVCTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WebConfig webConfig;

    @MockBean
    private RequestInterceptor requestInterceptor;

    @MockBean
    private ExceptionVoFactory exceptionVoFactory;

    @MockBean
    private PolicyRuleService policyRuleService;

    @Test
    void putNewRules() throws Exception {
        mvc
            .perform(post(MockPolicyRuleControllerConstants.mockUrlPost)
                // HttpRequester在发送请求如果发送的是json数据
                // 自动帮我们加上了Content-Type字段的声明，否则415报错
                .contentType("application/json")
                .content(
                    //event code长度超过合法长度72的json文件
                    MockPolicyRuleControllerConstants.illegalMockContent))
            .andExpect(status().isBadRequest());
    }

    @Test
    void putNewRules2() throws Exception {
        mvc
            .perform(post(MockPolicyRuleControllerConstants.mockUrlPost)
                .contentType("application/json")
                .content(
                    //event code长度不超过合法长度72的json文件
                    MockPolicyRuleControllerConstants.legalMockContent))
            .andExpect(status().isOk());
    }
}
```

## 4. 准备输入数据

### 4.1 使用工具类准备输入数据

在编写测试用例时，准备输入数据是非常关键的一步。在实际场景中，输入数据有时是一个较长的字符串，有时是一个Pojo类。在Java代码中直接构造这类输入是不容易的，非常容易造成代码更复杂，可读性更差。

因此我们规定，在输入参数比较复杂时，应将输入数据存储到文件中，并使用工具类读取文件内容。

```java
public class DebugPreprocessUtilsTest {
    @Test()
    public void replaceParameters_defaultValueIsNull() {
        // 读取Json文件内容，并将其转换为DebugTaskEntity类
        DebugTaskEntity debugTaskEntity = DataHandleUtils.readJson2Entity(
            "data/DebugPreprocessUtilsTest/DebugTaskEntity_defaultValueNullPara.json", DebugTaskEntity.class);
        // 读取Json文件内容，并将其转换为FlowInfoEntity类
        FlowInfoEntity flowEntity = DataHandleUtils.readJson2Entity("data/DebugPreprocessUtilsTest/FlowInfoEntity.json",
            FlowInfoEntity.class);

        Assertions.assertThatThrownBy(() -> {
            DebugPreprocessUtils.replaceParameters(debugTaskEntity, flowEntity, null);
        })
            .isInstanceOf(BusinessException.class)
            .extracting(throwable -> ((BusinessException) throwable).getCode())
            .isEqualTo("6003");
    }
}
```

查看[DataHandleUtils.java](java/com/huawei/utils/DataHandleUtils.java)获取工具类支持的更多方法

### 4.2 与参数化用例结合（实验性功能）

Junit 5 提供了@ParameterizedTest注解用于实现参数化测试用例，与其配套地，@Source系列注解提供了不同的注入参数的方式

```
    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    void palindromes(String candidate) {
        log.error(candidate);
    }
```

参数化测试用例的本意是通过方法入参和注解构造可重用的测试用例代码，使用不同的输入数据，运行同一段测试用例逻辑。

但除此之外，@Source系列注解使得我们可以用另一种方式获取输入数据

```java
public class DebugServiceTest {
    // 使用@ParameterizedTest取代@Test
    @ParameterizedTest
    // TestJsonFileSource读取文件，并通过Json解析转换为clazz指定的类
    @TestJsonFileSource(value = "data/DebugServiceTest/getParamsInstantiationTest_flowInfoEntity.json",
        clazz = FlowInfoEntity.class)
    public void getParamsInstantiationTest(FlowInfoEntity flowInfoEntity) {
        // 由于ArgumentSource本身的限制，无法很好地支持多个入参场景
        String params = DataHandleUtils.readFile("data/DebugServiceTest/getParamsInstantiationTest_params.json");

        assertThat(debugService.getParamsInstantiation(params, flowInfoEntity))
            .hasSize(4)
            // 有已定义已引用的参数
            .anyMatch(dto -> "flow_param_1".equals(dto.getName()) && "123".equals(dto.getDefaultValue()))
            // 有未定义已引用的参数，值要等于params字符串中定义的值
            .anyMatch(dto -> "undefined_param".equals(dto.getName()) && "abc".equals(dto.getDefaultValue()))
            // 不能有已定义未引用的参数
            .noneMatch(dto -> "flow_param_2".equals(dto.getName()))
            // MO_Offset已定义未引用，特殊处理
            .anyMatch(dto -> "MO_Offset".equals(dto.getName()));
    }
}
```

使用注解获取输入数据对于单个入参的测试用例很有用，但无法很好地支持读取多个文件作为入参。

已实现的注解有：

- @TestFileSource：读取文件，返回文件内容字符串

- @TestJsonFileSource：读取Json格式文件，使用Jackson将文件内容映射为java类

## 5. 静态导入

静态导入可以使测试代码更整洁，更易读，更易编写

由于我们的工程一般开启了自动简化导入，所以以下形式的导入会被自动修改为按需导入

```java
import static org.assertj.core.api.Assertions.*;
```

### 5.1 静态导入Assertj

导入任意一条后，后面再使用相同库中的方法时，idea一般可以自动联想出来

```java
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;
```

## 6. SpringBootTest 集成测试

模拟服务启动后的真实环境对于测试十分有用，在模拟环境下我们可以像编写生产代码一样编写测试代码，从而使得业务逻辑的测试更加简便

由于服务启动后会对接各种中间件，相当于将我们的服务与其他模块集成起来，所以这类测试被称为**集成测试**

但此类模拟真实环境的集成测试要求在任何环境下都可以直接运行。无论是在编码者的本机，还是在流水线的执行机，都要保证测试成功。

因此我们需要处理某些测试环境不需要，或测试环境无法保证创建成功的Bean。处理方法有使用嵌入式中间件代替，测试替身，直接排除等。

- 嵌入式中间件代替：h2 数据库替代 GaussDB

- 测试替身：Redis, Cse, Minio 等中间件，MetaOneService等依赖其他模块的服务类

- 直接排除：SslConfiguration，HttpConfig 等自研组件；SpringBoot AutoConfiguration引入的所有配置类

### 6.1 注意事项（非常重要！）

启动集成测试时，测试框架会启动一个完整的SpringBoot ApplicationContext，创建所有的Bean，初始化数据库。
这个过程会消耗数十秒的时间，因此对于较大规模的集成测试来说，共用同一个Context（上下文）就显得十分重要。因此集成测试的编写要遵守以下规范，以避免重复的Context创建。

1. **禁止使用MockBean**。MockBean会导致Context与标准的集成测试Context不同，一旦发现不同，测试框架就会试图重新创建一个。

2. **提供必需的Bean的实现，屏蔽不需要的Bean**。这条规则暗含了一个原则，即所有的集成测试类中，对于某一个Bean的行为预期应该是统一的。Bean的实现（或打桩）应该在定义时决定好，而不是在测试类中单独编写。

### 6.2 使用方法

启动集成测试十分简单，只需要在测试类上加一个注解@IntegralTestAutoConfiguration

```java
// 添加该注解，启动集成测试
@IntegralTestAutoConfiguration
public class FlowInfoServiceIntegralTest {
    // 需要测试的类，使用@Autowired直接引入
    @Autowired
    FlowInfoService flowInfoService;

    @ParameterizedTest()
    @TestJsonFileSource(value = "data/DebugServiceTest/getParamsInstantiationTest_flowInfoEntity.json",
        clazz = FlowInfoEntity.class)
    public void basicDaoTest(FlowInfoEntity flowInfoEntity) {
        // 轻松测试Dao层逻辑
        flowInfoService.save(flowInfoEntity);

        assertThat(flowInfoService.getFlowInfo("08872cbca49eafaa6e1ecbd538ac8b86"))
            .isNotNull();
    }
}
```

[@IntegralTestAutoConfiguration](java/com/huawei/utils/annotation/meta/IntegralTestAutoConfiguration.java)
注解集成了启动SpringBootTest，指定Profile，初始化AutoConfiguration，排除Bean等功能。后续涉及到SpringBoot测试框架及上下文的改动时，都应修改此注解及其相关类。

### 6.3 数据库

数据源信息配置于 [application-test.yml](resources/application-test.yml)

```yaml
spring:
  flyway:
    enabled: true
    locations: classpath:db/{vendor}/migration
  datasource:
    # 测试使用h2数据库
    driver-class-name: org.h2.Driver
    # Oracle兼容模式
    url: jdbc:h2:mem:testdb;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
```

数据库仍然使用flyway脚本初始化，最终使用的脚本可以在运行测试用例后生成的
[Test Classpath](../../target/test-classes/db/h2/migration/V1.0.0.1__init_table.sql)下找到

使用兼容Oracle模式启动h2可以解决部分Sql方言不适配的问题，因此大多数生产环境脚本可以直接使用。 当遇到确实无法兼容的场景时，应新增文件覆盖原有脚本。
方法是在resources/db/h2/migration下新增一个文件名与欲覆盖的脚本文件完全相同的文件，然后在其中写入针对h2的脚本

### 6.4 其他中间件

将Bean替换为测试替身，可以达成Mock中间件的效果

[@IntegralTestAutoConfiguration](java/com/huawei/utils/annotation/meta/IntegralTestAutoConfiguration.java)
引入了Minio，Cse和Redis的测试配置类，今后如果有新的Mock中间件需求，可以在此添加。

```java
// ...
@ImportAutoConfiguration(
    classes = {
        // ...
        // 中间件Mock
        MinioTestAutoConfig.class,
        RestTemplateTestConfiguration.class,
        RedisTemplateTestConfiguration.class,
    }
)
// ...
public @interface IntegralTestAutoConfiguration {
}
```

### 6.5 排除自研组件

有一些配置类/Bean归属于com.huawei.ryoma包，属于自研组件，或直接就是我们服务自己实现的类。
这种类型的类由SpringBoot的@ComponentScan注解扫描，即使不通过@ImportAutoConfiguration手动引入，也会自动创建。

排除这些类需要使用到@TypeExcludeFilter注解（已集成到@IntegralTestAutoConfiguration）。该注解引入了IntegralTestTypeExcludeFilter作为类型过滤器。

排除时，只需要在Filter类中添加类名或包名即可

```java
public class IntegralTestTypeExcludeFilter extends TypeExcludeFilter {
    // 指定需要排除的包
    private final List<String> excludePackages = Arrays.asList(
        "com.huawei.us"
    );

    // 指定需要排除的类
    private final List<String> excludes = Arrays.asList(
        "com.huawei.ryoma.datafactory.core.common.config.HttpConfig"
    );

    // Implementation...
}
```

### 6.6 依赖其他服务的Service

依赖其他服务的类在测试/开发环境下无法正常运行。此时我们同样选择测试替身进行替代

以MetaOneService为例，首先在IntegralTestTypeExcludeFilter中排除

```java
public class IntegralTestTypeExcludeFilter extends TypeExcludeFilter {
    // 指定需要排除的类
    private final List<String> excludes = Arrays.asList(
        // ...
        // Following beans are provided by TestServiceConfiguration
        "com.huawei.ryoma.datafactory.core.datastudio.service.MetaOneService"
    );

    // Implementation...
}
```

然后在TestServiceConfiguration中定义Bean

```java

@TestConfiguration
public class TestServiceConfiguration {
    /**
     * Mocked MetaOneService
     *
     * @return Mocked MetaOneService
     */
    @Bean
    public MetaOneService metaOneService() {
        MetaOneService metaOneService = mock(MetaOneService.class);

        return metaOneService;
    }
}
```

最后在IntegralTestAutoConfiguration中添加Configuration配置

```java
// ...
@ImportAutoConfiguration(
    classes = {
        // ...
        // Service Mock
        TestServiceConfiguration.class
    }
)
// ...
public @interface IntegralTestAutoConfiguration {
}
```

现在，集成测试场景下，@Autowire获取的MetaOneService变为一个Mock实例，可以对其进行测试打桩。示例如下

```java

@IntegralTestAutoConfiguration
class PhysicalModelBloodRelationShipIntegralTest {
    @Autowired
    MetaOneService metaOneService;

    @Test
    public void getBloodRelationShipForGraph_withRing() {
        MetaOneRestResponse.Result<List<IntegralPhyModel>> ret = DataHandleUtils.readJson2Entity(
            // ...
        );

        when(metaOneService.getTableById(any(), any(), any(), any(), any(), any()))
            .thenReturn(ret);
        // ...
    }
}
```

## 7. 获取算子配置

算子使用JSON配置，单独存放管理，因此未使用flyway脚本管理入库，测试时难以获取。因此实现了OperatorInfoExtension用于较方便地获取算子配置。

### 7.1 自动 Mock OperatorInfoService

在未启动spring profile的情况下，OperatorInfoService默认从数据库读取算子配置，且一定会失败（因为没有数据库）。

而Mock该类需要一些繁杂的步骤，因此我们将其封装到Extension中。使用方法参见[OperatorValidatorService](java/com/huawei/ryoma/datafactory/core/datastudio/service/OperatorValidatorServiceTest.java)

```java
// 1. 注册OperatorInfoExtension
@ExtendWith({MockitoExtension.class, OperatorInfoExtension.class})
public class OperatorValidatorServiceTest {
    // ...
    // 2. Spy或Mock operatorInfoService
    @Spy
    private OperatorInfoService operatorInfoService;

    // 3. 直接使用测试替身，其中配置已自动更改
    @InjectMocks
    private OperatorValidatorService operatorValidatorService;
    // ...    
}
```

OperatorInfoExtension会自动处理OperatorInfoService实例，使其读取本地算子配置文件。

### 7.2 注入算子配置

为了灵活地获取算子配置（用于Mock，测试数据构造等场景），我们使用JUnit 5的参数注入能力，使用注解向测试方法灵活地注入算子配置。

使用方法参见[OperatorInfoEntityTest](java/com/huawei/ryoma/datafactory/core/datastudio/entity/OperatorInfoEntityTest.java)

```java
// 1. 注册OperatorInfoExtension
@ExtendWith(OperatorInfoExtension.class)
public class OperatorInfoEntityTest {
    //...

    @Test
    // 2. 在方法入参中标注@OperatorResource，参数类型支持String或OperatorInfoEntity
    public void operatorSourceAsEntity(@OperatorSource(name = "Load JDBC", flowType = DataFlowTypeEnum.DATA_BATCH)
        OperatorInfoEntity operatorInfoEntity) {

        // 3. 在测试用例中使用注入的算子配置
        assertThat(operatorInfoEntity)
            .isNotNull()
            .extracting(OperatorInfoEntity::getNameEn, OperatorInfoEntity::getDataFlowTypeId,
                OperatorInfoEntity::getOperatorVersion)
            .containsExactly("Load JDBC", "batch", "0.0.1");
    }

    @Test
    // 4. 注入String类型样例
    public void pluginOperatorSourceAsString(
        @OperatorSource(name = "Load Oracle", flowType = DataFlowTypeEnum.DATA_BATCH, isPlugin = true)
            String operatorInfo) {
        assertThat(operatorInfo)
            .isNotEmpty()
            .contains("Load Oracle");
    }
}
```