<!--
 * @Descripttion:
 * @version: 1.0.0
 * @Author: lizhixiang
 * @Email: admin@zjjhyzd.com
 * @Date: 2021-01-06 10:00:21
 * @LastEditors: lizhixiang
 * @LastEditTime: 2021-01-06 12:26:54
-->
<template>
  <div>
    <el-table
      v-loading="$props.loading"
      :data="$props.tableData"
      row-key="uuid"
      border
      stripe
      style="width: 100%"
    >
      <slot name="default"/>
    </el-table>
    <div style="margin-top: 10px">
      <el-pagination
        :current-page="$props.page"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="$props.pageSize"
        :total="$props.total"
        layout="total, prev, pager, next, jumper"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "CardTable",
  props: ["tableData", "row-key", "loading", "page", "pageSize", "total"],
  data() {
    return {};
  },
  methods: {
    handleSizeChange(val) {
      this.$emit("size-change", val);
    },
    handleCurrentChange(val) {
      this.$emit("current-change", val);
    },
  },
};
</script>

<style lang='scss' scoped>
</style>
