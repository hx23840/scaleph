import {ModalFormProps} from '@/app.d';
import {DICT_TYPE} from '@/constant';
import {DictDataService} from '@/services/admin/dictData.service';
import {DataSourceService} from '@/services/project/dataSource.service';
import {JobService} from '@/services/project/job.service';
import {DiJob, MetaDataSourceParam} from '@/services/project/typings';
import {NsGraph} from '@antv/xflow';
import {Button, Col, Form, message, Modal, Space} from 'antd';
import {useEffect} from 'react';
import {getIntl, getLocale} from 'umi';
import {JdbcParams, STEP_ATTR_TYPE} from '../../constant';
import {
  ProForm,
  ProFormDependency,
  ProFormDigit,
  ProFormGroup,
  ProFormSelect,
  ProFormSwitch,
  ProFormText,
  ProFormTextArea
} from "@ant-design/pro-components";
import {InfoCircleOutlined} from "@ant-design/icons";

const SinkJdbcStepForm: React.FC<ModalFormProps<{
  node: NsGraph.INodeConfig;
  graphData: NsGraph.IGraphData;
  graphMeta: NsGraph.IGraphMeta;
}>> = ({data, visible, onCancel, onOK}) => {
  const nodeInfo = data.node.data;
  const jobInfo = data.graphMeta.origin as DiJob;
  const jobGraph = data.graphData;
  const intl = getIntl(getLocale(), true);
  const [form] = Form.useForm();

  useEffect(() => {
    form.setFieldValue(STEP_ATTR_TYPE.stepTitle, nodeInfo.data.displayName);
  }, []);

  return (
    <Modal
      open={visible}
      title={nodeInfo.data.displayName}
      width={780}
      bodyStyle={{overflowY: 'scroll', maxHeight: '640px'}}
      destroyOnClose={true}
      onCancel={onCancel}
      onOk={() => {
        form.validateFields().then((values) => {
          let map: Map<string, any> = new Map();
          map.set(STEP_ATTR_TYPE.jobId, jobInfo.id + '');
          map.set(STEP_ATTR_TYPE.jobGraph, JSON.stringify(jobGraph));
          map.set(STEP_ATTR_TYPE.stepCode, nodeInfo.id);
          map.set(STEP_ATTR_TYPE.stepAttrs, values);
          JobService.saveStepAttr(map).then((resp) => {
            if (resp.success) {
              message.success(intl.formatMessage({id: 'app.common.operate.success'}));
              onCancel();
              onOK ? onOK() : null;
            }
          });
        });
      }}
    >
      <ProForm form={form} initialValues={nodeInfo.data.attrs} grid={true} submitter={false}>
        <ProFormText
          name={STEP_ATTR_TYPE.stepTitle}
          label={intl.formatMessage({id: 'pages.project.di.step.stepTitle'})}
          rules={[{required: true}, {max: 120}]}
        />
        <ProFormSelect
          name={"dataSourceType"}
          label={intl.formatMessage({id: 'pages.project.di.step.jdbc.dataSourceType'})}
          colProps={{span: 6}}
          initialValue={"Mysql"}
          allowClear={false}
          request={(() => {
            return DictDataService.listDictDataByType(DICT_TYPE.datasourceType);
          })}
        />
        <ProFormSelect
          name={JdbcParams.dataSource}
          label={intl.formatMessage({id: 'pages.project.di.step.jdbc.dataSource'})}
          rules={[{required: true}]}
          colProps={{span: 18}}
          dependencies={["dataSourceType"]}
          request={((params, props) => {
            const param: MetaDataSourceParam = {
              datasourceName: params.keyWords,
              datasourceType: params.dataSourceType
            };
            return DataSourceService.listDataSourceByPage(param).then((response) => {
              return response.data.map((item) => {
                return {label: item.datasourceName, value: item.id, item: item};
              });
            });
          })}
        />
        <ProFormDigit
          name={JdbcParams.connectionCheckTimeoutSec}
          label={intl.formatMessage({id: 'pages.project.di.step.jdbc.connectionCheckTimeoutSec'})}
          fieldProps={{
            min: 0
          }}
        />
        <ProFormDigit
          name={JdbcParams.batchSize}
          label={intl.formatMessage({id: 'pages.project.di.step.jdbc.batchSize'})}
          tooltip={{
            title: intl.formatMessage({id: 'pages.project.di.step.jdbc.batch.tooltip'}),
            icon: <InfoCircleOutlined/>,
          }}
          colProps={{span: 8}}
          initialValue={300}
          fieldProps={{
            min: 0,
            step: 100
          }}
        />
        <ProFormDigit
          name={JdbcParams.batchIntervalMs}
          label={intl.formatMessage({id: 'pages.project.di.step.jdbc.batchIntervalMs'})}
          tooltip={{
            title: intl.formatMessage({id: 'pages.project.di.step.jdbc.batch.tooltip'}),
            icon: <InfoCircleOutlined/>,
          }}
          colProps={{span: 8}}
          initialValue={1000}
          fieldProps={{
            min: 0,
            step: 100
          }}
        />
        <ProFormDigit
          name={JdbcParams.maxRetries}
          label={intl.formatMessage({id: 'pages.project.di.step.jdbc.maxRetries'})}
          colProps={{span: 8}}
          initialValue={3}
          fieldProps={{
            min: 0
          }}
        />

        <ProFormSwitch
          name={"is_exactly_once"}
          label={intl.formatMessage({id: 'pages.project.di.step.jdbc.isExactlyOnce'})}
          tooltip={{
            title: intl.formatMessage({id: 'pages.project.di.step.jdbc.isExactlyOnce.tooltip'}),
            icon: <InfoCircleOutlined/>,
          }}
          initialValue={false}
        />
        <ProFormDependency name={["is_exactly_once"]}>
          {({is_exactly_once}) => {
            if (is_exactly_once) {
              return (
                <ProFormGroup>
                  <ProFormText
                    name={JdbcParams.xaDataSourceClassName}
                    label={intl.formatMessage({id: 'pages.project.di.step.jdbc.xaDataSourceClassName'})}
                    rules={[{required: true}]}
                  />
                  <ProFormDigit
                    name={JdbcParams.maxCommitAttempts}
                    label={intl.formatMessage({id: 'pages.project.di.step.jdbc.maxCommitAttempts'})}
                    colProps={{span: 12}}
                    initialValue={3}
                    fieldProps={{
                      min: 0
                    }}
                  />
                  <ProFormDigit
                    name={JdbcParams.transactionTimeoutSec}
                    label={intl.formatMessage({id: 'pages.project.di.step.jdbc.transactionTimeoutSec'})}
                    tooltip={{
                      title: intl.formatMessage({id: 'pages.project.di.step.jdbc.transactionTimeoutSec.tooltip'}),
                      icon: <InfoCircleOutlined/>,
                    }}
                    colProps={{span: 12}}
                    initialValue={-1}
                    fieldProps={{
                      min: -1
                    }}
                  />
                </ProFormGroup>
              );
            }
            return <ProFormGroup/>;
          }}
        </ProFormDependency>

        <ProFormTextArea
          name={JdbcParams.query}
          label={intl.formatMessage({id: 'pages.project.di.step.jdbc.query'})}
          rules={[{required: true}]}
          colProps={{span: 20}}
          fieldProps={{
            rows: 8
          }}
        />
        <Col span={4}>
          <Space direction="vertical" style={{paddingTop: '28px', width: '100%'}}>
            <Button
              type="default"
              block
              onClick={() => alert('comming soon ...')}
            >
              {intl.formatMessage({id: 'pages.project.di.step.jdbc.getsql'})}
            </Button>
            <Button
              type="default"
              block
              onClick={() => alert('comming soon ...')}
            >
              {intl.formatMessage({id: 'pages.project.di.step.jdbc.preview'})}
            </Button>
          </Space>
        </Col>
      </ProForm>
    </Modal>
  );
};

export default SinkJdbcStepForm;
