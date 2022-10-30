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

package cn.sliew.scaleph.core.di.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.sliew.scaleph.core.di.service.dto.DiJobAttrDTO;

/**
 * <p>
 * 数据集成-作业参数 服务类
 * </p>
 *
 * @author liyu
 * @since 2022-03-10
 */
public interface DiJobAttrService {

    List<DiJobAttrDTO> listJobAttr(Long jobId);

    int upsert(DiJobAttrDTO jobAttrDTO);

    int deleteByProjectId(Collection<? extends Serializable> projectIds);

    int deleteByJobId(Collection<? extends Serializable> jobIds);

    int clone(Long sourceJobId, Long targetJobId);
}
