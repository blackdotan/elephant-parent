

https://github.com/blackdotan/elephant-parent



# elephant kit 简介

钚氪基础组件包

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-parent</artifactId>
        <version>2.4.1</version>
    <dependency>

🔥🔥🔥

CHANGELOG

发布 2.4.6 稳定版本
- FIX elephant-utils 支持IFunction、IPredicate
- ImageUtilities 支持图片、Base64互转、支持数据压缩
- Word2ImageUtilities 支持字体转头像图片
- 新增 NormalResponseWrapper、PaginationResponseWrapper 用于封装 接口返回 对象



# 七牛存储

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-cloud-storage</artifactId>
        <version>2.4.1</version>
    <dependency>


```
    private QiniuStorage mStorage;

    /**
     * 七牛存储配置
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        QiniuStorageConfig config = QiniuStorageConfig.custom()
                .accessKey("TIfWJ8n_YCiOfnA8O4eOx79VdgfduHQO0svjhO2K")
                .secretKey("960i9OllMoLEkUfkPPV0UpnE4c9Qg2DPIRFv_tN1")
                .domain("https://oustorage.ipukr.cn/")
                .bucket("outsourcing")
                .build();
        mStorage = new QiniuStorage(config);
    }

    /**
     * 测试上传文件到七牛
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        File file = new File(this.getClass().getResource("/").getPath().concat("test.jpg"));
        mStorage.upload(IOUtils.toByteArray(new FileInputStream(file)),"Hekki.jpg");
    }

    /**
     * 测试获取域名
     * @throws Exception
     */
    @Test
    public void testdomain() throws Exception {
        String domain = mStorage.domain();
        System.out.println(domain);
    }

    /**
     * 测试拼接文件地址
     * @throws Exception
     */
    @Test
    public void testaddress() throws Exception {
        String domain = mStorage.address("Hekki.jpg");
        System.out.println(domain);
    }

```


# Httpclient使用

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-httpclient</artifactId>
        <version>2.4.1</version>
    </dependency>

Httpclient组件主要是简化httpclient的代码，


```

    private HttpClientPool pool;

    /**
     * 初始化配置
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        HttpClientPoolConfig config = new HttpClientPoolConfig.Builder()
                .schema("https")
                .build();
        pool = new HttpClientPool(config);
    }

    /**
     * 测试HTTP请求
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        URI URI = new URIBuilder()
                .setScheme("https")
                .setHost("www.baidu.com")
                .setPort(443)
                .setCharset(Consts.UTF_8)
                .build();
        HttpUriRequest request = RequestBuilder.get(URI)
                .build();

        HttpResponse response = pool.getConnection().execute(request);
        String content = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(content);
    }

```

# KV缓存

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-kv</artifactId>
        <version>2.4.1</version>
    </dependency>


```

    private KV mKV;

    /**
     * 初始化Redis配置
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        IpukrRedisConfig iconfig = IpukrRedisConfig.custom()
                .address("10.244.69.170")
                .port(6379)
                .auth("admin123@$^!")
                .db(11)
                .build();
        mKV = new RedisKV(iconfig);
    }

    /**
     * 测试新增记录
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        for (int i = 0; i < 100; i++) {
            Client client = new Client();
            client.setId(i);
            mKV.add(StringUtils.uuid(), SerializableUtils.serialize(client));
        }
    }

    /**
     * 测试获取数据
     * @throws IOException
     */
    @Test
    public void get() throws IOException {
        byte[] bytes = mKV.get("dcaad4a9-6955-46e7-ba51-23fad2cdd841");
        Client client = (Client) SerializableUtils.deserialize(bytes);
        System.out.println(JsonUtils.parserObj2String(client));
    }

    /**
     * 测试获取匹配数据
     * @throws IOException
     */
    @Test
    public void keys() throws IOException {
        List<byte[]> values = mKV.match("*");
    }

```


# 邮件功能

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-mail</artifactId>
        <version>2.4.1</version>
    </dependency>


```
    private MailSender sender;

    @Before
    public void setUp() throws Exception {
        SSLMailSenderConfig config = SSLMailSenderConfig.custom()
                .host("smtp.exmail.qq.com")
                .port(25)
                .protocol("smtp")
                .auth(true)
                .username("username")
                .password("")
                .build();
        sender = new SSLMailSender(config);
    }

    @Test
    public void tst() throws Exception {
        sender.send("msg@ipukr.cn","wmw@ipukr.cn", "主题:测试邮件发送","测试邮件发送");
    }
```


# 推送功能

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-push</artifactId>
        <version>2.4.1</version>
    </dependency>


```
    private IPush mIPush;

    /**
     * 个推配置
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        GetuiIMPushConfig config = GetuiIMPushConfig.custom()
                .appid("WwZVpeNxbG7yZTdRV9Mhf6")
                .appkey("uotLQQq7Mm9gYHkQpzx4l7")
                .master("4tJ62jk6ug88C8kGkFkJP4")
                .offline(true)
                .build();
        mIPush = new GetuiIMPush(config);
    }

    /**
     * 推送到单个设备
     */
    @Test
    public void testSingle() {
        mIPush.push("7db50e64153e078a2e8bc398e43bd3c1", "应用推送","测试推送1003");
    }

    /**
     * 推送到多个设备
     */
    @Test
    public void testList() {
        mIPush.push(Arrays.asList(
                "8f2596f92137af2d8d4904b72e4c77bb",
                "7db50e64153e078a2e8bc398e43bd3c1",
                "637559faca63ef0f4241b6c3a80208f4"),
                "应用推送",
                "测试推送1004");
    }
```

# 短信功能

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-sms</artifactId>
        <version>2.4.1</version>
    </dependency>



```
    private Sms mSms;

    @Before
    public void setUp() throws Exception {
        AliyunSmsConfig config = new AliyunSmsConfig.Builder()
                .accessKeyId("LTAIe2O2YMLHgt2y")
                .accessKeySecret("Gh0xyxDxwmGzPdXhNGqU6DxeMAl7BJ")
                .domain("dysmsapi.aliyuncs.com")
                .product("Dysmsapi")
                .templateId("SMS_123738450")
                .sign("云扬健身")
                .build();
        mSms = new AliyunSms(config);
    }

    @Test
    public void send1() throws Exception {
        Assert.assertEquals(SmsResponse.Status.Success, mSms.send("15805903814", "6666").getStatus());
    }

    @Test
    public void send2() throws Exception {
        boolean bool = mSms.send("15805903814", "8888").getStatus() == SmsResponse.Status.Success;
        Thread.sleep(60000);
        bool = bool && mSms.send("15805903814", "7777").getStatus() == SmsResponse.Status.Success;
        Assert.assertEquals(true, bool);
    }

```



# 微信工具

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-weixin</artifactId>
        <version>2.4.1</version>
    </dependency>


```
    private WeixinMPHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new WeixinMPHelper();
    }

    /**
     * 测试小程序发送模板消息功能
     * 参考链接：https://developers.weixin.qq.com/miniprogram/dev/api/sendTemplateMessage.html
     * @throws Exception
     */
    @Test
    public void sendTemplateMessage() throws Exception {
        String appid = "wx12c736b1c7124d61";
        String secret = "bd88550081ec46ae3fb6217c4dc304a1";
        WxAccessTokenResponse access = helper.getAccessToken(appid, secret);

        List<String> items = Arrays.asList("1064420215661527040", "itself find of to that", "0.01", DateUtils.dateToString(DateUtils.now(), "yyyy-MM-dd: HH:mm:ss"), "钚氪健身房小程序云");

        Map data = new HashMap<>();
        for (int i=1; i <= items.size(); i++) {
            Map value = new HashMap();
            value.put("value", items.get(i - 1));
            data.put("keyword" + i, value);
        }

        WxTemplateMessageRequest template = WxTemplateMessageRequest.custom()
                .templateId("NJylYZ6egtYk5Y9j6ezkPmVeg6ohKVVD3HnoSINj3d8")
                .formId("wx1915285033355447fb3af83a1597869495")
                .touser("obwP-45MmD4OjreWVXYCP1Tj0h7w")
                .page("pages/myorder/myorder")
                .data(data)
                .build();
        helper.sendTemplateMessage(access.getAccess(), template);
    }
```


# 在线支付

## 阿里支付

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-payment-alipay</artifactId>
        <version>2.4.1</version>
    </dependency>


## 微信支付

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-payment-weixin</artifactId>
        <version>2.4.1</version>
    </dependency>


```

    private WeixinPay pay;

    @Before
    public void setUp() throws Exception {
        WeixinPayConfig config = WeixinPayConfig.custom()
                .appid("wx12c736b1c7124d61")
                .mchid("1518547931")
                .signature("ABCABCABCABCABCABCABCABCABCABCDD")
                .certification("classpath:weixin/15418283701854570.p12")
                .magnification(1.0F)
                .build();
        pay = new WeixinPay(config);
    }

    /**
     * 创建预支付订单
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        String no = String.valueOf(IdGen.get().nextId());
        // 统一支付
        WeixinCreateOrder wco = new WeixinCreateOrder.Builder()
                .no(no)
                .amount(0.1F)
                .body("购买")
                .tradeType("JSAPI")
                .openid("obwP-45MmD4OjreWVXYCP1Tj0h7w")
                .build();
        WeixinCreateOrder pOrder = pay.create(wco);
        System.out.println(pOrder.toString());

        // 小程序返回参数 & App返回参数 处理
        Date date = DateUtils.now();
        Map map = pOrder.getResmap();
        Map result = new HashMap();
        result.put("appid", map.get("appid"));
        result.put("partnerid", map.get("mch_id"));
        result.put("prepayid", map.get("prepay_id"));
        result.put("noncestr", map.get("nonce_str"));
        result.put("timestamp", String.valueOf(date.getTime()));
        result.put("package", "Sign=WXPay");
        result.put("sign", pay.tosignature(result));
    }

    /**
     * 测试退款功能
     * @throws Exception
     */
    @Test
    public void refund() throws Exception {
        WeixinRefundOrder order = new WeixinRefundOrder.Builder()
                .amount(2.0F)
                .no("975005819370209280")
                .build();
        RefundOrder ret = pay.refund(order);
        System.out.println(ret.toString());
    }

    /**
     * 验证微信支付回调请求
     * @throws Exception
     */
    @Test
    public void verify() throws Exception {
        // 微信返回参数示例
        String text =
                "<xml><appid><![CDATA[wx7c044c92ffbee262]]></appid>\n" +
                        "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                        "<cash_fee><![CDATA[2]]></cash_fee>\n" +
                        "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                        "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                        "<mch_id><![CDATA[1497977952]]></mch_id>\n" +
                        "<nonce_str><![CDATA[8e40e59f78c8432c8f90165379996ac9]]></nonce_str>\n" +
                        "<openid><![CDATA[ozkVA0dJ4f5tw03s_uYROBKC6-eE]]></openid>\n" +
                        "<out_trade_no><![CDATA[968417425890476032]]></out_trade_no>\n" +
                        "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                        "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                        "<sign><![CDATA[C10E5CF56986DF97D0D1B05803DECDDE]]></sign>\n" +
                        "<time_end><![CDATA[20180227174024]]></time_end>\n" +
                        "<total_fee>2</total_fee>\n" +
                        "<trade_type><![CDATA[APP]]></trade_type>\n" +
                        "<transaction_id><![CDATA[4200000056201802279403991918]]></transaction_id>\n" +
                        "</xml>";
        Map params = WXPayUtil.xmlToMap(text);
        Boolean bool = pay.verify(params);
        Assert.assertEquals(true, bool);
    }
```


# 代码生成

    <dependency>
        <groupId>com.blackdotan.elephant</groupId>
        <artifactId>elephant-mybatis-plugin</artifactId>
        <version>2.4.1</version>
    </dependency>


已迁移，参考scrat-mybatis-plugin项目

基于Mybatis Generator项目实现的代码生成扩展插件

1. 自动生成基础CRUD SQL语句
2. 自动生成Mybatis关联配置等

参考链接：http://www.mybatis.org/generator/

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>

    <!-- 连接器jar -->
    <context id="MYSQLTestdb" defaultModelType="flat" targetRuntime="MyBatis3">

        <property name="xmlFormatter" value="org.mybatis.generator.api.dom.DefaultXmlFormatter"/>
        <property name="beginningDelimiter" value="["/>
        <property name="endingDelimiter" value="]"/>


        <!--
            自定义插件
            参考链接：http://www.mybatis.org/generator/
        -->
        <plugin type="com.blackdotan.elephant.mybatis.plugins.SerializablePlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.ToStringPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.ConditionPlugin"></plugin>

        <plugin type="com.blackdotan.elephant.mybatis.plugins.DeleteByIdsPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.FindAllPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.FindByIdsPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.QueryPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.ExamplePlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.SearchPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.ExistPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.CountPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.IgsertPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.UpsertPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.UniquePlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.UniletePlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.ModelAppendPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.AbstractMapperPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.QConditionPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.SConditionPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.VirtualPrimaryKeyPlugin"></plugin>
        <plugin type="com.blackdotan.elephant.mybatis.plugins.GeneratedEnumerationHandlerJavaFilePlugin">
            <property name="OverrideEnumeration" value="false"/>
        </plugin>

        <plugin type="com.google.code.mybatis.generator.plugins.ModelBuilderPlugin">
            <property name="builderClassName" value="Builder"/>
        </plugin>


        <!--
            A1:连接器配置

            :修改连接配置
        -->
        <jdbcConnection driverClass="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                        connectionURL="jdbc:sqlserver://10.99.255.254:1433;DatabaseName=LineView4"
                        userId="sa"
                        password="Kc0MNAXy">
        </jdbcConnection>

        <!--
            Mybatis 代码生成规则
        -->
        <javaModelGenerator targetPackage="com.blackdotan.ants.entity" targetProject="MAVEN">
            <property name="enableSubPackages" value="false"/>
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="com.blackdotan.ants.dao.mapper" targetProject="MAVEN">
            <property name="enableSubPackages" value="false"/>
        </sqlMapGenerator>

        <javaClientGenerator type="XMLMAPPER" targetPackage="com.blackdotan.ants.dao" targetProject="MAVEN">
            <property name="enableSubPackages" value="false"/>
        </javaClientGenerator>

        <!--
            实体对象配置
        -->
        <table tableName="Lines" domainObjectName="Line"
               delimitIdentifiers="true" delimitAllColumns="true"
               enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false">
            <generatedKey column="LineId" sqlStatement="SqlServer" identity="true"/>
        </table>

        <table tableName="LinePDTModes" domainObjectName="LinePDTMode"
               delimitIdentifiers="true" delimitAllColumns="true"
               enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false">
            <property name="Associations" value="Line:line"></property>
            <!--
                Associations：对象关联：语法 [对象:变量名,对象:变量名,...]；SQLMap自动生成：association 配置
                Collections：集合关联，语法 [对象:变量名,对象:变量名,...]；SQLMap自动生成：collection 配置

                Model：对象属性，语法 [对象:变量名,对象:变量名,...]；Entity自动生成：对象 属性
                Lists：集合属性，语法 [对象:变量名,对象:变量名,...]；Entity自动生成：List<对象> 属性

                VirtualKeyColumns：虚拟主键，语法 [字段名,字段名,...]；自定义表主键字段
                Enumerations：枚举属性，语法 [字段名,字段名,...]；Entity自动生成：枚举 属性 & 自动生成枚举类文件 & 自动生成枚举解析类文件
            -->
            <generatedKey column="LineId" sqlStatement="SqlServer" identity="true"/>
        </table>

        <table tableName="LineNames" domainObjectName="LineName"
               delimitIdentifiers="true" delimitAllColumns="true"
               enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false">
            <property name="Associations" value="Line:line"></property>
            <property name="VirtualKeyColumns" value="LineId,Language"></property>
            <generatedKey column="LineId" sqlStatement="SqlServer" identity="true"/>
        </table>

    </context>

</generatorConfiguration>
```


