# 钚氪组件化开发框架

## 简介

组件开发框架

## 使用说明

配置文件 `prop/prop.properties`

```
# 默认label=kv
architecture.label=kv

# 指定kv实现backend
architecture.kv.class=com.ipukr.elephant.architecture.third.MemoryKV
# 属性参数
architecture.kv.map.class=java.util.concurrent.ConcurrentHashMap
architecture.kv.expire=999999
```

定义接口类KV

```
public interface KV {
    /**
     * 新增Key-Value
     * @param key
     * @param value
     */
    void add(String key, Object value);

    /**
     * 删除Key
     * @param key
     */
    void remove(String key);

    /**
     * 判断Key是否存在
     * @param key
     */
    boolean exists(String key);

    /**
     * Key模糊匹配
     * @param pattern
     * @return
     */
    Map<String, Object> match(String pattern);
}
```

定义实现类MemoryKV, 继承AbstractAPI

```
public class MemoryKV extends AbstractAPI implements KV {

    private static final Logger logger = LoggerFactory.getLogger(MemoryKV.class);

    private static final String MAP_CLASS = "map.class";
    private static final String EXPIRE = "expire";

    private Integer expire = 999_999_999;

    private Map<String, Object> map = null;

    /**
     * 构造方法重载
     *
     * <p>
     *  AbstractAPI实现类必须继承:
     *  com.ipukr.elephant.architecture.context.Context
     *  或
     *  com.ipukr.elephant.architecture.context.Context, Class 构造方法
     * </p>
     *
     * @param context
     */
    public MemoryKV(Context context) throws Exception {
        super(context);
        this.init();
    }

    private void init() throws Exception {
        // 获取expire
        expire = context.findNumberAccordingKey(EXPIRE, expire).intValue();
        // 根据MAP_CLASS, 实例化map
        String mapClazz = context.findStringAccordingKey(MAP_CLASS, "java.util.HashMap");
        map = (Map) Class.forName(mapClazz).newInstance();
        logger.info("init MemoryKV, expire={}, map.calss={}", expire, map.getClass().getName());
    }

    /**
     * 新增Key-Value
     * @param key
     * @param value
     */
    @Override
    public void add(String key, Object value) {
        logger.info("add key/value: key={}, value={}", key, value.toString());
        map.put(key, value);
    }

    /**
     * 删除Key
     * @param key
     */
    @Override
    public void remove(String key) {
        logger.info("remove key: key={}", key);
        if(map.containsKey(key))
            map.remove(key);
    }

    /**
     * 判断Key是否存在
     * @param key
     */
    @Override
    public boolean exists(String key) {
        logger.info("exists key: key={}", key);
        return map.containsKey(key);
    }

    @Override
    public Map<String, Object> match(String pattern) {
        try {
            Map ret = (Map) Class.forName(context.findStringAccordingKey(MAP_CLASS)).newInstance();
            for(Map.Entry<String,Object> entry : map.entrySet()) {
                if (entry.getKey().matches(pattern)) {
                    ret.put(entry.getKey(), entry.getValue());
                }
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(ExceptionUtils.castEx2Str(e));
        }
        return null;
    }
}
```


测试用例

```
public class KVTest {
    @Test
    public void testKV() throws Exception {
        // 通过配置文件, 获取KV实例
        KV kv = Factory.getInstance().build("prop/prop.properties");
        // 执行KV操作
        kv.add("1", 123);
        kv.add("2", 456);
        Map<String, Object> rst = kv.match(".*");
        if(rst!=null) {
            for (Map.Entry<String, Object> entry : rst.entrySet()) {
                System.out.println(entry.getKey() + "-" + entry.getValue());
            }
        }
    }
}
```




