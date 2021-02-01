<!--
 * @Descripttion: 
 * @version: 1.0.0
 * @Author: lizhixiang
 * @Email: admin@zjjhyzd.com
 * @Date: 2021-01-06 09:32:14
 * @LastEditors: lizhixiang
 * @LastEditTime: 2021-01-06 15:36:37
-->
<template>
  <el-card class="box-card" style="height: 88vh; overflow: auto">
    <div slot="header" class="card-header">
      <span>{{ $props.title }}</span>
    </div>
    <div>
      <el-input
        placeholder="输入关键字进行过滤"
        v-model="filterText"
        style="margin-bottom: 10px"
      >
      </el-input>
      <el-tree
        :data="$props.treeData"
        :props="$props.defaultProps"
        node-key="channel_id"
        default-expand-all
        @node-click="handleNodeClick"
        ref="tree"
        :filter-node-method="$props.filterNode"
        :expand-on-click-node="false"
        v-loading="$props.loading"
      >
        <span class="custom-tree-node" slot-scope="{ node, data }">
          <span>{{ node[$props.nodeLabel] }}</span>
          <span v-if="(data.showAdd==null || data.showAdd) || (data.showEdit==null || data.showEdit)">
            <el-dropdown trigger="click" @command="handlerCommand">
              <i class="el-icon-more"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="add" v-if="data.showAdd==null || data.showAdd">新增下级</el-dropdown-item>
                <el-dropdown-item command="edit" v-if="data.showEdit==null || data.showEdit">编辑</el-dropdown-item>
                <el-dropdown-item command="del" v-if="data.showEdit==null || data.showEdit">删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </span>
        </span>
      </el-tree>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "CardTree",
  data() {
    return {
      filterText: "",
    };
  },
  props: [
    "loading",
    "defaultProps",
    "treeData",
    "filterNode",
    "title",
    "nodeLabel",
  ],
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    },
  },
  methods: {
    handleNodeClick(data) {
      this.$emit("node-click", data);
    },
    handlerCommand(val, node,) {
      this.$emit("command",val,node)
    },
  },
};
</script>

<style lang='scss' scoped>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>