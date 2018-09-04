
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
