import Mock from 'mockjs'
import loginAPI from './login'
import articleAPI from './article'
import remoteSearchAPI from './remoteSearch'
import transactionAPI from './transaction'
import roleAPI from './role'
import newsAPI from './news'
import bookingAPI from './booking'
import userAPI from './user'
import doctorAPI from './expert'
import itemAPI from './orderSet'

// 修复在使用 MockJS 情况下，设置 withCredentials = true，且未被拦截的跨域请求丢失 Cookies 的问题
// https://github.com/nuysoft/Mock/issues/300
Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send
Mock.XHR.prototype.send = function() {
  if (this.custom.xhr) {
    this.custom.xhr.withCredentials = this.withCredentials || false
  }
  this.proxy_send(...arguments)
}

// Mock.setup({
//   timeout: '350-600'
// })

// 登录相关
//Mock.mock(/\/login\/login/, 'post', loginAPI.loginByUsername)
//Mock.mock(/\/login\/logout/, 'post', loginAPI.logout)
//Mock.mock(/\/user\/info\.*/, 'get', loginAPI.getUserInfo)

// 文章相关
Mock.mock(/\/article\/list/, 'get', articleAPI.getList)
Mock.mock(/\/article\/detail/, 'get', articleAPI.getArticle)
Mock.mock(/\/article\/pv/, 'get', articleAPI.getPv)
Mock.mock(/\/article\/create/, 'post', articleAPI.createArticle)
Mock.mock(/\/article\/update/, 'post', articleAPI.updateArticle)

// 搜索相关
Mock.mock(/\/search\/user/, 'get', remoteSearchAPI.searchUser)

// 账单相关
Mock.mock(/\/transaction\/list/, 'get', transactionAPI.getList)
// 角色相关
Mock.mock(/\/role\/getRoleList/, 'get', roleAPI.getList)
Mock.mock(/\/role\/updateRoleStatus/, 'put', roleAPI.modifyRoleStatus)
Mock.mock(/\/role\/updateRole/, 'put', roleAPI.updateRole)
Mock.mock(/\/role\/createRole/, 'post', roleAPI.createRole)
// 账户相关
Mock.mock(/\/account\/getAccountList/, 'get', roleAPI.getAccountList)
Mock.mock(/\/account\/updateAccountStatus/, 'put', roleAPI.modifyAccountStatus)
Mock.mock(/\/account\/updateAccount/, 'put', roleAPI.updateAccount)
Mock.mock(/\/account\/createAccount/, 'post', roleAPI.createAccount)
Mock.mock(/\/account\/updateAccountPassWorld/, 'post', roleAPI.resetAccount)
// 资讯相关
Mock.mock(/\/news\/tagList/, 'get', newsAPI.fetchTagList)
Mock.mock(/\/news\/tagAdd/, 'post', newsAPI.executeCreateTag)
Mock.mock(/\/news\/tagEdit/, 'put', newsAPI.executeUpdateTag)
Mock.mock(/\/news\/tagDel/, 'delete', newsAPI.executeDelTag)
Mock.mock(/\/news\/categoryList/, 'get', newsAPI.fetchCategoryList)
Mock.mock(/\/news\/categoryAdd/, 'post', newsAPI.executeCreateCategory)
Mock.mock(/\/news\/categoryEdit/, 'put', newsAPI.executeUpdateCategory)
Mock.mock(/\/news\/categoryDel/, 'delete', newsAPI.executeDelCategory)
Mock.mock(/\/news\/categoryOptions/, 'get', newsAPI.fetchCategoryOptions)
Mock.mock(/\/news\/tagOptions/, 'get', newsAPI.fetchTagOptions)
Mock.mock(/\/news\/newsList/, 'get', newsAPI.fetchNewsList)
Mock.mock(/\/news\/newsAdd/, 'post', newsAPI.executeCreateNews)
Mock.mock(/\/news\/newsEdit/, 'put', newsAPI.executeUpdateNews)
Mock.mock(/\/news\/newsDel/, 'delete', newsAPI.executeDelNews)
//检测与订单
// 专家列表查询
//Mock.mock(/\/booking\/expertList/, 'get', bookingAPI.fetchExpertList)
// 预约订单列表查询
//Mock.mock(/\/booking\/bookingList/, 'get', bookingAPI.fetchBookingList)
// 约见
//Mock.mock(/\/booking\/confirmYuejian/, 'post', bookingAPI.executeConfirmYuejian)
// 取消预约
//Mock.mock(/\/booking\/cancelBooking/, 'post', bookingAPI.executeCancelBooking)
// 采血
//Mock.mock(/\/booking\/confirmBlood/, 'post', bookingAPI.executeConfirmBlood)
// 发布报告
//Mock.mock(/\/booking\/releaseReport/, 'post', bookingAPI.executeReleaseReport)
// 检测项目列表
//Mock.mock(/\/booking\/checkItemList/, 'get', bookingAPI.fetchCheckItemList)
// 预约订单详情
//Mock.mock(/\/booking\/getBookingDetail/, 'get', bookingAPI.fetchBookingDetail)
// 更新预约检测订单
//Mock.mock(/\/booking\/updateBookingDetail/, 'post', bookingAPI.executeUpdateBookingDetail)
// 诊断等级列表
//Mock.mock(/\/booking\/getReportLevelList/, 'get', bookingAPI.fetchReportLevelList)
// 查询报告详情
//Mock.mock(/\/booking\/reportDetail/, 'get', bookingAPI.fetchReportDetail)
// 提交报告
//Mock.mock(/\/booking\/executeSubmitReport/, 'post', bookingAPI.executeSubmitReport)
// 专家解读报告
//Mock.mock(/\/booking\/executeSubmitUnscramble/, 'post', bookingAPI.executeSubmitUnscramble)
// 更新专家解读报告
//Mock.mock(/\/booking\/executeUpdateSubmitUnscramble/, 'post', bookingAPI.executeUpdateSubmitUnscramble)

// 检测项目专用
// Mock.mock(/\/booking\/itemList/, 'get', itemAPI.fetchItemList)
// Mock.mock(/\/booking\/itemAdd/, 'post', itemAPI.executeCreateItem)

//用户管理相关
Mock.mock(/\/user\/userList/, 'get', userAPI.fetchUserList)
Mock.mock(/\/user\/gradeList/, 'get', userAPI.fetchGradeOptions)
Mock.mock(/\/user\/labelList/, 'get', userAPI.fetchLabelOptions)
Mock.mock(/\/user\/userEdit/, 'put', userAPI.executeUpdateUser)
Mock.mock(/\/user\/userInviteEdit/, 'put', userAPI.executeUpdateUserinvite)

Mock.mock(/\/user\/userCollectionList/, 'get', userAPI.fetchUserCollectionList)
Mock.mock(/\/user\/collTypeList/, 'get', userAPI.fetchCollTypeOptions)
Mock.mock(/\/user\/collLabelList/, 'get', userAPI.fetchCollLabelOptions)

Mock.mock(/\/user\/userLabelList/, 'get', userAPI.fetchUserLabelList)
Mock.mock(/\/user\/newsList/, 'get', userAPI.fetchNewsOptions)
Mock.mock(/\/user\/userLabelEdit/, 'put', userAPI.executeUpdateUserLabel)
Mock.mock(/\/user\/userLabelAdd/, 'post', userAPI.executeCreateUserLabel)

Mock.mock(/\/user\/userGroupsList/, 'get', userAPI.fetchUserGroupsList)
Mock.mock(/\/user\/usersList/, 'get', userAPI.fetchUserOptions)
Mock.mock(/\/user\/userGroupsEdit/, 'put', userAPI.executeUpdateUserGroups)
Mock.mock(/\/user\/userGroupsConEdit/, 'put', userAPI.executeUpdateConfigurationUser)
Mock.mock(/\/user\/userGroupsAdd/, 'post', userAPI.executeCreateUserGroups)
//Mock.mock(/\/userMsg\/getGroupList/, 'get', userAPI.fetchGroupsOptions)

Mock.mock(/\/user\/userDetail/, 'get', userAPI.fetchUserDetail)
Mock.mock(/\/user\/detectionList/, 'get', userAPI.fetchDetectionList)

Mock.mock(/\/user\/userMessageList/, 'get', userAPI.fetchUserMessageList)
// Mock.mock(/\/user\/userMessageAdd/, 'post', userAPI.executeCreateUserMessage)

//专家管理相关
Mock.mock(/\/doctor\/doctorList/, 'get', doctorAPI.fetchDoctorList)
Mock.mock(/\/doctor\/doctorTitleList/, 'get', doctorAPI.fetchDoctorTitleOptions)
Mock.mock(/\/doctor\/subjectList/, 'get', doctorAPI.fetchSubjectOptions)
Mock.mock(/\/doctor\/departmentList/, 'get', doctorAPI.fetchDepartmentOptions)
Mock.mock(/\/doctor\/doctorEdit/, 'put', doctorAPI.executeUpdateDoctor)
Mock.mock(/\/doctor\/doctorAdd/, 'post', doctorAPI.executeCreateDoctor)

export default Mock
