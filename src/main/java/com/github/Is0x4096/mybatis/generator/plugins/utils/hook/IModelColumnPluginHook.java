/*
 * Copyright (c) 2019.
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
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2019/7/5 14:17
 * ---------------------------------------------------------------------------
 */
public interface IModelColumnPluginHook {
    /**
     * Model Column 枚举生成
     * @param innerEnum
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    boolean modelColumnEnumGenerated(InnerEnum innerEnum, TopLevelClass topLevelClass, IntrospectedTable introspectedTable);
}
