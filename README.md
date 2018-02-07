
# elephant kit

## 邮件服务
配置
```
architecture.ipukr.class=com.ipukr.elephant.mail.third.SSLMailSender
architecture.ipukr.mail.smtp.host=smtp.exmail.qq.com
architecture.ipukr.mail.smtp.port=25
architecture.ipukr.mail.transport.protocol=smtp
architecture.ipukr.mail.smtp.auth=true
architecture.ipukr.mail.username=msg@ipukr.cn
architecture.ipukr.mail.password=%H9N8x^7Oi%Nxm6w
architecture.ipukr.mail.from=msg@ipukr.cn
```
代码
```Java
    sender = Factory.getInstance().build("config.properties", "ipukr");
    sender.sender("wmw@ipukr.cn","主题:测试邮件发送","测试邮件发送");
```
接口
com.pukr.cattle.mail.MailSender
## KV服务

配置
```
architecture.redis.class=com.ipukr.elephant.kv.backend.RedisKV
architecture.redis.address=120.76.120.81
architecture.redis.port=6379
architecture.redis.auth=admin123@$^!
architecture.redis.db=0
architecture.redis.max_total=300
architecture.redis.max_idle=100
architecture.redis.max_wait=100
architecture.redis.max_active=300
architecture.redis.test_on_borrow=true
architecture.redis.timeout=10000
```

代码
```
    KV<Client> mKV = Factory.getInstance().build("config.properties", "redis", Client.class);
    mKV.add(key, new Client(), 100L);
```

接口
com.ipukr.elephant.kv.KV


## 推送服务
配置
```
architecture.getui.class=com.ipukr.elephant.push.backend.GetuiIMPush
architecture.getui.notification.offline=false;
architecture.getui.notification.offline.expire.time=0
architecture.getui.appid=bVSc2tqfVf5XCT64LmmdH3
architecture.getui.appkey=mvBDiZQ8ku9YP9gN9CcgT
architecture.getui.master=PJy8j0DItQ6MMx91NgpzH5
```
代码
```Java
    IPush mIPush = Factory.getInstance("config.properties", "getui").build();
    mIPush.push("b65c186b8e67e9c5594cef04d4fcc385", "123");
```
接口
com.ipukr.elephant.push.IPush

## 云存储服务
配置
```
application.storage.qiniu.class=com.ipukr.elephant.cloud.storage.backend.QiniuStorage
application.storage.qiniu.access_key=TIfWJ8n_YCiOfnA8O4eOx79VdgfduHQO0svjhO2K
application.storage.qiniu.secret_key=960i9OllMoLEkUfkPPV0UpnE4c9Qg2DPIRFv_tN1
application.storage.qiniu.bucket=dynamics
application.storage.qiniu.url.template={}
application.storage.qiniu.url.domain=https://images.ipukr.cn/
```

代码
```Java
    Storage mStorage = Factory.getInstance("config.properties", "storage").build();
    File file = new File(this.getClass().getResource("/").getPath().concat("test.jpg"));
    mStorage.upload(IOUtils.toByteArray(new FileInputStream(file)),"Hekki.jpg");
```
接口
com.ipukr.elephant.cloud.storage.Storage

## 支付服务
配置
```
# 支付宝
architecture.alipay.class=com.ipukr.elephant.payment.third.AliPay
architecture.alipay.url=https://openapi.alipay.com/gateway.do
architecture.alipay.notify.url=https://tst.ipukr.cn/kong-appint/account/bill/notify
architecture.alipay.app.id=
architecture.alipay.app.private.key=
architecture.alipay.public.key=
architecture.alipay.format=json
architecture.alipay.charset=UTF-8
architecture.alipay.sign_type=RSA2

# 微信
architecture.weixin.class=com.ipukr.elephant.payment.third.WeixinPay
architecture.weixin.http.schema=https
architecture.weixin.http.hostname=api.mch.weixin.qq.com
architecture.weixin.http.port=443
architecture.weixin.http.protocol=TLSv1,TLSv1.1,TLSv1.2
architecture.weixin.http.timeout=5000
architecture.weixin.http.connections=20
architecture.weixin.appid=wx7c044c92ffbee262
architecture.weixin.mchid=1497977952
architecture.weixin.notify.url=https://tst.ipukr.cn/kong-appint/account/bill/notify
architecture.weixin.sign.key=hHJhYD6iDBvRHAzHLZ7kC9GsrWpuUmB9
```
代码
```Java
    Pay pay = Factory.getInstance().build("config.properties", "alipay");

    String token = StringUtils.uuid();
    String no = StringUtils.uuid();
    List<Good> goods = new ArrayList<Good>();
    Good good = new Good.Builder()
            .no("20170912312312123")
            .name("Member Fee")
            .price(0.01F)
            .quantity(1)
            .build();
    PayOrder order = new SimplePayOrder.Builder()
            .amount(0.01F)
            .no(no)
            .subject("支付测试")
            .secret(token)
            .build();
    PayOrder pOrder = pay.create(order);
```

