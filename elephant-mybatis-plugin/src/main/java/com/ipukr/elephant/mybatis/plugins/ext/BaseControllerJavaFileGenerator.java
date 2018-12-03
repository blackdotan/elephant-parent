package com.ipukr.elephant.mybatis.plugins.ext;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

import com.ipukr.elephant.common.web.http.PaginationResponseEntity;
import com.ipukr.elephant.mybatis.plugins.utils.MethodUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.Context;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by wmw on 1/12/17.
 */
public abstract class BaseControllerJavaFileGenerator extends AbstractJavaGenerator{

    public static final String BASE_PACKAGE = "BasePackage";
    public static final String CONTROLLER_FLAG = "Controllers";

    private Context context;
    private Properties properties;
    private IntrospectedTable introspectedTable;

    private String iBasePackage;
    private String iCommand;
    private String iEntityClazz;
    private String iModel;
    private String iDTO;
    private String iDTOClazz;

    public BaseControllerJavaFileGenerator(Context context, IntrospectedTable introspectedTable, Properties properties) {
        this.context = context;
        this.properties = properties;
        this.introspectedTable = introspectedTable;

        this.iBasePackage = properties.getProperty(BASE_PACKAGE);
        this.iCommand = introspectedTable.getTableConfigurationProperty(CONTROLLER_FLAG);
        this.iEntityClazz = introspectedTable.getBaseRecordType();
        this.iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        this.iDTO = StringUtils.easyAppend("{}DTO", iModel);
        this.iDTOClazz = StringUtils.easyAppend("{}.web.response.{}", iBasePackage, iDTO);
    }

    /**
     *

     * @RequestMapping(method = RequestMethod.PUT)
     * @ResponseBody
     * public Object insert({Model}Form form){
     *         {Model} model = DataUtils.copyPropertiesIgnoreNull(form, {Model}.class);
     *         return ResponseUtils.entResponse(m{Model}Service.insert(model));
     * }

     * @param Model
     * @return
     */
    protected Method insert(String Model, FullyQualifiedJavaType iFormClazz){
        Method method = new Method();

        method.addAnnotation(MethodUtils.annotation("This Is Insert Method, Please Check Your Parameter Is Valid"));
        method.addAnnotation("@ApiOperation(value = \"新增记录\", notes = \"新增记录\", response = "+iDTO+".class)");
        method.addAnnotation("@RequestMapping(method = RequestMethod.PUT)");
        method.addAnnotation("@ResponseBody");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(ResponseEntity.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(iDTOClazz));
        method.setReturnType(returnType);
        method.addException(new FullyQualifiedJavaType(Exception.class.getName()));
        method.setName("insert");


        Parameter parameter1 = new Parameter(iFormClazz, "form");
        parameter1.addAnnotation("@ModelAttribute");
        method.addParameter(parameter1);
        Parameter parameter2 = new Parameter(new FullyQualifiedJavaType(String.class.getName()), "token");
        parameter2.addAnnotation("@RequestHeader(value = \"token\", required = true)");
        method.addParameter(parameter2);


        StringBuffer body = new StringBuffer();
        body.append("//if(!form.validate()){\n")
                .append("//\t\t\treturn ResponseEntity.status(HttpStatus.BAD_REQUEST).header(\"msg\", \"新增失败，输入的参数不合法\").build();\n")
                .append("//\t\t}\n")
                .append("\t\t{Model} model = new {Model}();\n")
                .append("//\t\tboolean bool = m{Model}Service.insert(model);\n")
                .append("//\t\tif ( bool ) {\n")
                .append("//\t\t\t{Model}DTO dto = {Model}DTO.parser(model);\n")
                .append("//\t\t\treturn ResponseEntity.ok(dto);\n")
                .append("//\t\t} else {\n")
                .append("//\t\t\treturn ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header(\"msg\", \"新增失败\").build();\n")
                .append("//\t\t} \n")
                .append("\t\tthrow new UnsupportedOperationException(\"不支持此操作\");");
        method.addBodyLine(body.toString().replace("{Model}", Model));

        StringBuffer annotation = new StringBuffer();

        return method;
    }

    /**
     *  @RequestMapping(method = RequestMethod.GET)
     *  @ResponseBody
     *  public Object find(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize){
     *      PageBounds bounds = new PageBounds(pageIndex, pageSize, true);
     *      return ResponseUtils.entResponse(m{Model}Service.find(editor, bounds));
     *  }
     * @param Model
     * @return
     */
    protected Method find(String Model){

        Method method = new Method();

        method.addAnnotation(MethodUtils.annotation("This Is Find Method, No Condition Match And Return Object List Info"));
        method.addAnnotation("@ApiOperation(value = \"获取数据\", notes = \"获取数据\", response = "+iDTO+".class)");
        method.addAnnotation("@RequestMapping(method = RequestMethod.GET)");
        method.addAnnotation("@ResponseBody");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(PaginationResponseEntity.class.getName());
        method.setReturnType(returnType);
        method.addException(new FullyQualifiedJavaType(Exception.class.getName()));
        method.setName("find");

        Parameter p1 = new Parameter(new FullyQualifiedJavaType(Integer.class.getName()), "pageIndex");
        p1.addAnnotation("@RequestParam(defaultValue = \"1\")");
        method.addParameter(p1);
        Parameter p2 = new Parameter(new FullyQualifiedJavaType(Integer.class.getName()), "pageSize");
        p2.addAnnotation("@RequestParam(defaultValue = \"10\")");
        method.addParameter(p2);

        Parameter parameter3 = new Parameter(new FullyQualifiedJavaType(String.class.getName()), "token");
        parameter3.addAnnotation("@RequestHeader(value = \"token\", required = true)");
        method.addParameter(parameter3);


        StringBuffer body = new StringBuffer();
        body.append("PageBounds bounds = new PageBounds(pageIndex, pageSize, true);\n")
                .append("\t\treturn PaginationResponseEntity.ok(m{Model}Service.find(bounds));");
        method.addBodyLine(body.toString().replace("{Model}", Model));

        return method;
    }

    /**
     *  @RequestMapping(value = "/query", method = RequestMethod.POST)
     *  @ResponseBody
     *  public Object query({Model}Form form, @RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize) {
     *      PageBounds bounds = new PageBounds(pageIndex, pageSize, true);
     *      return ResponseUtils.entResponse(m{Model}Service.query(editor, bounds));
     *  }
     * @param Model
     * @return
     */
    protected Method query(String Model, FullyQualifiedJavaType iFormClazz){

        Method method = new Method();

        method.addAnnotation(MethodUtils.annotation("This Is Query Method, Return Object List Info"));
        method.addAnnotation("@ApiOperation(value = \"匹配查询\", notes = \"匹配查询\", response = "+iDTO+".class)");
        method.addAnnotation("@RequestMapping(value = \"/query\",method = RequestMethod.POST)");
        method.addAnnotation("@ResponseBody");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(PaginationResponseEntity.class.getName());
        method.setReturnType(returnType);
        method.addException(new FullyQualifiedJavaType(Exception.class.getName()));
        method.setName("query");

        Parameter parameter = new Parameter(iFormClazz, "form");
        parameter.addAnnotation("@ModelAttribute");
        method.addParameter(parameter);

        Parameter p2 = new Parameter(new FullyQualifiedJavaType(Integer.class.getName()), "pageIndex");
        p2.addAnnotation("@RequestParam(defaultValue = \"1\")");
        method.addParameter(p2);
        Parameter p3 = new Parameter(new FullyQualifiedJavaType(Integer.class.getName()), "pageSize");
        p3.addAnnotation("@RequestParam(defaultValue = \"10\")");
        method.addParameter(p3);
        Parameter parameter4 = new Parameter(new FullyQualifiedJavaType(String.class.getName()), "token");
        parameter4.addAnnotation("@RequestHeader(value = \"token\", required = true)");
        method.addParameter(parameter4);

        StringBuffer body = new StringBuffer();
        body.append("PageBounds bounds = new PageBounds(pageIndex, pageSize, true);\n");
        body.append("\t\t{Model} model = DataUtils.copyPropertiesIgnoreNull(form, {Model}.class);\n");

        body.append("\t\treturn PaginationResponseEntity.ok(m{Model}Service.query(model, bounds));");
        method.addBodyLine(body.toString().replace("{Model}", Model));

        return method;
    }


    /**
     *  @RequestMapping(value = "/search", method = RequestMethod.POST)
     *  @ResponseBody
     *  public Object query({Model}Form form, @RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
     *      PageBounds bounds = new PageBounds(pageIndex, pageSize, true);
     *      {Model} model = DataUtils.copyPropertiesIgnoreNull(form, {Model}.class);
     *      return ResponseUtils.entResponse(m{Model}Service.query(model, bounds));
     *  }
     *
     * @param Model
     * @return
     */
    protected Method search(String Model, FullyQualifiedJavaType iFormClazz) {


        Method method = new Method();

        method.addAnnotation(MethodUtils.annotation("This Is Search Method, Return Object List Info"));
        method.addAnnotation("@ApiOperation(value = \"搜索查询\", notes = \"搜索查询\", response = "+iDTO+".class)");
        method.addAnnotation("@RequestMapping(value = \"/search\",method = RequestMethod.POST)");
        method.addAnnotation("@ResponseBody");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(PaginationResponseEntity.class.getName());
        method.setReturnType(returnType);
        method.addException(new FullyQualifiedJavaType(Exception.class.getName()));
        method.setName("search");


        Parameter parameter = new Parameter(iFormClazz, "form");
        parameter.addAnnotation("@ModelAttribute");
        method.addParameter(parameter);

        Parameter p2 = new Parameter(new FullyQualifiedJavaType(Integer.class.getName()), "pageIndex");
        p2.addAnnotation("@RequestParam(defaultValue = \"1\")");
        method.addParameter(p2);
        Parameter p3 = new Parameter(new FullyQualifiedJavaType(Integer.class.getName()), "pageSize");
        p3.addAnnotation("@RequestParam(defaultValue = \"10\")");
        method.addParameter(p3);
        Parameter parameter4 = new Parameter(new FullyQualifiedJavaType(String.class.getName()), "token");
        parameter4.addAnnotation("@RequestHeader(value = \"token\", required = true)");
        method.addParameter(parameter4);

        StringBuffer body = new StringBuffer();
        body.append("PageBounds bounds = new PageBounds(pageIndex, pageSize, true);\n")
                .append("\t\t{Model} model = DataUtils.copyPropertiesIgnoreNull(form, {Model}.class);\n");

        body.append("\t\treturn PaginationResponseEntity.ok(m{Model}Service.search(model, bounds));");

        method.addBodyLine(body.toString().replace("{Model}", Model));

        return method;
    }


    /**
     *  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
     *  @ResponseBody
     *  public Object update({Model}Form form, @PathVariable Integer id){
     *      {Model} model = DataUtils.copyPropertiesIgnoreNull(form, {Model}.class);
     *      editor.setId(id);
     *      return ResponseUtils.entResponse(m{Model}Service.update(editor));
     *  }
     * @param Model
     * @return
     */
    protected Method update(String Model, String PrimaryType, FullyQualifiedJavaType iFormClazz){

        Method method = new Method();

        method.addAnnotation(MethodUtils.annotation("This Is Update Method, Please Check Your Parameter Is Valid"));
        method.addAnnotation("@ApiOperation(value = \"更新记录\", notes = \"更新记录\", response = "+iDTO+".class)");
        method.addAnnotation("@RequestMapping(value = \"/{id}\",method = RequestMethod.PATCH)");
        method.addAnnotation("@ResponseBody");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(ResponseEntity.class.getName());
        method.setReturnType(returnType);
        method.addException(new FullyQualifiedJavaType(Exception.class.getName()));
        method.setName("update");



        Parameter p2 = new Parameter(new FullyQualifiedJavaType(PrimaryType), "id");
        p2.addAnnotation("@PathVariable");
        method.addParameter(p2);
        Parameter p3 = new Parameter(iFormClazz, "form");
        p3.addAnnotation("@ModelAttribute");
        method.addParameter(p3);
        Parameter parameter4 = new Parameter(new FullyQualifiedJavaType(String.class.getName()), "token");
        parameter4.addAnnotation("@RequestHeader(value = \"token\", required = true)");
        method.addParameter(parameter4);



        StringBuffer body = new StringBuffer();
        body.append("\t{Model} model = DataUtils.copyPropertiesIgnoreNull(form, {Model}.class);\n");



        String setter = "setId";
        for(IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()){
            setter = StringUtils.easyAppend("set{}{}",
                    column.getJavaProperty().substring(0,1).toUpperCase(),
                    column.getJavaProperty().substring(1));
        }
        body.append("\t\tmodel.{Setter}(id);\n")
                .append("//\t\tboolean bool = m{Model}Service.update(model);\n")
                .append("//\t\tif ( bool ) {\n")
                .append("//\t\t\treturn ResponseEntity.ok(model);\n")
                .append("//\t\t} else {\n")
                .append("//\t\t\treturn ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header(\"msg\", \"更新失败\").build();\n")
                .append("//\t\t} \n")

                .append("\t\tthrow new UnsupportedOperationException(\"不支持此操作\");");
        method.addBodyLine(body.toString().replace("{Setter}", setter).replace("{Model}", Model));

        return method;
    }

    /**
     *  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
     *  @ResponseBody
     *  public Object delete(@PathVariable Integer id){
     *      return ResponseUtils.entResponse(m{Model}Service.delete(id));
     *  }
     * @param Model
     * @param PrimaryType
     * @return
     */
    protected Method delete(String Model, String PrimaryType){

        Method method = new Method();

        method.addAnnotation(MethodUtils.annotation("This Is Delete Method, Please Overwrite This Method To ReImplement"));
        method.addAnnotation("@ApiOperation(value = \"删除记录\", notes = \"删除记录\", response = Boolean.class)");
        method.addAnnotation("@RequestMapping(value = \"/{id}\",method = RequestMethod.DELETE)");
        method.addAnnotation("@ResponseBody");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("ResponseEntity");
        method.setReturnType(returnType);
        method.addException(new FullyQualifiedJavaType("Exception"));
        method.setName("delete");

        Parameter p1 = new Parameter(new FullyQualifiedJavaType(PrimaryType), "id");
        p1.addAnnotation("@PathVariable");
        method.addParameter(p1);
        Parameter parameter2 = new Parameter(new FullyQualifiedJavaType(String.class.getName()), "token");
        parameter2.addAnnotation("@RequestHeader(value = \"token\", required = true)");
        method.addParameter(parameter2);

        StringBuffer body = new StringBuffer();
        body.append("//return ResponseEntity.ok(ImmutableSet.of(\"ops\",mSocialSharingService.delete(id)));\n");
        body.append("\t\tthrow new UnsupportedOperationException(\"不支持此操作\");");
        method.addBodyLine(body.toString().replace("{Model}", Model));

        return method;
    }


    /**
     *  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     *  @ResponseBody
     *  public Object find(@PathVariable Integer id){
     *      return ResponseUtils.entResponse(m{Model}Service.find(id));
     *  }
     * @param Model
     * @param PrimaryType
     * @return
     */
    protected Method find(String Model, String PrimaryType){

        Method method = new Method();

        method.addAnnotation(MethodUtils.annotation("This Is Find Method, Return Detail Object Info"));
        method.addAnnotation("@ApiOperation(value = \"获取记录\", notes = \"获取记录\", response = "+iDTO+".class)");
        method.addAnnotation("@RequestMapping(value = \"/{id}\",method = RequestMethod.GET)");
        method.addAnnotation("@ResponseBody");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("ResponseEntity");
        method.setReturnType(returnType);
        method.addException(new FullyQualifiedJavaType("Exception"));
        method.setName("find");

        Parameter p1 = new Parameter(new FullyQualifiedJavaType(PrimaryType), "id");
        p1.addAnnotation("@PathVariable");
        method.addParameter(p1);
        Parameter parameter2 = new Parameter(new FullyQualifiedJavaType(String.class.getName()), "token");
        parameter2.addAnnotation("@RequestHeader(value = \"token\", required = true)");
        method.addParameter(parameter2);

        StringBuffer body = new StringBuffer();
        body.append("return ResponseEntity.ok(m{Model}Service.find(id));");
        method.addBodyLine(body.toString().replace("{Model}", Model));

        return method;
    }

}
