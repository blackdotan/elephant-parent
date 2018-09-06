
# elephant kit

## 配置清单

```
ipukr.elephant:
    # 七牛
    storage.qiniu:
        access_key: TIfWJ8n_YCiOfnA8O4eOx79VdgfduHQO0svjhO2K
        secret_key: 960i9OllMoLEkUfkPPV0UpnE4c9Qg2DPIRFv_tN1
        bucket: outsourcing
        domain: https://oustorage.ipukr.cn/
    # 个推
    push.getui:
        host: http://sdk.open.api.igexin.com/apiex.htm
        offline: true
        notification.offline.expire.time: 0
        appid: WwZVpeNxbG7yZTdRV9Mhf6
        appkey: uotLQQq7Mm9gYHkQpzx4l7
        master: 4tJ62jk6ug88C8kGkFkJP4
    # 阿里短信
    sms.aliyun:
        product: Dysmsapi
        domain: dysmsapi.aliyuncs.com
        accessKeyId: LTAIe2O2YMLHgt2y
        accessKeySecret: Gh0xyxDxwmGzPdXhNGqU6DxeMAl7BJ
        templateId: SMS_123738450
        sign: 云扬健身
    # 邮件
    mail.ssl:
        host: smtp.exmail.qq.com
        port: 25
        protocol: smtp
        auth: true
        username: msg@ipukr.cn
        password: "%H9N8x^7Oi%Nxm6w"
        from: msg@ipukr.cn
    # KV Redis
    kv.redis:
        address: tst.ipukr.cn
        port: 6379
        auth: admin123@$^!
        db: 7
    # httpclient
    httpclient:
        schema: https
        hostname: www.baidu.com
        port: 443
        protocol: TLSv1,TLSv1.1,TLSv1.2
        timeout: 60000
        connections: 200
        route.max: 50
        dns: dictation.nuancemobility.net:205.197.192.118
```

## 七牛存储


```
    @Autowired
    private QiniuStorage mStorage;

    # 上传文件
    File file = new File(this.getClass().getResource("/").getPath().concat("test.jpg"));
    mStorage.upload(IOUtils.toByteArray(new FileInputStream(file)),"Hekki.jpg");
    # 获取图片域名
    String domain = mStorage.domain();
    # 获取图片URL地址
    String address = mStorage.address("Hekki.jpg");

```


## Httpclient使用

Httpclient组件主要是简化httpclient的代码，

```
    @Autowired
    private HttpClientPool pool;

    # 发送HTTP请求
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

```

## KV缓存

```
    @Autowired
    private KV mKV;

    # 新增
    for (int i = 0; i < 100; i++) {
        Client client = new Client();
        client.setId(i);
        mKV.add(StringUtils.uuid(), SerializableUtils.serialize(client));
    }

    # 获取
    byte[] bytes = mKV.get("dcaad4a9-6955-46e7-ba51-23fad2cdd841");
    Client client = (Client) SerializableUtils.deserialize(bytes);

    # 匹配获取
    List<byte[]> values = mKV.match("*");

```


## 邮件功能

```
    @Autowired
    private MailSender sender;

    # 发送该邮件
    sender.send("msg@ipukr.cn","wmw@ipukr.cn", "主题:测试邮件发送","测试邮件发送");
```


## 推送功能

```
@Autowired
    private IPush mIPush;

    # 单客户端推送
    mIPush.push("7db50e64153e078a2e8bc398e43bd3c1", "应用推送","测试推送1003");

    # 多客户端推送
    mIPush.push(Arrays.asList(
            "8f2596f92137af2d8d4904b72e4c77bb",
            "7db50e64153e078a2e8bc398e43bd3c1",
            "637559faca63ef0f4241b6c3a80208f4"),
            "应用推送",
            "测试推送1004");
```

## 短信功能

```
    @Autowired
    private AliyunSms sms;

    # 发送短信
    SmsResponse response = sms.send("15805903814", "6666");
    System.out.println(response.getMsg());
    Assert.assertEquals(SmsResponse.Status.Success, response.getStatus());

```
