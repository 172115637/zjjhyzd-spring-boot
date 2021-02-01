<template>
	<div :class="{fullscreen:fullscreen}" class="tinymce-container editor-container">
		<textarea :id="tinymceId" class="tinymce-textarea" />
		<div class="editor-custom-btn-container">
			<div class="upload-container">
				<el-button icon="el-icon-upload" size="mini" type="primary" @click="startUploadImg">上传图片</el-button>
				<el-dialog title="上传图片" :visible.sync="uploadImgDialog" center>
					<el-upload :action="baseUrl + '/uploadImage'" :on-error="onError" :on-success="onSuccess" :before-upload="onBeforeUpload" :file-list="fileList" list-type="picture-card" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :data="filePaht" :limit="10" multiple>
						<i class="el-icon-plus"></i>
					</el-upload>
					<el-button type="primary" style="margin-top: 10px;" @click="aaaa">确 定</el-button>
				</el-dialog>
				<el-dialog title="查看图片" :visible.sync="dialogVisible" center>
					<img width="100%" :src="seleectModel.imgUrl" alt="">
				</el-dialog>
			</div>
		</div>

		<!--<editorImage color="#1890ff" class="editor-upload-btn" @successCBK="imageSuccessCBK"/>-->
	</div>
</template>

<script>
	import editorImage from './components/editorImage'
	import plugins from './plugins'
	import toolbar from './toolbar'

	export default {
		name: 'Tinymce',
		components: {
			editorImage
		},
		props: {
			id: {
				type: String,
				default: function() {
					return 'vue-tinymce-' + +new Date() + ((Math.random() * 1000).toFixed(0) + '')
				}
			},
			value: {
				type: String,
				default: ''
			},
			toolbar: {
				type: Array,
				required: false,
				default() {
					return []
				}
			},
			menubar: {
				type: String,
				default: 'file edit insert view format table'
			},
			height: {
				type: Number,
				required: false,
				default: 360
			}
		},
		data() {
			return {
				baseUrl: process.env.BASE_API,
				hasChange: false,
				hasInit: false,
				tinymceId: this.id,
				fullscreen: false,
				languageTypeList: {
					'en': 'en',
					'zh': 'zh_CN'
				},
				dialogVisible: false,
				fileName: '',
				filePaht: {
					path: ''
				},
				fileList: [],
				fileUrl:[],
				seleectModel: {
					imgUrl: ''
				},
				uploadImgDialog: false,
				loading: null,
			}
		},
		computed: {
			language() {
				return 'zh_CN'
			}
		},
		watch: {
			value(val) {
				if(!this.hasChange && this.hasInit) {
					this.$nextTick(() =>
						window.tinymce.get(this.tinymceId).setContent(val || ''))
				}
			},
			language() {
				this.destroyTinymce()
				this.$nextTick(() => this.initTinymce())
			}
		},
		mounted() {
			this.initTinymce()
		},
		activated() {
			this.initTinymce()
		},
		deactivated() {
			this.destroyTinymce()
		},
		destroyed() {
			this.destroyTinymce()
		},
		methods: {
			initTinymce() {
				const _this = this
				window.tinymce.init({
					language: this.language,
					selector: `#${this.tinymceId}`,
					height: this.height,
					body_class: 'panel-body ',
					object_resizing: false,
					toolbar: this.toolbar.length > 0 ? this.toolbar : toolbar,
					menubar: this.menubar,
					plugins: plugins,
					end_container_on_empty_block: true,
					powerpaste_word_import: 'clean',
					code_dialog_height: 450,
					code_dialog_width: 1000,
					advlist_bullet_styles: 'square',
					advlist_number_styles: 'default',
					imagetools_cors_hosts: ['www.tinymce.com', 'codepen.io'],
					default_link_target: '_blank',
					link_title: false,
					convert_urls: false,
					nonbreaking_force_tab: true, // inserting nonbreaking space &nbsp; need Nonbreaking Space Plugin
					init_instance_callback: editor => {
						if(_this.value) {
							editor.setContent(_this.value)
						}
						_this.hasInit = true
						editor.on('NodeChange Change KeyUp SetContent', () => {
							this.hasChange = true
							this.$emit('input', editor.getContent())
						})
					},
					setup(editor) {
						editor.on('FullscreenStateChanged', (e) => {
							_this.fullscreen = e.state
						})
					}
				})
			},
			destroyTinymce() {
				const tinymce = window.tinymce.get(this.tinymceId)
				if(this.fullscreen) {
					tinymce.execCommand('mceFullScreen')
				}

				if(tinymce) {
					tinymce.destroy()
				}
			},
			setContent(value) {
				window.tinymce.get(this.tinymceId).setContent(value)
			},
			getContent() {
				window.tinymce.get(this.tinymceId).getContent()
			},
			imageSuccessCBK(arr) {
				const _this = this
				arr.forEach(v => {
					window.tinymce.get(_this.tinymceId).insertContent(`<img class="wscnph" style="max-width:100%;height:auto;display:block;" src="${v.url}" >`)
				})
			},
			startUploadImg() {
				var that = this;
				// 重置参数 打开上传框
				that.fileName = ''
				that.filePaht = {
					path: ''
				}
				that.fileList = []
				that.fileUrl = []
				that.seleectModel = {
					imgUrl: ''
				};
				that.uploadImgDialog = true
			},
			aaaa(arr) {
				var _this = this;
				var arr = _this.fileUrl
				arr.forEach(v => {
					window.tinymce.get(_this.tinymceId).insertContent(`<img class="wscnph" style="width:100%;height:auto;display:block;" src="${_this.baseUrl + v.raw.url}" >`)
				})
				// 关闭窗口
				_this.uploadImgDialog = false
			},
			onSuccess(response, file, fileList){
				var urldata = {url:file.raw.url}
				this.fileUrl.push(urldata)
				if(this.fileUrl.length == fileList.length){
					this.fileUrl = fileList;
					this.loading.close();
				}
			},
			onError(err, file, fileList){
				if(this.fileUrl.length == fileList.length){
					this.loading.close();
				}
			},
			handleRemove(file, fileList) {
				this.seleectModel.imgUrl = '';
			},
			handlePictureCardPreview(file) {
				this.seleectModel.imgUrl = file.url;
				this.dialogVisible = true;
			},
			onBeforeUpload(file) {
				const _this = this
				_this.loading = this.$loading({
		          lock: true,
		          text: '上传中',
		          spinner: 'el-icon-loading',
		          background: 'rgba(0, 0, 0, 0.7)'
		        });
				const isJPG = file.type === 'image/jpeg';
				const isGIF = file.type === 'image/gif';
				const isPNG = file.type === 'image/png';
				const isBMP = file.type === 'image/bmp';
				const isLt2M = file.size / 1024 / 1024 < 2;
				var perfix = file.name.substring(file.name.lastIndexOf("."))
				var fileName = this.guid() + perfix;
				this.filePaht.path = fileName;
				var imgUrl = "/file/image/" + fileName;
				file.url = imgUrl
				this.seleectModel.imgUrl = imgUrl;
				return true
			},
			guid() {
				return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
					var r = Math.random() * 16 | 0,
						v = c == 'x' ? r : (r & 0x3 | 0x8);
					return v.toString(16);
				});
			}

		}
	}
</script>

<style scoped>
	.tinymce-container {
		position: relative;
		line-height: normal;
	}
	
	.tinymce-container>>>.mce-fullscreen {
		z-index: 10000;
	}
	
	.tinymce-textarea {
		visibility: hidden;
		z-index: -1;
	}
	
	.editor-custom-btn-container {
		position: absolute;
		right: 4px;
		top: 4px;
		/*z-index: 2005;*/
	}
	
	.fullscreen .editor-custom-btn-container {
		z-index: 10000;
		position: fixed;
	}
	
	.editor-upload-btn {
		display: inline-block;
	}
</style>