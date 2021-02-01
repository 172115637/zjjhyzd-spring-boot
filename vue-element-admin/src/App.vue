<!--
 * @Descripttion:
 * @version: 1.0.0
 * @Author: lizhixiang
 * @Email: admin@zjjhyzd.com
 * @Date: 2021-01-04 16:42:52
 * @LastEditors: lizhixiang
 * @LastEditTime: 2021-01-08 19:13:32
-->
<template>
  <div id="app">
    <router-view :abc="123"/>
  </div>
</template>

<script>
export default{
  name: 'App'
}
</script>
<style lang="scss">
  .sort-no-icon{
    cursor: pointer;
  }
	.sort-no-icon:hover{
		color: red;
	}
</style>
