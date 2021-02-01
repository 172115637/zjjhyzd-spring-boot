<template>
	<div  v-loading="loading">
	 	<div style="min-height:78vh; position: relative;">
	 		<div style="position: absolute;left: 50%; top: 50%;  transform:translate(-50%, -30%); -webkit-transform: translate(-50%,-30%);">
	    		<img src="@/assets/images/login_bodybg.png" alt="img">
	    	</div>
	    </div>
  	</div>
	
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  components: {  },
  data() {
    return {
    	loading:false,
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
   mounted(){
      /**
      * iframe-宽高自适应显示   
      */
      function changeMobsfIframe(){
        const mobsf = document.getElementById('mobsf');
        const deviceWidth = document.body.clientWidth;
        const deviceHeight = document.body.clientHeight;
      //  mobsf.style.width = (Number(deviceWidth)-120) + 'px'; //数字是页面布局宽度差值
//      mobsf.style.height = (Number(deviceHeight)-80) + 'px'; //数字是页面布局高度差
      }
 
      changeMobsfIframe()
 
      window.onresize = function(){
        changeMobsfIframe()
      }
    },   
  created() {
  },
  methods: {
  }
}
</script>
<style>
/*.el-input__inner, .el-textarea__inner {
	background-color: #444444 !important;
	border-color: #444444 !important;
    color: #fff !important;
}*/
</style>