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

package cn.sliew.scaleph.core.di.service.dto;

import cn.sliew.scaleph.common.dict.job.JobStatus;
import cn.sliew.scaleph.common.dict.job.JobType;
import cn.sliew.scaleph.common.dict.job.RuntimeState;
import cn.sliew.scaleph.common.dto.BaseDTO;
import cn.sliew.scaleph.core.di.service.vo.JobGraphVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 数据集成-作业信息
 * </p>
 *
 * @author liyu
 * @since 2022-03-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "作业信息", description = "数据集成-作业信息")
public class DiJobDTO extends BaseDTO {

    private static final long serialVersionUID = -4161534628783250968L;

    @NotNull
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "作业编码")
    private Long jobCode;

    @NotBlank
    @Length(min = 1, max = 200)
    @ApiModelProperty(value = "作业名称")
    private String jobName;

    @ApiModelProperty(value = "作业类型 实时、离线")
    private JobType jobType;

    @ApiModelProperty(value = "作业状态 草稿、发布、归档")
    private JobStatus jobStatus;

    @ApiModelProperty(value = "作业版本号")
    private Integer jobVersion;

    @Length(max = 200)
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "作业属性信息")
    private List<DiJobAttrDTO> jobAttrList;

    @ApiModelProperty(value = "作业连线信息")
    private List<DiJobLinkDTO> jobLinkList;

    @ApiModelProperty(value = "步骤信息")
    private List<DiJobStepDTO> jobStepList;

    @ApiModelProperty(value = "作业图信息")
    private JobGraphVO jobGraph;
}
