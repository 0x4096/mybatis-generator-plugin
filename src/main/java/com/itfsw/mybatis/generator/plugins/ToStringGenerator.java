package com.itfsw.mybatis.generator.plugins;

import com.itfsw.mybatis.generator.plugins.javatype.FastJSONFullyQualifiedJavaType;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/12/12
 */
public class ToStringGenerator extends PluginAdapter {

    private boolean useToStringFromRoot;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        useToStringFromRoot = isTrue(properties.getProperty("useToStringFromRoot"));
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return true;
    }

    private void generateToString(IntrospectedTable introspectedTable,
                                  TopLevelClass topLevelClass) {
        Method method = new Method("toString");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.addAnnotation("@Override");

        if (introspectedTable.getTargetRuntime() == IntrospectedTable.TargetRuntime.MYBATIS3_DSQL) {
            context.getCommentGenerator().addGeneralMethodAnnotation(method,
                    introspectedTable, topLevelClass.getImportedTypes());
        } else {
            context.getCommentGenerator().addGeneralMethodComment(method,
                    introspectedTable);
        }

        try {
            Class.forName("com.alibaba.fastjson.JSON");
            /* 导包 */
            topLevelClass.addImportedType(new FastJSONFullyQualifiedJavaType("com.alibaba.fastjson.JSON"));
            /* 生成方法体 */
            method.addBodyLine("return JSON.toJSONString(this);");
            topLevelClass.addMethod(method);
        } catch (ClassNotFoundException e) {
            /* 生成 mybatis 默认的 toString */

        }
    }

}
