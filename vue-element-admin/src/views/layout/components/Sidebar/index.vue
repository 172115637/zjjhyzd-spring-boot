<template>
  <el-scrollbar wrap-class="scrollbar-wrapper" >
    <el-menu
      :show-timeout="200"
      :default-active="$route.path"
      :collapse="isCollapse"
      mode="vertical"
      background-color="#304156"
      text-color="#bfcbd9"
      active-text-color="#fff"
    >
    	<div style="margin-bottom: 2px;padding: 10px 2px; text-align: center; height: 84px; background: #304156; box-shadow: 0px 0px 20px rgba(0,0,0,0.3);"> 
    		<img src="@/assets/images/logo_white.svg" alt="logo" class="logo-img">
    	</div>
    	<div style="padding-top: 20px; position: relative; z-index: 9;">
      <sidebar-item v-for="route in permission_routers" :key="route.path" :item="route" :base-path="route.path"  />
      </div>
    </el-menu>
  </el-scrollbar>
</template>

<script>
import { mapGetters } from 'vuex'
import SidebarItem from './SidebarItem'

export default {
  components: { SidebarItem },
  computed: {
    ...mapGetters([
      'permission_routers',
      'sidebar'
    ]),
    isCollapse() {
      return !this.sidebar.opened
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
	.logo-img{
		max-width: 100%;
		width: 90%;
	}
</style>
<style>
	 .el-menu-item.is-active {
      background:linear-gradient(#23c9c4, #34a79e)!important;
    } 
	#app .sidebar-container .scrollbar-wrapper .el-scrollbar__view:after{position: absolute;content: ''; background: #363636; width: 1px; right: 0px;top: 83px; bottom: 0; z-index: 20;}
	/*.el-submenu__title{padding-left: 10px !important;}*/
		/*.el-menu-item{padding: 0 10px!important;}*/
		/*#app .sidebar-container .el-submenu .el-menu-item{min-width: auto !important; width: 120px; margin: 0 auto 5px auto; }*/
		.el-menu-item,.el-submenu__title,.el-submenu .el-menu-item{line-height: 40px; height: 40px; /*margin: 0 10px;border-radius: 10px;margin-bottom: 5px;*/}
</style>
