## cattle-mail

邮件功能模块

使用方法查看测试类：com.pukr.cattle.mail.MailSender*

Maven依赖Location（版本持续更新）

```
<dependency>
  <groupId>com.pukr.cattle</groupId>
  <artifactId>cattle-mail</artifactId>
  <version>1.0.0-RELEASE</version>
</dependency>
```


发送邮件方式一（代码定义发送用户）

```
        MailSender sender = MailSenderHelper.newInstance("smtp.exmail.qq.com", 25, "msg@ipukr.cn", "%H9N8x^7Oi%Nxm6w");
        sender.send("msg@ipukr.cn","327373630@qq.com","ryan email tst2","mail function tster");
```

发送邮件方式二（配置定义邮件发送用户）

```
        MailSender sender = MailSenderFactory.newInstance().buildMailSender();
        List<String> tos = Lists.newArrayList("wmw1612@gmal.com","327373630@qq.com");
        List<String> ccs = new ArrayList<String>();
        sender.send("msg@ipukr.cn", tos, ccs, "ryan sender email", "tst mail function");

```

配置信息如下

> mail.smtp.host=smtp.exmail.qq.com
>
> mail.smtp.port=25
>
> mail.transport.protocol=smtp
>
> mail.smtp.auth=true
>
> mail.username=msg@ipukr.cn
>
> mail.password=`%H9N8x^7Oi%Nxm6w`
