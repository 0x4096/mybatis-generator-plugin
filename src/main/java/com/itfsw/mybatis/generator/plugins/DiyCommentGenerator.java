package com.itfsw.mybatis.generator.plugins;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/12/12
 */
public class DiyCommentGenerator implements CommentGenerator {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void addConfigurationProperties(Properties properties) {

    }

    /**
     * 获取列注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");

        List<IntrospectedColumn> columnList = introspectedTable.getPrimaryKeyColumns();
        if (null != columnList && columnList.size() != 0) {
            for (IntrospectedColumn column : columnList) {
                if (column.getActualColumnName().equals(introspectedColumn.getActualColumnName())) {
                    field.addJavaDocLine(" * IsIdentity: " + introspectedColumn.isIdentity());
                    field.addJavaDocLine(" * IsAutoIncrement: " + introspectedColumn.isAutoIncrement());
                }
            }
        }

        field.addJavaDocLine(" * Remark: " + introspectedColumn.getRemarks());
        field.addJavaDocLine(" * Length: " + introspectedColumn.getLength());
        field.addJavaDocLine(" * IsNullable: " + introspectedColumn.isNullable());
        field.addJavaDocLine(" * DefaultValue: " + introspectedColumn.getDefaultValue());
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    /**
     * 获取表注释
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        topLevelClass.addJavaDocLine("/**");
        if (null != remarks && remarks.length() != 0) {
            topLevelClass.addJavaDocLine(" * " + remarks);
            topLevelClass.addJavaDocLine(" * ");
        }
        topLevelClass.addJavaDocLine(" * @author " + System.getProperty("user.name"));
        topLevelClass.addJavaDocLine(" * @date " + dateTimeFormatter.format(LocalDateTime.now()));
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {

    }

    @Override
    public void addComment(XmlElement xmlElement) {

    }

    @Override
    public void addRootComment(XmlElement rootElement) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

}