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

package cn.sliew.scaleph.workflow.service.convert;

import cn.sliew.milky.common.util.JacksonUtil;
import cn.sliew.scaleph.common.convert.BaseConvert;
import cn.sliew.scaleph.dao.entity.master.workflow.WorkflowDefinitionVO;
import cn.sliew.scaleph.dao.entity.master.workflow.WorkflowSchedule;
import cn.sliew.scaleph.workflow.service.dto.WorkflowDefinitionDTO;
import cn.sliew.scaleph.workflow.service.dto.WorkflowScheduleDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

@Mapper(uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkflowDefinitionVOConvert extends BaseConvert<WorkflowDefinitionVO, WorkflowDefinitionDTO> {
    WorkflowDefinitionVOConvert INSTANCE = Mappers.getMapper(WorkflowDefinitionVOConvert.class);

    @Override
    default WorkflowDefinitionVO toDo(WorkflowDefinitionDTO dto) {
        WorkflowDefinitionVO entity = new WorkflowDefinitionVO();
        BeanUtils.copyProperties(dto, entity);
        if (CollectionUtils.isEmpty(dto.getParam()) == false) {
            entity.setParam(JacksonUtil.toJsonString(dto.getParam()));
        }
        WorkflowSchedule schedule = WorkflowScheduleConvert.INSTANCE.toDo(dto.getSchedule());
        entity.setSchedule(schedule);
        return entity;
    }

    @Override
    default WorkflowDefinitionDTO toDto(WorkflowDefinitionVO entity) {
        WorkflowDefinitionDTO dto = new WorkflowDefinitionDTO();
        BeanUtils.copyProperties(entity, dto);
        if (StringUtils.hasText(entity.getParam())) {
            dto.setParam(JacksonUtil.parseJsonString(entity.getParam(), new TypeReference<Map<String, Object>>() {
            }));
        }
        WorkflowScheduleDTO schedule = WorkflowScheduleConvert.INSTANCE.toDto(entity.getSchedule());
        dto.setSchedule(schedule);
        return dto;
    }
}
