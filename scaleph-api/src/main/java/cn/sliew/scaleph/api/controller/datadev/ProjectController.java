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

package cn.sliew.scaleph.api.controller.datadev;

import cn.sliew.scaleph.api.annotation.Logging;
import cn.sliew.scaleph.common.exception.ScalephException;
import cn.sliew.scaleph.core.di.service.DiProjectService;
import cn.sliew.scaleph.core.di.service.dto.DiProjectDTO;
import cn.sliew.scaleph.core.di.service.param.DiProjectParam;
import cn.sliew.scaleph.system.service.vo.DictVO;
import cn.sliew.scaleph.system.vo.ResponseVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author gleiyu
 */
@Slf4j
@Api(tags = "数据开发-项目管理")
@RestController
@RequestMapping(path = {"/api/datadev/project", "/api/di/project"})
public class ProjectController {

    @Autowired
    private DiProjectService diProjectService;

    @Logging
    @GetMapping
    @ApiOperation(value = "查询项目列表", notes = "分页查询项目列表")
    @PreAuthorize("@svs.validate(T(cn.sliew.scaleph.common.constant.PrivilegeConstants).DATADEV_PROJECT_SELECT)")
    public ResponseEntity<Page<DiProjectDTO>> listProject(DiProjectParam param) {
        Page<DiProjectDTO> page = diProjectService.listByPage(param);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @Logging
    @GetMapping(path = "/all")
    @ApiOperation(value = "查询所有项目列表", notes = "查询所有项目列表")
    @PreAuthorize("@svs.validate(T(cn.sliew.scaleph.common.constant.PrivilegeConstants).DATADEV_PROJECT_SELECT)")
    public ResponseEntity<List<DictVO>> listAll() {
        List<DictVO> result = diProjectService.listAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @Logging
    @PostMapping
    @ApiOperation(value = "新增项目", notes = "新增项目")
    @PreAuthorize("@svs.validate(T(cn.sliew.scaleph.common.constant.PrivilegeConstants).DATADEV_PROJECT_ADD)")
    public ResponseEntity<ResponseVO> addProject(@Validated @RequestBody DiProjectDTO diProjectDTO) {
        diProjectService.insert(diProjectDTO);
        return new ResponseEntity<>(ResponseVO.sucess(), HttpStatus.CREATED);
    }

    @Logging
    @PutMapping
    @ApiOperation(value = "修改项目", notes = "修改项目")
    @PreAuthorize("@svs.validate(T(cn.sliew.scaleph.common.constant.PrivilegeConstants).DATADEV_PROJECT_EDIT)")
    public ResponseEntity<ResponseVO> editProject(@Validated @RequestBody DiProjectDTO diProjectDTO) {
        diProjectService.update(diProjectDTO);
        return new ResponseEntity<>(ResponseVO.sucess(), HttpStatus.OK);
    }

    @Logging
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "删除项目", notes = "删除项目")
    @PreAuthorize("@svs.validate(T(cn.sliew.scaleph.common.constant.PrivilegeConstants).DATADEV_PROJECT_DELETE)")
    public ResponseEntity<ResponseVO> deleteProject(@PathVariable(value = "id") Long projectId) throws ScalephException {
        diProjectService.deleteById(projectId);
        return new ResponseEntity<>(ResponseVO.sucess(), HttpStatus.OK);
    }

    @Logging
    @PostMapping(path = "/batch")
    @ApiOperation(value = "批量删除项目", notes = "批量删除项目")
    @PreAuthorize("@svs.validate(T(cn.sliew.scaleph.common.constant.PrivilegeConstants).DATADEV_PROJECT_DELETE)")
    public ResponseEntity<ResponseVO> deleteProject(@RequestBody Map<Integer, Long> map) throws ScalephException {
        diProjectService.deleteBatch(map);
        return new ResponseEntity<>(ResponseVO.sucess(), HttpStatus.OK);
    }
}
