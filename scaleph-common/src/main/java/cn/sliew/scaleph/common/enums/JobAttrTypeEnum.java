/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.sliew.scaleph.common.enums;

import lombok.Getter;

/**
 * @author gleiyu
 */
@Getter
@Deprecated
public enum JobAttrTypeEnum {

    JOB_ATTR("1", "作业变量"),
    JOB_PROP("2", "作业属性"),
    ENGINE_PROP("3", "引擎属性"),
    ;

    private String value;
    private String label;

    JobAttrTypeEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
