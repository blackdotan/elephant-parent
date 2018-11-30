

## 内存存储

>application.label=kv
>application.kv.class=com.ipukr.elephant.kv.backend.MemoryKV

## Redis存储

> application.redis.class=com.ipukr.elephant.kv.backend.RedisKV
> application.redis.address=120.76.120.81
> application.redis.port=6379
> application.redis.auth=admin123@$^!
> application.redis.db=0
> application.redis.max_total=300
> application.redis.max_idle=100
> application.redis.max_wait=100
> application.redis.max_active=300
> application.redis.test_on_borrow=true
> application.redis.timeout=10000