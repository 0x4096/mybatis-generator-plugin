/*
 * Copyright (c) 2018.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.Is0x4096.mybatis.generator.plugins.utils.hook;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2018/5/7 18:51
 * ---------------------------------------------------------------------------
 */
public interface ISelectOneByExamplePluginHook {

    /**
     * selectOneByExampleWithBLOBs 接口方法生成
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    boolean clientSelectOneByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable);

    /**
     * selectOneByExample 接口方法生成
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    boolean clientSelectOneByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable);

    /**
     * selectOneByExample 方法sqlMap实现
     * @param document
     * @param element
     * @param introspectedTable
     * @return
     */
    boolean sqlMapSelectOneByExampleWithoutBLOBsElementGenerated(Document document, XmlElement element, IntrospectedTable introspectedTable);

    /**
     * selectOneByExampleWithBLOBs 方法sqlMap实现
     * @param document
     * @param element
     * @param introspectedTable
     * @return
     */
    boolean sqlMapSelectOneByExampleWithBLOBsElementGenerated(Document document, XmlElement element, IntrospectedTable introspectedTable);
}
