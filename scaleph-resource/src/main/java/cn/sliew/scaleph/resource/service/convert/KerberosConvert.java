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

package cn.sliew.scaleph.resource.service.convert;

import cn.sliew.scaleph.common.convert.BaseConvert;
import cn.sliew.scaleph.common.util.BeanUtil;
import cn.sliew.scaleph.dao.entity.master.resource.ResourceKerberos;
import cn.sliew.scaleph.resource.service.dto.KerberosDTO;
import cn.sliew.scaleph.resource.service.param.KerberosListParam;
import cn.sliew.scaleph.resource.service.param.ResourceListParam;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KerberosConvert extends BaseConvert<ResourceKerberos, KerberosDTO> {

    KerberosConvert INSTANCE = Mappers.getMapper(KerberosConvert.class);

    default KerberosListParam convert(ResourceListParam param) {
        KerberosListParam target = BeanUtil.copy(param, new KerberosListParam());
        target.setName(param.getLabel());
        target.setFileName(param.getName());
        return target;
    }
}
