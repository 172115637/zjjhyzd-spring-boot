<!--
 * @Descripttion:
 * @version: 1.0.0
 * @Author: lizhixiang
 * @Email: admin@zjjhyzd.com
 * @Date: 2021-01-04 18:12:07
 * @LastEditors: lizhixiang
 * @LastEditTime: 2021-01-05 20:06:20
-->
<template>
  <div style="display: inline-block">
    <div @click="showDialog()">
      <slot name="default"/>
    </div>

    <el-dialog
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      title="渠道修改"
      width="50%"
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-position="left"
        label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item
              label="渠道编号"
              prop="channelNo" >
              <el-input
                v-model="form.channelNo"
                disabled/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label="渠道名称"
              prop="channelName">
              <el-input v-model="form.channelName"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item
          label="渠道类型"
          prop="channelType">
          <el-select
            v-model="form.channelType"
            placeholder="请选择活动区域">
            <el-option
              :value="1"
              label="销售渠道"/>
            <el-option
              :value="2"
              label="会员渠道"/>
            <el-option
              :value="3"
              label="品牌渠道"/>
          </el-select>
        </el-form-item>
        <el-form-item label="上级渠道名称">
          <el-input
            v-model="form.parentChannelName"
            disabled/>
        </el-form-item>
        <el-form-item label="上级渠道编号">
          <el-input
            v-model="form.superiorChannelNo"
            disabled/>
        </el-form-item>
      </el-form>
      <span
        slot="footer"
        class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="onSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import service from "@/api/channel";
export default {
  name: "ChannelEdit",
  // eslint-disable-next-line vue/require-prop-types
  props: ["channel_type", "item"],
  data() {
    return {
      dialogVisible: false,
      form: {
        channelNo: "",
        channelName: "",
        channelType: "",
        parentChannelName: "",
        superiorChannelId: null,
        superiorChannelNo: null,
      },
      rules: {
        channelNo: [
          { required: true, message: "请输入渠道编号", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            trigger: "blur",
          },
        ],
        channelName: [
          { required: true, message: "请输入渠道名称", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            trigger: "blur",
          },
        ],
        channelType: [
          { required: true, message: "请选择渠道类型", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    showDialog() {
      this.form = this.$props.item;
      this.dialogVisible = true;
      this.$refs['form'].clearValidate()
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        // eslint-disable-next-line no-unused-vars
        .then((_) => {
          done();
        })
        // eslint-disable-next-line no-unused-vars
        .catch((_) => {});
    },
    onSubmit() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
           this.form.operationType = "1";
           this.form.children= null
          service.save(this.form).then((res) => {
            if (res.data.code === 20000) {
              this.$message({
                message: "修改成功",
                type: "success",
              });
              this.dialogVisible = false;
              this.$emit("load", this.form.channelType);
              this.form = {
                channelNo: "",
                channelName: "",
                channelType: "",
                superiorChannelId: null,
                superiorChannelNo: null,
              };
            }
          });
        }
      });
    },
  },
};
</script>

<style scoped>
</style>
