package com.itfsw.mybatis.generator.plugins;

import com.itfsw.mybatis.generator.plugins.javatype.FastJSONFullyQualifiedJavaType;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/12/12
 */
public class CommonBaseGenerator extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        if (isKeyColumn(introspectedTable)) {
            generateBeforeInsert(introspectedTable, topLevelClass, true);
            generateBeforeInsert(introspectedTable, topLevelClass, false);
            generateBeforeUpdate(introspectedTable, topLevelClass, true, true);
            generateBeforeUpdate(introspectedTable, topLevelClass, false, true);
            generateBeforeUpdate(introspectedTable, topLevelClass, false, false);
        }
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (isKeyColumn(introspectedTable)) {
            generateBeforeInsert(introspectedTable, topLevelClass, true);
            generateBeforeInsert(introspectedTable, topLevelClass, false);
            generateBeforeUpdate(introspectedTable, topLevelClass, true, true);
            generateBeforeUpdate(introspectedTable, topLevelClass, false, true);
            generateBeforeUpdate(introspectedTable, topLevelClass, false, false);
        }
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        if (isKeyColumn(introspectedTable)) {
            generateBeforeInsert(introspectedTable, topLevelClass, true);
            generateBeforeInsert(introspectedTable, topLevelClass, false);
            generateBeforeUpdate(introspectedTable, topLevelClass, true, true);
            generateBeforeUpdate(introspectedTable, topLevelClass, false, true);
            generateBeforeUpdate(introspectedTable, topLevelClass, false, false);
        }
        return true;
    }

    private void generateBeforeInsert(IntrospectedTable introspectedTable, TopLevelClass topLevelClass, boolean isHasDate) {
        Method method = new Method("beforeInsert");
        method.setVisibility(JavaVisibility.PUBLIC);

        if (introspectedTable.getTargetRuntime() == IntrospectedTable.TargetRuntime.MYBATIS3_DSQL) {
            context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable, topLevelClass.getImportedTypes());
        } else {
            context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        }

        /* 检查列 */
        List<IntrospectedColumn> columnList = introspectedTable.getAllColumns();
        if (null != columnList && columnList.size() != 0) {
            /*
             *  gmtCreate
             *  creator
             *  gmtModified
             *  modifier
             *  isDeleted
             */
            boolean hasGmtCreate = false;
            boolean hasCreator = false;
            boolean hasGmtModified = false;
            boolean hasModifier = false;
            boolean hasIsDeleted = false;

            for (IntrospectedColumn column : columnList) {
                if ("gmt_create".equals(column.getActualColumnName()) && column.isJDBCDateColumn()) {
                    hasGmtCreate = true;
                    continue;
                }

                if ("gmt_modified".equals(column.getActualColumnName()) && column.isJDBCDateColumn()) {
                    hasGmtModified = true;
                    continue;
                }

                if ("creator".equals(column.getActualColumnName())) {
                    hasCreator = true;
                    continue;
                }

                if ("modifier".equals(column.getActualColumnName())) {
                    hasModifier = true;
                    continue;
                }

                if ("is_deleted".equals(column.getActualColumnName())) {
                    hasIsDeleted = true;
                }
            }


            if (hasCreator) {
                method.addBodyLine("this.creator = operator;");
            }

            if (hasModifier) {
                method.addBodyLine("this.modifier = operator;");
            }


            if (hasGmtCreate || hasGmtModified) {
                /* 导包 */
                topLevelClass.addImportedType(new FastJSONFullyQualifiedJavaType("java.util.Date"));
            }

            if (hasGmtCreate) {
                if (!isHasDate) {
                    method.addBodyLine("Date now = new Date();");
                }
                method.addBodyLine("this.gmtCreate = now;");
            }

            if (hasGmtModified) {
                if (!hasGmtCreate && !isHasDate) {
                    method.addBodyLine("Date now = new Date();");
                }
                method.addBodyLine("this.gmtModified = now;");
            }

            if (hasIsDeleted) {
                method.addBodyLine("this.isDeleted = false;");
            }

            method.addJavaDocLine("/** ");
            method.addJavaDocLine(" * 插入数据前");
            method.addJavaDocLine(" */");

            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "operator"));
            if (isHasDate) {
                method.addParameter(new Parameter(FullyQualifiedJavaType.getDateInstance(), "now"));
            }

            topLevelClass.addMethod(method);
        }

    }


    private void generateBeforeUpdate(IntrospectedTable introspectedTable, TopLevelClass topLevelClass, boolean isHasDate, boolean isDeleted) {
        Method method = new Method("beforeUpdate");
        method.setVisibility(JavaVisibility.PUBLIC);

        if (introspectedTable.getTargetRuntime() == IntrospectedTable.TargetRuntime.MYBATIS3_DSQL) {
            context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable, topLevelClass.getImportedTypes());
        } else {
            context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        }

        /* 检查列 */
        List<IntrospectedColumn> columnList = introspectedTable.getAllColumns();
        if (null != columnList && columnList.size() != 0) {
            /*
             *  gmtCreate
             *  creator
             *  gmtModified
             *  modifier
             *  isDeleted
             */
            boolean hasGmtModified = false;
            boolean hasModifier = false;
            boolean hasIsDeleted = false;

            for (IntrospectedColumn column : columnList) {
                if ("gmt_modified".equals(column.getActualColumnName()) && (column.isJDBCDateColumn() || column.isJDBCTimeColumn() )) {
                    hasGmtModified = true;
                    continue;
                }

                if ("modifier".equals(column.getActualColumnName())) {
                    hasModifier = true;
                    continue;
                }

                if ("is_deleted".equals(column.getActualColumnName())) {
                    hasIsDeleted = true;
                }
            }


            if (hasModifier) {
                method.addBodyLine("this.modifier = operator;");
            }


            if (hasGmtModified) {
                topLevelClass.addImportedType(new FastJSONFullyQualifiedJavaType("java.util.Date"));
                if (isHasDate) {
                    method.addBodyLine("this.gmtModified = now;");
                } else {
                    method.addBodyLine("this.gmtModified = new Date();");
                }
            }

            if (hasIsDeleted && isDeleted) {
                method.addBodyLine("this.isDeleted = isDeleted;");
            }

            method.addJavaDocLine("/** ");
            method.addJavaDocLine(" * 更新数据前");
            method.addJavaDocLine(" */");

            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "operator"));
            if (isHasDate) {
                method.addParameter(new Parameter(FullyQualifiedJavaType.getDateInstance(), "now"));
            }

            if (isDeleted) {
                method.addParameter(new Parameter(FullyQualifiedJavaType.getBooleanPrimitiveInstance(), "isDeleted"));
            }

            topLevelClass.addMethod(method);
        }

    }


    private boolean isKeyColumn(IntrospectedTable introspectedTable) {
        /* 检查列 */
        List<IntrospectedColumn> columnList = introspectedTable.getAllColumns();
        boolean hasGmtCreate = false;
        boolean hasCreator = false;
        boolean hasGmtModified = false;
        boolean hasModifier = false;
        boolean hasIsDeleted = false;
        if (null != columnList && columnList.size() != 0) {
            /*
             *  gmtCreate
             *  creator
             *  gmtModified
             *  modifier
             *  isDeleted
             */
            for (IntrospectedColumn column : columnList) {
                if ("gmt_create".equals(column.getActualColumnName()) && column.isJDBCDateColumn()) {
                    hasGmtCreate = true;
                    continue;
                }

                if ("gmt_modified".equals(column.getActualColumnName()) && column.isJDBCDateColumn()) {
                    hasGmtModified = true;
                    continue;
                }

                if ("creator".equals(column.getActualColumnName())) {
                    hasCreator = true;
                    continue;
                }

                if ("modifier".equals(column.getActualColumnName())) {
                    hasModifier = true;
                    continue;
                }

                if ("is_deleted".equals(column.getActualColumnName())) {
                    hasIsDeleted = true;
                }
            }
        }

        return hasGmtCreate || hasCreator || hasModifier || hasGmtModified || hasIsDeleted;
    }

}
