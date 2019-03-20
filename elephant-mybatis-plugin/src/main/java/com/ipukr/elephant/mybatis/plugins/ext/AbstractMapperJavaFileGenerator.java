package com.ipukr.elephant.mybatis.plugins.ext;

import com.ipukr.elephant.common.Identifiable;
import com.ipukr.elephant.mybatis.plugins.utils.ColumnUtils;
import com.ipukr.elephant.mybatis.plugins.utils.MyBatisUtilities;
import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/2/24.
 */
public class AbstractMapperJavaFileGenerator extends AbstractJavaGenerator {
    /**
     * @return
     */
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();


        return answer;
    }


    private CompilationUnit generateEnumerationJavaFile() {
        String iTargetPackage = MyBatisUtilities.getMapperPackage(context);
        String clazz = iTargetPackage.concat(".AbstractMapper");
        FullyQualifiedJavaType iAbstractMapperType = new FullyQualifiedJavaType(clazz);



        Interface iInterface = new Interface(iAbstractMapperType);

        iInterface.setVisibility(JavaVisibility.PUBLIC);



        return iInterface;
    }
}
