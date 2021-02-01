import Mock from 'mockjs'
import { param2Obj } from '@/utils'

const List = []
const count = 11

// 预约List
for (let i = 0; i < count; i++) {
	if(i==0){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测早期防癌动态检测早期防癌动态检测早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 1,
	    bookingStatus: 0,
	    deployStatus: 0,
	    financeStatus: 0,
	    expertUnscrambleStatus: 0,
	    deployUnscrambleStatus:0
	  }))
	} else if(i==1){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: -1,
	    deployStatus: 0,
	    financeStatus: 0,
	    expertUnscrambleStatus: 0,
	    deployUnscrambleStatus:0
	  }))
	} else if(i==2){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: 1,
	    deployStatus: 0,
	    financeStatus: 0,
	    expertUnscrambleStatus: 0,
	    deployUnscrambleStatus:0
	  }))
	} else if(i==3){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: 2,
	    deployStatus: 0,
	    financeStatus: 1,
	    expertUnscrambleStatus: 0,
	    deployUnscrambleStatus:0
	  }))
	} else if(i==4){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: 3,
	    deployStatus: 0,
	    financeStatus: 1,
	    expertUnscrambleStatus: 0,
	    deployUnscrambleStatus:0
	  }))
	} else if(i==5){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: 4,
	    deployStatus: 0,
	    financeStatus: 1,
	    expertUnscrambleStatus: 1,
	    deployUnscrambleStatus:0
	  }))
	} else if(i==6){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: 5,
	    deployStatus: 1,
	    financeStatus: 1,
	    expertUnscrambleStatus: 2,
	    deployUnscrambleStatus:1
	  }))
	} else if(i==7){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: 7,
	    deployStatus: 2,
	    financeStatus: 1,
	    expertUnscrambleStatus: 2,
	    deployUnscrambleStatus:2
	  }))
	} else if(i==8){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: 7,
	    deployStatus: 2,
	    financeStatus: 1,
	    expertUnscrambleStatus: 3,
	    deployUnscrambleStatus:3
	  }))
	} else if(i==9){
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: 8,
	    deployStatus: 2,
	    financeStatus: 1,
	    expertUnscrambleStatus: 3,
	    deployUnscrambleStatus:3
	  }))
	} else {
	  List.push(Mock.mock({
	  	bookingId:i+1,
	    bookingNumber: 'J1000000012019022'+i,
	    checkItemNames: '早期防癌动态检测'+i,
	    checkTime: '2018-10-10 10:30:21',
	    checkPerson: '检查人'+(i+1),
	    checkPersonPhone: '1391234567'+(i+1),
	    reportTime: '2018.10.15 17:30',
	    expertName: '专家'+(i+1),
	    unscrambleTime: '2018-10-10 10:30:21',
	    bookingPrice: 11111,
	    bookingStatus: 9,
	    deployStatus: 2,
	    financeStatus: 1,
	    expertUnscrambleStatus: 2,
	    deployUnscrambleStatus:2
	  }))
	}

}

// 订单信息
const bookingDetail = Mock.mock({
	  	bookingId:11,
	    bookingNumber: 'J10000000120190221',
	    checkItemNames: '早期防癌动态检测 早期防癌动态检测 早期防癌动态检测 早期防癌动态检测',
	    checkItemIds: [1,2],
	    checkTime: '2018-10-10 10:30',
	    checkAddr:'圣悯医疗（上海市闵行区申滨路188号虹桥丽宝广场1层101号）',
	    checkPerson: '检查人'+(1),
	    checkPersonPhone: '1391234567'+(1),
	    createAt: '2019-10-10 10:20:30',
	    personSex: 0,
	    idNumber:'310987899878987678',
	    personBirthday:'1988-10-20',
	    bookingStatus: 0,
	    bookingPrice:12345.12,
	    unscrambleDateTime:'2019-10-11 09:30~10:00',
	    unscrambleDate:'2019-10-11 00:00',
	    unscrambleStartTime:'09:30',
	    unscrambleEndTime:'10:00',
	    expertId:2,
	    expertName:'测试数据专家',
	    
	    isCompletedReport:true,
	    reportLevel: 2,
	    reportContent: '诊断结果......',
	    pdfFile: '1544671709248_20190225150727.pdf',
	    pdfFileUrl:'/targetRegionApi/file/pdf/1544671709248_20190225150727.pdf',
	    
	    unscrambleContent:'专家解读内容专家解读内容专家解读内容专家解解读内容专家解读内容专家解读内容专家解读内容...',
	    needTreatment:'0',
	    treatmentContent:'专家治疗方案内容专家治疗方案内容专家治疗方案内容专家治疗方案内容...',
	    
	    reportTime:'2019-10-21 10:31:32'
	  })


// 检测项目List
const checkItemList = []
for (let i = 0; i < 5; i++) {
  checkItemList.push(Mock.mock({
    checkItemId: i+1,
    checkItemName: '检测项目'+(i+1),
  }))
}
// 诊断结果等级列表
const reportLevelList = []
for (let i = 0; i < 5; i++) {
  reportLevelList.push(Mock.mock({
    reportLevelId: i+1,
    reportLevelName: '诊断结果等级'+(i+1),
    reportLevelDesc: '诊断结果内容诊断结果内容诊断结果内容诊断结果内容诊断结果内容诊断结果内容'+(i+1) 
  }))
}
// 专家List
const expertList = []
for (let i = 0; i < 5; i++) {
  expertList.push(Mock.mock({
    expertId: i+1,
    expertName: '专家'+(i+1),
  }))
}

// 解读历史
const unscrambleList = []
for (let i = 0; i < 5; i++) {
  unscrambleList.push(Mock.mock({
    unscrambleName: '专家'+(i+1),
    unscrambleTime: '2010-10-10 10:20:30',
    unscrambleContext: '专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容专家解读内容'+(i+1)+"。。。",
    needTreatment:1,
    treatmentContent: '治疗方案'+(i+1)+"。。。"
  }))
}






// ****************************************************** Mock 方法开始 ******************************************************
export default {
  	// 专家列表
  	fetchExpertList: config => {
  		console.log('--mock fetchExpertList---')
    	return {
			code:20000,
			success:true,
			data:{
	    		items: expertList,
			},
			message:''
    	}
  	},

	// 预约订单列表
	fetchBookingList: config => {
	  	console.log('--mock fetchBookingList---')
	    return {
			code:20000,
			success:true,
			data:{
		    	items: List,
		    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
	    }
	},

	// 检测项目
	fetchCheckItemList: config => {
	  	console.log('--mock fetchCheckItemList---')
	    return {
			code:20000,
			success:true,
			data:{
		    	items: checkItemList
			},
			message:''
	    }
	},

	// 诊断结果等级列表
	fetchReportLevelList: config => {
	  	console.log('--mock fetchReportLevelList---')
	    return {
			code:20000,
			success:true,
			data:{
		    	items: reportLevelList
			}
	    }
	},

	// 订单详情
	fetchBookingDetail: config => {
	  	console.log('--mock fetchBookingDetail---')
	    return {
			code:20000,
			success:true,
			data:{
	    		items: {'bookingDetail':bookingDetail},
			},
			message:''
	    }
	},

	// 检测报告详情
	fetchReportDetail: config => {
  		console.log('--mock fetchReportDetail---')
	    return {
			code:20000,
			success:true,
			data:{
	    		items: {'bookingDetail':bookingDetail, 'unscrambleList':unscrambleList},
			},
			message:''
	    }
  	},


  
  	// 发布报告
  	executeReleaseReport: config => {
  		console.log('--mock executeReleaseReport---')
    	return {
			code:20000,
			success:true,
			data:{},
			message:''
    	}
  	},

  	// 取消预约
  	executeCancelBooking: config => {
  		console.log('--mock executeCancelBooking---')
    	return {
			code:20000,
			success:true,
			data:{},
			message:''
    	}
  	},

	// 约见
  	executeConfirmYuejian: config => {
  		console.log('--mock executeConfirmYuejian---')
    	return {
			code:20000,
			success:true,
			data:{},
			message:''
    	}
  	},

  	// 采血
  	executeConfirmBlood: config => {
  		console.log('--mock executeConfirmBlood---')
    	return {
			code:20000,
			success:true,
			data:{},
			message:''
    	}
  	},

	// 更新预约检测订单
  	executeUpdateBookingDetail: config => {
  		console.log('---mock executeUpdateBookingDetail --')
		return {
			code:20000,
			success:true,
			data:{},
			message:'更新成功'
		}
  	},

  	// 提交报告
  	executeSubmitReport: config => {
  		console.log('---mock executeSubmitReport --')
		return {
			code:20000,
			success:true,
			data:{},
			message:'更新成功'
		}
  	},

  	// 专家解读报告
  	executeSubmitUnscramble: config => {
  		console.log('---mock executeSubmitUnscramble --')
		return {
			code:20000,
			success:true,
			data:{},
			message:'更新成功'
		}
  	},

  	// 更新专家解读报告
  	executeUpdateSubmitUnscramble: config => {
  		console.log('---mock executeUpdateSubmitUnscramble --')
		return {
			code:20000,
			success:true,
			data:{},
			message:'更新成功'
		}
  	}
 }