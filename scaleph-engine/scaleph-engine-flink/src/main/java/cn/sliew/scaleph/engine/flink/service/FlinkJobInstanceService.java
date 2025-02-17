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

package cn.sliew.scaleph.engine.flink.service;

import cn.sliew.scaleph.dao.entity.master.flink.FlinkJobInstance;
import cn.sliew.scaleph.engine.flink.service.dto.FlinkJobInstanceDTO;
import cn.sliew.scaleph.engine.flink.service.param.FlinkJobInstanceListParam;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface FlinkJobInstanceService extends IService<FlinkJobInstance> {

    @Deprecated
    Page<FlinkJobInstanceDTO> list(FlinkJobInstanceListParam param);

    FlinkJobInstanceDTO selectByCode(Long flinkJobCode);

    FlinkJobInstanceDTO selectOne(Long id);

    boolean upsert(FlinkJobInstanceDTO dto);

    int insert(FlinkJobInstanceDTO dto);

    int update(FlinkJobInstanceDTO dto);

    FlinkJobInstanceDTO deleteById(Long id);

    int transferToLog(FlinkJobInstanceDTO dto);

}
