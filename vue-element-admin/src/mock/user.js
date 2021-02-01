import Mock from 'mockjs'
import { param2Obj } from '@/utils'

const List = []
const count = 10

/**
 * 用户列表
 */
// 用户列表页专用GradeList
const gradeOptions = []
for (let i = 0; i < 5; i++) {
  gradeOptions.push(Mock.mock({
    gradeId: i+1,
    gradeName: '用户等级'+(i+1),
  }))
}
// 用户列表页专用LabelList
const labelOptions = []
for (let i = 0; i < 5; i++) {
  labelOptions.push(Mock.mock({
    labelId: i+1,
    labelName: '标签'+(i+1),
  }))
}
// 用户列表专用UserList
const userList = []
const Random = Mock.Random;
for (let i = 0; i < count; i++) {
  userList.push(Mock.mock({
    emplId: '@increment',
    userName: '@first',
    emplName: '@first',
    emplPhone: '@first',
    'gradeId|1': [1, 2, 3, 4,5],
    gradeName:'用户等级'+i,
    labelId: [1, 4,5],
    labelName:'标签1,标签4,标签5',
    integral: '@integer(1, 1000)',
    reserveNum: '@integer(1, 10)',
    reserveMoney: '@integer(300, 100000)',
    'status|1': ['published', 'deleted'],
    'emplSex|1': ['0', '1'],
    collectNum: '@integer(1, 10)',
    invite: '@first'
  }))
}

/**
 * 用户收藏
 */
// 用户收藏页专用CollTypeList
const collTypeOptions = []
for (let i = 0; i < 5; i++) {
  collTypeOptions.push(Mock.mock({
    collTypeId: i+1,
    collTypeName: '分类'+(i+1),
  }))
}
// 用户收藏页专用CollLabelList
const collLabelOptions = []
for (let i = 0; i < 5; i++) {
  collLabelOptions.push(Mock.mock({
    collLabelId: i+1,
    collLabelName: '标签'+(i+1),
  }))
}
// 用户收藏专用userCollectionList
const userCollectionList = []
for (let i = 0; i < count; i++) {
  userCollectionList.push(Mock.mock({
    collId: '@increment',
    collTitle: '@first',
    'collTypeId|1': [1, 2, 3, 4,5],
    collTypeName:'收藏分类',
    'collLabelId|1': [1, 2, 3, 4,5],
    collLabelName:'收藏标签',
    imgUrl: '/static/tinymce4.7.5/plugins/emoticons/img/smiley-yell.gif',
    collContent: i+'Lorem ipsum dolor sit amet, cing elit. . Proin gravida dolor sit,dasjk asdjsafkjs dsfdsfkjdsa fjsakdhfdsf askjfdhsdkfs',
  }))
}

/**
 * 用户标签
 */
// 用户标签页专用newsList
const newsOptions = []
for (let i = 0; i < 5; i++) {
  newsOptions.push(Mock.mock({
    newsId: i+1,
    newsName: '资讯分类'+(i+1),
  }))
}
// 用户标签专用userLabelList
const userLabelList = []
for (let i = 0; i < count; i++) {
  userLabelList.push(Mock.mock({
    labelId: '@increment',
    labelName: '@first',
    newsId: [1, 2,5],
    newsName:'资讯分类1、资讯分类2、资讯分类5',
    labelDescription: i+'Lorem ipsum dolor sit amet, cing elit. . Proin gravida dolor sit,dasjk asdjsafkjs dsfdsfkjdsa fjsakdhfdsf askjfdhsdkfs',
 		'status|1': ['published', 'deleted']
  }))
}

/**
 * 用户分组
 */
const groupsOptions = []
for (let i = 0; i < 10; i++) {
  groupsOptions.push(Mock.mock({
    groupsId: i+1,
    groupsName: '分组'+(i+1),
  }))
}
// 用户分组页专用usersList
const usersOptions = []
for (let i = 0; i < 10; i++) {
  usersOptions.push(Mock.mock({
    userId: i+1,
    userName: '用户'+(i+1),
  }))
}
// 用户分组专用userGroupsList
const userGroupsList = []
for (let i = 0; i < count; i++) {
  userGroupsList.push(Mock.mock({
    groupsId: '@increment',
    groupsName: '@first',
    usersId: [1, 2, 3],
 		'status|1': ['published', 'deleted']
  }))
}

/**
 * 用户详情
 */
const userDetail = Mock.mock({
    emplId: '@increment',
    userName: '@first',
    emplName: '@first',
    emplPhone: '@first',
    'gradeId|1': [1, 2, 3, 4,5],
    gradeName:'用户等级1',
    inviteUserId: [1, ,5],
    inviteUserName:'邀请成员1,,邀请成员2',
    integral: '@integer(1, 1000)',
    reserveNum: '@integer(1, 10)',
    reserveMoney: '@integer(300, 100000)',
    'status|1': ['published', 'deleted'],
    'emplSex|1': ['0', '1'],
    collectNum: '@integer(1, 10)',
    invite: '@first',
    recentlyLoggedTime:new Date(),
    registeredTime: new Date(),
    diseaseName:'肺癌',
    sickTime:'3年',
    hospitalName:'xxxx三甲医院',
    condition:'病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重病情严重'
  })
// 用户分组专用DetectionList
const detectionList = []
for (let i = 0; i < count; i++) {
  detectionList.push(Mock.mock({
    detectionId: '@increment',
    detectionName: '@first',
    reservationTime:new Date(),
 	'status|1': ['已检测', '未检测', '已过期']
  }))
}

/**
 * 用户消息
 */
// 用户消息专用UserMessageList
const userMessageList = []
for (let i = 0; i < count; i++) {
  userMessageList.push(Mock.mock({
    messageId: '@increment',
    messageTitle: '消息标题'+i,
    releaseTime:new Date(),
 	userName:'@first',
 	'status|1': ['published', 'deleted']
  }))
}


export default {
	
	//用户列表Data
  fetchUserList: config => {
  	console.log('--mock fetchUserList---')
    return {
			code:20000,
			data:{
	    	items: userList,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
  },
  //用户列表页用户等级Data
  fetchGradeOptions: config => {
  	console.log('--mock fetchGradeOptions ---')
    return {
			code:20000,
			data:{
	    	items: gradeOptions
			},
			message:''
    }
  },
  //用户列表页标签Data
  fetchLabelOptions: config => {
  	console.log('--mock fetchLabelOptions ---')
    return {
			code:20000,
			data:{
	    	items: labelOptions
			},
			message:''
    }
  },
  //更新用户信息
  executeUpdateUser: config => {
  	console.log('---mock executeUpdateUser --')
		return {
			code:20000,
			data:{},
			message:'更新成功'
		}
  },
  //设置用户邀请信息
  executeUpdateUserinvite: config => {
  	console.log('---mock executeUpdateUserinvite --')
		return {
			code:20000,
			data:{},
			message:'设置成功'
		}
  },
  
  //用户收藏Data
  fetchUserCollectionList: config => {
  	console.log('--mock fetchUserCollectionList---')
    return {
			code:20000,
			data:{
	    	items: userCollectionList,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
  },
  //用户收藏页分类Data
  fetchCollTypeOptions: config => {
  	console.log('--mock fetchCollTypeOptions ---')
    return {
			code:20000,
			data:{
	    	items: collTypeOptions
			},
			message:''
    }
  },
  //用户收藏页标签Data
  fetchCollLabelOptions: config => {
  	console.log('--mock fetchCollLabelOptions ---')
    return {
			code:20000,
			data:{
	    	items: collLabelOptions
			},
			message:''
    }
  },
  
  //用户标签Data
  fetchUserLabelList: config => {
  	console.log('--mock fetchUserLabelList---')
    return {
			code:20000,
			data:{
	    	items: userLabelList,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
  },
  //用户列表页资讯分类Data
  fetchNewsOptions: config => {
  	console.log('--mock fetchNewsOptions ---')
    return {
			code:20000,
			data:{
	    	items: newsOptions
			},
			message:''
    }
  },
  //更新用户标签信息
  executeUpdateUserLabel: config => {
  	console.log('---mock executeUpdateUserLabel --')
		return {
			code:20000,
			data:{},
			message:'更新成功'
		}
  },
  //创建用户标签信息
  executeCreateUserLabel: config => {
  	console.log('---mock executeCreateUserLabel --')
		return {
			code:20000,
			data:{},
			message:'新增成功'
		}
  },
  
  //用户分组Data
  fetchUserGroupsList: config => {
  	console.log('--mock fetchUserGroupsList---')
    return {
			code:20000,
			data:{
	    	items: userGroupsList,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
  },
  fetchGroupsOptions: config => {
  	console.log('--mock fetchGroupsOptions ---')
    return {
			code:20000,
			data:{
	    	items: groupsOptions
			},
			message:''
    }
  },
  //用户列表页资讯分类Data
  fetchUserOptions: config => {
  	console.log('--mock fetchUserOptions ---')
    return {
			code:20000,
			data:{
	    	items: usersOptions
			},
			message:''
    }
  },
  //更新用户分组信息
  executeUpdateUserGroups: config => {
  	console.log('---mock executeUpdateUserGroups --')
		return {
			code:20000,
			data:{},
			message:'更新成功'
		}
  },
  //给分组配置用户
  executeUpdateConfigurationUser: config => {
  	console.log('---mock executeUpdateConfigurationUser --')
		return {
			code:20000,
			data:{},
			message:'更新成功'
		}
  },
  //创建用户分组信息
  executeCreateUserGroups: config => {
  	console.log('---mock executeCreateUserGroups --')
		return {
			code:20000,
			data:{},
			message:'新增成功'
		}
  },
  
  //用户详情页用户Data
  fetchUserDetail: config => {
  	console.log('--mock fetchUserDetail ---')
    return {
			code:20000,
			data:{
	    	items: userDetail
			},
			message:''
    }
  },
  //用户详情页检测信息Data
  fetchDetectionList: config => {
  	console.log('--mock fetchDetectionList ---')
    return {
			code:20000,
			data:{
	    	items: detectionList
			},
			message:''
    }
  },
  
  //用户信息Data
  fetchUserMessageList: config => {
  	console.log('--mock fetchUserMessageList---')
    return {
			code:20000,
			data:{
	    	items: userMessageList,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
  },
  //创建用户信息
  executeCreateUserMessage: config => {
  	console.log('---mock executeCreateUserMessage --')
		return {
			code:20000,
			data:{},
			message:'新增成功'
		}
  },

}
