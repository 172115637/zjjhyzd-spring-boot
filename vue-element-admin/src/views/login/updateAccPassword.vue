<template>
  <div class="app-container">
  	<div class="password-box">
  		<el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="120px" >
        <el-form-item label="原密码：" prop="oldPassword">
          <el-input v-model="temp.oldPassword" style="width:80%;" type="password" />
        </el-form-item>
        <el-form-item label="新密码：" prop="newPassword">
          <el-input v-model="temp.newPassword" style="width:80%;"  type="password" />
        </el-form-item>
        <el-form-item label="确认新密码：" prop="reNewPassword">
          <el-input v-model="temp.reNewPassword" style="width:80%;" type="password" />
        </el-form-item>
       
      </el-form>
      <div slot="footer" class="dialog-footer"  >
        <el-button type="primary" @click="editPwd()" >修改密码</el-button>
      </div>
  	</div>
      
      
  </div>
</template>

<script>
import {editPassword} from '@/api/login'
import waves from '@/directive/waves' // Waves directive
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  filters: {
  },
  data() {
    return {
      tableKey: 0,
      list: [],
      total: 100,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 10,
        params: {}
      },
      temp: {
        usersId:'',
        oldPassword:'',
        newPassword:'',
        reNewPassword:''
      },
      rules: {
        oldPassword: [{ required: true, message: '请输入原密码', trigger: 'change' }],
        newPassword: [{ required: true, message: '请输入新密码', trigger: 'change' }],
        reNewPassword: [{ required: true, message: '请再次输入新密码', trigger: 'change' }]
      },
      dialogStatus: '',
      usersOptions:[],
      dialogFormVisible: false,
      dialogFormVisible2: false
    }
  },
  created() {
  },
  methods: {
  	clearTemp() {
  		this.temp = {
        usersId:'',
        oldPassword:'',
        newPassword:'',
        reNewPassword:''
      }
  	},
    editPwd() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
        	if(this.temp.reNewPassword!=this.temp.newPassword){
        		this.$message({
		          showClose: true,
		          message: '你输入的两次密码不一致',
		          type: 'error'
		        });
		        return;
        	}
          editPassword(this.temp).then((res) => {
          	if (res.data.success) {
          		this.$message({
		            message: '修改成功',
		            type: 'success',
		          })
          		this.$store.dispatch('FedLogOut').then(() => {
				        location.reload()// In order to re-instantiate the vue-router object to avoid bugs
				      })
          		this.clearTemp();
          	} else{
          		this.$message({
		          showClose: true,
		          message: res.data.msg,
		          type: 'error'
		        });
          	}
        	})
        }
      })
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
	.password-box{
		/*box-shadow: 0 2px 10px rgba(0,0,0,0.15);*/
		padding:40px 20px;
		width: 550px;
		position: absolute;
		/*top:50%;
		left:50%;
		margin-left: -300px;
		margin-top: -280px;*/
		.dialog-footer{
			text-align: left;
			padding-left:170px;
			margin-top: 40px;
		}
	}
</style>