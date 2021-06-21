package com.github.Is0x4096.mybatis.generator.plugins;

import com.github.Is0x4096.mybatis.generator.plugins.utils.XmlElementGeneratorTools;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * 是否存在
 *
 * @author 0x4096.peng@gmail.com
 * @date 2020/12/2
 */
public class IsExistPlugin extends PluginAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String IS_EXIST = "isExist";  // 方法名


    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * Java Client Methods 生成
     * 具体执行顺序 http://www.mybatis.org/generator/reference/pluggingIn.html
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        Method isExistMethod = new Method();
        isExistMethod.setName(IS_EXIST);
        isExistMethod.setVisibility(JavaVisibility.DEFAULT);
        isExistMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example"));

        isExistMethod.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());

        isExistMethod.addJavaDocLine("/** ");
        isExistMethod.addJavaDocLine(" * 是否存在(大于等于一条数据)");
        isExistMethod.addJavaDocLine("*/");

        context.getCommentGenerator().addGeneralMethodComment(isExistMethod, introspectedTable);

        interfaze.addMethod(isExistMethod);
        return true;
    }

    /**
     * 具体执行顺序 http://www.mybatis.org/generator/reference/pluggingIn.html
     *
     * @param element
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document element, IntrospectedTable introspectedTable) {
        // ------------------------------------ selectOneByExample ----------------------------------
        // 生成查询语句
        XmlElement isExistElement = new XmlElement("select");
        // 添加注释(!!!必须添加注释，overwrite覆盖生成时，@see XmlFileMergerJaxp.isGeneratedNode会去判断注释中是否存在OLD_ELEMENT_TAGS中的一点，例子：@mbg.generated)
        context.getCommentGenerator().addComment(isExistElement);

        // 添加ID
        isExistElement.addAttribute(new Attribute("id", IS_EXIST));
        // 添加返回类型
        isExistElement.addAttribute(new Attribute("resultType", "java.lang.Boolean"));
        // 添加参数类型
        isExistElement.addAttribute(new Attribute("parameterType", introspectedTable.getExampleType()));
        isExistElement.addElement(new TextElement("select count(*)"));

        StringBuilder sb = new StringBuilder();
        if (stringHasValue(introspectedTable.getSelectByExampleQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByExampleQueryId());
            sb.append("' as QUERYID,");
            isExistElement.addElement(new TextElement(sb.toString()));
        }

        sb.setLength(0);
        sb.append("from ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        isExistElement.addElement(new TextElement(sb.toString()));
        isExistElement.addElement(XmlElementGeneratorTools.getExampleIncludeElement(introspectedTable));

        XmlElement xmlElement = element.getRootElement();
        xmlElement.addElement(isExistElement);
        return true;
    }

}
