<!--
 * @Descripttion:
 * @version: 1.0.0
 * @Author: lizhixiang
 * @Email: admin@zjjhyzd.com
 * @Date: 2021-01-04 18:12:07
 * @LastEditors: lizhixiang
 * @LastEditTime: 2021-01-05 19:44:39
-->
<template>
  <div style="display: inline-block">
    <div @click="showDialog()"><slot name="default" /></div>

    <el-dialog :visible.sync="dialogVisible" :before-close="handleClose" center title="渠道新增" :width="form.superiorChannelNo ? '50%' : '30%'">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px" label-position="left">
        <el-row>
          <el-col :span="form.superiorChannelNo ? 11 : 24">
            <el-form-item label="渠道编号" prop="channelNo"><el-input v-model="form.channelNo" /></el-form-item>
          </el-col>
          <el-col :span="form.superiorChannelNo ? 11 : 24" :offset="form.superiorChannelNo ? 2 : 0">
            <el-form-item label="渠道名称" prop="channelName"><el-input v-model="form.channelName" /></el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="上级名称" v-if="form.parentChannelName"><el-input v-model="form.parentChannelName" disabled /></el-form-item>
          </el-col>
          <el-col :span="11" :offset="2">
            <el-form-item label="上级编号" v-if="form.superiorChannelNo"><el-input v-model="form.superiorChannelNo" disabled /></el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="渠道类型" prop="channelType">
          <el-select v-model="form.channelType" placeholder="请选择渠道类型" clearable style="width: 100%;" :disabled="hasChannelType">
            <el-option label="销售渠道" :value="1" />
            <el-option label="会员渠道" :value="2" />
            <el-option label="品牌渠道" :value="3" />
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import service from '@/api/channel';
export default {
  name: 'ChannelAdd',
  // eslint-disable-next-line vue/require-prop-types
  props: ['channel_type', 'item'],
  data() {
    return {
      dialogVisible: false,
      form: {
        channelNo: '',
        channelName: '',
        channelType: '',
        parentChannelName: '',
        superiorChannelId: null,
        superiorChannelNo: null
      },
      rules: {
        channelNo: [
          { required: true, message: '请输入渠道编号', trigger: 'blur' },
          {
            min: 1,
            max: 20,
            message: '长度在 1 到 20 个字符',
            trigger: 'blur'
          }
        ],
        channelName: [
          { required: true, message: '请输入渠道名称', trigger: 'blur' },
          {
            min: 1,
            max: 20,
            message: '长度在 1 到 20 个字符',
            trigger: 'blur'
          }
        ],
        channelType: [{ required: true, message: '请选择渠道类型', trigger: 'blur' }]
      },
      hasChannelType: false
    };
  },
  methods: {
    showDialog() {
      this.form.channelType = this.$props.item.channelType;
      this.hasChannelType = this.form.channelType ? true : false;
      this.form.superiorChannelId = this.$props.item.superiorChannelId;
      this.form.superiorChannelNo = this.$props.item.superiorChannelNo;
      this.form.parentChannelName = this.$props.item.parentChannelName;
      this.dialogVisible = true;
      this.$refs['form'].clearValidate()
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        // eslint-disable-next-line no-unused-vars
        .then(_ => {
          done();
        })
        // eslint-disable-next-line no-unused-vars
        .catch(_ => {
        });
    },
    onSubmit() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.form.operationType = '0';
          service.save(this.form).then(res => {
            if (res.data.code === 20000) {
              this.$message({
                message: '添加成功',
                type: 'success'
              });
              this.dialogVisible = false;
              this.$emit('load', this.form.channelType);
              this.form = {
                channelNo: '',
                channelName: '',
                channelType: '',
                superiorChannelId: null,
                superiorChannelNo: null
              };
            }
          });
        }
      });
    }
  }
};
</script>

<style scoped></style>
