package com.itfsw.mybatis.generator.plugins;

import com.itfsw.mybatis.generator.plugins.utils.JavaElementGeneratorTools;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

/**
 * 是否存在 暂时无效
 *
 * @author 0x4096.peng@gmail.com
 * @date 2020/12/2
 */
public class IsExistGenerator extends PluginAdapter {

    public static final String METHOD_SELECT_ONE_BY_EXAMPLE = "isExist";  // 方法名


    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientBasicCountMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 方法生成 isExist
        Method selectOneByExample = JavaElementGeneratorTools.generateMethod(
                METHOD_SELECT_ONE_BY_EXAMPLE,
                JavaVisibility.DEFAULT,
                JavaElementGeneratorTools.getModelTypeWithoutBLOBs(introspectedTable),
                new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example")
        );

        selectOneByExample.addJavaDocLine("/** ");
        selectOneByExample.addJavaDocLine(" * 是否存在(大于等于一条数据)");
        selectOneByExample.addJavaDocLine("*/");

        interfaze.addMethod(selectOneByExample);
        return true;
    }

}
