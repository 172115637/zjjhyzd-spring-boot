
<template>
  <div class="login-container">
    <el-container>
      <!--<el-header>
        <img src="@/assets/login/logo_white.png" alt="logo">
      </el-header>-->
      <el-main>
        <div style="width: 730px;position: absolute; top: 50%; left: 50%; transform: translate(-50%,-60%);-webkit-transform: translate(-50%,-50%); box-shadow: 0 0 10px  rgba(0,0,0,0.13);">
          <el-row>
            <el-col
              :span="10"
              class="login-left-content">
              <div class="title-container">
                <img
                  src="@/assets/login/login_logo.png"
                  alt="img" >
              </div>
            </el-col>
            <el-col
              :span="14"
              class="login-right-content">
              <h4 style="color:#34A79E; text-align: center; margin-top: 50px; margin-bottom: 30px; font-size: 20px;">用户登录</h4>
              <el-form
                ref="loginForm"
                :model="loginForm"
                :rules="loginRules"
                class="login-form"
                auto-complete="on"
                label-position="left">
                <el-form-item
                  prop="username"
                  class="login-form-item" >
                  <span class="svg-container">
                    <svg-icon icon-class="user" />
                  </span>
                  <el-input
                    v-model="loginForm.username"
                    placeholder="请输入用户名"
                    name="username"
                    type="text"
                    auto-complete="off"
                    @keyup.enter.native="handleLogin"
                  />
                </el-form-item>
                <el-form-item
                  prop="password"
                  class="login-form-item" >
                  <span class="svg-container">
                    <svg-icon icon-class="password" />
                  </span>
                  <el-input
                    :type="passwordType"
                    v-model="loginForm.password"
                    placeholder="请输入密码"
                    name="password"
                    auto-complete="off"
                    show-password
                    @focus="pwdFocus"
                    @blur="pwdBlur"
                    @keyup.enter.native="handleLogin"
                  />
                </el-form-item>
                <el-row style="display:flex;align-items: center;">
                  <el-col :span="15">
                    <el-form-item
                      prop="regcode"
                      class="login-form-item" >
                      <span
                        class="svg-container"
                        style="font-size: 17px;">
                        <svg-icon icon-class="regcode" />
                      </span>
                      <el-input
                        v-model="loginForm.regcode"
                        placeholder="请输入验证码"
                        name="regcode"
                        type="text"
                        auto-complete="off"
                        style="width: 80%;"
                        @keyup.enter.native="handleLogin"
                      />
                    </el-form-item>
                  </el-col>
                  <el-col :span="9">
                    <div class="codeimg">
                      <!--<img src="@/assets/login/code.jpg" alt="code"/>-->
                      <div @click="refreshCode">
                        <reg-code :identify-code="identifyCode"/>
                      </div>
                    </div>
                  </el-col>
                </el-row>
                <el-row style="display:flex;align-items: center;">
                  <el-col :span="24">
                    <el-button
                      :loading="loading"
                      style="width:100%;"
                      @click.native.prevent="handleLogin">{{ $t('login.logIn') }}</el-button>
                  </el-col>
                  <!--<el-col :span="7" style="text-align: right;">
				        		<el-button type="text">忘记密码</el-button>
				        	</el-col>-->
                </el-row>

              </el-form>
            </el-col>
          </el-row>
        </div>
      </el-main>
      <el-footer>
        <div style="color: #999;text-align: center; font-size: 13px;">CopyRight © {{ year }}  上海领肤医疗科技有限公司</div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import Mock from 'mockjs'
import { isvalidUsername } from '@/utils/validate'
import LangSelect from '@/components/LangSelect'
import SocialSign from './socialsignin'
import RegCode from './regcode'
import { Loading } from 'element-ui'

export default {
	name: 'Login',
	components: {
		LangSelect,
		SocialSign,
		RegCode
	},
	data() {
		//用户名验证
		const validateUsername = (rule, value, callback) => {
//			if(!isvalidUsername(value)){
//				callback(new Error('用户名不存在'))
//			}else{
//				console.log('user',value)
//				callback()
//			}
			callback()
		}

		const validatePassword = (rule, value, callback) => {
			if(value.length < 6) {
				callback(new Error('请输入正确的密码'))
			} else {
				callback()
			}
		}
		//验证码验证
		const validateRegcode =(rule,value,callback)=>{
			if(value===''){
				callback(new Error('请输入验证码'))
			}else if(value.toUpperCase() !==this.identifyCode.toUpperCase()){
				// console.log('validateRegcode:',value)
				callback(new Error('验证码不正确！'))
			}else{
				callback()
			}
		}

		return {
			year:new Date().getFullYear(),
      focusFlag:true,
			fontstyle:{},
			loginForm: {
				username: '',
				password: '',
				regcode:''
			},
			identifyCodes:'1234567890',//+'ABCDEFGHIGKLMNOPQRSTUVWXYZ'+'abcdefghigklmnopqrstuvwxyz',
			identifyCode:'',
			loginRules: {
				username: [{
					required: true,
					trigger: 'blur',
//					validator: validateUsername
					message:'请输入用户名'
				}],
				password: [{
					required: true,
					trigger: 'blur',
//					validator: validatePassword
					message:'请输入密码'
				},{
					min:6,
					message:'密码长度最少为六位',
					trigger: 'blur'
				}],
				regcode:[{
					required: true,
					trigger: 'blur',
					validator: validateRegcode
				}],
			},
			passwordType: 'password',
			loading: false,
			showDialog: false,
			redirect: undefined
		}
	},
	watch: {
		$route: {
			handler: function(route) {
				this.redirect = route.query && route.query.redirect
			},
			immediate: true
		}
	},
	created() {
		// window.addEventListener('hashchange', this.afterQRScan)
	},

	mounted(){
		//验证码初始化
		this.identifyCode = ''
		this.makeCode(this.identifyCodes, 4)
	},

	destroyed() {
		// window.removeEventListener('hashchange', this.afterQRScan)
	},
	methods: {
    pwdFocus(e){
      console.log("聚焦")
      this.focusFlag=true
    },
    pwdBlur(e){
      console.log("失焦")
      this.focusFlag=false
    },
		showPwd() {
			if(this.passwordType === 'password') {
				this.passwordType = ''
			} else {
				this.passwordType = 'password'
			}
		},
		handleLogin() {
			this.$refs.loginForm.validate(valid => {
				if(valid) {
	        const loading = this.$loading({
	          lock: true,
	          text: 'Loading',
	          spinner: 'el-icon-loading',
	          background: 'rgba(0, 0, 0, 0)'
	        });
					this.$store.dispatch('LoginByUsername', this.loginForm).then(() => {
						loading.close();
						this.loading = false
						this.$router.push({
							path: this.redirect || '/'
						})
					}).catch((error) => {
						// console.log(error)
						loading.close();
						this.loading = false
					})
				} else {
					// console.log('error submit!!')
					loading.close();
					return false
				}
			})
		},
		//生成随机数
		randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    //切换验证码
    refreshCode(){
    	this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    //生成随机验证码
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        	this.identifyCode += this.identifyCodes[
          this.randomNum(0, this.identifyCodes.length)
        ]
      }
      console.log(this.identifyCode)
    }

	}
}
</script>

<style rel="stylesheet/scss" lang="scss">/* 修复input 背景不协调 和光标变色 */


/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#eee;
$cursor: #333;
@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
	.login-container .el-input input {
		color: $cursor;
		/*&::first-line {
			color: #666;
		}*/
	}
}


/* reset element-ui css */
.login-left-content{
		background:#f5f6f8;
		height: 428px;
		position: relative;
		.title-container{
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-52%,-50%);
			img{
				width: 68%;
			}
		}
	}
	.login-right-content{
		background:#fff;
		border: 1px solid #f0f0f0;
		height: 428px;
	}

.login-container {
	.el-input {
		display: inline-block;
		height: 47px;
		width: 85%;
		input {
			background: transparent !important;
			border: 0px;
			-webkit-appearance: none;
			border-radius: 0px;
			padding: 12px 5px 12px 15px;
			color: $light_gray;
			height: 47px;
			color:$cursor;
			caret-color: $cursor;
			&:-webkit-autofill {
				-webkit-box-shadow: 0 0 0px 1000px  #f2f2f2 inset !important;
				/*-webkit-text-fill-color: transparent !important;*/
			}
		}
	}

	.el-main {
		height: 95vh;
		overflow: hidden;
	}
	.el-footer {
		height: 30px !important;
		line-height: 30px;
	}

}</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#808080;
$light_gray:#eee;
.login-container {
	min-height: 100%;
	width: 100%;
	// background-color: $bg;
	background-image: linear-gradient( #fdfbfb 0%, #ebedee 100%);
	/* background:url(../../assets/login/login_bg.jpg) no-repeat;
  background-size:cover;*/
	overflow: hidden;
	.login-form {
		/*position: relative;
    width: 520px;
    max-width: 100%;
    margin: 0 auto;*/
    padding:0 60px 45px;
		overflow: hidden;
	}
	.tips {
		font-size: 14px;
		color: #fff;
		margin-bottom: 10px;
		span {
			&:first-of-type {
				margin-right: 16px;
			}
		}
	}
	.svg-container {
		padding: 6px 5px 6px 15px;
		color: $dark_gray;
		vertical-align: middle;
		width: 30px;
		display: inline-block;
	}
	.title-container {
		position: relative;
		text-align: center;
		margin-bottom: 20px;
		.title {
			font-size: 20px;
			color: #333;
			margin: 0px auto 30px auto;
			text-align: left;
			font-weight: bold;
		}
		.set-language {
			color: #fff;
			position: absolute;
			top: 5px;
			right: 0px;
		}
		.container-logo {
			width: 180px;
			margin: 0 auto;
			display: block;
			margin-bottom: 30px;
			margin-top: 50px;
		}
	}
	.show-pwd {
		position: absolute;
		right: 10px;
		top: 7px;
		font-size: 16px;
		color: $dark_gray;
		cursor: pointer;
		user-select: none;
	}
	.thirdparty-button {
		position: absolute;
		right: 0;
		bottom: 6px;
	}
	.codeimg {
		text-align: right;
		padding-left: 10px;
		margin-bottom: 20px;
		img {
			width: 100%;
			height: auto;
			max-width: 100%;
			display: inline-block;
			margin-bottom: 22px;
		}
	}
	.el-button--medium {
		padding: 15px 20px;
		font-size: 15px;
	}
	.login-img {
		text-align: center;
		width: 100%;
		.login-banner {
			display: inline-block;
			width: 100%;
			max-width: 100%;
			height: auto;
		}
	}
}
.el-button{ background-color:#34A79E ; border-color: #34A79E; color: #fff !important; margin-top:20px; }
.el-button:hover{ background: #23c9c4;}
input{background-color: transparent !important;}

/*.el-input::-webkit-scrollbar-corner,.el-input::-webkit-scrollbar-thumb,.el-input::-webkit-scrollbar-track{background-color:#363636 !important; color: #fff;}*/
</style>
<style type="text/css">
	.login-form-item{position: relative;}
	.login-form-item:before {
		content: '';
		width: 100%;
		position: absolute;
		left: 0;
		right: 0;
		bottom: 0;
		height: 1px;
		background: #4d4d4d;
		transform:scaleY(0.5) ;
	}
	.el-form-item__error{margin-top: 5px;}
	.login-container .el-input input:-webkit-autofill{
	-webkit-text-fill-color: #333 !important;
	-webkit-box-shadow: 0 0 0px 1000px transparent  inset !important;
    background-color:transparent;
    background-image: none;
     transition: background-color 50000s ease-in-out 0s;}
.login-container .el-input input {
	 background-color:transparent;
}
</style>
