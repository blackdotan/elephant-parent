package com.ipukr.elephant.architecture.context;

import com.ipukr.elephant.architecture.configuration.Configuration;
import com.ipukr.elephant.architecture.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by wmw on 12/27/16.
 */
public class Context {

    private Logger logger = LoggerFactory.getLogger(com.ipukr.elephant.architecture.context.Context.class);

    private Configuration mConfiguration;

    private String label = null;

    public Context(Configuration configuration){
        this.mConfiguration = configuration;
    }


    private String prefix(){
        String label = this.label==null ? mConfiguration.getString(Constant.ARCHITECTURE.concat(".").concat(Constant.LABEL)):this.label;
        String prefix = Constant.ARCHITECTURE.concat(".").concat(label).concat(".");
        return prefix;
    }

    public String findStringAccordingKey(String key){
        logger.debug("获取Context key={}", prefix().concat(key));
        return mConfiguration.getString(prefix().concat(key));
    }


    public String findStringAccordingKey(String key, String def){
        logger.debug("获取Context key={}, def={}", prefix().concat(key), def);
        return mConfiguration.getString(prefix().concat(key), def);
    }

    public Object findObjectAccordingKey(String key){
        logger.debug("获取Context key={}", prefix().concat(key));
        return mConfiguration.getProperty(prefix().concat(key));
    }

    public Object findObjectAccordingKey(String key, Object obj){
        logger.debug("获取Context key={}, def={}", prefix().concat(key), obj);
        Object ret = mConfiguration.getProperty(prefix().concat(key));
        return ret==null ? obj:ret;
    }

    public Boolean findBooleanAccordingKey(String key) {
        logger.debug("获取Context key={}", prefix().concat(key));
        return mConfiguration.getBoolean(prefix().concat(key));
    }


    public Boolean findBooleanAccordingKey(String key, Boolean bool) {
        logger.debug("获取Context key={}, def={}", prefix().concat(key), bool);
        return mConfiguration.getBoolean(prefix().concat(key), bool);
    }

    public Number findNumberAccordingKey(String key) throws ParseException {
        logger.debug("获取Context key={}", prefix().concat(key));
        return NumberFormat.getInstance().parse(mConfiguration.getString(prefix().concat(key)));
    }

    public Number findNumberAccordingKey(String key, Number obj) throws ParseException {
        logger.debug("获取Context key={}, def={}", prefix().concat(key), obj);
        return NumberFormat.getInstance().parse(mConfiguration.getString(prefix().concat(key), obj.toString()));
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
