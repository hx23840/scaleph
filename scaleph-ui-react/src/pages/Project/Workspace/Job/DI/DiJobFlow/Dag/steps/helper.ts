import {KafkaParams} from "@/pages/Project/Workspace/Job/DI/DiJobFlow/Dag/constant";

export const StepSchemaService = {

  formatSchema: (values: Record<string, any>) => {
    const fields: Record<string, any> = {}
    values.fields?.forEach(function (item: Record<string, any>) {
      fields[item.field] = item.type;
    });
    values.schema = JSON.stringify({fields: fields})
    return values
  },

  formatFields: (values: Record<string, any>) => {
    const fields: Record<string, any> = {}
    values.fieldArray?.forEach(function (item: Record<string, any>) {
      fields[item.field] = item.type;
    });
    values.fields = JSON.stringify(fields)
    return values
  },

  formatHeader: (values: Record<string, any>) => {
    const headers: Record<string, any> = {}
    values.headerArray?.forEach(function (item: Record<string, any>) {
      headers[item.header] = item.headerValue;
    });
    values.headers = JSON.stringify(headers)
    return values
  },

  formatParam: (values: Record<string, any>) => {
    const params: Record<string, any> = {}
    values.paramArray?.forEach(function (item: Record<string, any>) {
      params[item.param] = item.paramValue;
    });
    values.params = JSON.stringify(params)
    return values
  },

  formatUserIds: (values: Record<string, any>) => {
    const userIds: Array<any> = []
    values.mentionedArray?.forEach(function (item: any) {
      userIds.push(item.userId)
    });
    values.mentioned_list = JSON.stringify(userIds)
    return values
  },

  formatMobiles: (values: Record<string, any>) => {
    const mobiles: Array<any> = []
    values.mentionedMobileArray?.forEach(function (item: any) {
      mobiles.push(item.mobile)
    });
    values.mentioned_mobile_list = JSON.stringify(mobiles)
    return values
  },

  formatPositionMapping: (values: Record<string, any>) => {
    const mappings: Record<string, any> = {}
    values.queryParamPositionArray?.forEach(function (item: Record<string, any>) {
      mappings[item.field] = item.position;
    });
    values.queryParamPosition = JSON.stringify(mappings)
    return values
  },

  formatKafkaConf: (values: Record<string, any>) => {
    values.kafkaConf?.forEach(function (item: Record<string, any>) {
      values['kafka.' + item.key] = item.value;
    });
    return values
  },

  formatAssginPartitions: (values: Record<string, any>) => {
    const assignPartitions: Array<string> = []
    values.assignPartitionArray?.forEach(function (item: Record<string, any>) {
      assignPartitions.push(item.assignPartition)
    });
    values[KafkaParams.assignPartitions] = JSON.stringify(assignPartitions)
    return values
  },

  formatClickHouseConf: (values: Record<string, any>) => {
    values.clickhouse_conf?.forEach(function (item: Record<string, any>) {
      values['clickhouse.' + item.key] = item.value;
    });
    return values
  },

};
